package com.example.app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        PersonalInformation info = (PersonalInformation) bundle.getSerializable("obj");

        listView = findViewById(R.id.list);
        ArrayList<String> infoList = new ArrayList<>();

        if (info.getNom() == null) {
            infoList.add("Email: " + info.getEmail());
        } else {
            infoList.add("Nom: " + info.getNom());
            infoList.add("Prenom: " + info.getPrenom());
            infoList.add("Email: " + info.getEmail());
            infoList.add("Telephone: " + info.getCode() + " " + info.getPhone());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, infoList);

        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
    }

}
