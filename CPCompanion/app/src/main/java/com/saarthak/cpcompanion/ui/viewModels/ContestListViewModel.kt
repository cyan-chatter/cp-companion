package com.saarthak.cpcompanion.ui.viewModels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.saarthak.cpcompanion.model.ContestResponse
import com.saarthak.cpcompanion.repository.ContestRepo
import com.saarthak.cpcompanion.util.CPApplication
import com.saarthak.cpcompanion.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class ContestListViewModel(val app: Application, val contestRepo: ContestRepo): AndroidViewModel(app) {

    val contestDetails: MutableLiveData<Resource<ContestResponse>> = MutableLiveData()
    var contestResponse: ContestResponse? = null
    var curPg = 1

    init {
        getContestList()
    }

    private fun hasInternetConnection(): Boolean{
        val connectivityManager = getApplication<CPApplication>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

            return when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }

        return false
    }

    fun getContestList() = viewModelScope.launch {
        contestDetails.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()){
                val response = contestRepo.getContestDetails(curPg)
                processContestList(response)
            }
            else contestDetails.postValue(Resource.Error("No Internet Connection !"))
        } catch (t: Throwable){
            contestDetails.postValue(Resource.Error("Something went wrong ! Please try again later."))
        }
    }

    private fun processContestList(response: Response<ContestResponse>): Resource<ContestResponse>{
        if(response.isSuccessful){
            response.body()?.let {
                ++curPg
                if(contestResponse == null) contestResponse = it
                else{
                    val prevResponse = contestResponse?.contests
                    val newResponse = it.contests
                    prevResponse?.addAll(newResponse)
                }

                return Resource.Success(contestResponse ?: it)
            }
        }

        return Resource.Error(response.message())
    }
}