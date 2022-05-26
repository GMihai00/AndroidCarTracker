package com.example.cartracker;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CarConsumablesUpdateActivity extends AppCompatActivity {

    private Button updateButton;
    private Switch oilSwitch;
    private Switch distributionSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_consumables_update);

        oilSwitch = findViewById(R.id.oilSwitch);
        distributionSwitch = findViewById(R.id.distributionSwitch);
        updateButton = findViewById(R.id.updateButtonCarConsumablesPopUp);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateConsumablesExpireDate();
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

    void updateConsumablesExpireDate()
    {

        if(oilSwitch.isChecked())
            MainActivity.car.getEngine().resetOilExpireDate();

        if(distributionSwitch.isChecked())
            MainActivity.car.getEngine().resetDistributionExpireDate();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(MainActivity.car.getOilExpireDate() != null)
        {
            CarConsumableActivity.expireOil.setText(dateFormat.format(MainActivity.car.getOilExpireDate()));
        }

        if(MainActivity.car.getDistributionExpireDate() != null)
        {
            CarConsumableActivity.expireCarDistribution.setText(dateFormat.format(MainActivity.car.getDistributionExpireDate()));
        }
        finish();
    }
}