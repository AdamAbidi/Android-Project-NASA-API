package com.example.monad;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class service2 extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton1;
    Button btn2;
    EditText date;
    EditText lon;
    EditText lat, dim;
    TextView tv;
    private RequestQueue mQueue;
    JSONObject jsonObject1 = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service2);
        radioGroup = findViewById(R.id.radioGroup1);
        date = findViewById(R.id.date);
        lat = findViewById(R.id.lat);
        dim = findViewById(R.id.dim);
        lon = findViewById(R.id.lon);
        tv = findViewById(R.id.adem);


        mQueue = Volley.newRequestQueue(this);
        btn2 = findViewById(R.id.btn_serv2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = fonction(view);
                radioButton1 = findViewById(n);
                sendData();

            }
        });

    }

    public int fonction(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        // radioButton1 = findViewById(radioId);
        //Toast.makeText(this, "Selected : " + radioButton1.getText(),Toast.LENGTH_SHORT).show();
        // btn2.setOnClickListener(new View.OnClickListener());

        return radioId;
    }

    public void sendData() {

      //  String Url = "https://api.nasa.gov/planetary/earth/imagery/?lat=1&lon=100&date=2014-02-01&cloud_score=true&api_key=1ML4zptf9ly1xu6eJUXnjVoLZUjJBKnP3QvPWgNJ";
        String Url = "https://api.nasa.gov/planetary/earth/imagery/?lat="+lat.getText()+"&lon="+lon.getText()+"&date="+date.getText()+"&cloud_score="+radioButton1.getText()+"&api_key=1ML4zptf9ly1xu6eJUXnjVoLZUjJBKnP3QvPWgNJ";
        //tv.setText(Url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                           // tv.setText("abc");


                           if(response.isNull("msg")==true) {
                               tv.setText("msg n'existe pas");
                               String ch1 = response.getString("url");
                               Uri uri=Uri.parse(ch1);


                               Intent a=new Intent(Intent.ACTION_VIEW,uri);
                               a.setData(Uri.parse(ch1));
                               startActivity(a);
                           }

                            if(!response.isNull("msg")) {
                               tv.setText("données fausses");


                           }



/*
                            String ch1 ="abc" ;




                            tv.setText(String.valueOf(response.isNull("msg")));
                                ch1 = response.getString("url");
                                Uri uri=Uri.parse(ch1);


                            Intent a=new Intent(Intent.ACTION_VIEW,uri);
                            a.setData(Uri.parse(ch1));
                           startActivity(a);
*/






                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);


    }
}

