package com.lkm.lkmydhltest.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.lkm.lkmydhltest.R;

import java.util.ArrayList;
import java.util.List;

public class LineChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private static final String TAG = LineChartActivity.class.getSimpleName();
    private LineChart lc_basic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        setTitle("LineChartActivity");
        initView();
        initData();
    }

    private void initData() {
        {   // // Chart Style // //
            // 整个图表的背景色
            lc_basic.setBackgroundColor(Color.WHITE);

            // 隐藏描述文本
            lc_basic.getDescription().setEnabled(false);

            // 是否可放大缩小或者左右滑动
            lc_basic.setTouchEnabled(true);

            // 设置点击图表事件
            lc_basic.setOnChartValueSelectedListener(this);
            // 是否绘制网格的背景色
            lc_basic.setDrawGridBackground(true);

            // create marker to display box when values are selected
//            MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);

            // Set the marker to the chart
//            mv.setChartView(lc_basic);
//            lc_basic.setMarker(mv);

            // enable scaling and dragging
            lc_basic.setDragEnabled(true);
            lc_basic.setScaleEnabled(true);
            // chart.setScaleXEnabled(true);
            // chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            lc_basic.setPinchZoom(true);
        }

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = lc_basic.getXAxis();

            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = lc_basic.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            lc_basic.getAxisRight().setEnabled(false);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            yAxis.setAxisMaximum(200f);
            yAxis.setAxisMinimum(-50f);
        }

        setData(45, 180);

        // draw points over time
        lc_basic.animateX(5000);

        // get the legend (only possible after setting data)
        Legend l = lc_basic.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.LINE);
    }

    private void initView() {
        lc_basic = (LineChart) findViewById(R.id.lc_basic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.line, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.viewGithub: {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/LineChartActivity1.java"));
                startActivity(i);
                break;
            }
            case R.id.actionToggleValues: {
                List<ILineDataSet> sets = lc_basic.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setDrawValues(!set.isDrawValuesEnabled());
                }

                lc_basic.invalidate();
                break;
            }
            case R.id.actionToggleIcons: {
                List<ILineDataSet> sets = lc_basic.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setDrawIcons(!set.isDrawIconsEnabled());
                }

                lc_basic.invalidate();
                break;
            }
            case R.id.actionToggleHighlight: {
                if(lc_basic.getData() != null) {
                    lc_basic.getData().setHighlightEnabled(!lc_basic.getData().isHighlightEnabled());
                    lc_basic.invalidate();
                }
                break;
            }
            case R.id.actionToggleFilled: {

                List<ILineDataSet> sets = lc_basic.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    if (set.isDrawFilledEnabled())
                        set.setDrawFilled(false);
                    else
                        set.setDrawFilled(true);
                }
                lc_basic.invalidate();
                break;
            }
            case R.id.actionToggleCircles: {
                List<ILineDataSet> sets = lc_basic.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    if (set.isDrawCirclesEnabled())
                        set.setDrawCircles(false);
                    else
                        set.setDrawCircles(true);
                }
                lc_basic.invalidate();
                break;
            }
            case R.id.actionToggleCubic: {
                List<ILineDataSet> sets = lc_basic.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.CUBIC_BEZIER
                            ? LineDataSet.Mode.LINEAR
                            :  LineDataSet.Mode.CUBIC_BEZIER);
                }
                lc_basic.invalidate();
                break;
            }
            case R.id.actionToggleStepped: {
                List<ILineDataSet> sets = lc_basic.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.STEPPED
                            ? LineDataSet.Mode.LINEAR
                            :  LineDataSet.Mode.STEPPED);
                }
                lc_basic.invalidate();
                break;
            }
            case R.id.actionToggleHorizontalCubic: {
                List<ILineDataSet> sets = lc_basic.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.HORIZONTAL_BEZIER
                            ? LineDataSet.Mode.LINEAR
                            :  LineDataSet.Mode.HORIZONTAL_BEZIER);
                }
                lc_basic.invalidate();
                break;
            }
            case R.id.actionTogglePinch: {
                if (lc_basic.isPinchZoomEnabled())
                    lc_basic.setPinchZoom(false);
                else
                    lc_basic.setPinchZoom(true);

                lc_basic.invalidate();
                break;
            }
            case R.id.actionToggleAutoScaleMinMax: {
                lc_basic.setAutoScaleMinMaxEnabled(!lc_basic.isAutoScaleMinMaxEnabled());
                lc_basic.notifyDataSetChanged();
                break;
            }
            case R.id.animateX: {
                lc_basic.animateX(2000);
                break;
            }
            case R.id.animateY: {
                lc_basic.animateY(2000, Easing.EaseInCubic);
                break;
            }
            case R.id.animateXY: {
                lc_basic.animateXY(2000, 2000);
                break;
            }
        }
        return true;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i(TAG, e.toString());
        Log.i(TAG, "low: " + lc_basic.getLowestVisibleX() + ", high: " + lc_basic.getHighestVisibleX());
        Log.i(TAG, "xMin: " + lc_basic.getXChartMin() + ", xMax: " + lc_basic.getXChartMax() + ", yMin: " + lc_basic.getYChartMin() + ", yMax: " + lc_basic.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i(TAG, "Nothing selected.");
    }
    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) - 30;
            values.add(new Entry(i, val, getResources().getDrawable(R.drawable.star)));
        }

        LineDataSet set1;

        if (lc_basic.getData() != null &&
                lc_basic.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lc_basic.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            lc_basic.getData().notifyDataChanged();
            lc_basic.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "温度");

            set1.setDrawIcons(false);

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return lc_basic.getAxisLeft().getAxisMinimum();
                }
            });

            // drawables only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
            set1.setFillDrawable(drawable);

            set1.setDrawValues(false);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            lc_basic.setData(data);
        }
    }
}