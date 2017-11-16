package com.calftracker.project.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.calftracker.project.adapters.CalfListListViewAdapter;
import com.calftracker.project.models.Calf;
import com.calftracker.project.calftracker.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CalfListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static ArrayList<Calf> calfList;
    public static boolean arrayExists;


    private EditText editText;
    private ListView listView;
    private CalfListListViewAdapter adapter;
    private ArrayList<String> idArrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_calf_list, frameLayout);
        mNavigationView.getMenu().findItem(R.id.nav_list).setChecked(true);

        // Load in the calf list
        SharedPreferences mPreferences = getSharedPreferences("CalfTracker", Activity.MODE_PRIVATE);
        if(mPreferences.contains("CalfList")) {
            arrayExists = true;
            SharedPreferences.Editor editor = mPreferences.edit();

            Gson gson = new Gson();
            String json = mPreferences.getString("CalfList", "");
            calfList = gson.fromJson(json, new TypeToken<ArrayList<Calf>>() {
            }.getType());
        } else {
            arrayExists = false;
            calfList = new ArrayList<Calf>();
        }

        // Calf Test Set of 10,000 calfs
//        calfList.clear();
//        for (int i = 0; i < 10000; i++) {
//            String testID = "";
//            if (i < 10)
//                testID = "000" + i;
//            if (i < 100 && i >= 10)
//                testID = "00" + i;
//            if (i < 1000 && i >= 100)
//                testID = "0" + i;
//            if (i < 10000 && i >= 1000) {
//                testID = Integer.toString(i);
//            }
//            Calf testCalf = new Calf(testID, "Male", Calendar.getInstance());
//            calfList.add(testCalf);
//        }

        listView = (ListView) findViewById(R.id.recyclerViewCalfList);

        // Load the calf IDs into an ArrayList


        if(arrayExists) {
            for (int i = 0; i < calfList.size(); i++) {
                idArrayList.add(calfList.get(i).getFarmId());
            }
        } else { idArrayList.add("No calves exist. Click here to get started!"); }

        adapter = new CalfListListViewAdapter(getApplicationContext(), idArrayList);
        listView.setAdapter(adapter);
        editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                // Search
                String currID = editText.getText().toString().toLowerCase(Locale.getDefault());
                adapter.myFilter(currID);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void clickAdd(View view) {
        Intent intent = new Intent(this,AddCalfActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,DashboardActivity.class);
        startActivity(intent);
    }
}