package com.conversationmoderator.Adapters

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.DisplayMetrics
import android.view.Gravity.CENTER

import android.widget.TextView

import com.smarteist.autoimageslider.SliderViewAdapter

import android.widget.Toast

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.conversationmoderator.R
import com.conversationmoderator.Sliders.SliderItem
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter
import org.w3c.dom.Text

class GraphCellAdapter(context: Context) : SliderViewAdapter<GraphCellAdapter.SliderAdapterVH>() {
    private val context: Context
    private var mSliderItems: MutableList<SliderItem> = ArrayList()
    fun renewItems(sliderItems: MutableList<SliderItem>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: SliderItem) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.graph_cell, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val sliderItem: SliderItem = mSliderItems[position]



        val barWidth = 0.45f
        sliderItem.barData!!.barWidth = barWidth;
        sliderItem.barData!!.setValueFormatter(LargeValueFormatter())


        viewHolder.chart.data = sliderItem.barData
        viewHolder.chart.xAxis.position = XAxis.XAxisPosition.BOTTOM;
        viewHolder.titleChart.text = sliderItem.chartTitle
        var xaxis = viewHolder.chart.xAxis
        xaxis.position = XAxis.XAxisPosition.BOTTOM;
        xaxis.textSize = 14f;
        xaxis.textColor = Color.BLACK;
        xaxis.yOffset = 5f;
        xaxis.setDrawLabels(true);
        xaxis.isGranularityEnabled = true;
        xaxis.xOffset = -50f;

    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItems.size
    }

    @RequiresApi(Build.VERSION_CODES.R)
    inner class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        var chart: BarChart;
        var titleChart: TextView;
        lateinit var itemView: View

        init {
            chart = itemView.findViewById<BarChart>(R.id.chart1);
            titleChart = itemView.findViewById<TextView>(R.id.titleChart);
            chart.description.isEnabled = true
            chart.description.isEnabled = false
            chart.setPinchZoom(false);
            chart.setDrawBarShadow(false);
            chart.setDrawGridBackground(false);
            chart.legend.isEnabled = false
        }
    }

    init {
        this.context = context
    }
}