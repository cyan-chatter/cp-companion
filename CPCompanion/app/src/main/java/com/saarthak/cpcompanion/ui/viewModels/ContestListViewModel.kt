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

class ContestListViewModel(val app: Application, val contestRepo: ContestRepo): AndroidViewModel(app) {

    val contestDetails: MutableLiveData<Resource<ContestResponse>> = MutableLiveData()
    var contestResponse: ContestResponse? = null
    var limit = 30

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

    }
}