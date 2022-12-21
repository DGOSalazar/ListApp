package com.example.listexercise.loadActivity.ui.view

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listexercise.R
import com.example.listexercise.databinding.ActivityMainBinding
import com.example.listexercise.loadActivity.domain.model.GobModel
import com.example.listexercise.loadActivity.ui.view.adapter.FragmentAdapter
import com.example.listexercise.loadActivity.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter : FragmentAdapter
    private val mainViewMain : MainViewModel by viewModels()
    private lateinit var alertBuilder : AlertDialog.Builder
    private val INTERNET_REQUEST_CODE = 1234

    var list: List<GobModel> = listOf()
    var listPagination: List<GobModel> = listOf()
    private var count =0

    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        alertBuilder = AlertDialog.Builder(this)
        checkInternetPermission()

        if (networkInfo != null && networkInfo.isConnected){
            mainViewMain.onLoading()
            mainViewMain.isLoading.observe(this, Observer {
                mBinding.indeterminateLinearIndicator.isVisible = it
            })
            mainViewMain.onCreate()
            mainViewMain.onPagination(count)

            //Clicks
            mBinding.recyclerView.setOnClickListener{
                launchList()
            }
            mainViewMain.gobModelPagination.observe(this, Observer {
                listPagination=it
                mAdapter.setList(it)
            })
            mainViewMain.gobModel.observe(this, Observer {
                list=it
            })
            mBinding.search.addTextChangedListener { slug ->
                val listFilter = list.filter { it.slug.contains(slug.toString().lowercase())}
                mAdapter.setList(listFilter)
            }
            mBinding.btNext.setOnClickListener{
                if(count<90) {
                    count += 10
                    mainViewMain.onPagination(count)
                }
            }
            mBinding.btBack.setOnClickListener{
                if (count>0) {
                    count -= 10
                    mainViewMain.onPagination(count)
                }
            }
            launchAdapter()
        } else alertOne()
    }
    private fun launchAdapter(){
        mAdapter= FragmentAdapter(listPagination) {click(it)}
        mBinding.recyclerView.layoutManager = LinearLayoutManager(mBinding.root.context)
        mBinding.recyclerView.adapter= mAdapter
    }
    private fun launchList(){
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack("main")
            add<ListFragment>(R.id.main_id)
        }
    }
    private fun toastResponse(text: String){
        Toast.makeText(mBinding.root.context, text, Toast.LENGTH_SHORT).show()
    }
    private fun alertOne(){
        alertBuilder.setTitle("You dont have internet conection")
            .setMessage("When you turn on you wifi select refresh, or exit for end the aplication")
            .setPositiveButton("Refresh", DialogInterface.OnClickListener { _, _ ->
                val intent = intent
                finish()
                startActivity(intent)
            })
            .setNegativeButton("Exit", DialogInterface.OnClickListener { _, _ ->
                finish()
            }).show()
    }
    private fun click(result:GobModel){
        mainViewMain.onItemSelect(mAdapter.listResult)
        launchList()
    }

    private fun checkInternetPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
            != PackageManager.PERMISSION_GRANTED) {
            requestInternetPermission()
        }
    }
    private fun requestInternetPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.INTERNET)) {
            toastResponse("Debes permitir el permiso de internet desde los ajuster")
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.INTERNET),
                INTERNET_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==INTERNET_REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                toastResponse("Permisos concedidos")
            }else{
                toastResponse("Rechazaste los permisos")
            }
        }
    }
}
