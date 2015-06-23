package com.asaduzzamankochi.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.asaduzzamankochi.DB.DBHelper;
import com.asaduzzamankochi.familyhealth.FamilyMemberInformation;
import com.asaduzzamankochi.icare.R;
import com.asaduzzamankochi.modelClass.Doctor;

import java.util.ArrayList;

/**
 * Created by kochi on 20-Jun-15.
 */
public class DoctorList extends ActionBarActivity {

    private LinearLayout mainLayout;
    private LinearLayout secondaryLayout;
    private Button btnAddNewFamily;

    private static DBHelper dbHelper;
    private ListView listView;
    private ArrayAdapter<Doctor> listAdapter;
    private ArrayList<Doctor> doctorName = new ArrayList<Doctor>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_list);
        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        secondaryLayout = (LinearLayout) findViewById(R.id.secondary_layout);
        btnAddNewFamily = (Button)findViewById(R.id.buttonAddNewProfile);


        listView = (ListView) findViewById(R.id.listView);
        dbHelper = new DBHelper(DoctorList.this);

        doctorName = dbHelper.showDoctorList();

        if (doctorName.isEmpty()) {

            secondaryLayout.setVisibility(View.VISIBLE);
            mainLayout.setVisibility(View.GONE);
            btnAddNewFamily.setText("Add New Doctor");

        }
        //listAdapter = new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1, doctorName);
        listAdapter = new ArrayAdapter<Doctor>(this, R.layout.doctor_list_view_center, R.id.txtNameView, doctorName);
        listView.setAdapter(listAdapter);
//        listView.setAdapter(new NameListViewAdapter(this,empName));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String itemName = (String) listView.getAdapter().getItem(position);
                int item = doctorName.get(position).getId();
                Intent intent = new Intent(DoctorList.this, DoctorFullDataView.class);
                intent.putExtra("id", item);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), itemName + " Selected !!", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void addInfo(View v) {
        Intent intent = new Intent(DoctorList.this, DoctorFullDataView.class);
        intent.putExtra("id", -2);
        startActivity(intent);
    }

    public void btnAddNew(View v) {
        Intent intent = new Intent(DoctorList.this, DoctorFullDataView.class);
        intent.putExtra("id", -2);
        startActivity(intent);
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
            Intent intent = new Intent(DoctorList.this, DoctorFullDataView.class);
            intent.putExtra("id", -2);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
