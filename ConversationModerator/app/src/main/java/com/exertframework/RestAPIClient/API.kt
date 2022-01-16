package com.exertframework.RestAPIClient

import com.google.gson.JsonElement
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface API {

    @POST(Constant.LOGIN)
    fun userLogin(@Body userObj: Map<String, String>): Call<JsonElement>

    @GET(Constant.GET_ALL_CATEGORY)
    fun getAllCategory(): Call<JsonElement>

    @GET(Constant.GET_SLIDER)
    fun getSlider(): Call<JsonElement>

    @GET(Constant.GET_PRODUCTS_BY_ID)
    fun getProducts(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @GET(Constant.FILTER_PRODUCT)
    fun filterProducts(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @GET(Constant.GET_WISHLIST)
    fun getWishlist(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @GET(Constant.GET_SORTBIES)
    fun getSortbies(): Call<JsonElement>

    @GET(Constant.GET_PRODUCT_BY_ID)
    fun getProductDetail(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @POST(Constant.ADD_WISH_LIST_ITEM)
    fun addWishlistItem(@Body params : Map<String, String> ): Call<JsonElement>

    @POST(Constant.ADD_CART_ITEM)
    fun addCartItem(@Body params : Map<String, String> ): Call<JsonElement>

    @GET(Constant.GET_CART_ITEM)
    fun getCartItems(): Call<JsonElement>

    @DELETE(Constant.DELETE_CART_ITEM)
    fun deleteCartItem(@QueryMap params : Map<String, String>): Call<JsonElement>

    @DELETE(Constant.DELETE_ITEM_ORDER)
    fun deleteItemOrder(@QueryMap params : Map<String, String>): Call<JsonElement>

    @GET(Constant.GET_CART)
    fun getCart(): Call<JsonElement>

    @PUT(Constant.CHECK_OUT)
    fun checkOut(@QueryMap params : Map<String, String>): Call<JsonElement>

    @PUT(Constant.UPDATE_CART)
    fun updateCart(@QueryMap params : Map<String, String>): Call<JsonElement>

    @DELETE(Constant.CLEAN_CART)
    fun cleanCart(): Call<JsonElement>

    @GET(Constant.ORDER_ALERTS)
    fun orderAlerts(@QueryMap params : Map<String, String>): Call<JsonElement>

    @GET(Constant.GET_ORDER)
    fun getOrder(@QueryMap params : Map<String, String>): Call<JsonElement>

    @PUT(Constant.UPDATE_ORDER)
    fun updateOrder(@QueryMap params : Map<String, String>): Call<JsonElement>

    @PUT(Constant.ADD_ITEM_ORDER)
    fun addItemOrder(@Body params : Map<String, String> ): Call<JsonElement>

    @GET(Constant.ORDER_STATUS)
    fun orderStatus(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @GET(Constant.GET_MY_ORDER_DETAIL)
    fun getMyOrderDetails(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @GET(Constant.ORDER_LIST)
    fun orderList(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @GET(Constant.GET_DISPATCH_ORDER)
    fun getDispatchOrder(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @GET(Constant.GET_REFUND_ORDER)
    fun getRefundOrder(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @POST(Constant.REFUND_COMPLETE_ORDER)
    fun refundCompleteOrder(@Body params : Map<String, String> ): Call<JsonElement>

    @POST(Constant.REFUND_ORDER_ITEMS)
    fun refundOrderItems(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @GET(Constant.SEARCHING_ORDER)
    fun searchingOrder(): Call<JsonElement>

    @GET(Constant.SEARCHED_ORDERS)
    fun searchedOrders(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @GET(Constant.ORDER_REPORT)
    fun orderReport(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @GET(Constant.GET_ALL_BRAND)
    fun getAllBrands(): Call<JsonElement>

    @GET(Constant.INVENTORY_REPORT)
    fun inventoryReport(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @PUT(Constant.UPDATE_USER)
    fun updateUser(@Body params : Map<String, String> ): Call<JsonElement>

    @DELETE(Constant.DISCARD_ORDER)
    fun discardOrder(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @PUT(Constant.SAVE_ORDER)
    fun saveOrder(@QueryMap params : Map<String, String>  ): Call<JsonElement>

    @PUT(Constant.DISPATCHED_ORDER)
    fun dispatchedOrder(@QueryMap params : Map<String, String>  ): Call<JsonElement>

    @GET(Constant.GENERATE_RECEVING_NOTE)
    fun generateReceivingNote(@QueryMap params : Map<String, String>  ): Call<JsonElement>

    @DELETE(Constant.DROP_RECEIPT)
    fun dropReceipt(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @GET(Constant.ORDER_RECEIPT)
    fun orderReceipt(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @Multipart
    @POST(Constant.UPLOAD_RECEIPTS)
    fun uploadReceipts(@PartMap params: Map<String,@JvmSuppressWildcards  RequestBody> ): Call<JsonElement>;

    @Multipart
    @POST(Constant.DIARIZATION)
    fun diarization(@PartMap params: Map<String,@JvmSuppressWildcards  RequestBody> ): Call<JsonElement>;


    @GET(Constant.FORGOT)
    fun forgot(): Call<JsonElement>

    @GET(Constant.LOGOUT)
    fun logout(): Call<JsonElement>

    @DELETE(Constant.DELETE_CART_PRODUCT)
    fun deleteCartProduct(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @DELETE(Constant.DELETE_WISHLIST_ITEM)
    fun deleteWishlistItem(@QueryMap params : Map<String, String> ): Call<JsonElement>

    @GET(Constant.HELP)
    fun help(): Call<JsonElement>
    //    @POST(Constant.REGISTER)
    //    Call<JsonElement> userRegister(@Body Map obj);
//    @Multipart
//    @POST(Constant.REGISTER)
//    fun userRegister(@PartMap obj: Map<String, RequestBody>): Call<JsonElement>
    //    @Multipart
    //    @POST(Constant.REGPHOTO+"{user_id}")
    //    Call<JsonElement> userRegPhotos(@Path(value="user_id") String userid ,@Part MultipartBody.Part cnicfront , @Part MultipartBody.Part cnicback ,@Part MultipartBody.Part drivinglicensephoto);

    //    @POST(Constant.REGPHOTO+"{user_id}")
    //    Call<JsonElement> userRegPhotos(@Path(value="user_id") String userid ,@Body Map userObj);
}