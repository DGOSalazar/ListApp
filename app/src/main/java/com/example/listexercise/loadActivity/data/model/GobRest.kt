package com.example.listexercise.loadActivity.data.model

import com.google.gson.annotations.SerializedName

data class GobRest (
@SerializedName("pagination") var info : PageResponse = PageResponse(),
@SerializedName("results") var gobEntity : ArrayList<ListResult> = ArrayList<ListResult>()
)
data class PageResponse(
@SerializedName("pageSize") val pageSize:Int=0,
@SerializedName("page") val page:Int=0,
@SerializedName("total") val total:Int=0,
)


