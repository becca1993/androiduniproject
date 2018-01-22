package com.example.darren.projecttest;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * This is the class for setting up the database for the project.
 * It contains the set up for the database and inserting methods for inserting values into the database.
 * Created by Becca on 23/03/2017.
 */
public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1; //Database version.
    private static final String DATABASE_NAME = "belfastgo.db"; //Name of database, .db is needed for it to work.
    private static final String TABLE_NAME = "users"; //Name of table that stores user detail
    private static final String COLMUM_ID = "id"; //Tuple for user id.
    private static final String COLUM_USERNAME = "username"; // Tuple for username
    private static final String COLUM_PASSWORD = "password"; // Tuple for password
    private static final String COLUM_FIRST_NAME = "first_name"; // Tuple for first name
    private static final String COLUM_LAST_NAME = "last_name"; // Tuple for last name
    private static final String COLUM_DATE_OF_BIRTH = "date_of_birth"; //Tuple for date of birth
    SQLiteDatabase belfastgo; //Creating SQLite database.
    private static final String TABLE_CREATE = "CREATE TABLE users (id INTEGER PRIMARY KEY NOT NULL, username VARCHAR(20) UNIQUE NOT NULL, password VARCHAR(255) NOT NULL, first_name VARCHAR(25) NOT NULL, last_name VARCHAR(25) NOT NULL, date_of_birth VARCHAR(25) NOT NULL);"; //SQL statement for creating the table.
    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    } //Constructor for the database.
    @Override
    public void onCreate(SQLiteDatabase belfastgo) { //On creation of database do this
        belfastgo.execSQL(TABLE_CREATE); //Execute SQL command listed in the String TABLE_CREATE.
        this.belfastgo = belfastgo;
    }
    public void insertUser(User user){ //Will insert user details in the database
        belfastgo = this.getWritableDatabase(); //Enables writing to database
        ContentValues values = new ContentValues(); //Values that will be stored in database
        String query = "SELECT * FROM users;"; //SQL query - select everything from users table
        Cursor cursor = belfastgo.rawQuery(query, null); //Cursor object - to enable SQL queries
        int count = cursor.getCount(); //Count to increment user_id value
        values.put(COLMUM_ID, count); //Put ID value in database
        values.put(COLUM_USERNAME, user.getUsername()); //Put username value in databasse
        values.put(COLUM_PASSWORD, user.getPassword()); //Put password value in database
        values.put(COLUM_FIRST_NAME, user.getFirst_name()); //Put first name value in database
        values.put(COLUM_LAST_NAME, user.getLast_name()); //Put last name value in database
        values.put(COLUM_DATE_OF_BIRTH, user.getDate_of_birth()); // Put date of birth value in database
        belfastgo.insert(TABLE_NAME, null, values); //Inserting values in database
        belfastgo.close(); //Close connection to database
    }
    public String searchPassword(String uname) { //Searching password for login.
        belfastgo = this.getReadableDatabase(); //Enable reading from database
        String query = "SELECT * FROM users;"; //Setting up String query - select all from users table
        Cursor cursor = belfastgo.rawQuery(query, null); //Setting up query with Cursor object
        String username, password; //String set up for username/password
        password = "not found";
        if (cursor.moveToFirst()) {
            do { //start of do-while loop
                username = cursor.getString(2); //cursor will find this value at 2
                if (username.equals(uname)) { //If username is found in the database do this
                    password = cursor.getString(3); //Getting password from table
                    break; //break loop
                }
            } while (cursor.moveToNext()) ;
        }
        return password; //return password value
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME + ";"; //If table already exists, any other instances will be dropped.
        belfastgo.execSQL(query);
        this.onCreate(belfastgo);
    }
}
