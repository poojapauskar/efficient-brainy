package com.bitjini.efficientbrainy;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by bitjini on 2/3/16.
 */
public class Login extends Fragment {
    Button loginBtn;
    View loginView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         loginView = inflater.inflate(R.layout.login, container, false);



        loginBtn=(Button)loginView.findViewById(R.id.loginbtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Fragment newfragment = new PlayList();
                // get the id of fragment
                FrameLayout contentView2 = (FrameLayout) loginView.findViewById(R.id.login_frame);

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager1 = getFragmentManager();
                fragmentManager1.beginTransaction()
                        .replace(contentView2.getId(), newfragment)
                        .commit();

            }
        });
       return loginView;
    }
}
