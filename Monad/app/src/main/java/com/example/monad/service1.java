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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class service1 extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton1;
    Button btn2;
    EditText date,teste;
    private RequestQueue mQueue;
    //String ch ;
    JSONObject jsonObject = new JSONObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service1);
        radioGroup = findViewById(R.id.radioGroup);
        date=findViewById(R.id.dt);

        btn2=findViewById(R.id.btn);
        mQueue = Volley.newRequestQueue(this);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int n = checkButton(v);
                radioButton1=findViewById(n);

                // teste.setText(radioButton1.getText());

                sendData();


            }
        });

    }

    public void sendData(){


        String Url="https://api.nasa.gov/planetary/apod?date=" + date.getText()+"&hd=" + radioButton1.getText() + "&api_key=1ML4zptf9ly1xu6eJUXnjVoLZUjJBKnP3QvPWgNJ";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String ch1 ="abc" ;
                            ch1 = response.getString("hdurl");
                            Uri uri=Uri.parse(ch1);

                            //teste.setText(ch1);
                            Intent k=new Intent(Intent.ACTION_VIEW,uri);
                            k.setData(Uri.parse(ch1));
                            startActivity(k);



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




    public int checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        //radioButton1=findViewById(radioId);
        //Toast.makeText(this, "Selected : " + radioButton1.getText(),Toast.LENGTH_SHORT).show();
        // btn2.setOnClickListener(new View.OnClickListener());

        return radioId ;


    }
}
