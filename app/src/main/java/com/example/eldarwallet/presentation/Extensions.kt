package com.example.eldarwallet.presentation

import android.graphics.Bitmap
import android.os.Looper
import android.widget.ImageView
import com.example.eldarwallet.infrastructure.service.RetrofitImage

fun ImageView.setBitmapFrom(url: String) {
    val imageView = this
    RetrofitImage.getBitmapFrom(url) {
        val bitmap: Bitmap? = if (it != null) it else {
            val w = 1
            val h = 1
            val conf = Bitmap.Config.ARGB_8888
            Bitmap.createBitmap(w, h, conf)
        }

        Looper.getMainLooper().run {
            imageView.setImageBitmap(bitmap!!)
        }
    }
}