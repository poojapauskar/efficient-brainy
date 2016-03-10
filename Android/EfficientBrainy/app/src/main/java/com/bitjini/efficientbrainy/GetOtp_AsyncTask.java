package com.bitjini.efficientbrainy;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;

/**
 * Created by bitjini on 9/3/16.
 */
public class GetOtp_AsyncTask extends AsyncTask<String, Void, String> {
    private ProgressDialog progress;
    private final Context context;

    public GetOtp_AsyncTask(Context c) {
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

            HttpClient client = new DefaultHttpClient();

            HttpResponse httpResponse;


            HttpGet request = new HttpGet(urlString);


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
            JSONArray jsonresponse= new JSONArray(result);

                int status = jsonresponse.getJSONObject(0).getInt("status");
                String objects = jsonresponse.getJSONObject(0).getString("objects");
                Log.e(" status =",""+status);
                Log.e(" objects =",""+objects);

            JSONArray jsonresponse2= new JSONArray(objects);
            int otp = jsonresponse2.getJSONObject(0).getInt("otp");
            Log.e(" otp =",""+otp);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
