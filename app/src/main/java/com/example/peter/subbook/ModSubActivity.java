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

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ModSubActivity extends AppCompatActivity {
    private static final String FILENAME = "subs.sav";
    private Subscription listItem;
    private ArrayList<Subscription> subList;
    private ArrayAdapter<Subscription> adapter;
    private int position;
    private EditText modName;
    private EditText modDate;
    private EditText modCharge;
    private EditText modComment;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_sub);
        Intent intent = getIntent();

        listItem = (Subscription) intent.getSerializableExtra("listItem");
        subList = (ArrayList<Subscription>)
                intent.getSerializableExtra("subList");
        position = intent.getIntExtra("position",-1);

        adapter = new ArrayAdapter<Subscription>(this,
                R.layout.list_item, subList);

        modName = (EditText) findViewById(R.id.modName);
        modName.setText(listItem.getName());
        modDate = (EditText) findViewById(R.id.modDate);
        modDate.setText(listItem.getDate().toString());
        modCharge = (EditText) findViewById(R.id.modCharge);
        modCharge.setText(listItem.getCharge());
        modComment = (EditText) findViewById(R.id.modComment);
        modComment.setText(listItem.getComment());

        Button DoneButton = (Button) findViewById(R.id.doneButton);
        Button DeleteButton = (Button) findViewById(R.id.deleteButton);

        DoneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setResult(RESULT_OK);
                String name = modName.getText().toString();
                String charge = modCharge.getText().toString();
                String comment = modComment.getText().toString();

                subList.get(position).setName(name);
                subList.get(position).setCharge(charge);
                subList.get(position).setComment(comment);
                adapter.notifyDataSetChanged();
                saveInFile();
                onBackPressed();
            }
        });

        DeleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setResult(RESULT_OK);
                subList.remove(position);
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
