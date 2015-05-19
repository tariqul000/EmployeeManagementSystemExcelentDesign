package com.example.tariqul.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=2;
    public static final String DATABASE_NAME = "db_employee";
    public static final String TABLE_EMPLOYEE="tb1_employee";

    private static final String KEY_ID="id";
    private static final String KEY_EMPLOYEE_NAME="employee_name";
    private static final String KEY_PHONE="phone";
    private static final String KEY_EMAIL="email";
    private static final String KEY_ADDRESS="address";

    SQLiteDatabase sqLiteDatabase;


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " +
                TABLE_EMPLOYEE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EMPLOYEE_NAME
                + " TEXT," + KEY_PHONE
                + " TEXT," + KEY_EMAIL
                + " TEXT," + KEY_ADDRESS
                + " TEXT" + ")";


        db.execSQL(CREATE_EMPLOYEE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        onCreate(db);

    }
public void addEmployee(Employee employee ){

SQLiteDatabase sqLiteDatabase= super.getWritableDatabase();
    ContentValues values= new ContentValues();

    values.put(KEY_EMPLOYEE_NAME, employee.getName());
    values.put(KEY_PHONE, employee.getPhone());
    values.put(KEY_EMAIL, employee.getEmail());
    values.put(KEY_ADDRESS, employee.getAddress());

    sqLiteDatabase.insert(TABLE_EMPLOYEE, null, values);
    sqLiteDatabase.close();

}
public List<Employee> getAllEmployee() {
    List<Employee> employeeList = new ArrayList<>();

    String selectQuery = "SELECT * FROM " + TABLE_EMPLOYEE;
    sqLiteDatabase = this.getWritableDatabase();

    Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

    if (cursor.moveToFirst()) {
        do {
            Employee employee = new Employee();
            employee.setKey(Integer.parseInt(cursor.getString(0)));
            employee.setName(cursor.getString(1));
            employee.setPhone(cursor.getString(2));
            employee.setEmail(cursor.getString(3));
            employee.setAddress(cursor.getString(4));
            employeeList.add(employee);

        } while (cursor.moveToNext());

    }
    return employeeList;
}
public void deleteEmployee(){
    sqLiteDatabase = this.getWritableDatabase();
    String selectQuery = "DELETE FROM " + TABLE_EMPLOYEE;
    sqLiteDatabase.execSQL(selectQuery);

    sqLiteDatabase.close();
}
    public void deleteEmployeeSearch(String id){

        sqLiteDatabase= this.getWritableDatabase();
        String searchDelete= "DELETE FROM " + TABLE_EMPLOYEE + " WHERE"+" "+KEY_ID + "="+ id;

        sqLiteDatabase.execSQL(searchDelete);
        sqLiteDatabase.close();

    }
    public long updateEmployeeInfo(String id, Employee employee) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_EMPLOYEE_NAME,
                employee.getName().toString());
        contentValues.put(KEY_PHONE,
                employee.getPhone().toString());
        contentValues.put(KEY_EMAIL,
                employee.getEmail().toString());
        contentValues.put(KEY_ADDRESS,
                employee.getAddress().toString());


        return db.update(TABLE_EMPLOYEE, contentValues, "id = ? ",
                new String[] { id });
        // return true;
    }


}