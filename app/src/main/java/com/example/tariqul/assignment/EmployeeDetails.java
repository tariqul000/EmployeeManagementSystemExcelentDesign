package com.example.tariqul.assignment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class EmployeeDetails extends ActionBarActivity {

    TextView tvName,tvPhone, tvEmail, tvAddress;

    DBHandler db;
    String KeyId;
    String KeyName;
    String KeyPhone;
    String KeyEmail;
    String KeyAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        init();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2738A3")));
    }

    private void init() {
        KeyId = getIntent().getStringExtra("KeyId");
         KeyName = getIntent().getStringExtra("KeyName");
         KeyPhone = getIntent().getStringExtra("KeyPhone");
         KeyEmail = getIntent().getStringExtra("KeyEmail");
         KeyAddress = getIntent().getStringExtra("KeyAddress");




        tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText(KeyName);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvPhone.setText(KeyPhone);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvEmail.setText(KeyEmail);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvAddress.setText(KeyAddress);

       db= new DBHandler(this);

    }
        public void DeleteFromDetails(View view){
            db.deleteEmployeeSearch(KeyId);
            db.close();

            Intent intent= new Intent(this, Search.class);
            startActivity(intent);
        }

    public void updateEmployeeInfo(View view){

        Intent intent= new Intent(this, Activity_Update.class);
        intent.putExtra("KeyId1", KeyId);
        intent.putExtra("KeyName1", KeyName);
        intent.putExtra("KeyPhone1", KeyPhone);
        intent.putExtra("KeyEmail1", KeyEmail);
        intent.putExtra("KeyAddress1", KeyAddress);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employee_details, menu);
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
