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
import android.view.inputmethod.InputMethodManager;
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
import java.util.concurrent.ExecutionException;

/**
 * Created by bitjini on 2/3/16.
 */

/* *
      * class for creating  custom dialog box
      */
public  class Otp extends DialogFragment implements View.OnClickListener {

    String URL_Resend_OTP = "https://efficient-brainy.herokuapp.com/generate_otp/?access_token=";
    String URL_Verify_OTP = "https://efficient-brainy.herokuapp.com/valid_otp/?access_token=";
    String token_sharedPreference;

    public Activity c;
    public Dialog d;
    Button okBtn, resend;
    View otpView;
    public TextView otpTxt;

    private ProgressDialog progress;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.otp, container, false);
//        getDialog().setTitle("OTP Dialog");
        otpTxt = (TextView) rootView.findViewById(R.id.otp_txt);
        okBtn = (Button) rootView.findViewById(R.id.ok_btn);
        resend = (Button) rootView.findViewById(R.id.resend);
        okBtn.setOnClickListener(this);
        resend.setOnClickListener(this);

        Login l = new Login();
        l.sharedPreferences = getActivity().getSharedPreferences(l.Efficient_Brainy, 0);
        token_sharedPreference = l.sharedPreferences.getString(l.TOKEN_KEY, null);
        System.out.println(" getting token from sharedpreference " + token_sharedPreference);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_btn:

                //To hide keypad
                InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (getActivity().getCurrentFocus() != null){
                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);}

                try {
                    String receiveDate=new ConfirmOtp_AsyncTask(getActivity()).execute(URL_Verify_OTP+token_sharedPreference).get();
                    JSONObject jsonresrponse = new JSONObject(receiveDate);
                    int status = jsonresrponse.getInt("status");

                Bundle mArgs = getArguments();
                String myValue = mArgs.getString("audio"); // Retrieving audio from PlayList
                    String myfile = mArgs.getString("file");
                Log.e("status ", "" + status);

                if (status==200) {
                    Intent intent = new Intent(getActivity(), Played_File.class);
                    intent.putExtra("audio", myValue);   //put the value to pass
                    intent.putExtra("file", myfile);
                    //start the second activity

                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Invalid otp", Toast.LENGTH_LONG).show();
                }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                dismiss();

                break;
            case R.id.resend:


                new GetOtp_AsyncTask(getActivity()).execute(URL_Resend_OTP + token_sharedPreference);
                break;

        }
    }


    class ConfirmOtp_AsyncTask extends AsyncTask<String, Void, String> {

        private ProgressDialog progress;
        private final Context context;

        public ConfirmOtp_AsyncTask(Context c) {
            this.context = c;
        }

        protected void onPreExecute() {
            progress = new ProgressDialog(this.context);
            progress.setMessage("Loading");
            progress.show();
        }

        @Override
        protected String doInBackground(String... urls) {
            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (Exception e) {
                return "Unable to download the requested page.";
            }
        }

        private String downloadUrl(String urlString) {
            String response = null;
            try {
//                final TextView outputView = (TextView) findViewById(R.id.content);


//
                String otp = otpTxt.getText().toString();


                HttpClient client = new DefaultHttpClient();

                HttpResponse httpResponse;

                HttpGet request = new HttpGet(urlString);
                request.addHeader("otp", otp);


                httpResponse = client.execute(request);
                int responseCode = httpResponse.getStatusLine().getStatusCode();
                String message = httpResponse.getStatusLine().getReasonPhrase();
                Log.e("responseCode..", "" + responseCode);
                Log.e("message..", "" + message);
                HttpEntity entity = httpResponse.getEntity();

                if (entity != null) {

                    InputStream instream = entity.getContent();
                    response = convertStreamToString(instream);
                    Log.e("Response..", "" + response);
                    // Closing the input stream will trigger connection release
                    instream.close();
                }
                return response;

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        private String convertStreamToString(InputStream is) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }

        protected void onPostExecute(String result) {
            progress.dismiss();

            try {
                JSONObject jsonresrponse = new JSONObject(result);
                String status = jsonresrponse.getString("status");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
