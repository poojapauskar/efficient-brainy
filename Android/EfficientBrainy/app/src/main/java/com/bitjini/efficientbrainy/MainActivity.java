package com.bitjini.efficientbrainy;

import android.app.Activity;
import android.content.Intent;


import android.os.Bundle;
import android.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    Button splashBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splashBtn=(Button)findViewById(R.id.splashbtn);

        splashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment newfragment = new Login();
                FrameLayout contentView2 = (FrameLayout)findViewById(R.id.mainFrame);
                getFragmentManager()
                        .beginTransaction()
                        .replace(contentView2.getId(),newfragment)
                        .commit();


            }
        });

    }
}
