package com.example.brett.calftracker;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

public class DashboardActivity extends BaseActivity {
    GridView grid;
    String[] dashboard_items = {
            "Calf List",
            "Add Calf",
            "Tasks",
            "Protocols",
            "Insights",
            "Settings"
    } ;
    int[] imageId = {
            R.drawable.ic_view_list_black_24dp,
            R.drawable.ic_add_circle_black_24dp,
            R.drawable.ic_check_box_black_24dp,
            R.drawable.ic_local_hospital_black_24dp,
            R.drawable.ic_insert_chart_black_24dp,
            R.drawable.ic_settings_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_dashboard, frameLayout);
        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        CustomGrid adapter = new CustomGrid(DashboardActivity.this, dashboard_items, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent;
                switch(position) {
                    case 0: intent = new Intent(DashboardActivity.this,CalfListActivity.class);
                            startActivity(intent);
                            break;
                    case 1: intent = new Intent(DashboardActivity.this,AddCalfActivity.class);
                            startActivity(intent);
                            break;
                    case 2: break;
                    case 3: break;
                    case 4: break;
                    case 5: break;
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}