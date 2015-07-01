package com.asaduzzamankochi.appointments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.asaduzzamankochi.DB.DBHelper;
import com.asaduzzamankochi.icare.R;
import com.asaduzzamankochi.modelClass.Appointment;
import com.asaduzzamankochi.modelClass.Doctor;

/**
 * Created by kochi on 30-Jun-15.
 */
public class ShowAppointments extends ActionBarActivity {
    private TextView txtDoctor;
    private TextView txtDate;
    private TextView txtTime;
    private Spinner spinner;
    private Button btnAdd;
    private Button btnEdit;
    private Button btnUpdate;
    private Button btnDelete;

    private int id;
    private int idDoctor;
    private int idProfile;
    private String doctorName;
    private String date;
    private String time;

    private Appointment appointment = new Appointment();
    private Doctor doctor = new Doctor();
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_appointment);
        txtDoctor = (TextView) findViewById(R.id.txtDoctor);
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtTime = (TextView) findViewById(R.id.txtTime);
        spinner = (Spinner) findViewById(R.id.spinner);
        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnEdit = (Button) findViewById(R.id.buttonEdit);
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);
        btnDelete = (Button) findViewById(R.id.buttonDelete);
        dbHelper = new DBHelper(ShowAppointments.this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        appointment = dbHelper.showAppointment(id);
        idDoctor = appointment.getIdDoctor();
        idProfile = appointment.getIdProfile();
        doctor = dbHelper.showDoctorInformation(idDoctor);

        doctorName = doctor.getName();
        date = appointment.getDate();
        time = appointment.getTime();

        spinner.setVisibility(View.GONE);
        txtDoctor.setVisibility(View.VISIBLE);
        txtDate.setVisibility(View.VISIBLE);
        txtTime.setVisibility(View.VISIBLE);
        btnAdd.setVisibility(View.GONE);
        btnEdit.setVisibility(View.VISIBLE);
        btnDelete.setVisibility(View.VISIBLE);

        txtDoctor.setText(doctorName);
        txtDate.setText(date);
        txtTime.setText(time);
    }


    public void editAppointment(View v) {
        Bundle b = new Bundle();
        b.putInt("update", -5);
        b.putInt("id", id);
        b.putInt("idProfile",idProfile);
        Intent intent = new Intent(ShowAppointments.this, AddMyAppointment.class);
        intent.putExtras(b);
        startActivity(intent);
    }


    public void deleteAppointment(View v) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete");
        alertDialogBuilder.setMessage("Are you sure want to delete ?");
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dbHelper.deleteAppointment(id)) {
                    Toast.makeText(getApplicationContext(), "Delete Success", Toast.LENGTH_LONG).show();
                    Bundle b = new Bundle();
                    b.putInt("id",idProfile);
                    Intent intent = new Intent(ShowAppointments.this, MyAppointmentList.class);
                    intent.putExtras(b);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Delete Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", null);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();




    }

}
