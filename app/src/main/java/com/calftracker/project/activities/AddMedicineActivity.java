package com.calftracker.project.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.calftracker.project.calftracker.R;
import com.calftracker.project.models.Medicine;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class AddMedicineActivity extends AppCompatActivity {

    private AlertDialog alertDialog;
    private EditText medicineName;
    private EditText dosage;
    private EditText dosageUnits;
    private EditText timeActive;
    private List<Medicine> medicineList;
    private Button notesButton;
    private EditText medicineNotes;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        // Custom title
        getSupportActionBar().setTitle(R.string.protocols_medicine_add);

        SharedPreferences mPreferences = getSharedPreferences("CalfTracker", Activity.MODE_PRIVATE);
        if(mPreferences.contains("MedicineList")) {
            SharedPreferences.Editor editor = mPreferences.edit();

            Gson gson = new Gson();
            String json = mPreferences.getString("MedicineList", "");
            medicineList = gson.fromJson(json, new TypeToken<ArrayList<Medicine>>() {
            }.getType());
        } else { medicineList = new ArrayList<Medicine>(); }

        ((Button) findViewById(R.id.edit_medicine_buttonAdd)).setText("Add");
    }

    public void clickAddMedicineButton(View view){
        medicineName = (EditText) findViewById(R.id.edit_medicine_editTextMedicine);
        dosage = (EditText) findViewById(R.id.edit_medicine_editTextDosage);
        dosageUnits = (EditText) findViewById(R.id.edit_medicine_editTextDosageUnits);
        timeActive = (EditText) findViewById(R.id.edit_medicine_editTextTimeActive);
        medicineNotes = (EditText) findViewById(R.id.edit_medicine_editTextNotes);

        if (medicineName.getText().toString().matches("")
                || dosage.getText().toString().matches("")
                || dosageUnits.getText().toString().matches("")
                || timeActive.getText().toString().matches("")){
            Toast.makeText(AddMedicineActivity.this, R.string.empty_fields_message,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < medicineList.size(); i++) {
            if (medicineList.get(i).getName().equals(medicineName.getText().toString())) {
                Toast.makeText(this, "A medicine with this name already exists, please choose a new name or delete the other medicine",
                        Toast.LENGTH_LONG).show();
                medicineName.setText("");
                return;
            }
        }

        Toast.makeText(AddMedicineActivity.this, R.string.add_medicine_successful_message,
                Toast.LENGTH_SHORT).show();

        String nameStr = medicineName.getText().toString();
        Double dosageDbl = Double.parseDouble(dosage.getText().toString());
        String dosageUnitsStr = dosageUnits.getText().toString();
        int timeActiveInt = Integer.parseInt(timeActive.getText().toString());
        String medicineNotesStr = medicineNotes.getText().toString();


        // MAKE A NEW Medicine OBJECT
        Medicine medicine = new Medicine(nameStr,dosageDbl,dosageUnitsStr,timeActiveInt,medicineNotesStr);

        medicineList.add(medicine);

        // SAVE NEW Medicine
        SharedPreferences mPrefs = getSharedPreferences("CalfTracker", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(medicineList);
        prefsEditor.putString("MedicineList",json);
        prefsEditor.apply();

        Intent intent = new Intent(this,MedicineActivity.class);
        startActivity(intent);
    }

    public void clickCancelButton(View view){
        Intent intent = new Intent(AddMedicineActivity.this, MedicineActivity.class);
        startActivity(intent);
    }

}
