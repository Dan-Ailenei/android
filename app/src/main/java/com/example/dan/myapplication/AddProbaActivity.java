package com.example.dan.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dan.myapplication.Entities.Proba;
import com.example.dan.myapplication.api.AppDatabase;
import com.example.dan.myapplication.api.ProbaDao;

public class AddProbaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_proba);
        final TextInputEditText numeView = (TextInputEditText )findViewById(R.id.nume);
        final TextInputEditText swimstyleView = (TextInputEditText ) findViewById(R.id.swimstyle);
        final TextInputEditText distantaView = (TextInputEditText ) findViewById(R.id.distanta);

        final Button back_button = (Button) findViewById(R.id.button3);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = back_button.getContext();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                context.startActivity(intent);
            }
        });

        Button sumbit = (Button) findViewById(R.id.button);

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nume = numeView.getText().toString();
                final String swimstyle = swimstyleView.getText().toString();
                final String distanta = distantaView.getText().toString();

                boolean cancel = false;
                if (nume.equals("")){
                    numeView.setError("Nu are voie sa fie null");
                    cancel = true;
                }
                if (swimstyle.equals("")){
                    swimstyleView.setError("Nu are voie sa fie null");
                    cancel = true;
                }
                boolean isFloat = true;
                try{
                    Float.parseFloat(distanta);
                }catch(NumberFormatException e){
                    isFloat = false;
                }

                if(!isFloat || swimstyle.equals("")){
                    distantaView.setError("Trebuie sa fie float");
                    cancel =true;
                }

                if(cancel){
                    numeView.requestFocus();
                } else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Context context = back_button.getContext();
                            ProbaDao access = AppDatabase.getInstance(context).daoAccess();
                            Proba proba = new Proba(nume, swimstyle, Double.parseDouble(distanta));
                            proba.setNewData(true);
                            access.insertProba(proba);

                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);
                        }
                    }).start();
                }
            }
        });
    }
}
