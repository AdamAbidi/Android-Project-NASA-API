package com.example.monad;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class finish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);



        String[] arraysrc=new String[25];
        int p;
        TextView txt;
        Button chob;





            txt=findViewById(R.id.txtv);
            chob=findViewById(R.id.chob);
            Bundle b=getIntent().getExtras();

            arraysrc=b.getStringArray("arraysrc1");
            p=Integer.parseInt(b.getString("nbr"));



            //txt.setText(String.valueOf(p));

            String[] arraysrcc=new String[p];


            for(int k=0;k<p;k++){
                arraysrcc[k]=arraysrc[k];

            }

            txt.setText(arraysrcc[0]);



            final Spinner spin = (Spinner) findViewById(R.id.spinner);



            ArrayList<String> list = new ArrayList<>();

            for (int k=0;k < p ; k++){
                list.add(arraysrcc[k]);
            }



            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arraysrcc);
            spin.setAdapter(adapter);
            chob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url= spin.getItemAtPosition(spin.getSelectedItemPosition()).toString();
                    Uri uri=Uri.parse(url);


                    Intent a=new Intent(Intent.ACTION_VIEW,uri);
                    a.setData(Uri.parse(url));
                    startActivity(a);

                }
            });


        }



    }


