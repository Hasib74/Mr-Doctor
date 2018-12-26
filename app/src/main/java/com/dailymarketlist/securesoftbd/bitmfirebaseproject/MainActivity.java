package com.dailymarketlist.securesoftbd.bitmfirebaseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button showDoctorButton,addDoctorButton, updateDoctorButton,addMedicalButton,showMedicalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilaization();
        onClick();

    }

    private void onClick()
    {
        addDoctorButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this,AddDoctorActivity.class).putExtra("Data","add"));
            }
        });

        addMedicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddMedicalDetailsActivity.class));
            }
        });


        showDoctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this,ShowDoctorActivity.class));
            }
        });

        showMedicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ShowMedicalActivity.class));
            }
        });

        updateDoctorButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this,UpdateDoctorInformationActivity.class));
            }
        });

    }

    private void initilaization()
    {
        showDoctorButton = findViewById(R.id.showDoctorBtnId);
        addDoctorButton = findViewById(R.id.addDoctorButtonId);
        showMedicalButton = findViewById(R.id.showMedicalButtonId);
        addMedicalButton = findViewById(R.id.addMedicalButtonId);
        updateDoctorButton = findViewById(R.id.aboutId);

    }
}