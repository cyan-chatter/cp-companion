package com.saarthak.cpcompanion.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saarthak.cpcompanion.R
import com.saarthak.cpcompanion.ui.MainActivity
import com.saarthak.cpcompanion.ui.adapter.ContestListAdapter
import com.saarthak.cpcompanion.ui.viewModels.ContestListViewModel
import com.saarthak.cpcompanion.util.Constants.Companion.PG_SZ
import com.saarthak.cpcompanion.util.Resource

class ContestListFragment: Fragment(R.layout.fragment_contest_list) {
    lateinit var viewModel: ContestListViewModel
    lateinit var contestListAdapter: ContestListAdapter
    lateinit var progBar: ProgressBar
    lateinit var rvContestList: RecyclerView

    var isLoading = false
    var isLastPg = false
    var isScrolling = false

    val scrollListener = object: RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosn = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCnt = layoutManager.childCount
            val totalItemCnt = layoutManager.itemCount

            val isNotLoadingNdNotLastPg = !isLoading and !isLastPg
            val isAtLastItem = firstVisibleItemPosn + visibleItemCnt >= totalItemCnt
            val isNotAtBeginning = firstVisibleItemPosn > 0
            val isTotalMoreThanVisible = totalItemCnt >= PG_SZ

            val shouldPaginate = isNotLoadingNdNotLastPg and isNotAtBeginning and isTotalMoreThanVisible and isAtLastItem and isScrolling
            if(shouldPaginate){
                viewModel.getContestList()
                isScrolling = false
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =(activity as MainActivity).viewModel
        progBar = view.findViewById(R.id.progBar)
        rvContestList = view.findViewById(R.id.rvContestList)

        setUpRv()

        viewModel.contestDetails.observe(viewLifecycleOwner, { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { contestResponse ->
                        contestListAdapter.differ.submitList(contestResponse.contests)

                        val totalPgs = contestResponse.totalCount / PG_SZ + 2
                        isLastPg = viewModel.curPg == totalPgs

                        if (isLastPg) rvContestList.setPadding(0, 0, 0, 0)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()

                    response.msg?.let {
//                        Log.d(TAG, "error: $it")
                        Toast.makeText(activity, "Error : $it", Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar(){
        progBar.visibility = View.GONE
        isLoading = false
    }

    private fun showProgressBar(){
        progBar.visibility = View.VISIBLE
        isLoading = true
    }

    private fun setUpRv(){
        contestListAdapter = ContestListAdapter()

        rvContestList.apply {
            adapter = contestListAdapter
            layoutManager = LinearLayoutManager(activity)

            addOnScrollListener(this@ContestListFragment.scrollListener)
        }
    }
}