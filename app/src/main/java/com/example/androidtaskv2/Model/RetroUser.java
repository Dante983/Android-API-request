package com.example.androidtaskv2.Model;


public class RetroUser {
    private String name;
    private int id;
    private String username;
    private String email;
    private String phone;
    private RetroCompany company;
    private RetroAddress address;


    //Retrieve the data using setter/getter methods

    public String getName() {
        return name;
    }

    public void setUser(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getCompanyName(){
        if(company == null)
            return "";
        return company.getName();
    }

    public String getAddress(){
        if (address == null)
            return "";
        return address.getCity() + ", " + address.getStreet();
    }


}

