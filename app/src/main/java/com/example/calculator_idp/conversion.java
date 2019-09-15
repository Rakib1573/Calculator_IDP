package com.example.calculator_idp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class conversion extends AppCompatActivity {

    private EditText taka;
    private EditText dollar;
    private Button convert;
    private EditText kg;
    private EditText pound;
    private Button convertpound;

    DatabaseReference reference;
    dbCon dbCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion);
        convert=(Button)findViewById(R.id.btnConvert);
        taka=(EditText)findViewById(R.id.etTaka);
        dollar=(EditText)findViewById(R.id.etDollar);
        convertpound=(Button)findViewById(R.id.btnConvertpound);
        kg=(EditText)findViewById(R.id.etkg);
        pound=(EditText)findViewById(R.id.etpound);
        dbCon=new dbCon();
        reference= FirebaseDatabase.getInstance().getReference().child("dbCon");
    }
    public void convert(View view) {

        if(dollar.getText().toString().equals("")) {
            taka = (EditText) findViewById(R.id.etTaka);
            int Taka = Integer.parseInt(taka.getText().toString());
            Double Dollar = 0.012;
            double result = Taka * Dollar;
            dbCon.setResult(taka.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
            dollar = (EditText) findViewById(R.id.etDollar);
            dollar.setText(Double.toString(result) + " Dollar");
            dbCon.setResult(dollar.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
        }


        else if(taka.getText().toString().equals("")){
            dollar = (EditText) findViewById(R.id.etDollar);
            int Dollar = Integer.parseInt(dollar.getText().toString());
            Double Taka = 84.50;
            double result = Taka * Dollar;
            dbCon.setResult(dollar.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
            taka = (EditText) findViewById(R.id.etTaka);
            taka.setText(Double.toString(result) + " Taka");
            dbCon.setResult(taka.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
        }
        };
    public void convertpound(View view) {

        if(pound.getText().toString().equals("")) {
            kg = (EditText) findViewById(R.id.etkg);
            double kilo = Double.parseDouble(kg.getText().toString());
            Double Pound = 2.205;
            double result = kilo * Pound;
            dbCon.setResult(kg.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
            pound = (EditText) findViewById(R.id.etpound);
            pound.setText(Double.toString(result) + " pound");
            dbCon.setResult(pound.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
        }


        else if(kg.getText().toString().equals("")){
            pound = (EditText) findViewById(R.id.etpound);
            double pound = Double.parseDouble(dollar.getText().toString());
            Double Kg = 0.454;
            double result = Kg * pound;
            dbCon.setResult(dollar.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
            kg = (EditText) findViewById(R.id.etkg);
            kg.setText(Double.toString(result) + " kg");
            dbCon.setResult(kg.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
        }
    };


    }

