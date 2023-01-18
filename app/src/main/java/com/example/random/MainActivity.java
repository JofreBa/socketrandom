package com.example.random;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {
    private Socket socket;
    private TextView num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num = findViewById(R.id.numero);
        try {
            socket = IO.socket("http://a21jofbalgon.labs.inspedralbes.cat:7099");
        } catch (URISyntaxException e) {}
        socket.connect();
        socket.on("chat message", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                runOnUiThread(() -> {
                    num.setText(args[0].toString());
                });
            }
        });
    }
}