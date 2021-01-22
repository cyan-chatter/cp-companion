package com.saarthak.cpcompanion.ui.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saarthak.cpcompanion.repository.ContestRepo

class ContestListViewModelProvider(val app: Application, val constestRepo: ContestRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContestListViewModel(app, constestRepo) as T
    }

}