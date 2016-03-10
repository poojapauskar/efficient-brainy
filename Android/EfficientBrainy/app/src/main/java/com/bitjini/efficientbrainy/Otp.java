package com.bitjini.efficientbrainy;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by bitjini on 2/3/16.
 */

/* *
      * class for creating  custom dialog box
      */
public  class Otp extends DialogFragment implements View.OnClickListener {

    String URL_Resend_OTP = "https://efficient-brainy.herokuapp.com/generate_otp/?access_token=";
    public Activity c;
    public Dialog d;
    Button okBtn, resend;
    View otpView;
    TextView otpTxt;

    private ProgressDialog progress;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.otp, container, false);
        getDialog().setTitle("OTP Dialog");
        otpTxt = (TextView) rootView.findViewById(R.id.otp_txt);
        okBtn = (Button)rootView.findViewById(R.id.ok_btn);
        resend = (Button)rootView.findViewById(R.id.resend);
        okBtn.setOnClickListener(this);
        resend.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_btn:
                String constantOtp="4096";
                String otp = otpTxt.getText().toString();

                Bundle mArgs = getArguments();
                String myValue = mArgs.getString("audio"); // Retrieving audio from PlayList
                  Log.e("constantOtp ",""+constantOtp);
                Log.e("otp ",""+otp);
                if(constantOtp.equals(otp)){
                Intent intent=new Intent(getActivity(), Played_File.class);
                    intent.putExtra("audio", myValue);   //put the value to pass
                    //start the second activity

                startActivity(intent);
                }
                else
                {
                    Toast.makeText(getActivity(),"Invalid otp",Toast.LENGTH_LONG).show();
                }
                dismiss();

                break;
            case R.id.resend:
                Login l=new Login();
                l.sharedPreferences = getActivity().getSharedPreferences(l.Efficient_Brainy, 0);
                String token_sharedPreference =l.sharedPreferences.getString(l.TOKEN_KEY, null);
                System.out.println(" getting token from sharedpreference " + token_sharedPreference);


                new GetOtp_AsyncTask(getActivity()).execute(URL_Resend_OTP+token_sharedPreference);
                break;

        }
    }
}

// class ConfirmOtp_AsyncTask extends AsyncTask<String, Void, String> {
//
//    private ProgressDialog progress;
//    private final Context context;
//
//    public ConfirmOtp_AsyncTask(Context c) {
//        this.context = c;
//    }
//
//    protected void onPreExecute() {
//        progress = new ProgressDialog(this.context);
//        progress.setMessage("Loading");
//        progress.show();
//    }
//
//    @Override
//    protected String doInBackground(String... urls) {
//        // params comes from the execute() call: params[0] is the url.
//        try {
//            return downloadUrl(urls[0]);
//        } catch (Exception e) {
//            return "Unable to download the requested page.";
//        }
//    }
//
//    private String downloadUrl(String urlString)  {
//        String response = null;
//        try {
////                final TextView outputView = (TextView) findViewById(R.id.content);
//
//
////
//            otp = otpTxt.getText().toString();
//
//
//            HttpClient client = new DefaultHttpClient();
//
//            HttpResponse httpResponse;
//
//            HttpGet request = new HttpGet(urlString);
//            request.addHeader("username",username);
//            request.addHeader("password",password);
//
//            httpResponse = client.execute(request);
//            int responseCode = httpResponse.getStatusLine().getStatusCode();
//            String message = httpResponse.getStatusLine().getReasonPhrase();
//            Log.e("responseCode..",""+responseCode);
//            Log.e("message..",""+message);
//            HttpEntity entity = httpResponse.getEntity();
//
//            if (entity != null) {
//
//                InputStream instream = entity.getContent();
//                response = convertStreamToString(instream);
//                Log.e("Response..",""+response);
//                // Closing the input stream will trigger connection release
//                instream.close();
//            }
//            return response;
//
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//    private  String convertStreamToString(InputStream is) {
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        StringBuilder sb = new StringBuilder();
//
//        String line = null;
//        try {
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return sb.toString();
//    }
//    protected void onPostExecute(String result) {
//        progress.dismiss();
//
//        try {
//            JSONObject jsonresrponse= new JSONObject(result);
//            String valid=jsonresrponse.getString("valid");
//            int status=jsonresrponse.getInt("status");
//            String access_token=jsonresrponse.getString("access_token");
//            Log.e(" access_token =",""+access_token);
//            Log.e(" status =",""+status);
//
//            if(status!=200)
//            {
//                Toast.makeText(getActivity(),"Invalid Credentials. Please enter valid username and password",Toast.LENGTH_LONG).show();
//            }
//            else{
//                sharedPreferences = getActivity().getSharedPreferences(Efficient_Brainy, 0);
//                SharedPreferences.Editor sEdit = sharedPreferences.edit();
//                String token= String.valueOf(sEdit.putString(TOKEN_KEY, access_token));
//                sEdit.commit();
//                System.out.println(" saving token generated " + token);
//
//
//                SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences(Efficient_Brainy, 0);
//                token_sharedPreference=sharedPreferences2.getString(TOKEN_KEY,access_token);
//
//                Log.e(" getting token  ",""+ token_sharedPreference);
//
//                Fragment newfragment = new PlayList();
//                // get the id of fragment
//                FrameLayout contentView2 = (FrameLayout) loginView.findViewById(R.id.login_frame);
//
//                // Insert the fragment by replacing any existing fragment
//                FragmentManager fragmentManager1 = getFragmentManager();
//                fragmentManager1.beginTransaction()
//                        .replace(contentView2.getId(), newfragment)
//                        .commit();
//
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//}
//}
