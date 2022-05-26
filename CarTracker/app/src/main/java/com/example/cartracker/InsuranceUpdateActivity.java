package com.example.cartracker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InsuranceUpdateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Spinner insuranceTypeSpinner;
    private ArrayAdapter<CharSequence> insuranceTypeAdapter;
    private Button startDateButton;
    private Button endDateButton;
    private Button updateButton;

    public int buttonPicked;
    private DatePickerFragment startDatePicker;
    private DatePickerFragment endDatePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_update);

        insuranceTypeAdapter = ArrayAdapter.createFromResource(this, R.array.Insurances, android.R.layout.simple_spinner_item);
        insuranceTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        insuranceTypeSpinner = findViewById(R.id.InsuranceTypeSpinner);
        insuranceTypeSpinner.setAdapter(insuranceTypeAdapter);

        insuranceTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        startDatePicker = new DatePickerFragment();
        startDateButton = findViewById(R.id.StartDateButton);
        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonPicked = 0;
                startDatePicker.show(getSupportFragmentManager(), "start date picker");
            }
        });

        endDatePicker = new DatePickerFragment();
        endDateButton = findViewById(R.id.EndDateButton);
        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonPicked = 1;
                endDatePicker.show(getSupportFragmentManager(), "end date picker");
            }
        });

        updateButton = findViewById(R.id.InsuranceUpdateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width * .7), (int) (height * 0.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
    }

    void updateData() {
        String startDate = startDateButton.getText().toString();
        String endDate = endDateButton.getText().toString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            Date d1 = dateFormat.parse(startDate.trim());
            Date d2 = dateFormat.parse(endDate.trim());
            if(d1.compareTo(d2) < 0)
            {
                String name = insuranceTypeSpinner.getSelectedItem().toString();
                MainActivity.updateInsurance(name, d1, d2);
                switch (name)
                {
                    case "RCA":
                        InsurancesActivity.expireRCA.setText(endDate);
                        break;
                    case "ITP":
                        InsurancesActivity.expireITP.setText(endDate);
                        break;
                    case "VIGNETA":
                        InsurancesActivity.expireVigneta.setText(endDate);
                        break;
                }

                finish();
            }
        } catch (ParseException pe) {
            //TO DO
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateString = dateFormat.format(c.getTime());

        if(buttonPicked == 0)
            startDateButton.setText(currentDateString);
        else
        if(buttonPicked == 1) {
            String errorMessage = "--INVALID DATE--";
            try {
                Date endDate = c.getTime();
                Date startDate = dateFormat.parse(startDateButton.getText().toString().trim());

                if(startDate.compareTo(endDate) <= 0)
                    endDateButton.setText(currentDateString);
                else
                    endDateButton.setText(errorMessage);
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
    }
}