package com.example.listexercise.loadActivity.ui.view

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.listexercise.R
import com.example.listexercise.loadActivity.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
const val INTERNET_REQUEST_CODE = 1234
@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var alertBuilder : AlertDialog.Builder
    private val mainViewModel:MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkInternetPermission()
    }
    private fun toastResponse(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
    private fun checkInternetPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            requestInternetPermission()
        }
    }
    private fun requestInternetPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            toastResponse(getString(R.string.toastPermission_adjust))
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                INTERNET_REQUEST_CODE)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==INTERNET_REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                toastResponse(getString(R.string.toastPermission_accepted))
            }else{
                toastResponse(getString(R.string.toastPermission_denied))
            }
        }
    }
}
