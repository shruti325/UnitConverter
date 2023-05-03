package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;


public class ConvertOptionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_option);

        LinearLayout weightConverter = findViewById(R.id.weightConverter);
        LinearLayout lengthConverter = findViewById(R.id.lengthConverter);
        LinearLayout timeConverter = findViewById(R.id.timeConverter);
        LinearLayout temperatureConverter = findViewById(R.id.temperatureConverter);


        weightConverter.setOnClickListener(v -> {
            Intent intent = new Intent(this, WeightConverterActivity.class);
            startActivity(intent);
        });
        lengthConverter.setOnClickListener(v -> {
            Intent intent = new Intent(this, LengthConverterActivity.class);
            startActivity(intent);
        });
        temperatureConverter.setOnClickListener(v -> {
            Intent intent = new Intent(this, TemperatureConverterActivity.class);
            startActivity(intent);
        });
        timeConverter.setOnClickListener(v -> {
            Intent intent = new Intent(this, TimeConverterActivity.class);
            startActivity(intent);
        });


    }
}
