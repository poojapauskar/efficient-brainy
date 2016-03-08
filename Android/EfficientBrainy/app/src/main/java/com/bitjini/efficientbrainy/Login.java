package com.bitjini.efficientbrainy;

import android.app.Activity;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bitjini on 2/3/16.
 */
public class Login extends Fragment {
    String URL_Login = "https://efficient-brainy.herokuapp.com/login/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9";

    String token_sharedPreference, phone_sharedPreference, vz_id_sharedPreference;

    public static final String VZCARD_PREFS = "MySharedPref";
    public SharedPreferences sharedPreferences;
    public String TOKEN_KEY = "token";
    ;

    String username, password;
    private ProgressDialog progress;
    Button loginBtn;
    View loginView;
    TextView unameTxt, pwdTxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        loginView = inflater.inflate(R.layout.login, container, false);
        unameTxt = (TextView) loginView.findViewById(R.id.uname);
        pwdTxt = (TextView) loginView.findViewById(R.id.pwd);


        loginBtn = (Button) loginView.findViewById(R.id.loginbtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sendGetRequest(view);
//               Fragment newfragment = new PlayList();
//                // get the id of fragment
//                FrameLayout contentView2 = (FrameLayout) loginView.findViewById(R.id.login_frame);
//
//                // Insert the fragment by replacing any existing fragment
//                FragmentManager fragmentManager1 = getFragmentManager();
//                fragmentManager1.beginTransaction()
//                        .replace(contentView2.getId(), newfragment)
//                        .commit();

            }
        });
        return loginView;
    }


    // method to call AsyncTask PostClass for registration
    public void sendGetRequest(View View) {
        new GetHttpRequest(getActivity()).execute(URL_Login);
    }

    /* *
      * PostClass for registration
      */
    private class GetHttpRequest extends AsyncTask<String, Void, String> {

        private final Context context;

        public GetHttpRequest(Context c) {
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
            } catch (IOException e) {
                return "Unable to download the requested page.";
            }
        }

        private String downloadUrl(String urlString) throws IOException {
            String response = null;
            try {
//                final TextView outputView = (TextView) findViewById(R.id.content);


                HttpClient httpclient = new DefaultHttpClient();



                username = unameTxt.getText().toString();
                password = pwdTxt.getText().toString();

                HttpGet httpGet = new HttpGet(urlString); // create new httpGet object
                httpGet.setHeader("Accept", "application/xml");
                httpGet.setHeader("Content-Type", "application/xml");
                httpGet.addHeader("Username",username);
                httpGet.addHeader("Password",password);


                StringBuilder sb = new StringBuilder();

                    HttpResponse responsehttp = httpclient.execute(httpGet); // execute httpGet
                    StatusLine statusLine = responsehttp.getStatusLine();
                    int statusCode = statusLine.getStatusCode();
                    if (statusCode == HttpStatus.SC_OK) {
                        // System.out.println(statusLine);
                        sb.append(statusLine + "\n");
                        HttpEntity e = responsehttp.getEntity();
                        String entity = EntityUtils.toString(e);
                        sb.append(entity);
                        Log.e("Response :",""+entity);
                    } else {
                        sb.append(statusLine + "\n");
                        // System.out.println(statusLine);
                    }

                return sb.toString(); // return the String
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                }

            return null;

        }

        protected void onPostExecute(String result) {
            progress.dismiss();
        }
    }
}