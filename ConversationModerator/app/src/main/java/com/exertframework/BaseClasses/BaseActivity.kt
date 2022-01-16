package com.exertframework.BaseClasses

import android.content.Context
import android.graphics.PorterDuff

import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.conversationmoderator.R
import io.github.inflationx.viewpump.ViewPumpContextWrapper

/**
 * Created by solanki on 11/10/17.
 */

open class BaseActivity : AppCompatActivity() {

    var toolbar: Toolbar? = null
    private lateinit var leftActionBarBtn1: AppCompatTextView
    private lateinit var leftActionBarBtn2: AppCompatTextView
    private lateinit var leftActionBarBtn3: AppCompatTextView

    private lateinit var rightActionBarBtn1: AppCompatTextView
    private lateinit var rightActionBarBtn2: AppCompatTextView
    private lateinit var rightActionBarBtn3: AppCompatTextView

    private lateinit var centerActionBarBtn: AppCompatTextView

    private var toolbarColor: Int = 0
    private var isBackButton: Boolean? = false

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onStart() {
        super.onStart()
        leftActionBarBtn1 = findViewById(R.id.leftActionBarBtn1) as AppCompatTextView
        leftActionBarBtn2 = findViewById(R.id.leftActionBarBtn2) as AppCompatTextView
        leftActionBarBtn3 = findViewById(R.id.leftActionBarBtn3) as AppCompatTextView

        leftActionBarBtn1!!.setOnClickListener { view -> leftActionBarBtn1Pressed(view) }
        leftActionBarBtn2!!.setOnClickListener { view -> leftActionBarBtn2Pressed(view) }
        leftActionBarBtn3!!.setOnClickListener { view -> leftActionBarBtn3Pressed(view) }

        rightActionBarBtn1 = findViewById(R.id.rightActionBarBtn1) as AppCompatTextView
        rightActionBarBtn2 = findViewById(R.id.rightActionBarBtn2) as AppCompatTextView
        rightActionBarBtn3 = findViewById(R.id.rightActionBarBtn3) as AppCompatTextView

        rightActionBarBtn1!!.setOnClickListener { view -> rightActionBarBtn1Pressed(view) }
        rightActionBarBtn2!!.setOnClickListener { view -> rightActionBarBtn2Pressed(view) }
        rightActionBarBtn3!!.setOnClickListener { view -> rightActionBarBtn3Pressed(view) }

        centerActionBarBtn = findViewById(R.id.centerActionBarBtn) as AppCompatTextView
    }

