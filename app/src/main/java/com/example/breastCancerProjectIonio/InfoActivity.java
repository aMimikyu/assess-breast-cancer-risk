package com.example.breastCancerProjectIonio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this,
               drawerLayout,R.string.open_drawer,R.string.close_drawer);

        drawerLayout.setDrawerListener(drawerToggle);
        Intent intent = getIntent();
        int val = intent.getIntExtra(MainActivity.key,-1);
        initiateFragment(val);
    }

    private void initiateFragment(int val){
        Fragment fragment;
        switch(val){
            case 1:
                fragment = new AboutAppFragment();
                break;
            case 2:
                fragment = new RiskFactorsFragment();
                break;
            case 3:
                fragment = new AboutCancerFragment();
                break;
            case 4:
                fragment = new AssessRiskFragment();
                break;
            case 5:
                fragment = new PositiveResultFragment();
                break;
            case 6:
                fragment = new NegativeResultFragment();
                break;
            default:
                Toast toast = Toast.makeText(this,"Something went wrong",Toast.LENGTH_LONG);
                toast.show();
                return;
        }
        drawerLayout.closeDrawers();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }

    public void onHomeClick(MenuItem item){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void onAboutAppClick(MenuItem item){
        initiateFragment(1);
    }
    public void onRiskFactorsClick(MenuItem item){
        initiateFragment(2);
    }
    public void onAboutCancerClick(MenuItem item){
        initiateFragment(3);
    }
    public void onAssessRiskClick(MenuItem item){
        initiateFragment(4);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    public void onResultButtonClick(View view){
        EditText glucoseText = findViewById(R.id.glucose_edit_text);
        EditText ageText = findViewById(R.id.age_edit_text);
        EditText bmiText = findViewById(R.id.bmi_edit_text);
        EditText resistinText = findViewById(R.id.resistin_edit_text);

        boolean filledAll = true;
        if(glucoseText.length()==0){
            glucoseText.setError("Enter Glucose Levels");
            filledAll=false;
        }
        if(ageText.length()==0){
            ageText.setError("Enter your age");
            filledAll=false;
        }
        if(bmiText.length()==0){
            bmiText.setError("Enter BMI");
            filledAll=false;
        }
        if(resistinText.length()==0){
            resistinText.setError("Enter resistin Levels");
            filledAll=false;
        }
        if(filledAll){
            double glucose = Double.parseDouble(glucoseText.getText().toString());
            int age = Integer.parseInt(ageText.getText().toString());
            double bmi = Double.parseDouble(bmiText.getText().toString());
            double resistin = Double.parseDouble(resistinText.getText().toString());


            if(glucose>91){
                if(age<=48){
                   initiateFragment(5); //glucose >91 && age <=48 high risk
                }
                else{
                    if(glucose>118){    //if glucose>118 && age >48 high risk
                        initiateFragment(5);
                    }
                    else{
                        if(age>72){
                            if(bmi>29.154519){      //if glucose >91 && age>72 && bmi > 29 high risk
                                initiateFragment(5);
                            }
                            else{                   //if glucose >91 && age>72 && bmi < 29 low risk
                                initiateFragment(6);
                            }
                        }
                        else{
                            if(bmi>32.270788){
                                initiateFragment(6);
                            }
                            else{
                                initiateFragment(5);
                            }
                        }
                    }
                }
            }
            else{
                if(bmi>30.915577){
                    initiateFragment(6);
                }
                else{
                    if(resistin<=12.9361){
                        initiateFragment(6);    //low risk
                    }
                    else{
                        initiateFragment(5);
                    }
                }

            }
        }



    }

    public void onInfoButtonClick(View view){

    }
}
