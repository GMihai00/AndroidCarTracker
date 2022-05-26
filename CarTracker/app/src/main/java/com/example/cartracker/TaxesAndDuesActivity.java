package com.example.cartracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

public class TaxesAndDuesActivity extends AppCompatActivity {

    private TextView cubicCapacityValueLabel;
    private TextView yearlyFeesValueLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxes_and_dues);

        cubicCapacityValueLabel = findViewById(R.id.CubicCapacityValueLabel);
        cubicCapacityValueLabel.setText(MainActivity.car.getCubicCapacity().toString());

        yearlyFeesValueLabel = findViewById(R.id.YearlyFeesValueLabel);
        yearlyFeesValueLabel.setText(MainActivity.car.getTaxes().toString() + " lei");

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
}