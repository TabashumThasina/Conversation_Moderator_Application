package com.conversationmoderator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.conversationmoderator.Adapters.GraphCellAdapter
import com.conversationmoderator.Models.Data

import kotlin.collections.ArrayList
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.conversationmoderator.Sliders.SliderItem
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry


class AnalyticsActivity : AppCompatActivity(){



    lateinit var sliderView: SliderView
    lateinit var adapter: GraphCellAdapter
    lateinit var data : Data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)
        title = "Analytics"

        if (intent.extras != null) {
            data = (intent.getSerializableExtra("data") as Data?)!!
        }

        sliderView = findViewById(R.id.imageSlider);
        adapter = GraphCellAdapter(this)
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH;
//        sliderView.indicatorSelectedColor = Color.WHITE;
//        sliderView.indicatorUnselectedColor = Color.GRAY;
        sliderView.scrollTimeInSec = 3;
        sliderView.isAutoCycle = false;
//        sliderView.startAutoCycle();
        renewItems()
    }
    fun renewItems() {

        val sliderItemList: MutableList<SliderItem> = ArrayList()

        sliderItemList.add(createSliderItems(data.totalTimePauses,"Total Time With Pauses"))
        sliderItemList.add(createSliderItems(data.gapBtwTurns,"Gap Between Turns"))
        sliderItemList.add(createSliderItems(data.totalDurationPercentage,"Total Duration Percentage Per Speaker"))
        sliderItemList.add(createSliderItems(data.totalTurns,"Total Turn"))
        sliderItemList.add(createSliderItems(data.totalUniqueWords,"Total Unique Words"))
        sliderItemList.add(createSliderItems(data.pausesSentences,"Total Pauses In Sentences"))

        adapter.renewItems(sliderItemList)
    }
    fun createSliderItems(list: MutableList<MutableList<Int>>, title : String): SliderItem {
        val colorGreen = Color.rgb(104, 241, 175)
        val colorBlue = Color.rgb(164, 228, 251)
        val sliderItem = SliderItem()
        val values1: ArrayList<BarEntry> = ArrayList()
        list.forEachIndexed{ index,i ->
            values1.add(BarEntry(i[0].toFloat(), i[1].toFloat()))
        }
        val set1 = BarDataSet(values1, title)
        set1.color = colorBlue
        val data = BarData(set1)
        sliderItem.barData = data
        sliderItem.chartTitle = title

        return sliderItem
    }
}