package com.bingtechs.portablewebcam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bingtechs.portablewebcam.databinding.ActivityChangeIpactivityBinding;

public class ChangeIPActivity extends AppCompatActivity {

    ActivityChangeIpactivityBinding bind;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityChangeIpactivityBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        preferenceManager = new PreferenceManager(this);

        bind.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bind.edtUrl.getText().toString().equals("")){
                    preferenceManager.putString(Constants.KEY_URL, bind.edtUrl.getText().toString());
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });

    }
}