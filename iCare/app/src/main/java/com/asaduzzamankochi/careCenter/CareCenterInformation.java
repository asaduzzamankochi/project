package com.asaduzzamankochi.careCenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asaduzzamankochi.DB.DBHelper;
import com.asaduzzamankochi.doctor.DoctorList;
import com.asaduzzamankochi.icare.R;
import com.asaduzzamankochi.modelClass.CareCenter;
import com.asaduzzamankochi.modelClass.Doctor;

import java.util.ArrayList;
/**
 * Created by kochi on 01-Jul-15.
 */
public class CareCenterInformation extends ActionBarActivity {

    private Button btnSubmit;
    private Button btnEdit;
    private Button btnUpdate;
    private Button btnCancel;

    private EditText edtName;
    private EditText edtAddress;
    private EditText edtPhone;
    private EditText edtEmail;
    private EditText edtNotes;

    private TextView txtName;
    private TextView txtAddress;
    private TextView txtPhone;
    private TextView txtEmail;
    private TextView txtNotes;

    private String name;
    private String address;
    private String phone;
    private String email;
    private String notes;

    private DBHelper dbHelper;
    private ArrayList<CareCenter> careCenters = new ArrayList<CareCenter>();
    private CareCenter careCenter = new CareCenter();

    int id = -2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.care_center_information);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnCancel = (Button) findViewById(R.id.btnDelete);

        edtName = (EditText) findViewById(R.id.edtDocotrName);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtPhone = (EditText) findViewById(R.id.edtDoctorPhone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtNotes = (EditText) findViewById(R.id.edtNotes);

        txtName = (TextView) findViewById(R.id.txtViewDoctorName);
        txtAddress = (TextView) findViewById(R.id.txtViewAddress);
        txtPhone = (TextView) findViewById(R.id.txtViewDoctorPhone);
        txtPhone.setClickable(true);
        txtEmail = (TextView) findViewById(R.id.txtViewEmail);
        txtEmail.setClickable(true);
        txtNotes = (TextView) findViewById(R.id.txtViewNotes);

        dbHelper = new DBHelper(CareCenterInformation.this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        if (id == -2) {
            informationEditMode();
            btnSubmit.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
        } else {
            showProfileData();
        }


    }


    public void addCareCenter(View v) {

        getValue();
        if (isFieldEmpty()) {
            Toast.makeText(getApplicationContext(), "Field's could not empty", Toast.LENGTH_LONG).show();

        } else {
            careCenter.setName(name);
            careCenter.setAddress(address);
            careCenter.setPhone(phone);
            careCenter.setEmail(email);
            careCenter.setNotes(notes);

            if (dbHelper.insertCareCenter(careCenter)) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CareCenterInformation.this, CareCenterList.class);
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }


        }

    }

    public void editCareCenter(View v) {
        informationEditMode();
        btnSubmit.setVisibility(View.GONE);
        btnEdit.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
    }

    public void updateCareCenter(View v) {
        getValue();
        if (isFieldEmpty()) {
            Toast.makeText(getApplicationContext(), "Field's could not empty", Toast.LENGTH_LONG).show();

        } else {
            careCenter.setId(id);
            careCenter.setName(name);
            careCenter.setAddress(address);
            careCenter.setPhone(phone);
            careCenter.setEmail(email);
            careCenter.setNotes(notes);
            if (dbHelper.updateCareCenter(careCenter)) {
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

    public void deleteCareCenter(View v) {
        careCenter.setId(id);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Are you sure you want to delete? ");
        alertBuilder.setNegativeButton("Cancel", null);
        alertBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Delete function call using DBHelper object
                if (dbHelper.deleteCareCenter(careCenter)) {
                    Toast.makeText(getApplicationContext(), "Delete Successfull", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CareCenterInformation.this, CareCenterList.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Delete Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
//--


    }


    public void showProfileData() {
        careCenter = dbHelper.showCareCenterInformation(id);

        name = careCenter.getName();
        address = careCenter.getAddress();
        phone = careCenter.getPhone();
        email = careCenter.getEmail();
        notes = careCenter.getNotes();

        informationViewMode();

        txtName.setText(name);
        txtAddress.setText(address);
        txtPhone.setText(phone);
        txtEmail.setText(email);
        txtNotes.setText(notes);

    }

    public void informationViewMode() {
        txtName.setVisibility(View.VISIBLE);
        txtAddress.setVisibility(View.VISIBLE);
        txtPhone.setVisibility(View.VISIBLE);
        txtEmail.setVisibility(View.VISIBLE);
        txtNotes.setVisibility(View.VISIBLE);

        edtName.setVisibility(View.GONE);
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
        txtAddress.setVisibility(View.GONE);
        txtPhone.setVisibility(View.GONE);
        txtEmail.setVisibility(View.GONE);
        txtNotes.setVisibility(View.GONE);

        edtName.setVisibility(View.VISIBLE);
        edtAddress.setVisibility(View.VISIBLE);
        edtPhone.setVisibility(View.VISIBLE);
        edtEmail.setVisibility(View.VISIBLE);
        edtNotes.setVisibility(View.VISIBLE);


        careCenter = dbHelper.showCareCenterInformation(id);

        name = careCenter.getName();
        address = careCenter.getAddress();
        phone = careCenter.getPhone();
        email = careCenter.getEmail();
        notes = careCenter.getNotes();

        edtName.setText(name);
        edtAddress.setText(address);
        edtPhone.setText(phone);
        edtEmail.setText(email);
        edtNotes.setText(notes);

        //move the curser to the end of the line
        edtName.setSelection(edtName.getText().length());
        edtAddress.setSelection(edtAddress.getText().length());
        edtPhone.setSelection(edtPhone.getText().length());
        edtEmail.setSelection(edtEmail.getText().length());
        edtNotes.setSelection(edtNotes.getText().length());

    }


    public boolean isFieldEmpty() {
        if ((name.trim().length() > 0)  && (address.trim().length() > 0) && (phone.trim().length() > 0) && (email.trim().length() > 0)) {
            return false;
        } else {
            return true;
        }


    }

    public void getValue() {
        name = edtName.getText().toString();
        address = edtAddress.getText().toString();
        phone = edtPhone.getText().toString();
        email = edtEmail.getText().toString();
        notes = edtNotes.getText().toString();

    }

    public void callNumber(View v) {
        String num = txtPhone.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
        startActivity(Intent.createChooser(intent, "Call via..."));
//        startActivity(intent);

    }

    public void sendEmail(View v) {
        String email = txtEmail.getText().toString();
        String sub = "";
        String msg = "";

        String mail = "mailto:?to=" + email + "&subject=" + sub + "&body=" + msg;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(mail));
        startActivity(Intent.createChooser(intent, "Send via..."));


    }

}