package com.lkm.lkmydhltest.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lkm.lkmydhltest.R;
import com.lkm.lkmydhltest.activity.LineChartActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lv_main_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("YDHLExamples");
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        lv_main_page.setOnItemClickListener(this);
    }

    private void initData() {
        ArrayList<ContentItem> objects = new ArrayList<>();

        ////
        objects.add(0, new ContentItem("MPCharts"));
        objects.add(1, new ContentItem("LineChart", "折线图"));
        objects.add(2, new ContentItem("BarChart", "柱状图"));
        objects.add(3, new ContentItem("PieChart", "饼图"));
        objects.add(4, new ContentItem("ViewPagerChart", "ViewPager实现多个chart"));
        objects.add(5, new ContentItem("DynamicChart", "动态图表"));

        ////
        objects.add(6, new ContentItem("CircleImageView"));
        objects.add(7, new ContentItem("CircleImageViewExample", "一个CircleImageView例子"));

        ////
        objects.add(8, new ContentItem("GrrenDao"));
        objects.add(9, new ContentItem("GrrenDaoExample", "一个GrrenDao例子"));

        lv_main_page.setAdapter(new ListViewMainPageAdapter(this, objects));
    }

    private void initView() {
        lv_main_page = (ListView) findViewById(R.id.lv_main_page);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = null;
        switch (position) {
            case 1:
                i = new Intent(this, LineChartActivity.class);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                i = new Intent(this, GreenDaoActivity.class);
                break;
            default:
                Toast.makeText(this, "待完善", Toast.LENGTH_SHORT).show();
                break;
        }

        if (i != null) startActivity(i);

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

    private class ContentItem {
        final String name;
        final String desc;
        boolean isSection = false;

        ContentItem(String n) {
            name = n;
            desc = "";
            isSection = true;
        }

        ContentItem(String n, String d) {
            name = n;
            desc = d;
        }
    }

    private class ListViewMainPageAdapter extends ArrayAdapter<ContentItem> {
        private final Typeface mTypeFaceLight;
        private final Typeface mTypeFaceRegular;

        public ListViewMainPageAdapter(Context context, ArrayList<ContentItem> objects) {
            super(context, 0, objects);
            mTypeFaceLight = Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");
            mTypeFaceRegular = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ContentItem c = getItem(position);

            ViewHolder holder;

            holder = new ViewHolder();

            if (c != null && c.isSection) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_section, null);
            } else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
            }

            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.tvDesc = convertView.findViewById(R.id.tvDesc);

            convertView.setTag(holder);

            if (c != null && c.isSection)
                holder.tvName.setTypeface(mTypeFaceRegular);
            else
                holder.tvName.setTypeface(mTypeFaceLight);
            holder.tvDesc.setTypeface(mTypeFaceLight);

            holder.tvName.setText(c != null ? c.name : null);
            holder.tvDesc.setText(c != null ? c.desc : null);

            return convertView;
        }
        private class ViewHolder {
            TextView tvName, tvDesc;
        }
    }
}