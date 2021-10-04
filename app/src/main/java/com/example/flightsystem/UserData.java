package com.example.flightsystem;

public class UserData {

    public static String getId() {
        return Id;
    }

    public static void setId(String id) {
        Id = id;
    }

    public static String getPhonenumber() {
        return Phonenumber;
    }

    public static void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public static void setUsername(String username){
        Username = username;
    }

    public static String getUsername(){
        return Username;
    }

    private static String Username;
    private static String Id;
    private static String Phonenumber;



}
