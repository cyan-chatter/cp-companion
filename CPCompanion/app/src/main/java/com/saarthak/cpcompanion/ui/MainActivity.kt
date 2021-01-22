package com.saarthak.cpcompanion.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.saarthak.cpcompanion.R
import com.saarthak.cpcompanion.repository.ContestRepo
import com.saarthak.cpcompanion.ui.viewModels.ContestListViewModel
import com.saarthak.cpcompanion.ui.viewModels.ContestListViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ContestListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = ContestRepo()
        val viewModelProvider = ContestListViewModelProvider(application, repo)

        viewModel =ViewModelProvider(this, viewModelProvider).get(ContestListViewModel::class.java)
    }
}