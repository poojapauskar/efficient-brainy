package com.bitjini.efficientbrainy;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by bitjini on 2/3/16.
 */

/* *
      * class for creating  custom dialog box
      */
public  class Otp extends DialogFragment implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    Button okBtn, cancel;
    View otpView;
    TextView txtMsg;

    private ProgressDialog progress;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.otp, container, false);
        getDialog().setTitle("Simple Dialog");

        okBtn = (Button)rootView.findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_btn:

                Intent intent=new Intent(getActivity(), Played_File.class);

                Bundle mArgs = getArguments();
                String myValue = mArgs.getString("audio"); // Retrieving audio from PlayList
                intent.putExtra("audio", myValue);   //put the value to pass
                    //start the second activity

                startActivity(intent);
                dismiss();

                break;
//            case R.id.cancel_btn:
//                Fragment newfragment2 = new PlayList();
//                // get the id of fragment
//                FrameLayout contentView2 = (FrameLayout) otpView.findViewById(R.id.otp_frame);
//
//                // Insert the fragment by replacing any existing fragment
//                FragmentManager fragmentManager2 = getFragmentManager();
//                fragmentManager2.beginTransaction()
//                        .replace(contentView2.getId(), newfragment2)
//                        .commit();
//
//                break;

        }
    }
}