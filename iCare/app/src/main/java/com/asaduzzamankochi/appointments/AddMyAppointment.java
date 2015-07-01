package com.asaduzzamankochi.appointments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.asaduzzamankochi.DB.DBHelper;
import com.asaduzzamankochi.icare.R;
import com.asaduzzamankochi.modelClass.Appointment;
import com.asaduzzamankochi.modelClass.Doctor;
import com.asaduzzamankochi.modelClass.Profile;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by kochi on 29-Jun-15.
 */
public class AddMyAppointment extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private TextView txtDoctor;
    private TextView txtDate;
    private TextView txtTime;
    private Spinner spinner;
    private Button btnAdd;
    private Button btnEdit;
    private Button btnUpdate;
    private Button btnDelete;

    private Appointment appointment;
    private Doctor doctor;
    private String date;
    private String time;
    private String name;
    private int idPD;
    private Calendar calendar;
    private int year, month, day;
    private int hour, minute;
    private String format;

    private static DBHelper dbHelper;
    private ArrayAdapter<Doctor> listAdapter;
    private ArrayList<Doctor> doctorName = new ArrayList<Doctor>();
    private ArrayList<Profile> profileData = new ArrayList<Profile>();
    private Profile profile = new Profile();
    private String category = "M";

    private int idProfile;
    private int idDoctor;
    private int id;
    private int update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_appointment);
        txtDoctor = (TextView) findViewById(R.id.txtDoctor);
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtTime = (TextView) findViewById(R.id.txtTime);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnEdit = (Button) findViewById(R.id.buttonEdit);
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);
        btnDelete = (Button) findViewById(R.id.buttonDelete);

        appointment = new Appointment();

        registerForContextMenu(spinner);
        dbHelper = new DBHelper(AddMyAppointment.this);

//        Intent intent = getIntent();
//        idProfile = intent.getIntExtra("id", -1);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        id = b.getInt("id");
        update = b.getInt("update");
        idProfile = b.getInt("idProfile");

        doctorName = dbHelper.showPersonalDoctorList(idProfile);

        listAdapter = new ArrayAdapter<Doctor>(this,
                android.R.layout.simple_spinner_item, doctorName);
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(listAdapter);
        if (update==-5){
            updateMood();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        idDoctor = doctorName.get(position).getId();
        name = doctorName.get(position).getName();
//        Toast.makeText(getApplicationContext(), "Field's could not empty"+ idPD, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void dateView(View v) {
        showDialog(0);
    }

    public void timeView(View v) {
        showDialog(1);
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                return new DatePickerDialog(this,
                        mDateSetListener,
                        year, month, day);
            case 1:
                return new TimePickerDialog(this,
                        mTimeSetListener, hour, minute, false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener
            = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth + 1;
            day = selectedDay;
            date = stringCheck(day) + "-" + stringCheck(month) + "-" + year;
            appointment.setDate(date);
            txtDate.setText(date);
        }
    };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;
                    //time = hour + " : " + time;
                    //appointment.setTime(time);
                    showTime(hour, minute);
//                    txtTime.setText(time);
                }
            };

    private int getIndex(Spinner spinner, String myString) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void addAppointment(View v) {

        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setIdProfile(idProfile);
        appointment.setIdDoctor(idDoctor);
        dbHelper.insertAppointment(appointment);
        Intent intent = new Intent(AddMyAppointment.this, MyAppointmentList.class);
        intent.putExtra("id", idProfile);
        startActivity(intent);
    }

    public void updateAppointment(View v){

        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setIdProfile(idProfile);
        appointment.setIdDoctor(idDoctor);

        dbHelper.updateAppointment(appointment);

        Intent intent = new Intent(AddMyAppointment.this, MyAppointmentList.class);
        intent.putExtra("id", idProfile);
        startActivity(intent);
    }

    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

        txtTime.setText(new StringBuilder().append(stringCheck(hour)).append(" : ").append(stringCheck(min))
                .append(" ").append(format));
        time = txtTime.getText().toString();
    }

    public void updateMood() {
        btnAdd.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.VISIBLE);
        btnEdit.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);

        appointment = dbHelper.showAppointment(id);
        idDoctor = appointment.getIdDoctor();
        doctor = dbHelper.showDoctorInformation(idDoctor);

        name = doctor.getName();
        date = appointment.getDate();
        time = appointment.getTime();
//        Toast.makeText(getApplicationContext(),name+ " "+date, Toast.LENGTH_LONG).show();
        spinner.setSelection(getIndex(spinner,name));
        txtDate.setText(date);
        txtTime.setText(time);

    }

    public String stringCheck(int i){
        String string = Integer.toString(i);
        if (string.length()<2){
            string = "0"+string;
        }
        return string;
    }



}
