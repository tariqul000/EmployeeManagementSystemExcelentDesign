package com.example.tariqul.assignment;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Search extends ActionBarActivity {

    EditText etSearch;


    ListView listViewSearch;
    DBHandler db;
    List<Employee> values;
    ArrayAdapter<Employee> adapter;

    List<Employee> searchLists=new ArrayList<>();

        CustomListAdapter customListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        init();

        db=new DBHandler(this);
        values = db.getAllEmployee();
        customListAdapter=new CustomListAdapter(this, values);
        listViewSearch.setAdapter(customListAdapter);

        customListAdapter.notifyDataSetChanged();

        listViewItemClick();

     /* ActionBar actionBar=getActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#BFC1BE"));
        actionBar.setBackgroundDrawable(colorDrawable);
        */

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff342f")));
    }

    private void init() {

        db=new DBHandler(this);
        values = db.getAllEmployee();
        etSearch=(EditText)findViewById(R.id.etSearch);
        listViewSearch=(ListView)findViewById(R.id.ListViewSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(etSearch.getText().toString().trim().toLowerCase().length()>0){
            etSearch.setBackgroundColor(Color.parseColor("#0d1c42ea"));

            applySearch(etSearch.getText().toString().trim());

        }else {


            // use the SimpleCursorAdapter to show the
            // elements in a ListView

         /*   adapter = new ArrayAdapter<Employee>(Search.this,
                    android.R.layout.simple_list_item_1, values);
            listViewSearch.setAdapter(adapter);

            adapter.notifyDataSetChanged();*/

            customListAdapter=new CustomListAdapter(Search.this, searchLists);
            listViewSearch.setAdapter(customListAdapter);
            customListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
});



    }

    private void applySearch(String Search) {
        searchLists.clear();
        values=db.getAllEmployee();

        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).getName().toLowerCase().contains(Search.toLowerCase())) {
                Toast.makeText(this, values.get(i).getName().toLowerCase(), Toast.LENGTH_SHORT).show();

                searchLists.add(values.get(i));


            }

        }
        customListAdapter=new CustomListAdapter(this, searchLists);
        listViewSearch.setAdapter(customListAdapter);
       /* adapter = new ArrayAdapter<Employee>(Search.this,
                android.R.layout.simple_list_item_1, searchLists);
        listViewSearch.setAdapter(adapter);*/
        customListAdapter.notifyDataSetChanged();
    }

    public void listViewItemClick(){
        listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String KeyId= String.valueOf(db.getAllEmployee().get(position).getKey());
                String name=db.getAllEmployee().get(position).getName();
                String phone=db.getAllEmployee().get(position).getPhone();
                String email=db.getAllEmployee().get(position).getEmail();
                String address=db.getAllEmployee().get(position).getAddress();

                Intent i=new Intent(Search.this,EmployeeDetails.class);
                i.putExtra("KeyId", KeyId);
                i.putExtra("KeyName", name);
                i.putExtra("KeyPhone", phone);
                i.putExtra("KeyEmail", email);
                i.putExtra("KeyAddress", address);
                 startActivity(i);

            }
        });
    }
    public void AddNewEmployee(View view){
        Intent intent= new Intent(this, Activity_Employee.class);
        startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
        if (id == R.id.action_delete) {
            db.deleteEmployee();

            db=new DBHandler(this);
            values = db.getAllEmployee();
            customListAdapter=new CustomListAdapter(this, values);
            listViewSearch.setAdapter(customListAdapter);
            customListAdapter.notifyDataSetChanged();

            return true;
        }
        if (id == R.id.action_search) {
            etSearch.setVisibility(View.VISIBLE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
