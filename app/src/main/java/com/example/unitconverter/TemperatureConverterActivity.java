package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TemperatureConverterActivity extends AppCompatActivity {

    private TextView keypad1;
    private TextView keypad2;
    private TextView keypad3;
    private TextView keypad4;
    private TextView keypad5;
    private TextView keypad6;
    private TextView keypad7;
    private TextView keypad8;
    private TextView keypad9;
    private TextView keypad0;
    private TextView keypadDot;
    private ImageButton backspace;
    private EditText inputFrom;
    private EditText inputTo;
    private Spinner unitFrom;
    private Spinner unitTo;
    private Toolbar toolbar;
    private int selectedUnitFrom=0;
    private int selectedUnitTo=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        keypad1 = findViewById(R.id.keypad1);
        keypad2 = findViewById(R.id.keypad2);
        keypad3 = findViewById(R.id.keypad3);
        keypad4 = findViewById(R.id.keypad4);
        keypad5 = findViewById(R.id.keypad5);
        keypad6 = findViewById(R.id.keypad6);
        keypad7 = findViewById(R.id.keypad7);
        keypad8 = findViewById(R.id.keypad8);
        keypad9 = findViewById(R.id.keypad9);
        keypad0 = findViewById(R.id.keypad0);
        keypadDot = findViewById(R.id.keypadDot);
        backspace = findViewById(R.id.backspace);
        inputFrom = findViewById(R.id.input_from);
        inputTo = findViewById(R.id.input_to);
        unitFrom = findViewById(R.id.unit_from);
        unitTo = findViewById(R.id.unit_to);

        inputFrom.setShowSoftInputOnFocus(false);
        inputTo.setShowSoftInputOnFocus(false);

        keypad1.setOnClickListener(v -> {
            inputTo.append("1");
        });
        keypad2.setOnClickListener(v -> {
            inputTo.append("2");
        });
        keypad3.setOnClickListener(v -> {
            inputTo.append("3");
        });
        keypad4.setOnClickListener(v -> {
            inputTo.append("4");
        });
        keypad5.setOnClickListener(v -> {
            inputTo.append("5");
        });
        keypad6.setOnClickListener(v -> {
            inputTo.append("6");
        });
        keypad7.setOnClickListener(v -> {
            inputTo.append("7");
        });
        keypad8.setOnClickListener(v -> {
            inputTo.append("8");
        });
        keypad9.setOnClickListener(v -> {
            inputTo.append("9");
        });
        keypad0.setOnClickListener(v -> {
            inputTo.append("0");
        });

        keypadDot.setOnClickListener(v -> {
            String text = inputTo.getText().toString();
            if (!text.isEmpty() && !text.contains(".")){
                inputTo.append(".");
            }
        });

        unitFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUnitFrom=position;
                convertInputToSelectedUnit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        unitTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUnitTo=position;
                convertInputToSelectedUnit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        backspace.setOnClickListener(v -> {
            final Editable editable = inputTo.getText();
            if (!editable.toString().isEmpty()){
                editable.delete(editable.toString().length() - 1,editable.length());
            }
        });

        inputTo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                convertInputToSelectedUnit();
            }
        });



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    double convert(double number){
        switch(selectedUnitFrom){
            case 0: switch (selectedUnitTo){
                case 0: return number;
                case 1: return (number*1.8)+32;
                case 2: return number+273.15;
            }
            case 1: switch (selectedUnitTo){
                case 0: return (number-32)*0.55;
                case 1 : return number;
                case 2 : return (number-32)*0.55+273.15;
            }
            case 2: switch (selectedUnitTo){
                case 0: return number-273.15;
                case 1: return (number-273.15)*1.8+32;
                case 2: return number;
            }
            default: return number;

        }

    }

    /**
     * Convert current input text to selected unit
     */
    void  convertInputToSelectedUnit(){
        final String text = inputTo.getText().toString();
        if (!text.isEmpty()){
            try {
                double number = Double.parseDouble(text);
                number = convert(number);

                inputFrom.setText(String.valueOf(number));
            }catch (NumberFormatException e){
                Toast.makeText(TemperatureConverterActivity.this, "Invalid input.", Toast.LENGTH_SHORT).show();
            }

        }else {
            inputFrom.setText("");
        }
    }

}
