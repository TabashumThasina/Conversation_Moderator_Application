package com.exertframework.Singleton

//import com.qf.giftapp.Models.Brand
//import com.qf.giftapp.Models.GetAllCategoryModels.GetAllCategory
//import com.qf.giftapp.Models.GetDispatchOrder.GetDispatchOrder
//import com.qf.giftapp.Models.GetDispatchOrder.Store
//import com.qf.giftapp.Models.SearchingOrder.SearchingOrder
//import com.qf.giftapp.Models.Sortbies
import java.util.HashMap

class Singleton private constructor() {

//    lateinit var getAllCategory : GetAllCategory
//    lateinit var sliderImages : Array<String>
//    lateinit var sortbies :  ArrayList<Sortbies>
//    lateinit var getProducts : GetProducts
    var selectedCategory : Int = 0
    var isEditOrder : Boolean = false
    var orderId  = ""
    var token = ""
//    lateinit var dispatchedOrderStores : HashMap<String, List<Store>>
//    lateinit var searchingOrder : SearchingOrder
//    lateinit var brandList : ArrayList<Brand>
//    lateinit var getDispatchOrder : GetDispatchOrder

    init {
        INSTANCE = this
        println("init complete")
    }

    companion object {
        lateinit var INSTANCE: Singleton

        init {
            Singleton()
        }
        fun clear(){
            INSTANCE = Singleton()
        }
    }

}