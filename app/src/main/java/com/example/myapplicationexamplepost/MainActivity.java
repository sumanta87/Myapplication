package com.example.myapplicationexamplepost;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcv_list;
    public String Baseurl="http://esolz.co.in/lab6/proringer_latest/";
    public  String Messagelistapi=Baseurl+"app_homeowner_myproject?";
    MessageDataShowadapter messageDataShowadapter;
    ArrayList<Messagesetgetpojo>messagesetgetpojos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messagesetgetpojos=new ArrayList<>();
        rcv_list=(RecyclerView)findViewById(R.id.rcv_list);
        rcv_list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        infodata();

    }

    public void infodata()
    {
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected String doInBackground(String... strings) {

                String Api=Messagelistapi+"&user_id="+"56"+"&&type=Y";
                OkHttpClient client= new OkHttpClient.Builder().connectTimeout(10000, TimeUnit.MILLISECONDS).retryOnConnectionFailure(false).build();

                Request request= new Request.Builder()
                        .get()
                        .url(Api)
                        .build();


                Response response= null;

                try {
                    response = client.newCall(request).execute();
                    String responsebody=response.body().string();
                    try {
                        JSONObject job= new JSONObject(responsebody);
                        Log.d("dataresponse", String.valueOf(job));
                        JSONArray infoarry= job.getJSONArray("info_array");
                        for (int i=0;i<infoarry.length();i++)
                        {
                            JSONObject jo= infoarry.getJSONObject(i);

                            Messagesetgetpojo messagesetgetpojo= new Messagesetgetpojo();
                            messagesetgetpojo.setProject_name(jo.getString("project_name"));
                            messagesetgetpojo.setProject_image(jo.getString("project_image"));
                            for (int j=0;j<jo.length();j++)
                            {
                                JSONObject jobb= jo.getJSONObject("property_type");
                                messagesetgetpojo.setProperty_type_name(jobb.getString("property_type_name"));
                                JSONObject jobb1= jo.getJSONObject("project_category");
                                messagesetgetpojo.setCategory_name(jobb1.getString("category_name"));
                            }
                            messagesetgetpojos.add(messagesetgetpojo);

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (messageDataShowadapter==null)
                {
                    messageDataShowadapter= new MessageDataShowadapter(MainActivity.this,messagesetgetpojos);
                    rcv_list.setAdapter(messageDataShowadapter);
                }
                else
                {
                    messageDataShowadapter.notifyDataSetChanged();
                }
            }


        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }

}
