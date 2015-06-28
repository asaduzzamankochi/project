package com.asaduzzamankochi.myhealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.asaduzzamankochi.icare.R;

/**
 * Created by Mobile App Develop on 10-6-15.
 */
public class MyHealth extends ActionBarActivity {
    private Button btnDoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_health);
        btnDoctor = (Button)findViewById(R.id.btnDoctor);
        btnDoctor.setText("My Doctor");
    }

    public void btnProfileInformation(View v) {

        Intent intent = new Intent(MyHealth.this, MyProfileInformation.class);
        startActivity(intent);

    }
    public void btnMyPersonalDoctor(View v) {

        Intent intent = new Intent(MyHealth.this, MyPersonalDoctor.class);
        startActivity(intent);

    }
}
