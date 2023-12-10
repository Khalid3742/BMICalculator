package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

android.widget.Button mcalbmi;
TextView mcurrentheight,currentweight,currentage;
ImageView mincweight,mincage,mdecweight,mdecage;
SeekBar mseekbar;
RelativeLayout mmale,mfemale;
    int intweight=55;
int intage=22;
int currentprogress;
String mintprogress= "170";
String typeofuser= "0";
String weight2="55";
String age2= "22";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mcalbmi = findViewById(R.id.calbmi);
        currentweight = findViewById(R.id.currentweight);
        mcurrentheight= findViewById(R.id.currentheight);
        currentage = findViewById(R.id.currentage);
        mincage = findViewById(R.id.inage);
        mdecage = findViewById(R.id.deage);
        mincweight= findViewById(R.id.inweight);
        mdecweight = findViewById(R.id.deweight);
        mseekbar = findViewById(R.id.seekbarforheight);
        mmale = findViewById(R.id.male);
        mfemale = findViewById(R.id.female);

        mmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser = "Male";
            }
        });

        mfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser = "Female";
            }
        });

        mseekbar.setMax(300);
        mseekbar.setProgress(170);
        mseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentprogress = progress;
                mintprogress = String.valueOf(currentprogress);
                mcurrentheight.setText(mintprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mincage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intage= intage+1;
                age2 = String.valueOf(intage);
                currentage.setText(age2);

            }
        });

        mdecage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intage= intage-1;
                age2 = String.valueOf(intage);
                currentage.setText(age2);

            }
        });

        mincweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intweight= intweight+1;
                weight2 = String.valueOf(intweight);
                currentweight.setText(weight2);

            }
        });

        mdecweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intweight= intweight-1;
                weight2 = String.valueOf(intweight);
                currentweight.setText(weight2);

            }
        });

        mcalbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(typeofuser.equals("0"))
                {
                    Toast.makeText(getApplicationContext(),"Select Your Gender First",Toast.LENGTH_SHORT).show();
                }
                else if (mintprogress.equals("0")) {
                    Toast.makeText(getApplicationContext(),"Select Your Height First",Toast.LENGTH_SHORT).show();
                }
                else if (intage==0 || intage<0) {
                    Toast.makeText(getApplicationContext(),"Age is Incorrect",Toast.LENGTH_SHORT).show();
                } else if (intweight==0 || intweight<0) {
                    Toast.makeText(getApplicationContext(),"Weight is Incorrect",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(MainActivity.this, BmiActivity.class);
                    i.putExtra("gender", typeofuser);
                    i.putExtra("height",mintprogress);
                    i.putExtra("weight",weight2);
                    i.putExtra("age", age2);
                    startActivity(i);
                }
            }
        });
    }
}
