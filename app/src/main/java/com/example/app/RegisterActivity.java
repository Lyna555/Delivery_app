package com.example.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText prenom;
    private EditText nom;
    private EditText email;
    private EditText phone;
    private EditText mdps;
    private Spinner spinner;
    private Button button;

    private TextView prenom_err;
    private TextView nom_err;
    private TextView email_err;
    private TextView mdps_err;
    private TextView phone_err;

    private String prenomText;
    private String nomText;
    private String emailText;
    private String phoneText;
    private String mdpsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        button = findViewById(R.id.button);

        prenom = findViewById(R.id.prenom);
        nom = findViewById(R.id.nom);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        mdps = findViewById(R.id.mdps);

        prenom_err = findViewById(R.id.prenom_err);
        nom_err = findViewById(R.id.nom_err);
        email_err = findViewById(R.id.email_err);
        mdps_err = findViewById(R.id.mdps_err);
        phone_err = findViewById(R.id.phone_err);

        spinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.phone_code, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        button.setOnClickListener(view -> {

                if (prenomText == null || prenomText.equals("")) {
                    prenom_err.setText("* Field required!");
                } else {
                    prenom_err.setText("");
                }

                if (nomText == null || nomText.equals("")) {
                    nom_err.setText("* Field required!");
                } else {
                    nom_err.setText("");
                }

                if (emailText == null || emailText.equals("")) {
                    email_err.setText("* Field required!");
                } else {
                    email_err.setText("");
                }

                if (mdpsText == null || mdpsText.equals("")) {
                    mdps_err.setText("* Field required!");
                } else {
                    mdps_err.setText("");
                }

                if (phoneText == null || phoneText.equals("")) {
                    phone_err.setText("* Field required!");
                } else {
                    phone_err.setText("");
                }

                if (!prenom_err.getText().toString().equals("") || !nom_err.getText().toString().equals("") ||
                        !email_err.getText().toString().equals("") || !mdps_err.getText().toString().equals("") ||
                        !phone_err.getText().toString().equals("")) {
                } else {
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    String passwd_pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=-_])(?=\\S+$).{8,20}$";

                    if (emailText.matches(emailPattern) && mdpsText.matches(passwd_pattern)) {
                        PersonalInformation info = new PersonalInformation(prenomText, nomText, emailText,
                                spinner.getSelectedItem().toString(), phoneText);
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("obj", info);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {

                        if (!mdpsText.matches(passwd_pattern)){
                            mdps_err.setText("Invalid password");
                        } else {
                            mdps_err.setText("");
                        }

                        if (!emailText.matches(emailPattern)){
                            email_err.setText("Invalid email");
                        } else {
                            email_err.setText("");
                        }
                    }
                }
        });

        prenom.addTextChangedListener(textWatcher);
        nom.addTextChangedListener(textWatcher);
        email.addTextChangedListener(textWatcher);
        phone.addTextChangedListener(textWatcher);
        mdps.addTextChangedListener(textWatcher);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            prenomText = prenom.getText().toString();
            nomText = nom.getText().toString();
            emailText = email.getText().toString();
            phoneText = phone.getText().toString();
            mdpsText = mdps.getText().toString();
        }
    };
}
