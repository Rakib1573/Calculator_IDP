package com.example.calculator_idp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
        // IDs of all the numeric buttons
        private int[] numericButtons = {R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine};
        // IDs of all the operator buttons
        private int[] operatorButtons = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};
        // TextView used to display the output
        private TextView txtScreen;
        // Represent whether the lastly pressed key is numeric or not
        private boolean lastNumeric;
        // Represent that current state is in error or not
        private boolean stateError;
        // If true, do not allow to add another DOT
        private boolean lastDot;
        private DrawerLayout drawerLayout;
        private ActionBarDrawerToggle toggle;
        DatabaseReference reference;
        dbCon dbCon;
        Button btnEqual;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            // Find the TextView
            this.txtScreen = (TextView) findViewById(R.id.txtScreen);
            // Find and set OnClickListener to numeric buttons
            setNumericOnClickListener();
            // Find and set OnClickListener to operator buttons, equal button and decimal point button
            setOperatorOnClickListener();
            dbCon=new dbCon();
            reference= FirebaseDatabase.getInstance().getReference().child("dbCon");
            btnEqual=(Button)findViewById(R.id.btnEqual);

            drawerLayout = (DrawerLayout) findViewById(R.id.drawerid);
            NavigationView navigationView =(NavigationView) findViewById(R.id.navid);
            navigationView.setNavigationItemSelectedListener((OnNavigationItemSelectedListener) this);

            toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,R.string.open,R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.homeid){
            Intent intent = new Intent(MainActivity.this,conversion.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.calculator_id){
            Intent in = new Intent(MainActivity.this,MainActivity.class);
            startActivity(in);
        }
        else if(item.getItemId()==R.id.sci_cal_id){
            Intent i = new Intent(MainActivity.this,Scientific.class);
            startActivity(i);
        }
        return false;
    }

        /**
         * Find and set OnClickListener to numeric buttons.
         */
        private void setNumericOnClickListener() {
            // Create a common OnClickListener
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Just append/set the text of clicked button
                    Button button = (Button) v;
                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(button.getText());
                        stateError = false;
                    } else {
                        // If not, already there is a valid expression so append to it
                        txtScreen.append(button.getText());
                    }
                    // Set the flag
                    lastNumeric = true;
                }
            };
            // Assign the listener to all the numeric buttons
            for (int id : numericButtons) {
                findViewById(id).setOnClickListener(listener);
            }
        }


        /**
         * Find and set OnClickListener to operator buttons, equal button and decimal point button.
         */
        private void setOperatorOnClickListener() {
            // Create a common OnClickListener for operators
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // If the current state is Error do not append the operator
                    // If the last input is number only, append the operator
                    if (lastNumeric && !stateError) {
                        Button button = (Button) v;
                        txtScreen.append(button.getText());
                        lastNumeric = false;
                        lastDot = false;    // Reset the DOT flag
                    }
                }
            };
            // Assign the listener to all the operator buttons
            for (int id : operatorButtons) {
                findViewById(id).setOnClickListener(listener);
            }
            // Decimal point
            findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastNumeric && !stateError && !lastDot) {
                        txtScreen.append(".");
                        lastNumeric = false;
                        lastDot = true;
                    }
                }
            });

            // Clear button
           findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtScreen.setText("");  // Clear the screen
                     //Reset all the states and flags
                    lastNumeric = false;
                    stateError = false;
                    lastDot = false;

                }
            });

            // Equal button
            findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbCon.setResult(txtScreen.getText().toString().trim());
                    reference.push().setValue(dbCon);
                    Toast.makeText(MainActivity.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
                    onEqual();
                    dbCon.setResult(txtScreen.getText().toString().trim());
                    reference.push().setValue(dbCon);
                    Toast.makeText(MainActivity.this,"data inserted Successfully",Toast.LENGTH_LONG).show();
                }
            });
        }

        /**
         * Logic to calculate the solution.
         */
        private void onEqual() {
            // If the current state is error, nothing to do.
            // If the last input is a number only, solution can be found.
            if (lastNumeric && !stateError) {
                // Read the expression
                String txt = txtScreen.getText().toString();
                // Create an Expression (A class from exp4j library)
                Expression expression = new ExpressionBuilder(txt).build();
                try {
                    // Calculate the result and display
                    double result = expression.evaluate();
                    txtScreen.setText(Double.toString(result));
                    lastDot = true; // Result contains a dot
                } catch (ArithmeticException ex) {
                    // Display an error message
                    txtScreen.setText("Error");
                    stateError = true;
                    lastNumeric = false;
                }
            }
        }
}