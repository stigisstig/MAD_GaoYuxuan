package com.example.italwaysrains;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    private static final String TAG = "MainActivity.java";
    private TextView tv_NewUser;

    MyDBHandler dbHandler =new MyDBHandler(this,null,null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_NewUser = findViewById(R.id.tvtv3);
        tv_NewUser.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.v(TAG,"Touch Start");
        Intent intent = new Intent(MainActivity.this,Main3Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        return true;
    }
    public void StartActivity(View v){

    }

    public void onClick(View v){
        final EditText etUserName = findViewById(R.id.editText);
        final EditText etPassword = findViewById(R.id.editText2);
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
        if(etUserName == null || etPassword == null){
            Toast.makeText(MainActivity.this, "Invalid user", Toast.LENGTH_LONG).show();
        }
        else{
            if(isValid(etUserName.getText().toString(),etPassword.getText().toString())){
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                Toast.makeText(MainActivity.this, "Valid user", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
            else{
                Toast.makeText(MainActivity.this, "Invalid user", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(MainActivity.this,MainActivity.class);

                startActivity(intent1);
            }
        }

        if(isValid(etUserName.getText().toString(),etPassword.getText().toString())){
            Intent intent = new Intent(MainActivity.this, Main4Activity.class);
            Toast.makeText(MainActivity.this, "Valid user", Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this, "Invalid user", Toast.LENGTH_LONG).show();
        }
        Log.v(TAG,"Login with:" + etUserName.getText().toString() + "," + etPassword.getText().toString());

    }
    public boolean isValid(String username, String password){
        UserData dbData=dbHandler.findUser(username);
        if (dbData != null ){
            if((dbData.getMyUserName().equals(username))&&(dbData.getMyPassword().equals(password))){
                return true;
            }

            else
            {
                return false;
            }
        }
        return false;

    }
}
