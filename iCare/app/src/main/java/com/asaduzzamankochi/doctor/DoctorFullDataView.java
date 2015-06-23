package com.asaduzzamankochi.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asaduzzamankochi.DB.DBHelper;
import com.asaduzzamankochi.icare.R;
import com.asaduzzamankochi.modelClass.Doctor;

import java.util.ArrayList;

/**
 * Created by kochi on 20-Jun-15.
 */
public class DoctorFullDataView extends ActionBarActivity {

    private Button btnSubmit;
    private Button btnEdit;
    private Button btnUpdate;
    private Button btnCancel;

    private EditText edtName;
    private EditText edtSpeciality;
    private EditText edtAddress;
    private EditText edtPhone;
    private EditText edtEmail;
    private EditText edtNotes;

    private TextView txtName;
    private TextView txtSpeciality;
    private TextView txtAddress;
    private TextView txtPhone;
    private TextView txtEmail;
    private TextView txtNotes;

    private String name;
    private String speciality;
    private String address;
    private String phone;
    private String email;
    private String notes;

    private DBHelper dbHelper;
    private ArrayList<Doctor> doctorData = new ArrayList<Doctor>();
    private Doctor doctor = new Doctor();

    int id = -2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_information);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnCancel = (Button) findViewById(R.id.btnDelete);

        edtName = (EditText) findViewById(R.id.edtDocotrName);
        edtSpeciality = (EditText) findViewById(R.id.edtSpeciality);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtPhone = (EditText) findViewById(R.id.edtDoctorPhone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtNotes = (EditText) findViewById(R.id.edtNotes);

        txtName = (TextView) findViewById(R.id.txtViewDoctorName);
        txtSpeciality = (TextView) findViewById(R.id.txtViewSpeciality);
        txtAddress = (TextView) findViewById(R.id.txtViewAddress);
        txtPhone = (TextView) findViewById(R.id.txtViewDoctorPhone);
        txtEmail = (TextView) findViewById(R.id.txtViewEmail);
        txtNotes = (TextView) findViewById(R.id.txtViewNotes);

        dbHelper = new DBHelper(DoctorFullDataView.this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        if (id == -2) {
            informationEditMode();
            btnSubmit.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
        }
        else {
            showProfileData();
        }



    }


    public void addDoctor(View v) {

        getValue();
        if (isFieldEmpty()) {
            Toast.makeText(getApplicationContext(), "Field's could not empty", Toast.LENGTH_LONG).show();

        } else {
            doctor.setName(name);
            doctor.setSpeciality(speciality);
            doctor.setAddress(address);
            doctor.setPhone(phone);
            doctor.setEmail(email);
            doctor.setNotes(notes);

            if (dbHelper.insertDoctorProfile(doctor)) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
//                showProfileData();
//                btnUpdate.setVisibility(View.GONE);
//                btnCancel.setVisibility(View.GONE);
                Intent intent = new Intent(DoctorFullDataView.this, DoctorList.class);
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }


        }

    }

    public void editDoctor(View v) {
        informationEditMode();
        btnSubmit.setVisibility(View.GONE);
        btnEdit.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
    }

    public void updateDoctor(View v) {
        getValue();
        if (isFieldEmpty()) {
            Toast.makeText(getApplicationContext(), "Field's could not empty", Toast.LENGTH_LONG).show();

        } else {
            doctor.setId(id);
            doctor.setName(name);
            doctor.setSpeciality(speciality);
            doctor.setAddress(address);
            doctor.setPhone(phone);
            doctor.setEmail(email);
            doctor.setNotes(notes);
            if (dbHelper.updateDoctorProfile(doctor)) {
                Toast.makeText(getApplicationContext(), "Update Successfull", Toast.LENGTH_LONG).show();
                showProfileData();
                btnUpdate.setVisibility(View.GONE);
                btnCancel.setVisibility(View.GONE);
//                Intent intent = new Intent(MyProfileInformation.this, MyHealth.class);
//                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void deleteDoctor(View v) {

        doctor.setId(id);
        if (dbHelper.deleteDoctorProfile(doctor)) {
            Toast.makeText(getApplicationContext(), "Delete Successfull", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(DoctorFullDataView.this, DoctorList.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Delete Failed", Toast.LENGTH_LONG).show();
        }
    }


    public void showProfileData() {
        // doctorData = dbHelper.showDoctorInformation(id);
        doctor = dbHelper.showDoctorInformation(id);
        // doctor = doctorData.get(0);

        name = doctor.getName();
        speciality = doctor.getSpeciality();
        address = doctor.getAddress();
        phone = doctor.getPhone();
        email = doctor.getEmail();
        notes = doctor.getNotes();

        informationViewMode();

        txtName.setText(name);
        txtSpeciality.setText(speciality);
        txtAddress.setText(address);
        txtPhone.setText(phone);
        txtEmail.setText(email);
        txtNotes.setText(notes);

    }

    public void informationViewMode() {
        txtName.setVisibility(View.VISIBLE);
        txtSpeciality.setVisibility(View.VISIBLE);
        txtAddress.setVisibility(View.VISIBLE);
        txtPhone.setVisibility(View.VISIBLE);
        txtEmail.setVisibility(View.VISIBLE);
        txtNotes.setVisibility(View.VISIBLE);

        edtName.setVisibility(View.GONE);
        edtSpeciality.setVisibility(View.GONE);
        edtAddress.setVisibility(View.GONE);
        edtPhone.setVisibility(View.GONE);
        edtEmail.setVisibility(View.GONE);
        edtNotes.setVisibility(View.GONE);

        btnSubmit.setVisibility(View.GONE);
        btnEdit.setVisibility(View.VISIBLE);

    }

    public void informationEditMode() {
        //set edit text field to editable
        txtName.setVisibility(View.GONE);
        txtSpeciality.setVisibility(View.GONE);
        txtAddress.setVisibility(View.GONE);
        txtPhone.setVisibility(View.GONE);
        txtEmail.setVisibility(View.GONE);
        txtNotes.setVisibility(View.GONE);

        edtName.setVisibility(View.VISIBLE);
        edtSpeciality.setVisibility(View.VISIBLE);
        edtAddress.setVisibility(View.VISIBLE);
        edtPhone.setVisibility(View.VISIBLE);
        edtEmail.setVisibility(View.VISIBLE);
        edtNotes.setVisibility(View.VISIBLE);


        doctor = dbHelper.showDoctorInformation(id);
//        doctorData = dbHelper.showDoctorInformation(id);

//        doctor = doctorData.get(0);

        name = doctor.getName();
        speciality = doctor.getSpeciality();
        address = doctor.getAddress();
        phone = doctor.getPhone();
        email = doctor.getEmail();
        notes = doctor.getNotes();

        edtName.setText(name);
        edtSpeciality.setText(speciality);
        edtAddress.setText(address);
        edtPhone.setText(phone);
        edtEmail.setText(email);
        edtNotes.setText(notes);

        //move the curser to the end of the line
        edtName.setSelection(edtName.getText().length());
        edtSpeciality.setSelection(edtSpeciality.getText().length());
        edtAddress.setSelection(edtAddress.getText().length());
        edtPhone.setSelection(edtPhone.getText().length());
        edtEmail.setSelection(edtEmail.getText().length());
        edtNotes.setSelection(edtNotes.getText().length());

    }


    public boolean isFieldEmpty() {
        if ((name.trim().length() > 0) && (speciality.trim().length() > 0) && (address.trim().length() > 0) && (phone.trim().length() > 0)&& (notes.trim().length() > 0)) {
            return false;
        } else {
            return true;
        }


    }

    public void getValue() {
        name = edtName.getText().toString();
        speciality = edtSpeciality.getText().toString();
        address = edtAddress.getText().toString();
        phone = edtPhone.getText().toString();
        email = edtEmail.getText().toString();
        notes = edtNotes.getText().toString();

    }


}