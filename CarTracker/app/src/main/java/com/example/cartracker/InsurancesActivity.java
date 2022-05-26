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

public class InsurancesActivity extends AppCompatActivity {

    private Button updateButton;
    private Button rcaButton;
    private Button itpButton;
    private Button vignetaButton;

    private final String RCALink = "https://www.emag.ro/asigurari-rca/new?gclid=Cj0KCQjwvqeUBhCBARIsAOdt45aRBrYzXpjRiaJx3aZVr6GwGLeRXMHsc5UyThcw7GQfhUgm6efEgrEaArzOEALw_wcB#step-1";
    private final String ITPLink = "https://asiguraricontactless.ro/verificare-itp/";
    private final String VignetaLink = "http://www.cnadnr.ro/ro/verificare-rovinieta";

    public static TextView expireRCA;
    public static TextView expireITP;
    public static TextView expireVigneta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurances);

        expireRCA = findViewById(R.id.ExpireRca);
        updateTextIfPossible("RCA");
        expireITP = findViewById(R.id.ExpireITP);
        updateTextIfPossible("ITP");
        expireVigneta = findViewById(R.id.ExpireVigneta);
        updateTextIfPossible("VIGNETA");
        rcaButton = findViewById(R.id.RCAButton);
        rcaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWebActivity(RCALink);
            }
        });

        itpButton = findViewById(R.id.ITPButton);
        itpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWebActivity(ITPLink);
            }
        });

        vignetaButton = findViewById(R.id.VignetaButton);
        vignetaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWebActivity(VignetaLink);
            }
        });

        updateButton = findViewById(R.id.UpdateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdatePopUp();
            }
        });
    }

    private void openUpdatePopUp()
    {
        Intent popupWindow = new Intent(InsurancesActivity.this, InsuranceUpdateActivity.class);
        startActivity(popupWindow);
    }

    public void startWebActivity(String url)
    {
        Intent ulrIntent = new Intent(Intent.ACTION_VIEW);
        ulrIntent.setData(Uri.parse(url));
        startActivity(ulrIntent);
    }

    private void updateTextIfPossible(String name)
    {
        Integer poz = MainActivity.getInsuranceByName(name);

        if(poz != -1)
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String endDate = dateFormat.format(MainActivity.car.getInsurances().get(poz).getEndDate());
            switch (name)
            {
                case "RCA":
                    expireRCA.setText(endDate);
                    break;
                case "ITP":
                    expireITP.setText(endDate);
                    break;
                case "VIGNETA":
                    expireVigneta.setText(endDate);
                    break;
            }
        }
        else
        {
            String noDate = getResources().getString(R.string.NoDateLabel);
            switch (name)
            {
                case "RCA":
                    expireRCA.setText(noDate);
                    break;
                case "ITP":
                    expireITP.setText(noDate);
                    break;
                case "VIGNETA":
                    expireVigneta.setText(noDate);
                    break;
            }
        }
    }
}