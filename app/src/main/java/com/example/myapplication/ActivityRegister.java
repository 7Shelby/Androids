package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityRegister extends AppCompatActivity {
    EditText etUser,etPwd, etRePwd;
    Button btnRegister, btnGoToLogin;
    DBHelper dbHelper;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUser =findViewById(R.id.etUsername);
        etPwd =findViewById(R.id.etPassword);
        etRePwd =findViewById(R.id.etRePassword);
        btnRegister=findViewById(R.id.btnRegister);
        dbHelper=new DBHelper(this);
        btnGoToLogin=findViewById(R.id.btnLogin);
        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister.this,ActivityLogin.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, pwd, rePwd;
                user=etUser.getText().toString();
                pwd = etPwd.getText().toString();
                rePwd=etRePwd.getText().toString();
                if(user.equals("") || pwd.equals("") || rePwd.equals("")){
                    Toast.makeText(ActivityRegister.this, "Please fill all the fields",Toast.LENGTH_LONG).show();
                }else {
                    if (pwd.equals(rePwd)){
                        if(dbHelper.checkUsername(user)){
                            Toast.makeText(ActivityRegister.this,"User Already Exists", Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean registeredSuccess = dbHelper.insertData(user,pwd);
                        if(registeredSuccess)
                            Toast.makeText(ActivityRegister.this,"User Registered Successfully", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ActivityRegister.this,"User Registration Failed!!!", Toast.LENGTH_LONG).show();
                    }
                    else {
                            Toast.makeText(ActivityRegister.this,"Passwords do not match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
