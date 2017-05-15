package br.com.weis.loginservice;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private DatePicker dpBirthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupUI();
    }

    private void setupUI(){
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        dpBirthdate = (DatePicker) findViewById(R.id.dpBirthdate);
    }

    public void validarLogin(View view){
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        int day = dpBirthdate.getDayOfMonth();
        int month = dpBirthdate.getMonth() + 1;
        int year = dpBirthdate.getYear();

        Intent intent = new Intent(this, validaLoginService.class);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("day", day);
        intent.putExtra("month", month);
        intent.putExtra("year", year);

        startService(intent);
    }
}
