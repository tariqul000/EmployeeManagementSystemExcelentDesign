package com.example.tariqul.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;


public class Activity_Employee extends ActionBarActivity {

   EditText etName, etPhone, etEmail, etAddress;

    DBHandler db;

    TextView tvDisplay;
    ListView lv;
    CustomListAdapter customListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe);

        init();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff342f")));
    }

    private void init() {
        db= new DBHandler(this);

        etName=(EditText)findViewById(R.id.etName);
        etPhone=(EditText)findViewById(R.id.etPhone);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etAddress=(EditText)findViewById(R.id.etAddress);



        lv=(ListView) findViewById(R.id.LV);


    }

        public void Save(View view){


            Toast.makeText(getApplicationContext(),"save", Toast.LENGTH_LONG).show();


            try {

                String name= etName.getText().toString();
                String phone= etPhone.getText().toString();
                String email= etEmail.getText().toString();
                String address= etAddress.getText().toString();

                Employee employee= new Employee(name, phone, email, address);
                db.addEmployee(employee);
            }catch (Exception e){

            }

            db.close();

            Intent intent= new Intent(this, Search.class);

            startActivity(intent);

        }

public void Show(View view){
    List<Employee> values = db.getAllEmployee();

    // use the SimpleCursorAdapter to show the
    // elements in a ListView
   /*ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(this,
            android.R.layout.simple_list_item_1, values);*/
    customListAdapter=new CustomListAdapter(this, values);
    lv.setAdapter(customListAdapter);

    Toast.makeText(getApplication(), String.valueOf(values), Toast.LENGTH_LONG).show();

/*
    db=new DBHandler(this);
    Employee employee=new Employee();

    lv=(ListView)view.findViewById(R.id.LV);

    ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(this,
            android.R.layout.simple_list_item_1, db.getAllEmployee());
    // Assign adapter to ListView
    lv.setAdapter(adapter);
    registerForContextMenu(lv);*/
    customListAdapter.notifyDataSetChanged();
}
    public void SearchDelete(View v){
        String id= etName.getText().toString();
        db.deleteEmployeeSearch(id);

        db.close();

        List<Employee> values = db.getAllEmployee();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(this,
                android.R.layout.simple_list_item_1, values);
        lv.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.DeleteAll) {
            db.deleteEmployee();

            return true;
    }
        if (id == R.id.Search) {
            Intent intent=new Intent(this, Search.class);
            startActivity(intent);

            return true;
    }

        return super.onOptionsItemSelected(item);
    }
}
