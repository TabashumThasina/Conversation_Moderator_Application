package com.exertframework.Utility;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.exertframework.Views.SpinnerProgressDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by solanki on 22/11/2017.
 */

public class Utility {


//    private static Utility utility = new Utility();
//    /* A private Constructor prevents any other
//     * class from instantiating.
//     */
//    private Utility() { }
//    /* Static 'instance' method */
//    public static Utility getInstance( ) {
//        return utility;
//    }
    private static SpinnerProgressDialog spinnerProgressDialog;
    /**
     * Starts Tablet size Calc
     */

    /**
     * This Func return Screen Width
     * @return Int
     */
    public static int getScreenWidth() {return Resources.getSystem().getDisplayMetrics().widthPixels;}

    /**
     * This Func return Screen Height
     * @return
     */
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * Get screen density
     * @return Screen Density
     */
    public static float getScreenDensity(){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
//        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        switch(metrics.densityDpi){
            case DisplayMetrics.DENSITY_260:
            case DisplayMetrics.DENSITY_280:
            case DisplayMetrics.DENSITY_300:
                return 1.75f;
            case DisplayMetrics.DENSITY_340:
            case DisplayMetrics.DENSITY_360:
            case DisplayMetrics.DENSITY_400:
            case DisplayMetrics.DENSITY_420:
                return 2.5f;
            case DisplayMetrics.DENSITY_560:
                return 3.5f;
            case DisplayMetrics.DENSITY_MEDIUM:
                return 1.0f;//1.0
            case DisplayMetrics.DENSITY_HIGH:
                return 1.5f;//1.5
            case DisplayMetrics.DENSITY_XHIGH:
                return 2.0f;//2.0
            case DisplayMetrics.DENSITY_XXHIGH:
                return 3.0f;
            case DisplayMetrics.DENSITY_XXXHIGH:
                return 4.0f;
                default:
                    return 1.0f;
        }
    }

    /**
     * Generate device specific layout parameters with ratio.
     * @param vp View parent to check layout params instance type.
     * @param lp Layout params to be updated of specific view.
     * @return update layout params.
     */
    public static ViewGroup.LayoutParams getNewLayoutParams(ViewParent vp, ViewGroup.LayoutParams lp){
        if (vp instanceof RelativeLayout){
            RelativeLayout.LayoutParams prm = (RelativeLayout.LayoutParams) lp;
            prm.setMarginEnd(getUpdatedWidthPx(prm.getMarginEnd()));
            prm.setMarginStart(getUpdatedWidthPx(prm.getMarginStart()));
            prm.setMargins(getUpdatedWidthPx(prm.leftMargin),getUpdatedHeightPx(prm.topMargin),getUpdatedWidthPx(prm.rightMargin),getUpdatedHeightPx(prm.bottomMargin));
            return prm;
        }else if (vp instanceof LinearLayout){
            LinearLayout.LayoutParams prm = (LinearLayout.LayoutParams) lp;
            prm.setMarginEnd(getUpdatedWidthPx(prm.getMarginEnd()));
            prm.setMarginStart(getUpdatedWidthPx(prm.getMarginStart()));
            prm.setMargins(getUpdatedWidthPx(prm.leftMargin),getUpdatedHeightPx(prm.topMargin),getUpdatedWidthPx(prm.rightMargin),getUpdatedHeightPx(prm.bottomMargin));
            return prm;
        }else if (vp instanceof ConstraintLayout){
            ConstraintLayout.LayoutParams prm = (ConstraintLayout.LayoutParams) lp;
            prm.setMarginEnd(getUpdatedWidthPx(prm.getMarginEnd()));
            prm.setMarginStart(getUpdatedWidthPx(prm.getMarginStart()));
            prm.setMargins(getUpdatedWidthPx(prm.leftMargin),getUpdatedHeightPx(prm.topMargin),getUpdatedWidthPx(prm.rightMargin),getUpdatedHeightPx(prm.bottomMargin));
            return prm;
        }else {
            return lp;
        }
    }

    /**
     * Set height with applying device ratio
     * @param view View
     */
    private static void setNewHight(View view){
        if (view.getLayoutParams().height == ViewGroup.LayoutParams.MATCH_PARENT){
            view.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        }else if(view.getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT){
            view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }else{
            view.getLayoutParams().height = getUpdatedHeightPx(view.getLayoutParams().height);
        }
    }

    /**
     * Set Width with applying device ratio
     * @param view
     */
    private static void setNewWidth(View view){
        if (view.getLayoutParams().width == ViewGroup.LayoutParams.MATCH_PARENT){
            view.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        }else if(view.getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT){
            view.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
        }else{
            view.getLayoutParams().width = getUpdatedWidthPx(view.getLayoutParams().width);
        }
    }

    /**
     * Get updated pixels form device ratio.
     * @param px current pixels.
     * @return update pixels in INT.
     */
    public static int getUpdatedPx(int px){
        //old Way
        Log.i("getScreenDensity" , ""+getScreenDensity());
        float density = getScreenDensity();
        int top = px;
        float dp = top / density;
        float newdp = dp / 4.0f;
        String[] s = getScreenDimension();
        Log.i("getScreenDimension" , "width " + s[0] + " height " + s[1] + "screenInches " + s[2]);

        return Math.round((newdp*density)*density);

//        Log.i("getScreenHeight" , ""+getScreenHeight() + " getScreenDensity " +getScreenDensity());
        //New way

//        float i = Float.parseFloat(s[2])/5.0f;
//        int pxs = Math.round(px * i);

//        return pxs;
    }
    public static int getUpdatedWidthPx(int px){
        String[] s = getScreenDimension();
        Log.i("getScreenDimension" , "width " + s[0] + " height " + s[1] + "screenInches " + s[2]);
        if (Integer.parseInt(s[0]) > 1440 ){
            return (px*Integer.parseInt(s[0]))/1440;
        }
        return px;
    }
    public static int getUpdatedHeightPx(int px){
        String[] s = getScreenDimension();
        Log.i("getScreenDimension" , "width " + s[0] + " height " + s[1] + "screenInches " + s[2]);
        return (px*Integer.parseInt(s[1]))/2560;
    }
    /**
     * Get screen dimension and screen size in inches.
     * @return String array 0. ScreenWidth, 1. ScreenHeight , 2. ScreenInches.
     */
    private static String[] getScreenDimension(){
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int dens = dm.densityDpi;
        double wi = (double)width / (double)dens;
        double hi = (double)height / (double)dens;
        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        double screenInches = Math.sqrt(x+y);

        String[] screenInformation = new String[3];
        screenInformation[0] = String.valueOf(width); //px
        screenInformation[1] = String.valueOf(height) ; //px
        screenInformation[2] = String.format("%.2f", screenInches) ;

        return screenInformation;
    }

    /**
     *
     * @param view Current View that needs to update
     * @param isTabWidth bool need to update view width for tablet
     * @param isTabHeight bool need to update view Height for tablet
     * @param isTabMargin bool need to update view Margin for tablet
     * @param isTabPadding bool need to update view Padding for tablet
     * @param isTabFont bool need to update view FontSize for tablet
     * @param isTabRatio bool need to update view Ration for tablet
     */
    public static void updateView(View view ,Boolean isTabWidth, Boolean isTabHeight,Boolean isTabMargin,boolean isTabPadding , boolean isTabFont, boolean isTabRatio){
        if(isTabFont){
            if (view instanceof TextView){
                TextView tv  = (TextView) view;
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,getUpdatedPx(Math.round(tv.getTextSize())));
            } else if (view instanceof EditText){
                EditText et = (EditText) view;
                et.setTextSize(TypedValue.COMPLEX_UNIT_PX,getUpdatedPx(Math.round(et.getTextSize())));
            }else if (view instanceof Button){
                Button b = (Button) view;
                b.setTextSize(TypedValue.COMPLEX_UNIT_PX,getUpdatedPx(Math.round(b.getTextSize())));
            }
        }
        if(isTabWidth){
            Log.i("BaseEditText","test");
            setNewWidth(view);
        }
        if(isTabHeight){
            setNewHight(view);
        }
        if(isTabMargin){
            view.setLayoutParams(getNewLayoutParams(view.getParent(),view.getLayoutParams()));
        }
        if(isTabPadding){
            view.setPadding(getUpdatedWidthPx(view.getPaddingLeft()),getUpdatedHeightPx(view.getPaddingTop()),getUpdatedWidthPx(view.getPaddingRight()),getUpdatedHeightPx(view.getPaddingBottom()));
        }
        if (isTabRatio){
            setNewWidth(view);
            setNewHight(view);
        }
    }
    /**
     * Ends Tablet size Calc
     */

    /**
     * Other Utility
     */

    /**
     * Check is network available
     * @param context context to get network manager
     * @return bool
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Toast.makeText(context.getApplicationContext(), "No Network",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        Toast.makeText(context.getApplicationContext(), "No Network",Toast.LENGTH_SHORT).show();
        return false;
    }
    /**
     * Show android alert
     * @param context Context
     * @param title Title
     * @param message Message
     */
    public static void showAlert(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).show();
    }
    /**
     * Hide keyboard for view
     * @param view View
     * @param context Context
     */
    public static void hideKeyboard(View view, Context context)
    {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    /**
     * Check email pattern
     * @param email email
     * @param context Context
     * @return bool
     */
    public static boolean isValidEmail(String email, Context context) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            return true;
        } else {
            // Helper.toast(context, "Email is not valid..!");

            return false;
        }
    }
    /**
     * Show spinner on context
     * @param context context
     */
    public static void showSpinnerProgressDialog(Context context ,Boolean showHud) {
        if(showHud) {
            if (spinnerProgressDialog == null) {
                spinnerProgressDialog = new SpinnerProgressDialog(context);
                spinnerProgressDialog.setCanceledOnTouchOutside(false);
                spinnerProgressDialog.show();
            } else {
                spinnerProgressDialog.show();
            }
        }
    }
    /**
     * hide spinner on activity
     */
    public static void hideSpinnerProgressDialog(Boolean showHud) {
        if (showHud) {
            if (spinnerProgressDialog != null) {
                try{
                    spinnerProgressDialog.dismiss();
                }catch (IllegalArgumentException e){
                    Log.i("IllegalArgumentEx", "Spinner not on window");
                }
                spinnerProgressDialog = null;
            }
        }
    }

    /**
     * animate with positions
     * @param view to be animate
     * @param propertyName translationY or translationX
     * @param setDuration time
     * @param values A set of values that the animation will animate between over time
     */
    public static void objectAnimator(View view, String propertyName,long setDuration, float... values){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, propertyName, values);
        objectAnimator.setDuration(setDuration);
        objectAnimator.start();
    }
    public static void objectAnimatorMultipleViews(View[] views, String propertyName,long setDuration, float... values){
        for (View view:views) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, propertyName, values);
            objectAnimator.setDuration(setDuration);
            objectAnimator.start();
        }
    }

    /**
     * convert json obj to class obj
     * @param data json object to convert
     * @param classofT in which object to be converted
     * @param <T> class dynamic return type
     * @return
     */
    public static <T> Object getObjectFromJsonObject(final Object data, Class<T> classofT) {

        if (data instanceof JsonObject) {
            JsonObject json = (JsonObject) data;
            GsonBuilder gb = new GsonBuilder();
            gb.registerTypeAdapter(String.class, new StringConverter());
            Gson gson = gb.create();
            //Gson gson = new Gson();

            T obj = gson.fromJson(json, classofT);
            return obj;
        }
        return null;
    }
    public static <T> Object getObjectFromJsonArrayObject(final Object data) {

        if (data instanceof JsonArray) {
            Gson gson = new Gson();
            Type type = new TypeToken<T>() {}.getType();
            T obj = gson.fromJson((JsonArray) data, type);
            return obj;
        }
        return null;
    }
    /**
     * compress image
     * @param imageFile file of image
     * @param quality compress max 100
     */
    public static void imageCompression(File imageFile, int quality) {
        Log.i("imageCompression", imageFile.getPath());
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getPath());
        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e("imageCompression", "Error writing bitmap", e);
        }
    }
    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0");
        return formatter.format(Double.parseDouble(amount));
    }
}
