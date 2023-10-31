package com.example.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextView facebook;
    private TextView creacte_account;
    private Button button;
    private EditText email;
    private EditText mdps;
    private TextView email_err;
    private TextView mdps_err;
    private String emailText;
    private String mdpsText;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        facebook = findViewById(R.id.facebook);
        creacte_account = findViewById(R.id.create_account);
        button = findViewById(R.id.button);
        email = findViewById(R.id.email);
        mdps = findViewById(R.id.password);
        email_err = findViewById(R.id.email_error);
        mdps_err = findViewById(R.id.mdps_error);

        creacte_account.setOnClickListener(view -> {
            try{
                Intent register = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(register);
            }catch (Exception e){
                Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        facebook.setOnClickListener( view -> {
            Uri uriUrl = Uri.parse("https://www.facebook.com/login/");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        });

        button.setOnClickListener(view -> {
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

            if (!email_err.getText().toString().equals("") || !mdps_err.getText().toString().equals("")) {
            } else {
                //Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (emailText.matches(emailPattern)) {
                    PersonalInformation info = new PersonalInformation(emailText);
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("obj", info);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    email_err.setText("Invalid Email");
                }
            }
        });

        email.addTextChangedListener(textWatcher);
        mdps.addTextChangedListener(textWatcher);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            emailText = email.getText().toString();
            mdpsText = mdps.getText().toString();
        }
    };
}
