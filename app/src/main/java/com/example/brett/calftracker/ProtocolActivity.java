package com.example.brett.calftracker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ProtocolActivity extends BaseActivity {

    private ListView lvVaccine;

    private VaccineAdapter vAdapter;

    private List<Vaccine> vaccineList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_protocol_vaccine, frameLayout);
        mNavigationView.getMenu().findItem(R.id.nav_protocols).setChecked(true);

        SharedPreferences mPreferences = getSharedPreferences("CalfTracker", Activity.MODE_PRIVATE);
        if(mPreferences.contains("VaccineList")) {
            SharedPreferences.Editor editor = mPreferences.edit();

            Gson gson = new Gson();
            String json = mPreferences.getString("VaccineList", "");
            vaccineList = gson.fromJson(json, new TypeToken<ArrayList<Vaccine>>() {
            }.getType());
        } else { vaccineList = new ArrayList<Vaccine>(); }

        ListView lvVaccine = (ListView)findViewById(R.id.listview_vaccine);

        VaccineAdapter vAdapter = new VaccineAdapter(getApplicationContext(), vaccineList);
        lvVaccine.setAdapter(vAdapter);

        lvVaccine.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
            }
        });
    }
    public void onMedicineButtonClick(View view) {
        Intent intent = new Intent(ProtocolActivity.this,MedicineActivity.class);
        startActivity(intent);
    }
    public void onVaccineButtonClick(View view){
        Intent intent = new Intent(ProtocolActivity.this, ProtocolActivity.class);
        startActivity(intent);
    }
    public void onIllnessButtonClick(View view){
        Intent intent = new Intent(ProtocolActivity.this, IllnessActivity.class);
        startActivity(intent);
    }
    public void onEditVaccineButtonClick(View view){
        Intent intent = new Intent(ProtocolActivity.this, EditVaccineActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}

