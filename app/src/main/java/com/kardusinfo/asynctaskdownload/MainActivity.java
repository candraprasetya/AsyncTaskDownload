package com.kardusinfo.asynctaskdownload;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText textURL;
    ImageView imageDownloaded;
    Button btnDownload;
    DownloadImageTask downloadImageTask;
    TextView textStatus;
    Button btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisialiasasi
        textURL = findViewById(R.id.urlText);
        imageDownloaded = findViewById(R.id.imageMantab);
        btnDownload = findViewById(R.id.buttonDownload);
        textStatus = findViewById(R.id.statusKoneksi);
        btnCheck = findViewById(R.id.btnCheckKoneksi);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternet()){
                    textStatus.setText("Status Koneksi : Connected");
                }else{
                    textStatus.setText("Status Koneksi : Disconnected");
                }
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternet()){
                    downloadImageTask = new DownloadImageTask(MainActivity.this, imageDownloaded);
                    downloadImageTask.execute(textURL.getText().toString());
                }else{
                    Toast.makeText(MainActivity.this, "Download Error, Please Check Your Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean checkInternet() {
        boolean connectStatus = true;
        ConnectivityManager ConnectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ConnectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            connectStatus = true;
        } else {
            connectStatus = false;
        }
        return connectStatus;
    }
}
