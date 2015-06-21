package com.asaduzzamankochi.myhealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.asaduzzamankochi.icare.R;

/**
 * Created by Mobile App Develop on 10-6-15.
 */
public class MyHealth extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_health);
    }

    public void btnProfileInformation(View v) {

        Intent intent = new Intent(MyHealth.this, MyProfileInformation.class);
        startActivity(intent);

    }
}
