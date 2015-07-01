package com.asaduzzamankochi.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.asaduzzamankochi.DB.DBHelper;
import com.asaduzzamankochi.careCenter.CareCenterList;
import com.asaduzzamankochi.doctor.DoctorList;
import com.asaduzzamankochi.familyhealth.FamilyMemberList;
import com.asaduzzamankochi.icare.R;
import com.asaduzzamankochi.modelClass.Profile;
import com.asaduzzamankochi.myhealth.MyHealth;
import com.asaduzzamankochi.myhealth.MyProfileInformation;

import java.util.ArrayList;

/**
 * Created by kochi on 19-Jun-15.
 */
public class HomePage extends Activity {

    private LinearLayout mainLayout;
    private LinearLayout secondaryLayout;

    private DBHelper dbHelper;
    private ArrayList<Profile> profileData = new ArrayList<Profile>();


    private String category = "M";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        secondaryLayout = (LinearLayout) findViewById(R.id.secondary_layout);

        dbHelper = new DBHelper(HomePage.this);

        profileData = dbHelper.showFamilyList(category);

        if (profileData.isEmpty()) {

            secondaryLayout.setVisibility(View.VISIBLE);
            mainLayout.setVisibility(View.GONE);

        }
    }

    public void btnAddNew(View v) {
//        secondaryLayout.setVisibility(View.GONE);
//        mainLayout.setVisibility(View.VISIBLE);
        Bundle b = new Bundle();
        b.putInt("home", -5);

        Intent intent = new Intent(HomePage.this, MyProfileInformation.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void btnMyHealth(View v) {
        Intent intent = new Intent(HomePage.this, MyHealth.class);
        startActivity(intent);

    }

    public void btnFamilyHealth(View v) {
        Intent intent = new Intent(HomePage.this, FamilyMemberList.class);
        startActivity(intent);

    }

    public void doctorList(View v) {
        Intent intent = new Intent(HomePage.this, DoctorList.class);
        startActivity(intent);
    }

    public void careCenterList(View v) {
        Intent intent = new Intent(HomePage.this, CareCenterList.class);
        startActivity(intent);
    }


}
