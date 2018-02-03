package com.example.peter.subbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by peter on 2018-02-03.
 */

public class SubscriptionActivity extends AppCompatActivity {
    private static final String FILENAME = "subs.sav";
    private EditText nameText;
    private EditText chargeText;
    private EditText commentText;
    //private ArrayList<Subscription> subList = new ArrayList<Subscription>();
    private ArrayList<Subscription> subList;
    private ArrayAdapter<Subscription> adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_service);
        adapter = new ArrayAdapter<Subscription>(this,
                    R.layout.list_item, subList);
            Intent intent = getIntent();

            subList = (ArrayList<Subscription>)
                    intent.getSerializableExtra("subList");
        nameText = (EditText) findViewById(R.id.name);
        chargeText = (EditText) findViewById(R.id.charge);
        commentText  = (EditText) findViewById(R.id.comment);
        Button DoneButton = (Button) findViewById(R.id.done);

        DoneButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                setResult(RESULT_OK);
                String name = nameText.getText().toString();
                String charge = chargeText.getText().toString();
                String comment = commentText.getText().toString();
                Subscription sub = new Subscription(name, charge, comment);
                subList.add(sub);
                adapter.notifyDataSetChanged();
                saveInFile();
                onBackPressed();
                }
            });
        }


        private void saveInFile() {
        try {

            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(subList, out);
            out.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }



}