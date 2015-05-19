package com.example.tariqul.assignment;

/**
 * Created by tariqul on 5/12/2015.
 */
public class Employee {

    private int key;
    private String name;
    private String phone;
    private String email;
    private String address;


    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Employee(String name, String phone, String email, String address){
    setName(name);
    setPhone(phone);
    setEmail(email);
    setAddress(address);


}
    public Employee(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name+" "+ email+" "+address +" "+ phone;
    }
}
