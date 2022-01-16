package com.exertframework.Views

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import com.conversationmoderator.R

class SpinnerProgressDialog(context: Context) : Dialog(context) {

    internal lateinit var spinner: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.widget_spinnerprogress_dialog)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        spinner = findViewById(R.id.spinner_pbar) as ProgressBar

        spinner.visibility = View.VISIBLE
        setCancelable(false)
        setOnCancelListener(null)
        //        spinner.getIndeterminateDrawable().setColorFilter(R.color.Vista_STRONG_GREY, android.graphics.PorterDuff.Mode.MULTIPLY);
    }

//    public override fun onCreate(savedInstanceState: Bundle) {
//        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        setContentView(R.layout.widget_spinnerprogress_dialog)
//        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        spinner = findViewById(R.id.spinner_pbar) as ProgressBar
//
//        spinner.visibility = View.VISIBLE
//        setCancelable(false)
//        setOnCancelListener(null)
//        //        spinner.getIndeterminateDrawable().setColorFilter(R.color.Vista_STRONG_GREY, android.graphics.PorterDuff.Mode.MULTIPLY);
//
//    }

    companion object {

        val TAG = SpinnerProgressDialog::class.java.simpleName
    }

}
