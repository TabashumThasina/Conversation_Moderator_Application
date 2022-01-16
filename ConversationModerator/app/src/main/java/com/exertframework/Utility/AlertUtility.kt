package com.exertframework.Utility

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.conversationmoderator.R


class AlertUtility {

    companion object {

        fun createAlert(context: Context, title: String, message: String, listner: AlertDialogInterface) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("App background color")
            builder.setMessage("Are you want to set the app background color to RED?")
            builder.setPositiveButton("YES") { dialog, which ->
                Toast.makeText(context, "Ok, we change the app background.", Toast.LENGTH_SHORT).show()
                listner.yes(dialog, which)
                dialog.dismiss()
            }
            builder.setPositiveButton("OK") { dialog, which ->
                listner.ok(dialog, which)
                dialog.dismiss()
            }
            builder.setNegativeButton("No") { dialog, which ->
                Toast.makeText(context, "You are not agree.", Toast.LENGTH_SHORT).show()
                listner.no(dialog, which)
                dialog.dismiss()
            }
            builder.setNeutralButton("Cancel") { dialog, which ->
                Toast.makeText(context, "You cancelled the dialog.", Toast.LENGTH_SHORT).show()
                listner.cancel(dialog, which)
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        fun createAlertWithOk(context: Context, title: String, message: String, listner: AlertDialogInterface){
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)

            builder.setPositiveButton("OK") { dialog, which ->
                listner.ok(dialog, which)
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        fun createAlertWithOkandCancel(context: Context, title: String, message: String, listner: AlertDialogInterface){
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton("OK") { dialog, which ->
                listner.ok(dialog, which)
                dialog.dismiss()
            }
            builder.setNeutralButton("Cancel") { dialog, which ->
                listner.cancel(dialog, which)
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        fun createAlertWithYesAndNo(context: Context, title: String, message: String, listner: AlertDialogInterface){
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton("YES") { dialog, which ->
                listner.yes(dialog, which)
                dialog.dismiss()
            }
            builder.setNegativeButton("No") { dialog, which ->
                listner.no(dialog, which)
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        fun createAlertWithRetryandCancel(context: Context, title: String, message: String, listner: AlertDialogInterface){
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setCancelable(false)
            builder.setMessage(message)
            builder.setPositiveButton("Retry") { dialog, which ->
                listner.ok(dialog, which)
                ///dialog.dismiss()
            }
            builder.setNeutralButton("Cancel") { dialog, which ->
                listner.cancel(dialog, which)
                //dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()

            dialog.show()
        }
        fun networkAlert(context: Context,title: String,message: String, listner: AlertDialogInterface){
            var li: LayoutInflater = LayoutInflater.from(context);
            var promptsView: View = li.inflate(R.layout.prompts, null);
            var alertDialogBuilder = AlertDialog.Builder(context);

            alertDialogBuilder.setView(promptsView);
            var alertDialog = alertDialogBuilder.create();
            promptsView.findViewById<Button>(R.id.btnCancel).setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    listner.cancel(alertDialog)
                }
            })
            promptsView.findViewById<Button>(R.id.btnRetry).setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    listner.retry(alertDialog)
                }
            })
            alertDialog.show();
            alertDialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//            alertDialog.window?.setBackgroundDrawable(context.resources.getDrawable(R.drawable.bgsignin))
        }
    }
}