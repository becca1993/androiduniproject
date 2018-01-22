package com.example.darren.projecttest;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class RegisterActivity extends MainActivity{
    Database db = new Database(this);
    /**
     * This Activity is used for registering the users into the system.
     */
    EditText emailEdit, passwordEdit, firstNameEdit, lastNameEdit, dobEdit, confirmpassword; //setting up EditTexts for user entry
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /**
         * XML objects, declared in activity_register.xml for use in Java.
         * Two EditTexts are set up for the user to enter their details for registration.
         * A button is also set up in order to confirm registration
         *
         */
        emailEdit = (EditText)findViewById(R.id.etEmailRegister);
        passwordEdit = (EditText) findViewById(R.id.etPasswordRegister);
        firstNameEdit = (EditText)findViewById(R.id.etFirstName);
        lastNameEdit = (EditText) findViewById(R.id.etLastName);
        dobEdit = (EditText) findViewById(R.id.etDob);
        confirmpassword = (EditText) findViewById(R.id.editText7);
        Button registerConfirm = (Button) findViewById(R.id.bRegisterConfirm);
        //if user clicks on the register button, do this.
        registerConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                final String newusername = emailEdit.getText().toString(); //setting up String newusername so that it has the value that the user enters
                final String newpassword = passwordEdit.getText().toString(); //setting up String newpassword so that it has the value that the user enters
                final String newFirstName = firstNameEdit.getText().toString(); //setting up String newFirstName so that it has the value that the user enters.
                final String newLastName = lastNameEdit.getText().toString(); //setting up String newLastName so that it has the value that the user enters.
                final String newdob = dobEdit.getText().toString(); //setting up String newDOB so it has the value that the user enters.
                final String confirmnewpass = confirmpassword.getText().toString(); //Setting up String confirmnewpass so it has the values that the user enters.
                User user = new User(); //Creating new user object, to allow values to be inserted to the database.
                user.setUsername(newusername); //Setting username to what the user has entered
                user.setPassword(newpassword); //Setting password to what the user has entered
                user.setFirst_name(newFirstName); //Setting first name to what the user has entered
                user.setLast_name(newLastName); //Setting last name to what the user has entered
                user.setDate_of_birth(newdob); //Setting DOB to what the user has entered
                    if(confirmnewpass.equals(newpassword)) { //If statement to make sure that both password values match. If they do, allow user to register. If they don't, allow user to reenter a matching password.
                        db.insertUser(user); //Take values from user object and call the insertUser method, in order to insert values within the database.
                        Intent i = new Intent(RegisterActivity.this, MainActivity.class); //Starting up new Intent that will take the user back to the main/login activity
                        startActivity(i); //Starts new Activity
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match!!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    }catch (Exception e){ //If something goes horrifically wrong with registeration, this will get called. This is bad and you should really fix what's going wrong.
                        Toast toast = Toast.makeText(getApplicationContext(), "Register ain't working, someone fix me", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent i = new Intent(RegisterActivity.this, MainActivity.class); //Starting up new Intent that will take the user back to the main/login activity
                        startActivity(i); //Starts new Activity
                    }
        }
    });
    }
}
