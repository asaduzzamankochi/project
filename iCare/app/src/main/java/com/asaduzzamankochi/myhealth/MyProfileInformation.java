package com.asaduzzamankochi.myhealth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.asaduzzamankochi.DB.DBHelper;
import com.asaduzzamankochi.home.HomePage;
import com.asaduzzamankochi.icare.R;
import com.asaduzzamankochi.modelClass.Profile;

import java.util.ArrayList;

/**
 * Created by Mobile App Develop on 10-6-15.
 */
public class MyProfileInformation extends ActionBarActivity implements AdapterView.OnItemSelectedListener {


    private Button btnSubmit;
    private Button btnEdit;
    private Button btnUpdate;
    private Button btnCancel;

    private LinearLayout mainLayout;
    private LinearLayout secondaryLayout;

    private EditText edtName;
    private EditText edtAge;
    private EditText edtHeight;
    private EditText edtWeight;
    private EditText edtPhone;

    private RadioGroup radioGender;
    private RadioButton radioBtnMale;
    private RadioButton radioBtnFemale;
    private RadioButton radioSelected;

    private Spinner spinnerBloodGroup;

    private TextView txtName;
    private TextView txtAge;
    private TextView txtGender;
    private TextView txtBloodGroup;
    private TextView txtHeight;
    private TextView txtWeight;
    private TextView txtPhone;

    private String name;
    private String age;
    private String gender;
    private String bloodGroup;
    private String height;
    private String weight;
    private String phoneNo;
    private String category = "M";

    private DBHelper dbHelper;
    private ArrayList<Profile> profileData = new ArrayList<Profile>();
    private Profile profile = new Profile();

    private int home;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_information);

        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        secondaryLayout = (LinearLayout) findViewById(R.id.secondary_layout);
        txtName = (TextView) findViewById(R.id.txtViewName);
        txtAge = (TextView) findViewById(R.id.txtViewAge);
        txtGender = (TextView) findViewById(R.id.txtViewGender);
        txtBloodGroup = (TextView) findViewById(R.id.txtViewBloodGroup);
        txtHeight = (TextView) findViewById(R.id.txtViewHeight);
        txtWeight = (TextView) findViewById(R.id.txtViewWeight);
        txtPhone = (TextView) findViewById(R.id.txtViewPhone);
        txtPhone.setClickable(true);

        edtName = (EditText) findViewById(R.id.edtName);
        edtAge = (EditText) findViewById(R.id.edtAge);
        edtHeight = (EditText) findViewById(R.id.edtHeight);
        edtWeight = (EditText) findViewById(R.id.edtWeight);
        edtPhone = (EditText) findViewById(R.id.edtPhoneNo);
        radioGender = (RadioGroup) findViewById(R.id.radioGender);
        radioBtnMale = (RadioButton) findViewById(R.id.radioBtnMale);
        radioBtnFemale = (RadioButton) findViewById(R.id.radioBtnFemale);
        spinnerBloodGroup = (Spinner) findViewById(R.id.spinnerBloodGroup);
        spinnerBloodGroup.setOnItemSelectedListener(this);
        radioBtnMale.setChecked(true);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnCancel = (Button) findViewById(R.id.btnDelete);

        dbHelper = new DBHelper(MyProfileInformation.this);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        home = b.getInt("home");
