package com.samples.bootcamp.udemyflikrapp

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(
    context: Context,
    recyclerView: RecyclerView,
    private val listener: OnRecyclerClickListener
) : RecyclerView.SimpleOnItemTouchListener() {
    private val TAG = "RecyclerItemsClickListen"

    interface OnRecyclerClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    private val gestureDetector =
        GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                Log.d(TAG, ".onSingleTapUp : $e")
                val childView = recyclerView.findChildViewUnder(e.x, e.y)
                Log.d(TAG, ".onSingleTapUp calling listener.onItemClick")
                if (childView != null) {
                    listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
                }

                return true

//            return super.onSingleTapUp(e)
            }

            override fun onLongPress(e: MotionEvent) {
//            super.onLongPress(e)

                Log.d(TAG, ".onLongPress : $e")
                val childView = recyclerView.findChildViewUnder(e.x, e.y)
                Log.d(TAG, ".onLongPress calling listener.onItemClick ${e.x}, ${e.y}")
                if (childView != null) {
                    listener.onItemLongClick(
                        childView,
                        recyclerView.getChildAdapterPosition(childView)
                    )
                }

            }
        })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG, ".onInterceptTouchEvent: starts a $e")
        val result = gestureDetector.onTouchEvent(e)
        Log.d(TAG, ".onInterceptTouchEvent() returning: $result")
        //return super.onInterceptTouchEvent(rv, e)
        return result

    }


}