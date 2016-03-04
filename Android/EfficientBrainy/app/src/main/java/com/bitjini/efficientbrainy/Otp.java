package com.example.bitjini.efficientbrainy;

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
public class Otp extends Fragment implements View.OnClickListener {
    Button okBtn, cancel;
    View otpView;
    TextView txtMsg;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        otpView = inflater.inflate(R.layout.otp, container, false);

        txtMsg=(TextView) otpView.findViewById(R.id.msg);
        okBtn = (Button) otpView.findViewById(R.id.ok_btn);
        cancel = (Button)otpView. findViewById(R.id.cancel_btn);
        okBtn.setOnClickListener(this);
        cancel.setOnClickListener(this);

        return otpView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_btn:

                Intent intent=new Intent(getActivity(), Played_File.class);
                // Retrieving audio from PlayList
                String value1=getArguments().getString("audio");
                if(value1!=null) {
                    //add data to the Intent object
                    intent.putExtra("audio", value1);   //put the value to pass

                    //start the second activity
                    startActivity(intent);}
//                Fragment newfragment = new Played_File();
//
//                // Retrieving audio from PlayList
//                String value1=getArguments().getString("audio");
//                if(value1!=null) {
//                    Bundle args1 = new Bundle();
//                    args1.putString("audio", value1);
//                    newfragment.setArguments(args1);
//                }
//                // get the id of fragment
//                FrameLayout contentView1 = (FrameLayout) otpView.findViewById(R.id.otp_frame);
//             // Insert the fragment by replacing any existing fragment
//                FragmentManager fragmentManager1 = getFragmentManager();
//                fragmentManager1.beginTransaction()
//                        .replace(contentView1.getId(), newfragment)
//                        .commit();

                break;
            case R.id.cancel_btn:
                Fragment newfragment2 = new PlayList();
                // get the id of fragment
                FrameLayout contentView2 = (FrameLayout) otpView.findViewById(R.id.otp_frame);

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager2 = getFragmentManager();
                fragmentManager2.beginTransaction()
                        .replace(contentView2.getId(), newfragment2)
                        .commit();

                break;

        }
    }
}
