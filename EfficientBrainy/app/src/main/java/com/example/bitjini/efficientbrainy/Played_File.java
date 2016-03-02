package com.example.bitjini.efficientbrainy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by bitjini on 2/3/16.
 */
public class Played_File extends Activity {
    Button stopExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.played_file);

        stopExit=(Button)findViewById(R.id.stopExit);

        stopExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Played_File.this,PlayList.class);
                startActivity(intent);
            }
        });

    }
}
