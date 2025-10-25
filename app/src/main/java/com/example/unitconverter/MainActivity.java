package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Spinner conversionTypeSpinner;
    private EditText inputValue;
    private TextView resultValue;
    private TextView inputUnitLabel;
    private TextView resultUnitLabel;
    private Button convertButton;
    private Button clearButton;

    private String selectedConversionType;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        conversionTypeSpinner = findViewById(R.id.conversionTypeSpinner);
        inputValue = findViewById(R.id.inputValue);
        resultValue = findViewById(R.id.resultValue);
        inputUnitLabel = findViewById(R.id.inputUnitLabel);
        resultUnitLabel = findViewById(R.id.resultUnitLabel);
        convertButton = findViewById(R.id.convertButton);
        clearButton = findViewById(R.id.clearButton);

        // Initialize decimal format for 4 decimal places
        decimalFormat = new DecimalFormat("#.####");

        // Setup spinner
        setupSpinner();

        // Convert button click listener
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });

        // Clear button click listener
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    private void setupSpinner() {
        // Conversion types array
        String[] conversionTypes = {
                "Centimeters to Meters",
                "Meters to Centimeters",
                "Grams to Kilograms",
                "Kilograms to Grams",
                "Inches to Centimeters",
                "Centimeters to Inches",
                "Feet to Meters",
                "Meters to Feet"
        };

        // Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                conversionTypes
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conversionTypeSpinner.setAdapter(adapter);

        // Spinner item selected listener
        conversionTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedConversionType = parent.getItemAtPosition(position).toString();
                updateLabels(selectedConversionType);
                clearFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedConversionType = "Centimeters to Meters";
            }
        });
    }

    private void updateLabels(String conversionType) {
        switch (conversionType) {
            case "Centimeters to Meters":
                inputUnitLabel.setText("Centimeters (cm)");
                resultUnitLabel.setText("Meters (m)");
                break;
            case "Meters to Centimeters":
                inputUnitLabel.setText("Meters (m)");
                resultUnitLabel.setText("Centimeters (cm)");
                break;
            case "Grams to Kilograms":
                inputUnitLabel.setText("Grams (g)");
                resultUnitLabel.setText("Kilograms (kg)");
                break;
            case "Kilograms to Grams":
                inputUnitLabel.setText("Kilograms (kg)");
                resultUnitLabel.setText("Grams (g)");
                break;
            case "Inches to Centimeters":
                inputUnitLabel.setText("Inches (in)");
                resultUnitLabel.setText("Centimeters (cm)");
                break;
            case "Centimeters to Inches":
                inputUnitLabel.setText("Centimeters (cm)");
                resultUnitLabel.setText("Inches (in)");
                break;
            case "Feet to Meters":
                inputUnitLabel.setText("Feet (ft)");
                resultUnitLabel.setText("Meters (m)");
                break;
            case "Meters to Feet":
                inputUnitLabel.setText("Meters (m)");
                resultUnitLabel.setText("Feet (ft)");
                break;
        }
    }

    private void performConversion() {
        String inputText = inputValue.getText().toString().trim();

        // Validate input
        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double input = Double.parseDouble(inputText);
            double result = 0.0;

            // Perform conversion based on selected type
            switch (selectedConversionType) {
                case "Centimeters to Meters":
                    result = input / 100;
                    break;
                case "Meters to Centimeters":
                    result = input * 100;
                    break;
                case "Grams to Kilograms":
                    result = input / 1000;
                    break;
                case "Kilograms to Grams":
                    result = input * 1000;
                    break;
                case "Inches to Centimeters":
                    result = input * 2.54;
                    break;
                case "Centimeters to Inches":
                    result = input / 2.54;
                    break;
                case "Feet to Meters":
                    result = input * 0.3048;
                    break;
                case "Meters to Feet":
                    result = input / 0.3048;
                    break;
            }

            // Display result
            resultValue.setText(decimalFormat.format(result));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        inputValue.setText("");
        resultValue.setText("0.0");
        inputValue.requestFocus();
    }
}
