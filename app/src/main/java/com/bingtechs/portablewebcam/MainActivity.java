package com.bingtechs.portablewebcam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bingtechs.portablewebcam.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bind;
    PreferenceManager preferenceManager;
    int clickCounter = 1;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        preferenceManager = new PreferenceManager(this);

        url = preferenceManager.getString(Constants.KEY_URL);

        bind.myToolbar.setTitle("");
        setSupportActionBar(bind.myToolbar);

        bind.webView.loadUrl(url);

        bind.webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                bind.progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                bind.progressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

        });

        bind.appName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickCounter == 5){
                    startActivity(new Intent(getApplicationContext(), ChangeIPActivity.class));
                    clickCounter = 1;
                    return;
                }
                clickCounter++;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (bind.webView.canGoBack()){
            bind.webView.goBack();
        }else{
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to Exit?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("NO", null)
                    .setIcon(R.drawable.warning)
                    .show();
        }
    }
}