package com.example.tariqul.assignment;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class Activity_Update extends ActionBarActivity {

    EditText etUpdateName, etUpdatePhone, etUpdateEmail, etUpdateAddress;


    String KeyId,KeyName,KeyPhone,KeyEmail,KeyAddress;

    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_update);


        init();

    }

    private void init() {

        db=new DBHandler(this);
        KeyId = getIntent().getStringExtra("KeyId1");
        KeyName = getIntent().getStringExtra("KeyName1");
        KeyPhone = getIntent().getStringExtra("KeyPhone1");
        KeyEmail = getIntent().getStringExtra("KeyEmail1");
        KeyAddress = getIntent().getStringExtra("KeyAddress1");

        etUpdateName=(EditText)findViewById(R.id.etUpdateName);
        etUpdateName.setText(KeyName);
        etUpdatePhone=(EditText)findViewById(R.id.etUpdatePhone);
        etUpdatePhone.setText(KeyPhone);
        etUpdateEmail=(EditText)findViewById(R.id.etUpdateEmail);
        etUpdateEmail.setText(KeyEmail);
        etUpdateAddress=(EditText)findViewById(R.id.etUpdateAddress);
        etUpdateAddress.setText(KeyAddress);


    }

    public void UpdateInfoFromUpdateActivity(View view){

        try {

            String name= etUpdateName.getText().toString();
            String phone= etUpdatePhone.getText().toString();
            String email= etUpdateEmail.getText().toString();
            String address= etUpdateAddress.getText().toString();

            Employee employee= new Employee(name, phone, email, address);
            db.updateEmployeeInfo(KeyId, employee);
        }catch (Exception e){

        }

        db.close();

        Intent intent= new Intent(this, Search.class);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity__update, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
