package com.asaduzzamankochi.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.asaduzzamankochi.modelClass.Doctor;
import com.asaduzzamankochi.modelClass.Profile;

import java.util.ArrayList;

/**
 * Created by Mobile App Develop on 10-6-15.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "iCare";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = null;
    public ArrayList<String> showProfile;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE profile(id integer primary key autoincrement,name TEXT, age TEXT, gender TEXT, bloodGroup TEXT, height TEXT, weight TEXT, phoneNo TEXT,category TEXT)");
        db.execSQL("CREATE TABLE doctor_info(id integer primary key autoincrement,name TEXT, speciality TEXT, address TEXT, phone TEXT, email TEXT, notes TEXT)");
        // db.execSQL("CREATE TABLE family_profile(id integer primary key autoincrement,name TEXT, birthDay TEXT, gender TEXT, bloodGroup TEXT, height TEXT, weight TEXT, phoneNo TEXT,category TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public boolean insertMyProfile(Profile profile) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", profile.getName());
        values.put("age", profile.getAge());
        values.put("gender", profile.getGender());
        values.put("bloodGroup", profile.getBloodGroup());
        values.put("height", profile.getHeight());
        values.put("weight", profile.getWeight());
        values.put("phoneNo", profile.getPhoneNo());
        values.put("category", profile.getCategory());
        try {
            sqLiteDatabase.insert("profile", null, values);
            Log.i(TAG, "Success");
            return true;
        } catch (SQLException e) {
            Log.i(TAG, "Error");
            return false;
        }


    }


//    public void insertFamilyProfile(String name, String birthDay, String gender, String bloodGroup, String height, String weight, String phoneNo, String category) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("name", name);
//        values.put("birthDay", birthDay);
//        values.put("gender", gender);
//        values.put("bloodGroup", bloodGroup);
//        values.put("height", height);
//        values.put("weight", weight);
//        values.put("phoneNo", phoneNo);
//        values.put("category", category);
//        try {
//            sqLiteDatabase.insert("family_profile", null, values);
//            Log.i(TAG, "Success");
//        } catch (SQLException e) {
//            Log.i(TAG, "Error");
//        }
//
//
//    }

    public boolean updateMyProfile(Profile profile) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", profile.getName());
        values.put("age", profile.getAge());
        values.put("gender", profile.getGender());
        values.put("bloodGroup", profile.getBloodGroup());
        values.put("height", profile.getHeight());
        values.put("weight", profile.getWeight());
        values.put("phoneNo", profile.getPhoneNo());
        values.put("category", profile.getCategory());

        try {
            sqLiteDatabase.update("profile", values, "category= ?", new String[]{profile.getCategory()});
            Log.i(TAG, "Success");
            return true;
        } catch (SQLException e) {
            Log.i(TAG, "Error");
            return false;
        }

    }

    public ArrayList<Profile> showProfile(String category) {
        ArrayList<Profile> profileData = new ArrayList<>();
        Profile profile = new Profile();
        String query = "SELECT * FROM profile WHERE category ='" + category + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    // get  the  data into array,or class variable
                    profile.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    profile.setName(cursor.getString(cursor.getColumnIndex("name")));
                    profile.setAge(cursor.getString(cursor.getColumnIndex("age")));
                    profile.setGender(cursor.getString(cursor.getColumnIndex("gender")));
                    profile.setBloodGroup(cursor.getString(cursor.getColumnIndex("bloodGroup")));
                    profile.setHeight(cursor.getString(cursor.getColumnIndex("height")));
                    profile.setWeight(cursor.getString(cursor.getColumnIndex("weight")));
                    profile.setPhoneNo(cursor.getString(cursor.getColumnIndex("phoneNo")));
                    profile.setCategory(cursor.getString(cursor.getColumnIndex("category")));

                    profileData.add(profile);

                    // profileData.add(new Profile(cursor.getInt(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("age")), cursor.getString(cursor.getColumnIndex("gender")), cursor.getString(cursor.getColumnIndex("bloodGroup")), cursor.getString(cursor.getColumnIndex("height")), cursor.getString(cursor.getColumnIndex("weight")), cursor.getString(cursor.getColumnIndex("phoneNo")), cursor.getString(cursor.getColumnIndex("category"))));
                } while (cursor.moveToNext());
            }


        } else {
            profileData.add(new Profile("NO"));
        }
        sqLiteDatabase.close();
        return profileData;
    }


    public int checkId() {
        String query = "SELECT * FROM profile";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String count = "SELECT count(*) FROM table";
        Cursor mcursor = sqLiteDatabase.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0)
            return 1;
        else
            return 0;

    }

    // Doctor Information

    public boolean insertDoctorProfile(Doctor doctor) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", doctor.getName());
        values.put("speciality", doctor.getSpeciality());
        values.put("address", doctor.getAddress());
        values.put("phone", doctor.getPhone());
        values.put("email", doctor.getEmail());
        values.put("notes", doctor.getNotes());
        try {
            sqLiteDatabase.insert("doctor_info", null, values);
            Log.i(TAG, "Success");
            return true;
        } catch (SQLException e) {
            Log.i(TAG, "Error");
            return false;
        }


    }

    public boolean updateDoctorProfile(Doctor doctor) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", doctor.getName());
        values.put("speciality", doctor.getSpeciality());
        values.put("address", doctor.getAddress());
        values.put("phone", doctor.getPhone());
        values.put("email", doctor.getEmail());
        values.put("notes", doctor.getNotes());
        values.put("id", doctor.getId());

        try {
            sqLiteDatabase.update("doctor_info", values, "id= ?", new String[]{Integer.toString(doctor.getId())});
            Log.i(TAG, "Success");
            return true;
        } catch (SQLException e) {
            Log.i(TAG, "Error");
            return false;
        }

    }

    public boolean deleteDoctorProfile(Doctor doctor) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.delete("doctor", "id= ?", new String[]{Integer.toString(doctor.getId())});
            Log.i(TAG, "Success");
            return true;
        } catch (SQLException e) {
            Log.i(TAG, "Error");
            return false;
        }
    }


    public ArrayList<Doctor> showDoctorList() {
        ArrayList<Doctor> doctorNames = new ArrayList<Doctor>();
        Doctor doctor = new Doctor();
        String query = "SELECT  * FROM doctor_info";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                // get  the  data into array,or class variable
                doctor.setId(cursor.getInt(cursor.getColumnIndex("id")));
                doctor.setName(cursor.getString(cursor.getColumnIndex("name")));
                //doctorNames.add(new Doctor(cursor.getInt(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("name"))));
                doctorNames.add(doctor);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return doctorNames;
    }

    public ArrayList<Doctor> showDoctorInformation(int id) {
        ArrayList<Doctor> doctorNames = new ArrayList<Doctor>();
        Doctor doctor = new Doctor();
        String query = "SELECT  * FROM doctor_info WHERE id ='" + id + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                // get  the  data into array,or class variable
                doctor.setId(cursor.getInt(cursor.getColumnIndex("id")));
                doctor.setName(cursor.getString(cursor.getColumnIndex("name")));
                doctor.setSpeciality(cursor.getString(cursor.getColumnIndex("speciality")));
                doctor.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                doctor.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                doctor.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                doctor.setNotes(cursor.getString(cursor.getColumnIndex("notes")));

                //doctorNames.add(new Doctor(cursor.getInt(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("name"))));
                doctorNames.add(doctor);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return doctorNames;
    }


}
