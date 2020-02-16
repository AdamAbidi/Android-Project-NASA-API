package com.example.monad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class service3 extends AppCompatActivity {
    EditText sol,page;
    TextView zone;
    Spinner cam;
    Button btn;
    private RequestQueue mQueue;
    String [] arraysrc=new String[25];
    JSONObject jsonObject = new JSONObject();
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_service3);
            sol=findViewById(R.id.sol);
            page=findViewById(R.id.page);
            cam=findViewById(R.id.cam);
            btn=findViewById(R.id.button3);
            zone=findViewById(R.id.text);
            mQueue = Volley.newRequestQueue(this);


            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendData1();
                }
            });
        }





        public void sendData1() {

            String Url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol="+sol.getText()+"&page="+page.getText()+"&camera="+cam.getItemAtPosition(cam.getSelectedItemPosition()).toString()+"&api_key=1ML4zptf9ly1xu6eJUXnjVoLZUjJBKnP3QvPWgNJ";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("photos");
                                int k;
                                for (k = 0; k < jsonArray.length(); k++) {
                                    JSONObject photo = jsonArray.getJSONObject(k);
                                    arraysrc[k]=photo.getString("img_src");

                                }
                                i=k;

                                Intent in;
                                Bundle b=new Bundle();
                                b.putStringArray("arraysrc1",arraysrc);
                                b.putString("nbr",String.valueOf(i));
                                zone.setText(b.getString("nbr"));
                                in=new Intent(service3.this,finish.class);
                                in.putExtras(b);
                                startActivity(in);




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            mQueue.add(request);
        }

}
