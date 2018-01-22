package com.example.darren.projecttest;
/**
 * Created by Becca on 23/03/2017.
 * This class contains getters and setters for the different values that the user will insert during registration and login.
 */
public class User {
    int user_id;
    String username, password, first_name, last_name, date_of_birth;
    public void setUser_id(int user_id){
        this.user_id = user_id;
    }
    public int getUser_id(){
        return this.user_id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
   public void setPassword(String password){
       this.password = password;
   }
   public String getPassword(){
       return this.password;
   }
   public void setFirst_name(String first_name){
       this.first_name = first_name;
   }
   public String getFirst_name(){
       return this.first_name;
   }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getLast_name(){
        return this.last_name;
    }
    public void setDate_of_birth(String date_of_birth){
        this.date_of_birth = date_of_birth;
    }
    public String getDate_of_birth(){
        return this.date_of_birth;
    }
}