//        id = intent.getIntExtra("id", -1);
//        secondaryLayout.setVisibility(View.VISIBLE);
//        mainLayout.setVisibility(View.GONE);
        if (home == -5) {
            txtName.setVisibility(View.GONE);
            txtAge.setVisibility(View.GONE);
            txtGender.setVisibility(View.GONE);
            txtBloodGroup.setVisibility(View.GONE);
            txtHeight.setVisibility(View.GONE);
            txtWeight.setVisibility(View.GONE);
            txtPhone.setVisibility(View.GONE);

            edtName.setVisibility(View.VISIBLE);
            edtAge.setVisibility(View.VISIBLE);
            radioGender.setVisibility(View.VISIBLE);
            spinnerBloodGroup.setVisibility(View.VISIBLE);
            edtHeight.setVisibility(View.VISIBLE);
            edtWeight.setVisibility(View.VISIBLE);
            edtPhone.setVisibility(View.VISIBLE);

            btnSubmit.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);

        } else {
            showProfileData();
        }

    }

    public String selectGender() {
        int selectedId = radioGender.getCheckedRadioButtonId();
        // find the radio button by returned id
        radioSelected = (RadioButton) findViewById(selectedId);
        return radioSelected.getText().toString();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bloodGroup = parent.getItemAtPosition(position).toString();
//        Toast.makeText(getApplicationContext(), "Field's could not empty" + bloodGroup, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void btnAddNew(View v) {
        secondaryLayout.setVisibility(View.GONE);
        mainLayout.setVisibility(View.VISIBLE);
    }

    public void addProfile(View v) {

        getValue();
        if (isFieldEmpty()) {
            Toast.makeText(getApplicationContext(), "Field's could not empty", Toast.LENGTH_LONG).show();

        } else {
            profile.setName(name);
            profile.setAge(age);
            profile.setGender(gender);
            profile.setBloodGroup(bloodGroup);
            profile.setHeight(height);
            profile.setWeight(weight);
            profile.setPhoneNo(phoneNo);
            profile.setCategory(category);

            if (dbHelper.insertMyProfile(profile)) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                if (home == -5) {
                    Intent intent = new Intent(MyProfileInformation.this, HomePage.class);
                    startActivity(intent);

                } else {
                    showProfileData();
                    btnUpdate.setVisibility(View.GONE);
                    btnCancel.setVisibility(View.GONE);
//                Intent intent = new Intent(MyProfileInformation.this, MyHealth.class);
//                startActivity(intent);


                }
            } else {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }


        }

    }

    public void editProfile(View v) {
        informationEditMode();
        btnSubmit.setVisibility(View.GONE);
        btnEdit.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
    }

    public void updateProfile(View v) {
        getValue();
        if (isFieldEmpty()) {
            Toast.makeText(getApplicationContext(), "Field's could not empty", Toast.LENGTH_LONG).show();

        } else {
            profile.setName(name);
            profile.setAge(age);
            profile.setGender(gender);
            profile.setBloodGroup(bloodGroup);
            profile.setHeight(height);
            profile.setWeight(weight);
            profile.setPhoneNo(phoneNo);
            profile.setCategory(category);
            if (dbHelper.updateMyProfile(profile)) {
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

    public void cancelProfile(View v) {
        showProfileData();
        btnUpdate.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
    }

    public void getValue() {
        name = edtName.getText().toString();
        age = edtAge.getText().toString();
//        gender = edtGender.getText().toString();
        gender = selectGender();
        //bloodGroup = edtBloodGroup.getText().toString();
        height = edtHeight.getText().toString();
        weight = edtWeight.getText().toString();
        phoneNo = edtPhone.getText().toString();

    }

    public void showProfileData() {
        profile = dbHelper.showProfile(category);

        name = profile.getName();
        age = profile.getAge();
        gender = profile.getGender();
        bloodGroup = profile.getBloodGroup();
        height = profile.getHeight();
        weight = profile.getWeight();
        phoneNo = profile.getPhoneNo();

        category = profile.getCategory();

        informationViewMode();

        txtName.setText(name);
        txtAge.setText(age);
        txtGender.setText(gender);
        txtBloodGroup.setText(bloodGroup);
        txtHeight.setText(height);
        txtWeight.setText(weight);
        txtPhone.setText(phoneNo);


    }

    public void informationViewMode() {
        txtName.setVisibility(View.VISIBLE);
        txtAge.setVisibility(View.VISIBLE);
        txtGender.setVisibility(View.VISIBLE);
        txtBloodGroup.setVisibility(View.VISIBLE);
        txtHeight.setVisibility(View.VISIBLE);
        txtWeight.setVisibility(View.VISIBLE);
        txtPhone.setVisibility(View.VISIBLE);

        edtName.setVisibility(View.GONE);
        edtAge.setVisibility(View.GONE);
        radioGender.setVisibility(View.GONE);
        spinnerBloodGroup.setVisibility(View.GONE);
        edtHeight.setVisibility(View.GONE);
        edtWeight.setVisibility(View.GONE);
        edtPhone.setVisibility(View.GONE);

        btnSubmit.setVisibility(View.GONE);
        btnEdit.setVisibility(View.VISIBLE);

    }

    public void informationEditMode() {
        //set edit text field to editable
        txtName.setVisibility(View.GONE);
        txtAge.setVisibility(View.GONE);
        txtGender.setVisibility(View.GONE);
        txtBloodGroup.setVisibility(View.GONE);
        txtHeight.setVisibility(View.GONE);
        txtWeight.setVisibility(View.GONE);
        txtPhone.setVisibility(View.GONE);

        edtName.setVisibility(View.VISIBLE);
        edtAge.setVisibility(View.VISIBLE);
        radioGender.setVisibility(View.VISIBLE);
        spinnerBloodGroup.setVisibility(View.VISIBLE);
        edtHeight.setVisibility(View.VISIBLE);
        edtWeight.setVisibility(View.VISIBLE);
        edtPhone.setVisibility(View.VISIBLE);


        profile = dbHelper.showProfile(category);

        name = profile.getName();
        age = profile.getAge();
        gender = profile.getGender();
        bloodGroup = profile.getBloodGroup();
        height = profile.getHeight();
        weight = profile.getWeight();
        phoneNo = profile.getPhoneNo();

        edtName.setText(name);
        edtAge.setText(age);

        if (gender.equals("Male")) {
            radioBtnMale.setChecked(true);
        } else {
            radioBtnFemale.setChecked(true);
        }
        spinnerBloodGroup.setSelection(getIndex(spinnerBloodGroup, bloodGroup));
        edtHeight.setText(height);
        edtWeight.setText(weight);
        edtPhone.setText(phoneNo);

        //move the curser to the end of the line
        edtName.setSelection(edtName.getText().length());
        edtAge.setSelection(edtAge.getText().length());
        edtHeight.setSelection(edtHeight.getText().length());
        edtWeight.setSelection(edtWeight.getText().length());
        edtPhone.setSelection(edtPhone.getText().length());

    }


    public boolean isFieldEmpty() {
        if ((name.trim().length() > 0) && (age.trim().length() > 0) && (gender.trim().length() > 0) && (bloodGroup.trim().length() > 0) && (height.trim().length() > 0) && (weight.trim().length() > 0) && (phoneNo.trim().length() > 0)) {
            return false;
        } else {
            return true;
        }


    }

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

    public void callNumber(View v){
        String num = txtPhone.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
        startActivity(Intent.createChooser(intent, "Call via..."));
//        startActivity(intent);

    }

}
