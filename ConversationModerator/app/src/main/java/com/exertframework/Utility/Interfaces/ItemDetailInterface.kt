package com.qf.giftapp.Interfaces

import android.view.View

interface ItemDetailInterface {
    fun showToast(msg: String)
    fun onItemClick(view: View, position: Int)
}