package com.example.calculator;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class BmiActivity extends AppCompatActivity {
    ImageView imageView;
    TextView bmiDisplay, genderDisplay, bmiCategory;
    android.widget.Button mrecalbmi;
    Intent intent;

    String height,weight, gender;

    float calculatedBmi,userWeightKg,userHeightMeters;
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        imageView = findViewById(R.id.imageview);
        bmiDisplay = findViewById(R.id.bmidisplay);
        genderDisplay = findViewById(R.id.genderdisplay);
        bmiCategory = findViewById(R.id.bmicategory);
        mrecalbmi = findViewById(R.id.recalbmi);

        intent = getIntent();

        gender = intent.getStringExtra("gender");
        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");


        Log.d("BmiActivity", "Received values - gender: " + gender + ", height: " + height + ", weight: " + weight);

        if (weight != null && !weight.isEmpty()) {
            userWeightKg = Float.parseFloat(weight.trim());
        } else {
            Log.e("BmiActivity", "Weight is null or empty");
            userWeightKg = 0.0f;
        }

        if (height != null && !height.isEmpty()) {
            userHeightMeters = Float.parseFloat(height.trim());
        } else {
            Log.e("BmiActivity", "Height is null or empty");
            // Handle the case where "height" is null or empty
            userHeightMeters = 0.0f;
        }

        Log.d("BmiActivity", "Parsed values - userWeightKg: " + userWeightKg + ", userHeightMeters: " + userHeightMeters);
        calculatedBmi =  calculateBmi(userWeightKg, userHeightMeters);


        bmiDisplay.setText(String.format("%.1f", calculatedBmi));

        genderDisplay.setText(gender);

        String calculatedBmiCategory = getBmiCategory(calculatedBmi);
        bmiCategory.setText(calculatedBmiCategory);


        mrecalbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BmiActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
    private float calculateBmi(float weightKg, float heightMeters) {
        if (heightMeters <= 0) {
            return 0;
        }
        return weightKg / (heightMeters * heightMeters);
    }


    private String getBmiCategory(float bmiValue) {
        if (bmiValue < 18.5) {
            return "Underweight";
        } else if (bmiValue < 24.9) {
            return "Normal Weight";
        } else if (bmiValue < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}