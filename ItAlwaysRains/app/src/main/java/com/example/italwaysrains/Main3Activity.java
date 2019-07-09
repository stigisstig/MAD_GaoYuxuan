package com.example.italwaysrains;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main3Activity extends AppCompatActivity {
    private static final String TAG = "Main3Activity.java";

    SharedPreferences sharedPreferences;
    public static final String MY_GLOBAL_PREFS = "MyPrefs";
    public static final String My_USERNAME = "MyUserName";
    public static final String My_PASSWORD = "MyPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }
    public void onCreateClick(View view){
        final EditText tv_username = findViewById(R.id.editText3);
        final EditText tv_password = findViewById(R.id.editText4);
        MyDBHandler myDBHandler = new MyDBHandler(this,null,null,1);



        if (isValidPassword(tv_password.getText().toString()) && isValidUser(tv_username.getText().toString())){
            UserData dbData = myDBHandler.findUser(tv_username.getText().toString());
            if (dbData == null){
                String dbUserName = tv_username.getText().toString();
                String dbPasswordd = tv_password.getText().toString();
                UserData dbUserData = new UserData();
                dbUserData.setMyUserName(dbUserName);
                dbUserData.setMyPassword(dbPasswordd);
                myDBHandler.addUser(dbUserData);
                Toast.makeText(Main3Activity.this,"UserCreated Successfully", Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(Main3Activity.this,"User already exist",Toast.LENGTH_LONG).show();
            }

        }
        else{
            Toast.makeText(Main3Activity.this,"Username or password is not valid",Toast.LENGTH_LONG).show();
        }





    }
    public boolean isValidPassword(String password){
        Pattern passwordPattern;
        Matcher passwordMatcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%]).{6,12}$";
        passwordPattern = Pattern.compile(PASSWORD_PATTERN);
        passwordMatcher=passwordPattern.matcher(password);
        Log.v(TAG,"does it match" + passwordMatcher.matches());

        return passwordMatcher.matches();
    }
    public boolean isValidUser(String username){
        Pattern usernamePattern;
        Matcher usernameMatcher;

        final String USERNAME_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,12}$";

        usernamePattern = Pattern.compile(USERNAME_PATTERN);
        usernameMatcher = usernamePattern.matcher(username);

        return usernameMatcher.matches();
    }
}
