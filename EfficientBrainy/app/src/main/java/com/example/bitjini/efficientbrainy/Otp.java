package com.example.bitjini.efficientbrainy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by bitjini on 2/3/16.
 */
public class Otp extends Activity {
    Button okBtn,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);

        okBtn=(Button)findViewById(R.id.ok_btn);
        cancel=(Button)findViewById(R.id.cancel_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Otp.this,Played_File.class);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Otp.this,PlayList.class);
                startActivity(intent);
            }
        });

    }
}
