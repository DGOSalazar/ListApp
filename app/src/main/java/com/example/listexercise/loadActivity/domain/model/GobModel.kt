package com.example.listexercise.loadActivity.domain.model

import com.example.listexercise.loadActivity.data.database.entities.ListResultQuote
import com.example.listexercise.loadActivity.data.model.ListResult

data class GobModel(var id : String="",
                    var date_insert : String ="",
                    var slug : String="",
                    var columns : String="",
                    var fact : String="",
                    var organization : String="",
                    var resource : String="",
                    var url : String="",
                    var operations : String="",
                    var dataset : String="",
                    var created_at : String="")
fun ListResult.toDomain() =
    GobModel(id,date_insert,slug,columns,fact,organization,date_insert,dataset,url,operations,created_at)
fun ListResultQuote.toDomain() =
    GobModel(id,date_insert,slug,columns,fact,organization,date_insert,dataset,url,operations,created_at)
