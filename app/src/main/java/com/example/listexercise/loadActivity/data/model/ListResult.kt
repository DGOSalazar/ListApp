package com.example.listexercise.loadActivity.data.model
import com.google.gson.annotations.SerializedName

data class ListResult(@SerializedName("_id") var id : String,
                      @SerializedName("fact") var fact : String,
                      @SerializedName("organization") var organization : String,
                      @SerializedName("url") var url : String)