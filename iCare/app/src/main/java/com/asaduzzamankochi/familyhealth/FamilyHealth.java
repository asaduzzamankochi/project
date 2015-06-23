package com.asaduzzamankochi.familyhealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.asaduzzamankochi.icare.R;
import com.asaduzzamankochi.myhealth.MyProfileInformation;

/**
 * Created by kochi on 23-Jun-15.
 */
public class FamilyHealth extends ActionBarActivity {
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_health);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }

    public void btnProfileInformation(View v) {

        Intent intent = new Intent(FamilyHealth.this, FamilyMemberInformation.class);
        intent.putExtra("id", id);
        startActivity(intent);

    }
}