    open fun leftActionBarBtn1Pressed(view: View) {
//        Toast.makeText(this,"leftActionBarBtn1Pressed",Toast.LENGTH_LONG).show();
        if (isBackButton!!) {
            finish()
        }
    }
    open fun leftActionBarBtn2Pressed(view: View) {
//        Toast.makeText(this,"leftActionBarBtn2Pressed",Toast.LENGTH_LONG).show();
    }
    open fun leftActionBarBtn3Pressed(view: View) {
//        Toast.makeText(this,"leftActionBarBtn3Pressed",Toast.LENGTH_LONG).show();
    }
    open fun rightActionBarBtn1Pressed(view: View) {
//        Toast.makeText(this,"rightActionBarBtn1Pressed",Toast.LENGTH_LONG).show();
    }
    open fun rightActionBarBtn2Pressed(view: View) {
//        Toast.makeText(this,"rightActionBarBtn2Pressed",Toast.LENGTH_LONG).show();
    }
    open fun rightActionBarBtn3Pressed(view: View) {
//        Toast.makeText(this,"rightActionBarBtn3Pressed",Toast.LENGTH_LONG).show();
    }
    //ActionBar Setters left
    fun setLeftActionBarImage( leftActionBarBtnId : Int, leftActionBarImage: Int) {
        (findViewById(leftActionBarBtnId) as AppCompatTextView)!!.setCompoundDrawablesWithIntrinsicBounds(
            leftActionBarImage, 0, 0, 0);
    }
    fun setLeftActionBarImageWithTint( leftActionBarBtnId : Int, leftActionBarImage: Int, tintColor: Int) {
        val mutatedDrawable = resources.getDrawable(leftActionBarImage).mutate()
        mutatedDrawable.setColorFilter(resources.getColor(tintColor), PorterDuff.Mode.SRC_IN)
        (findViewById(leftActionBarBtnId) as AppCompatTextView)!!.setCompoundDrawablesWithIntrinsicBounds(mutatedDrawable, null,null,null)
    }
    fun setLeftActionBarText(leftActionBarBtnId: Int, leftActionBarText: String, color: Int) {
        (findViewById(leftActionBarBtnId) as AppCompatTextView)!!.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
        (findViewById(leftActionBarBtnId) as AppCompatTextView)!!.text = leftActionBarText
        (findViewById(leftActionBarBtnId) as AppCompatTextView)!!.setTextColor(color)
    }
    //ActionBar Setters Right
    fun setRightActionBarImage(rightActionBarBtnId : Int, rightActionBarImage: Int) {
        (findViewById(rightActionBarBtnId) as AppCompatTextView)!!.setCompoundDrawablesWithIntrinsicBounds(
            rightActionBarImage, 0, 0, 0);
    }
    fun setRightActionBarImageWithTint( rightActionBarBtnId : Int, rightActionBarImage: Int, tintColor: Int) {
        val mutatedDrawable = resources.getDrawable(rightActionBarImage).mutate()
        mutatedDrawable.setColorFilter(resources.getColor(tintColor),PorterDuff.Mode.SRC_IN)
        (findViewById(rightActionBarBtnId) as AppCompatTextView)!!.setCompoundDrawablesWithIntrinsicBounds(mutatedDrawable, null,null,null)
    }
    fun setRightActionBarText(rightActionBarBtnId : Int,rightActionBarText: String, color: Int) {

        (findViewById(rightActionBarBtnId) as AppCompatTextView)!!.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
        (findViewById(rightActionBarBtnId) as AppCompatTextView)!!.text = rightActionBarText
        (findViewById(rightActionBarBtnId) as AppCompatTextView)!!.setTextColor(color)
    }

    //ActionBar Setters Center
    fun setTitleImage(image: Int) {
        (findViewById(R.id.centerActionBarBtn) as AppCompatTextView)!!.setCompoundDrawablesWithIntrinsicBounds(
            image, 0, 0, 0);
    }

    fun setTitleText(text: String, color: Int) {
        (findViewById(R.id.centerActionBarBtn) as AppCompatTextView)!!.text = text
        (findViewById(R.id.centerActionBarBtn) as AppCompatTextView)!!.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
        (findViewById(R.id.centerActionBarBtn) as AppCompatTextView)!!.setTextColor(resources.getColor(color))
    }

    fun setBackButton(backButton: Boolean?) {
        if (backButton!!) {
            //setLeftActionBarImageWithTint(R.id.leftActionBarBtn1, R.drawable.btnnavback,R.color.theme);
        }
        isBackButton = backButton
    }
    fun setBadgeCount(count : Int){
        if(count > 0 ){
            (findViewById(R.id.txtBadge) as AppCompatTextView)!!.visibility = View.VISIBLE
        }else{
            (findViewById(R.id.txtBadge) as AppCompatTextView)!!.visibility = View.GONE
        }
        (findViewById(R.id.txtBadge) as AppCompatTextView)!!.text = count.toString()
    }
    //Toolbar Init
    fun setToolbarColor(toolbarColor: Int) {
        this.toolbarColor = toolbarColor
        toolBarInit()
        toolbar!!.setBackgroundColor(toolbarColor)
    }

    private fun toolBarInit() {
        if (toolbar == null) {
            //toolbar = findViewById(R.id.toolbar) as Toolbar
            //            setSupportActionBar(toolbar);
        }
    }
}
