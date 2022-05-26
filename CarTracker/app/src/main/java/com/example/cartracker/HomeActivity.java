package com.example.cartracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button insuranceButton;
    private Button taxesAndDuesButton;
    private Button carConsumablesButton;
    private Button changeCarButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        insuranceButton = findViewById(R.id.InsuranceButton);
        insuranceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInsuranceScreen();
            }
        });

        taxesAndDuesButton = findViewById(R.id.TaxesAndDuesButton);
        taxesAndDuesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTaxesAndDuesScreen();
            }
        });

        carConsumablesButton = findViewById(R.id.CarConsumablesButton);
        carConsumablesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCarConsumablesScreen();
            }
        });

        changeCarButton = findViewById(R.id.ChangeCarButton);
        changeCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToChangeCarScreen();
            }
        });
    }

    void goBackToChangeCarScreen()
    {
        finish();
    }

    void openInsuranceScreen()
    {
        Intent insuranceScreen = new Intent(HomeActivity.this, InsurancesActivity.class);
        startActivity(insuranceScreen);
    }

    void openTaxesAndDuesScreen()
    {
        Intent taxesAndDuesScreen = new Intent(HomeActivity.this, TaxesAndDuesActivity.class);
        startActivity(taxesAndDuesScreen);
    }


    void openCarConsumablesScreen()
    {
        Intent carConsumablesScreen = new Intent(HomeActivity.this, CarConsumableActivity.class);
        startActivity(carConsumablesScreen);
    }

}