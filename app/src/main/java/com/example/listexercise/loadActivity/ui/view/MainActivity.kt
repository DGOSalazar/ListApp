package com.example.listexercise.loadActivity.ui.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listexercise.databinding.ActivityMainBinding
import com.example.listexercise.loadActivity.data.model.ListResult
import com.example.listexercise.loadActivity.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter : FragmentAdapter
    private lateinit var list : List<ListResult>
    private val mainViewMain : MainViewModel by viewModels()
    var counter = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        //Variables
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        //LaunchMethods
        if (networkInfo != null && networkInfo.isConnected) {
            launchAdapter()
            mBinding.counter.text=counter.toString()
            mainViewMain.onCreate()
        } else {
            finishAffinity()
            hideUi()
            toastResponse("Al parecer no tienes internet mi chavo")
        }
        //LiveData
        mainViewMain.gobModel.observe(this, Observer {
            mAdapter.setStores(it)
            list=it
        })
        mainViewMain.isLoading.observe(this, Observer {
            mBinding.indeterminateLinearIndicator.isVisible = it
        })
        //Clicks
        mBinding.back.setOnClickListener{
            back()
        }
        mBinding.next.setOnClickListener{
            next()
        }
    }

    private fun hideUi() {
        mBinding.counter.isGone
        mBinding.next.isGone
        mBinding.back.isGone
    }

    private fun next(){
        if (counter<10) {
            mAdapter.pagination((counter * 10))
            counter++
            mBinding.counter.text = counter.toString()
        }
    }
    private fun back() {
        if (counter==1){
            mBinding.counter.text=counter.toString()
            mAdapter.pagination(0)
        }else{
            counter--
            mBinding.counter.text=counter.toString()
            mAdapter.pagination((counter*10))
        }
    }
    private fun launchAdapter(){
        list= listOf()
        mAdapter= FragmentAdapter(list, page = 0)
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.adapter= mAdapter
        toastResponse("Welcome")
    }
    private fun toastResponse(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}