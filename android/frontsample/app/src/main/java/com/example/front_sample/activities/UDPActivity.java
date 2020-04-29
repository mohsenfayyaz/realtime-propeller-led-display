package com.example.front_sample.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.front_sample.R;
import com.example.front_sample.utils.HttpHandler.HttpHandler;
import com.example.front_sample.utils.udp.UDPHandler;

import java.io.IOException;

public class UDPActivity extends AppCompatActivity {
    private UDPHandler udpHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udp);
        final Button button = findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    new HttpHandler();
                } catch (IOException ioe) {
                    System.err.println("Couldn't start server:\n" + ioe);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        udpHandler = new UDPHandler();
        udpHandler.startServer();
        super.onStart();
    }

    @Override
    protected void onStop() {
        if(udpHandler != null){
            try {
                udpHandler.stopServer();
            } catch (Exception e) {
                e.printStackTrace();
            }
            udpHandler = null;
        }
        super.onStop();
    }


}