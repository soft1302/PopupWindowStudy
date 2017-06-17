package com.popupwindowstudy;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radio_group;
    private RadioButton radio_ok;
    private PopupWindow popupWindow;
    private List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        radio_ok = (RadioButton) findViewById(R.id.radio_ok);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_ok:
                        pop(radio_ok);
                        break;
                    case R.id.radio_cancel:
                        break;
                }
            }
        });
//        radio_ok.setChecked(true);
    }

    private void pop(View view) {
        if (popupWindow == null) {
            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.pop_window, null);
            popupWindow = new PopupWindow(v, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(MainActivity.this, R.color.gray));
            ListView listView = (ListView) v.findViewById(R.id.list_items);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.item, R.id.tv_name, datas);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    popupWindow.dismiss();
                    Toast.makeText(MainActivity.this, datas.get(i), Toast.LENGTH_SHORT).show();
                }
            });
            datas.add("星期一");
            datas.add("星期二");
            datas.add("星期三");
            adapter.notifyDataSetChanged();
        }
        popupWindow.update();
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1] + view.getMeasuredHeight());
        popupWindow.showAsDropDown(view);
    }
}
