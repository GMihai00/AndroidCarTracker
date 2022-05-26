package com.example.cartracker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CarConsumableActivity extends AppCompatActivity {

    private Button updateButton;
    private Button oilButton;
    private Button distributionButton;
    public static TextView expireOil;
    public static TextView expireCarDistribution;

    private  final static String OilLink = "https://www.youtube.com/watch?v=TnS53T3gcPg";
    private  final static String DistributionLink = "https://www.youtube.com/watch?v=heBAwFB1_Ys";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_consumable);

        expireOil = findViewById(R.id.expireOil);
        expireCarDistribution = findViewById(R.id.expireCarDistribution);
        updateTextIfPossible();

        oilButton = findViewById(R.id.OilButton);
        oilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWebActivity(OilLink);
            }
        });

        distributionButton = findViewById(R.id.DistributionButton);
        distributionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWebActivity(DistributionLink);
            }
        });
        updateButton = findViewById(R.id.updateButtonCarConsumables);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdatePopUp();
            }
        });
    }

    private void openUpdatePopUp()
    {
        Intent popupWindow = new Intent(CarConsumableActivity.this, CarConsumablesUpdateActivity.class);
        startActivity(popupWindow);
    }

    public void startWebActivity(String url)
    {
        Intent ulrIntent = new Intent(Intent.ACTION_VIEW);
        ulrIntent.setData(Uri.parse(url));
        startActivity(ulrIntent);
    }

    private  void updateTextIfPossible()
    {
        String noDate = getResources().getString(R.string.NoDateLabel);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(MainActivity.car.getOilExpireDate() != null)
        {
            expireOil.setText(dateFormat.format(MainActivity.car.getOilExpireDate()));
        }
        else
        {
            expireOil.setText(noDate);
        }
        if(MainActivity.car.getDistributionExpireDate() != null)
        {
            expireCarDistribution.setText(dateFormat.format(MainActivity.car.getDistributionExpireDate()));
        }
        else
        {
            expireCarDistribution.setText(noDate);
        }
    }
}