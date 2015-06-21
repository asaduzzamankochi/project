package com.asaduzzamankochi.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.asaduzzamankochi.doctor.DoctorList;
import com.asaduzzamankochi.icare.R;
import com.asaduzzamankochi.myhealth.MyHealth;

/**
 * Created by kochi on 19-Jun-15.
 */
public class HomePage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
    }

    public void btnMyHealth(View v) {
        Intent intent = new Intent(HomePage.this, MyHealth.class);
        startActivity(intent);

    }

    public void doctorList(View v){
        Intent intent = new Intent(HomePage.this, DoctorList.class);
        startActivity(intent);
    }


}
