package com.asaduzzamankochi.myhealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.asaduzzamankochi.DB.DBHelper;
import com.asaduzzamankochi.icare.R;
import com.asaduzzamankochi.modelClass.Appointment;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by kochi on 29-Jun-15.
 */
public class MyAppointmentList extends ActionBarActivity {
    private Button btnToday;
    private Button btnUpcoming;
    private TextView txtToday;
    private TextView txtUpcoming;
    private ListView listViewToday;
    private ListView listViewUpcoming;

    int todayFlag = 0;
    int upcomingFlag = 0;

    private ArrayList<Appointment> todayAppointment = new ArrayList<Appointment>();
    private ArrayList<Appointment> upcomingAppointment = new ArrayList<Appointment>();
    private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    private ArrayAdapter<Appointment> todayAdapter;
    private ArrayAdapter<Appointment> upcomingAdapter;
    private DBHelper dbHelper;
    private int idProfile;

    private Calendar calendar;
    private int year;
    private int month;
    private int day;
    private String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_list);
        btnToday = (Button) findViewById(R.id.buttonToday);
        btnUpcoming = (Button) findViewById(R.id.buttonUpcoming);
        txtToday = (TextView) findViewById(R.id.textViewTodayMessage);
        txtUpcoming = (TextView) findViewById(R.id.textViewUpcomingMessage);
        listViewToday = (ListView) findViewById(R.id.listViewToday);
        listViewUpcoming = (ListView) findViewById(R.id.listViewUpcoming);
        registerForContextMenu(listViewToday);
        registerForContextMenu(listViewUpcoming);
        currentDate = getCurrentDate();

        dbHelper = new DBHelper(MyAppointmentList.this);

        Intent intent = getIntent();
        idProfile = intent.getIntExtra("id", -1);
        appointments = dbHelper.showAppointmentList(idProfile);
        for (Appointment object : appointments) {
            if (object.getDate().equals(currentDate)) {
                todayAppointment.add(object);
            } else {
                upcomingAppointment.add(object);
            }
        }

        todayAdapter = new ArrayAdapter<Appointment>(this, R.layout.doctor_list_view_center, R.id.txtNameView, todayAppointment);
        listViewToday.setAdapter(todayAdapter);

        listViewToday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int item = todayAppointment.get(position).getId();
                Bundle b = new Bundle();
                b.putInt("id", item);
//                Toast.makeText(getApplicationContext(), idPD + item + itemName + " Selected !!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MyAppointmentList.this, ShowAppointments.class);
                intent.putExtras(b);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), itemName + " Selected !!", Toast.LENGTH_LONG).show();

            }
        });

        upcomingAdapter = new ArrayAdapter<Appointment>(this, R.layout.doctor_list_view_center, R.id.txtNameView, upcomingAppointment);
        listViewUpcoming.setAdapter(upcomingAdapter);

        listViewUpcoming.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int item = upcomingAppointment.get(position).getId();
                Bundle b = new Bundle();
                b.putInt("id", item);
//                Toast.makeText(getApplicationContext(), idPD + item + itemName + " Selected !!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MyAppointmentList.this, ShowAppointments.class);
                intent.putExtras(b);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), itemName + " Selected !!", Toast.LENGTH_LONG).show();

            }
        });


    }

    public String getCurrentDate() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        month++;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        Toast.makeText(getApplicationContext(), stringCheck(day) + "-" + stringCheck(month) + "-" + year, Toast.LENGTH_LONG).show();
        return stringCheck(day) + "-" + stringCheck(month) + "-" + year;
    }

    public String stringCheck(int i) {
        String string = Integer.toString(i);
        if (string.length() < 2) {
            string = "0" + string;
        }
        return string;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.doctor_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Bundle b = new Bundle();
            b.putInt("idProfile", idProfile);
            Intent intent = new Intent(MyAppointmentList.this, AddMyAppointment.class);
            intent.putExtras(b);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showTodayList(View v) {

        if (todayFlag == 0) {

            if (todayAppointment.isEmpty()) {
                txtToday.setVisibility(View.VISIBLE);
                txtToday.setText("No Appointments Today!!");
            }
            listViewToday.setVisibility(View.VISIBLE);
            todayFlag = 1;
        } else if (todayFlag == 1) {
            txtToday.setVisibility(View.INVISIBLE);
            listViewToday.setVisibility(View.INVISIBLE);
            todayFlag = 0;
        }

    }

    public void showUpcomingList(View v) {
        if (upcomingFlag == 0) {

            if (upcomingAppointment.isEmpty()) {
                txtUpcoming.setVisibility(View.VISIBLE);
                txtUpcoming.setText("No Upcoming Appointments!!");
            }

            listViewUpcoming.setVisibility(View.VISIBLE);
            upcomingFlag = 1;
        } else if (upcomingFlag == 1) {
            txtUpcoming.setVisibility(View.INVISIBLE);
            listViewUpcoming.setVisibility(View.INVISIBLE);
            upcomingFlag = 0;
        }
    }


}
