package com.example.listexercise.loadActivity.data.model

import javax.inject.Inject

class CacheGob @Inject constructor() {
    companion object{
        var gobCache : GobRest = GobRest(
            info = PageResponse(page = 0, pageSize = 0, total = 0),
            arrayListOf<ListResult>(ListResult(id = "", fact = "", organization = "", url = ""))
        )
    }
}