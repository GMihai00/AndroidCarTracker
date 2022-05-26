package com.example.cartracker;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cartracker.model.Car;
import com.example.cartracker.model.CarMake;
import com.example.cartracker.model.Engine;
import com.example.cartracker.model.MandatoryInsurance;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Spinner carMakeSpinner;
    private ArrayAdapter<CharSequence> carMakeAdapter;

    private Spinner modelSpinner;
    private ArrayAdapter<CharSequence> modelAdapterVW;
    private ArrayAdapter<CharSequence> modelAdapterBMW;
    private ArrayAdapter<CharSequence> modelAdapterToyota;
    private ArrayAdapter<CharSequence> modelAdapterAudi;

    private Spinner engineSpinner;
    private ArrayAdapter<CharSequence> engineAdapter;

    private Button homeButton;

    private final static String configFilePath = "config.txt";
    public static Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        engineAdapter = ArrayAdapter.createFromResource(this, R.array.Engines, android.R.layout.simple_spinner_item);
        engineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        engineSpinner = findViewById(R.id.engineSpinner);
        engineSpinner.setAdapter(engineAdapter);
        engineSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        modelAdapterVW = ArrayAdapter.createFromResource(this, R.array.VWModels, android.R.layout.simple_spinner_item);
        modelAdapterVW.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelAdapterBMW = ArrayAdapter.createFromResource(this, R.array.BMWModels, android.R.layout.simple_spinner_item);
        modelAdapterBMW.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelAdapterAudi = ArrayAdapter.createFromResource(this, R.array.AudiModels, android.R.layout.simple_spinner_item);
        modelAdapterAudi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelAdapterToyota = ArrayAdapter.createFromResource(this, R.array.ToyotaModels, android.R.layout.simple_spinner_item);
        modelAdapterToyota.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        modelSpinner = findViewById(R.id.modelSpinner);
        modelSpinner.setAdapter(modelAdapterVW);
        modelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        carMakeAdapter = ArrayAdapter.createFromResource(this, R.array.CarMakes, android.R.layout.simple_spinner_item);
        carMakeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        carMakeSpinner = findViewById(R.id.carMakeSpinner);
        carMakeSpinner.setAdapter(carMakeAdapter);
        carMakeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String choice = adapterView.getItemAtPosition(i).toString();

                if(car != null && car.getMake().toString().equals(choice))
                {
                    switch (car.getMake().toString()) {
                        case "VW":
                            modelSpinner.setAdapter(modelAdapterVW);
                            modelSpinner.setSelection(modelAdapterVW.getPosition(car.getModel()), true);
                            Log.d("SELECTED:", modelSpinner.getSelectedItem().toString() + " at pozition " + modelAdapterVW.getPosition(car.getModel()));
                            modelAdapterVW.notifyDataSetChanged();
                            break;
                        case "BMW":
                            modelSpinner.setAdapter(modelAdapterBMW);
                            modelSpinner.setSelection(modelAdapterBMW.getPosition(car.getModel()), true);
                            Log.d("SELECTED:", modelSpinner.getSelectedItem().toString() + " at pozition " + modelAdapterBMW.getPosition(car.getModel()));
                            modelAdapterBMW.notifyDataSetChanged();
                            break;
                        case "Audi":
                            modelSpinner.setAdapter(modelAdapterAudi);
                            modelSpinner.setSelection(modelAdapterAudi.getPosition(car.getModel()), true);
                            Log.d("SELECTED:", modelSpinner.getSelectedItem().toString() + " at pozition " + modelAdapterAudi.getPosition(car.getModel()));
                            modelAdapterAudi.notifyDataSetChanged();
                            break;
                        case "Toyota":
                            modelSpinner.setAdapter(modelAdapterToyota);
                            modelSpinner.setSelection(modelAdapterToyota.getPosition(car.getModel()), true);
                            Log.d("SELECTED:", modelSpinner.getSelectedItem().toString() + " at pozition " + modelAdapterToyota.getPosition(car.getModel()));
                            modelAdapterToyota.notifyDataSetChanged();
                            break;
                    }
                }
                else {
                    switch (choice) {
                        case "VW":
                            modelSpinner.setAdapter(modelAdapterVW);
                            break;
                        case "BMW":
                            modelSpinner.setAdapter(modelAdapterBMW);
                            break;
                        case "Audi":
                            modelSpinner.setAdapter(modelAdapterAudi);
                            break;
                        case "Toyota":
                            modelSpinner.setAdapter(modelAdapterToyota);
                            break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        homeButton = findViewById(R.id.HomeScreenButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeScreen();
            }
        });

        updateFromConfigFile();
    }

    void openHomeScreen()
    {

        CarMake make = CarMake.VW;
        String makeSelected = carMakeSpinner.getSelectedItem().toString();
        switch (makeSelected)
        {
            case "VW":
                make = CarMake.VW;
                break;
            case "Audi":
                make = CarMake.Audi;
                break;
            case "BMW":
                make = CarMake.BMW;
                break;
            case "Toyota":
                make = CarMake.Toyota;
                break;
        }
        Resources res = getResources();
        Engine.codes = res.getStringArray(R.array.Engines);
        Engine.oils = res.getStringArray(R.array.Oils);
        Engine.oilLongevity = Arrays.stream(res.getIntArray(R.array.OilLongevity)).boxed().toArray( Integer[]::new );
        Engine.distributions = res.getStringArray(R.array.Distributions);
        Engine.distributionLongevity = Arrays.stream(res.getIntArray(R.array.DistributionLongevity)).boxed().toArray( Integer[]::new );


        String engineCode = engineSpinner.getSelectedItem().toString();
        Engine engine = new Engine(engineCode);
        String model = modelSpinner.getSelectedItem().toString();
        if(car == null ||  (make != car.getMake() || engine.compareTo(car.getEngine()) != 0) || (car.getModel() != model))
        {
            car = new Car(make, engine, model);
        }

        Intent homeScreen = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(homeScreen);
    }

    public static void updateInsurance(String name, Date startDate, Date endDate)
    {
        Boolean found = false;
        for(MandatoryInsurance insurance : car.getInsurances())
        {
            if(Objects.equals(insurance.getName(), name))
            {
                found = true;
                insurance.setStartDate(startDate);
                insurance.setEndDate(endDate);
            }
        }
        if(!found)
        {
            MandatoryInsurance newInsurance = new MandatoryInsurance(name, startDate, endDate);
            car.getInsurances().add(newInsurance);
        }
    }

    public static Integer getInsuranceByName(String name)
    {
        Integer rez = -1;
        Integer cnt = 0;
        for(MandatoryInsurance insurance : car.getInsurances())
        {
            if(Objects.equals(insurance.getName(), name))
            {
                rez = cnt;
                break;
            }
            cnt++;
        }
        return rez;
    }

    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(configFilePath, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Log.d("FILEWRITE:", "SUCCES");
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput(configFilePath);


            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    private void saveDataToFile()
    {
        if(car != null) {
            Gson gson = new Gson();
            String savedDate = gson.toJson(car);
            writeToFile(savedDate);
        }
    }

    private void updateFromConfigFile()
    {
        Gson gson = new Gson();
        String data = readFromFile();
        try {
            Log.d("TRIE TO LOAD FROM JSON", "OUTGOING");
            car = gson.fromJson(data, Car.class);
            Log.d("LOADING FROM JSON", "DONE");
            carMakeSpinner.setSelection(carMakeAdapter.getPosition(car.getMake().toString()), true);
            engineSpinner.setSelection(engineAdapter.getPosition(car.getEngine().getCode()), true);
            Log.d("CAR MODEL IS:", car.getModel());
            Log.d("CAR MAKE IS:", car.getMake().toString());
            Log.d("ENGINE CODE IS:", car.getEngine().getCode());

        }
        catch (Exception e)
        {
            Log.e("LOADING FROM JSON", "FAILED");
        }

    }
    @Override
    protected void onPause(){
        super.onPause();
        saveDataToFile();
    }

    @Override
    protected  void onStop() {
        super.onStop();
        saveDataToFile();
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        saveDataToFile();
    }

    @Override
    protected  void onResume() {
        super.onResume();
        saveDataToFile();
    }

    @Override
    public void onBackPressed() {
        saveDataToFile();
        finish();

    }

}