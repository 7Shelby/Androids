package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.databinding.ActivityMainBinding;

public class ActivityLogin extends AppCompatActivity {
    DBHelper dbHelper;
    Button btnLogin;
    EditText etUsername, etPwd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper=new DBHelper(this);
        etUsername=findViewById(R.id.etUsername);
        etPwd=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            boolean isLoggedId=dbHelper.checkUser(etUsername.getText().toString(), etPwd.getText().toString());
            if (isLoggedId)
            {
                Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                startActivity(intent);
            }
            else
                Toast.makeText(ActivityLogin.this, "Login Failed!!!", Toast.LENGTH_LONG).show();
        });
    }
}
