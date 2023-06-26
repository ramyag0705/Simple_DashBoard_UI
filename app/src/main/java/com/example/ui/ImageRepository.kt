package com.example.ui

import com.example.ui.Constant.analytics
import com.example.ui.Constant.contact
import com.example.ui.Constant.inv
import com.example.ui.Constant.myAcc
import com.example.ui.Constant.req
import com.example.ui.Constant.searchM

class ImageRepository {
    fun getImageList(): List<ImageItem> {
        return listOf(
            ImageItem(R.drawable.profile, myAcc),
            ImageItem(R.drawable.inventory, inv),
            ImageItem(R.drawable.search, searchM),
            ImageItem(R.drawable.request, req),
            ImageItem(R.drawable.analytics, analytics),
            ImageItem(R.drawable.contact, contact)
        )
    }
}


