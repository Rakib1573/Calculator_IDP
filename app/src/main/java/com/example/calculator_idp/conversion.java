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

    private EditText feet;
    private EditText inch;
    private Button convertFtInch;
    DatabaseReference reference;
    dbCon dbCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion);
        convert = (Button) findViewById(R.id.btnConvert);
        taka = (EditText) findViewById(R.id.etTaka);
        dollar = (EditText) findViewById(R.id.etDollar);

        convertFtInch = (Button) findViewById(R.id.btnConvertFtInch);
        feet = (EditText) findViewById(R.id.etFeet);
        inch = (EditText) findViewById(R.id.etInch);


        dbCon = new dbCon();
        reference = FirebaseDatabase.getInstance().getReference().child("dbCon");
    }

    public void convert(View view) {

        if (dollar.getText().toString().equals("")) {
            taka = (EditText) findViewById(R.id.etTaka);
            Double Taka = Double.parseDouble(taka.getText().toString());
            Double Dollar = 0.012;
            double result = Taka * Dollar;
            dbCon.setResult(taka.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this, "data inserted Successfully", Toast.LENGTH_LONG).show();
            dollar = (EditText) findViewById(R.id.etDollar);
            dollar.setText(Double.toString(result) + " Dollar");
            dbCon.setResult(dollar.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this, "data inserted Successfully", Toast.LENGTH_LONG).show();
        } else if (taka.getText().toString().equals("")) {
            dollar = (EditText) findViewById(R.id.etDollar);
            int Dollar = Integer.parseInt(dollar.getText().toString());
            Double Taka = 84.50;
            double result = Taka * Dollar;
            dbCon.setResult(dollar.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this, "data inserted Successfully", Toast.LENGTH_LONG).show();
            taka = (EditText) findViewById(R.id.etTaka);
            taka.setText(Double.toString(result) + " Taka");
            dbCon.setResult(taka.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this, "data inserted Successfully", Toast.LENGTH_LONG).show();
        }
    }

    ;


    public void setConvertFtInch(View view) {

        if (inch.getText().toString().equals("")) {
            feet = (EditText) findViewById(R.id.etFeet);
            Double Feet = Double.parseDouble(feet.getText().toString());
            Double Inch = 12.0;
            double result = Feet * Inch;
            dbCon.setResult(feet.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this, "data inserted Successfully", Toast.LENGTH_LONG).show();
            inch = (EditText) findViewById(R.id.etInch);
            inch.setText(Double.toString(result) + " Inch");
            dbCon.setResult(inch.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this, "data inserted Successfully", Toast.LENGTH_LONG).show();
        } else if (feet.getText().toString().equals("")) {
            inch = (EditText) findViewById(R.id.etInch);
            Double Inch = Double.parseDouble(inch.getText().toString());
            Double Feet = 0.083;
            double result = Feet * Inch;
            dbCon.setResult(inch.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this, "data inserted Successfully", Toast.LENGTH_LONG).show();
            feet = (EditText) findViewById(R.id.etFeet);
            feet.setText(Double.toString(result) + " Feet");
            dbCon.setResult(feet.getText().toString().trim());
            reference.push().setValue(dbCon);
            Toast.makeText(conversion.this, "data inserted Successfully", Toast.LENGTH_LONG).show();
        }
    };

}


