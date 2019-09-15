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


    private EditText feet;
    private EditText inch;
    private Button convertFtInch;

    private EditText farenhite;
    private EditText celcius;
    private Button convertfar;


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

        convertFtInch=(Button)findViewById(R.id.btnConvertFtInch);
        feet=(EditText)findViewById(R.id.etFeet);
        inch=(EditText)findViewById(R.id.etInch);

        convertfar=(Button)findViewById(R.id.btn2Convert);
        farenhite=(EditText)findViewById(R.id.etFarenhite);
        celcius=(EditText)findViewById(R.id.etCelcius);



        dbCon=new dbCon();
        reference= FirebaseDatabase.getInstance().getReference().child("dbCon");
    }
    public void convert(View view) {

        if(dollar.getText().toString().equals("")) {
            taka = (EditText) findViewById(R.id.etTaka);
            Double Taka = Double.parseDouble(taka.getText().toString());
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
            Double Dollar = Double.parseDouble(dollar.getText().toString());
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



    public void setConvertFtInch(View view) {

        if(inch.getText().toString().equals("")) {
            feet = (EditText) findViewById(R.id.etFeet);
            Double Feet = Double.parseDouble(feet.getText().toString());
            Double Inch = 12.0;
            double result = Feet * Inch;
            dbCon.setResult(feet.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
            inch = (EditText) findViewById(R.id.etInch);
            inch.setText(Double.toString(result) + " Inch");
            dbCon.setResult(inch.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
        }


        else if(feet.getText().toString().equals("")){
            inch = (EditText) findViewById(R.id.etInch);
            double Inch = Double.parseDouble (inch.getText().toString());
            Double Feet = 0.083;
            double result = Feet * Inch;
            dbCon.setResult(inch.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
            feet = (EditText) findViewById(R.id.etFeet);
            feet.setText(Double.toString(result) + " Feet");
            dbCon.setResult(feet.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
        }
    };

    public void convertfar(View view) {

        if(celcius.getText().toString().equals("")) {
            farenhite = (EditText) findViewById(R.id.etFarenhite);
            double Farenhite = Double.parseDouble (farenhite.getText().toString());
            Double Celcius = -17.2222;
            double result = Farenhite * Celcius;
            dbCon.setResult(farenhite.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
            celcius = (EditText) findViewById(R.id.etCelcius);
            celcius.setText(Double.toString(result) + " Celcius");
            dbCon.setResult(celcius.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
        }


        else if(farenhite.getText().toString().equals("")){
            celcius = (EditText) findViewById(R.id.etCelcius);
            double Celcius = Double.parseDouble (celcius.getText().toString());
            Double Farenhite = 33.8;
            double result = Farenhite * Celcius;
            dbCon.setResult(celcius.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
            farenhite = (EditText) findViewById(R.id.etFarenhite);
            farenhite.setText(Double.toString(result) + " Farenhite");
            dbCon.setResult(farenhite.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
        }
    };


    }

