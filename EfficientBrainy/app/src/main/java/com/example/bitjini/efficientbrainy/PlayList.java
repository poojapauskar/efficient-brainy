package com.example.bitjini.efficientbrainy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by bitjini on 2/3/16.
 */
public class PlayList extends Activity implements View.OnClickListener {

    Button play1,play2,play3,play4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_list);

        play1=(Button)findViewById(R.id.play1);
        play2=(Button)findViewById(R.id.play2);
        play3=(Button)findViewById(R.id.play3);
        play4=(Button)findViewById(R.id.play4);

        play1.setOnClickListener(this);
        play2.setOnClickListener(this);
        play3.setOnClickListener(this);
        play4.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play1:
                     Intent intent = new Intent(PlayList.this, Otp.class);
                      startActivity(intent);
                    break;
            case R.id.play2:
                Intent intent2 = new Intent(PlayList.this, Otp.class);
                startActivity(intent2);
                break;
            case R.id.play3:
                Intent intent3 = new Intent(PlayList.this, Otp.class);
                startActivity(intent3);
                break;
            case R.id.play4:
                Intent intent4 = new Intent(PlayList.this, Otp.class);
                startActivity(intent4);
                break;

        }
    }
}
