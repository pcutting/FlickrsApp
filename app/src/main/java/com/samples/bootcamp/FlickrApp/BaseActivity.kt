package com.samples.bootcamp.FlickrApp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity

import androidx.appcompat.widget.Toolbar
import android.util.Log
import android.view.View
import com.samples.bootcamp.udemyflikrapp.R

internal const val FLICKR_QUERY = "FLICKER_QUERY"
internal const val PHOTO_TRANSER = "PHOTO_TRANSFER"

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    private val TAG  = "BaseActivity"

    internal fun activateToolbar(enableHome: Boolean) {
        Log.d(TAG, ".activateToolBar")

        var toolbar = findViewById<View>(R.id.toolbar) as Toolbar   // androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
    }

}