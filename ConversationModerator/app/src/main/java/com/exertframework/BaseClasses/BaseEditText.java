package com.exertframework.BaseClasses;

import android.content.Context;

import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatEditText;

import com.exertframework.Utility.Utility;

/**
 * Created by solanki on 26/11/2017.
 */

public class BaseEditText extends AppCompatEditText {

    boolean callOnDraw = false;
    boolean isTabWidth;
    boolean isTabHeight;
    boolean isTabMargin;
    boolean isTabPadding;
    boolean isTabFont;
    boolean isTabRatio;


    public BaseEditText(Context context) {
        super(context);
    }

    public BaseEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        for (int x=0 ; x<attrs.getAttributeCount();x++){
            if (attrs.getAttributeName(x).equalsIgnoreCase("isTabWidth")){
                isTabWidth = attrs.getAttributeBooleanValue(x,false);
            } else if (attrs.getAttributeName(x).equalsIgnoreCase("isTabHeight")){
                isTabHeight = attrs.getAttributeBooleanValue(x,false);
            } else if (attrs.getAttributeName(x).equalsIgnoreCase("isTabMargin")){
                isTabMargin = attrs.getAttributeBooleanValue(x,false);
            } else if (attrs.getAttributeName(x).equalsIgnoreCase("isTabPadding")){
                isTabPadding = attrs.getAttributeBooleanValue(x,false);
            } else if (attrs.getAttributeName(x).equalsIgnoreCase("isTabFont")){
                isTabFont = attrs.getAttributeBooleanValue(x,false);
            } else if (attrs.getAttributeName(x).equalsIgnoreCase("isTabRatio")){
                isTabRatio = attrs.getAttributeBooleanValue(x,false);
            }
        }
    }

    public BaseEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateViews();
    }
    private void updateViews() {

        Utility.updateView(this , isTabWidth , isTabHeight , isTabMargin , isTabPadding , isTabFont , isTabRatio);
//        setTextSize(TypedValue.COMPLEX_UNIT_PX,u.getUpdatedPx(Math.round(getTextSize())));
//        getLayoutParams().height = u.getUpdatedPx(getLayoutParams().height);
//        getLayoutParams().width = u.getUpdatedPx(getLayoutParams().width);
//        setPadding(u.getUpdatedPx(getPaddingLeft()),u.getUpdatedPx(getPaddingTop()),u.getUpdatedPx(getPaddingRight()),u.getUpdatedPx(getPaddingBottom()));
//        setLayoutParams(u.getNewLayoutParams(getParent(),getLayoutParams()));
    }
}
