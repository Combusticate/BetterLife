package com.example.betterlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Register extends AppCompatActivity {

    EditText newusername, newpassword, passwordconf, newemail;
    MaterialButton regbtn, logbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        newusername = findViewById(R.id.newusername);
        newpassword = findViewById(R.id.newpassword);
        passwordconf = findViewById(R.id.newpasswordconf);
        newemail = findViewById(R.id.newemail);
        regbtn = findViewById(R.id.regbtn);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nusername, npass, npassconf, nemail;
                nusername = String.valueOf(newusername.getText());
                npass = String.valueOf(newpassword.getText());
                npassconf = String.valueOf(passwordconf.getText());
                nemail = String.valueOf(newemail.getText());

                if(!npassconf.equals(npass)){
                    Toast.makeText(getApplicationContext(), "Passwords must match", Toast.LENGTH_SHORT).show();
                }
                else if(!nusername.equals("") && !npass.equals("")  && !npassconf.equals("") && !nemail.equals("")) {
                    Toast.makeText(getApplicationContext(), "Registration Complete", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Survey.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nusername, npass, npassconf, nemail;
                nusername = String.valueOf(newusername.getText());
                npass = String.valueOf(newpassword.getText());
                npassconf = String.valueOf(passwordconf.getText());
                nemail = String.valueOf(newemail.getText());

                if(!npassconf.equals(npass)){
                    Toast.makeText(getApplicationContext(), "Passwords must match", Toast.LENGTH_SHORT).show();
                }
                else if(!nusername.equals("") && !npass.equals("")  && !npassconf.equals("") && !nemail.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[3];
                            field[0] = "username";
                            field[1] = "password";
                            field[2] = "email";
                            //Creating array for data
                            String[] data = new String[3];
                            data[0] = nusername;
                            data[1] = npass;
                            data[2] = nemail;
                            PutData putData = new PutData("http://localhost:8080/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
            else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
            }
            }
        });*/


    }
}