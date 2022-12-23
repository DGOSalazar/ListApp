package com.example.listexercise.loadActivity.data.network.model
import com.google.gson.annotations.SerializedName

data class ListResult(@SerializedName("_id") var id : String="",
                      @SerializedName("date_insert") var date_insert : String ="",
                      @SerializedName("slug") var slug : String="",
                      @SerializedName("columns") var columns : String="",
                      @SerializedName("fact") var fact : String="",
                      @SerializedName("organization") var organization : String="",
                      @SerializedName("resource") var resource : String="",
                      @SerializedName("url") var url : String="",
                      @SerializedName("operations") var operations : String="",
                      @SerializedName("dataset") var dataset : String="",
                      @SerializedName("created_at") var created_at : String="",
                      @SerializedName("statusError") var statusError:String="")