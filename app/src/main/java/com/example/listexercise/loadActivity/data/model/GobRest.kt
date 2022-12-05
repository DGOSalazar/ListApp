package com.example.listexercise.loadActivity.data.model

import com.google.gson.annotations.SerializedName

data class GobRest (
@SerializedName("pagination") var info : PageResponse,
@SerializedName("results") var gobEntity : ArrayList<ListResult>
)
data class PageResponse(
@SerializedName("pageSize") val pageSize:Int,
@SerializedName("page") val page:Int,
@SerializedName("total") val total:Int,
)


