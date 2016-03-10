package com.bitjini.efficientbrainy;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
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

/**
 * Created by bitjini on 2/3/16.
 */
public class Login extends Fragment {
    String URL_Login = "https://efficient-brainy.herokuapp.com/login/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9";

    String token_sharedPreference;

    public static final String Efficient_Brainy = "SharedPref";
    public SharedPreferences sharedPreferences;
    public String TOKEN_KEY = "access_token";
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
                InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (getActivity().getCurrentFocus() != null){
                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);}
//
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
            } catch (Exception e) {
                return "Unable to download the requested page.";
            }
        }

        private String downloadUrl(String urlString)  {
            String response = null;
            try {
//                final TextView outputView = (TextView) findViewById(R.id.content);


//
                username = unameTxt.getText().toString();
                password = pwdTxt.getText().toString();

             HttpClient client = new DefaultHttpClient();

            HttpResponse httpResponse;

                HttpGet request = new HttpGet(urlString);
                request.addHeader("username",username);
                request.addHeader("password",password);

                httpResponse = client.execute(request);
                int responseCode = httpResponse.getStatusLine().getStatusCode();
                String message = httpResponse.getStatusLine().getReasonPhrase();
                Log.e("responseCode..",""+responseCode);
                Log.e("message..",""+message);
                HttpEntity entity = httpResponse.getEntity();

                if (entity != null) {

                    InputStream instream = entity.getContent();
                    response = convertStreamToString(instream);
                   Log.e("Response..",""+response);
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
        private  String convertStreamToString(InputStream is) {

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
                JSONObject jsonresrponse= new JSONObject(result);

                int status=jsonresrponse.getInt("status");
                String access_token=jsonresrponse.getString("access_token");
                Log.e(" access_token =",""+access_token);
                Log.e(" status =",""+status);

                if(status==200)
                {
                    sharedPreferences = getActivity().getSharedPreferences(Efficient_Brainy, 0);
                    SharedPreferences.Editor sEdit = sharedPreferences.edit();
                    String token= String.valueOf(sEdit.putString(TOKEN_KEY, access_token));
                    sEdit.commit();
                    System.out.println(" saving token generated " + token);


                    SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences(Efficient_Brainy, 0);
                    token_sharedPreference=sharedPreferences2.getString(TOKEN_KEY,access_token);

                    Log.e(" getting token  ",""+ token_sharedPreference);

                    Fragment newfragment = new PlayList();
                    // get the id of fragment
                    FrameLayout contentView2 = (FrameLayout) loginView.findViewById(R.id.login_frame);

                    // Insert the fragment by replacing any existing fragment
                    FragmentManager fragmentManager1 = getFragmentManager();
                    fragmentManager1.beginTransaction()
                            .replace(contentView2.getId(), newfragment)
                            .commit();


                }
                else  if(status==400 || username.length()==0 || password.length()==0){
                    // to hide the keyboard
                    InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (getActivity().getCurrentFocus() != null){
                        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);}
                    Toast.makeText(getActivity(),"Invalid Credentials. Please enter valid username and password",Toast.LENGTH_LONG).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

