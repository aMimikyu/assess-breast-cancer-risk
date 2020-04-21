package com.example.breastCancerProjectIonio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String key = "ABF3E";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAboutAppClick(View view){
        Intent intent = new Intent(this,InfoActivity.class);
        intent.putExtra(key,1);
        startActivity(intent);
    }

    public void onRiskFactorsClick(View view){
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra(key,2);
        startActivity(intent);
    }

    public void onAboutCancerClick(View view){
        Intent intent = new Intent(this,InfoActivity.class);
        intent.putExtra(key,3);
        startActivity(intent);
    }
    public void onAssessRiskClick(View view){
        Intent intent = new Intent(this,InfoActivity.class);
        intent.putExtra(key,4);
        startActivity(intent);
    }
}
