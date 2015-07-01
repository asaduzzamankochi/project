package com.asaduzzamankochi.myhealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.asaduzzamankochi.DB.DBHelper;
import com.asaduzzamankochi.appointments.MyAppointmentList;
import com.asaduzzamankochi.icare.R;
import com.asaduzzamankochi.modelClass.Profile;

/**
 * Created by Mobile App Develop on 10-6-15.
 */
public class MyHealth extends ActionBarActivity {
    private int id;
    private Button btnDoctor;
    private Profile profile = new Profile();
    private DBHelper dbHelper;
    private String category = "M";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_health);
        btnDoctor = (Button)findViewById(R.id.btnDoctor);
        btnDoctor.setText("My Doctor");
        dbHelper = new DBHelper(MyHealth.this);
        profile = dbHelper.showProfile(category);
        id = profile.getId();
        //sToast.makeText(getApplicationContext(),id+"",Toast.LENGTH_LONG).show();
    }

    public void btnProfileInformation(View v) {

        Intent intent = new Intent(MyHealth.this, MyProfileInformation.class);
        intent.putExtra("id", id);
        startActivity(intent);

    }
    public void btnMyPersonalDoctor(View v) {

        Intent intent = new Intent(MyHealth.this, MyPersonalDoctor.class);
        intent.putExtra("id", id);
        startActivity(intent);

    }
    public void btnMyAppointment(View v) {

        Intent intent = new Intent(MyHealth.this, MyAppointmentList.class);
        intent.putExtra("id", id);
        startActivity(intent);

    }
}
