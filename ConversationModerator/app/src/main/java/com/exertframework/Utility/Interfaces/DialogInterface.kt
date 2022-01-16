package com.exertframework.Utility

import android.content.DialogInterface

interface AlertDialogInterface {
    fun ok(dialog: DialogInterface, data:Any){}
    fun cancel(dialog: DialogInterface, data: Any){}
    fun yes(dialog: DialogInterface, data: Any){}
    fun no(dialog: DialogInterface, data: Any){}
    fun cancel(dialog: androidx.appcompat.app.AlertDialog){}
    fun retry(dialog: androidx.appcompat.app.AlertDialog){}
}