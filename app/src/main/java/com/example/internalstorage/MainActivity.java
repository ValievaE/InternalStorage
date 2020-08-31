package com.example.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegistration;
    private File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeLogin();
                writePassword();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextLogin.getText().toString().equals(readLogin()) && editTextPassword.getText().toString().equals(readPassword())) {
                    Toast.makeText(MainActivity.this, "Введены верные данные", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Логин или пароль указаны неверно", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private void writeLogin() {
        file = new File(getFilesDir(), "file_login.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput("file_login.txt", MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bw = new BufferedWriter(outputStreamWriter);

        try {
            bw.write(editTextLogin.getText().toString());
            if (editTextLogin.getText().toString() == null) {
                Toast.makeText(MainActivity.this, "Введите логин", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Логин сохранен", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private void writePassword() {
        file = new File(getFilesDir(), "file_password.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput("file_password.txt", MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bw = new BufferedWriter(outputStreamWriter);

        try {
            bw.write(editTextPassword.getText().toString());
            if (editTextPassword.getText().toString() == null || editTextPassword.getText().toString().length() < 6) {
                Toast.makeText(MainActivity.this, "Введите пароль не менее 6 символов", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Пароль сохранен", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private String readLogin() {
        String login = "";
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            inputStreamReader = new InputStreamReader(openFileInput("file_login.txt"));
            bufferedReader = new BufferedReader(inputStreamReader);
            login = bufferedReader.readLine();
            StringBuilder logins = new StringBuilder();
            while (login != null) {
                logins = logins.append(login);
                login = bufferedReader.readLine();
            }
            login = logins.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return login;
    }

    private String readPassword() {
        String password = "";
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            inputStreamReader = new InputStreamReader(openFileInput("file_password.txt"));
            bufferedReader = new BufferedReader(inputStreamReader);
            password = bufferedReader.readLine();
            StringBuilder passwords = new StringBuilder();
            while (password != null) {
                passwords = passwords.append(password);
                password = bufferedReader.readLine();
            }
            password = passwords.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return password;
    }

    private void init() {
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLodin);
        buttonRegistration = findViewById(R.id.buttonRegistration);
    }
}