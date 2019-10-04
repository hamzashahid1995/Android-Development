package com.example.hamzashahid.contacts_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hamzashahid.project1.R;

public class MainActivity2 extends AppCompatActivity
{
    EditText edtTxt;
    static String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtTxt = (EditText)findViewById(R.id.txtField);

        edtTxt.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                fullName = edtTxt.getText().toString();
                fullName = fullName.trim().replaceAll(" +", " ");

                // check for the only valid input possible with a
                // first name then a space and then a last name
                if (fullName.contains(" "))
                {
                    if (fullName.contains(";") || fullName.contains(":") || fullName.contains("'")
                     || fullName.contains(",") || fullName.contains("*") || fullName.contains("/")
                     || fullName.contains("!") || fullName.contains("@") || fullName.contains("#")
                     || fullName.contains("%") || fullName.contains("^") || fullName.contains("&"))
                    {
                        edtTxt.requestFocus();
                        edtTxt.setError("Invalid. Only Alphabetical Characters.");
                        return false;
                    }
                    Toast.makeText(MainActivity2.this, "Successful", Toast.LENGTH_LONG).show();

                    // pass input back
                    Intent intent = new Intent();
                    intent.putExtra("name",  fullName);
                    setResult(Activity.RESULT_OK,intent);

                    startActivity(new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
                else if (!fullName.matches("[a-zA-Z ]+"))
                {
                    edtTxt.requestFocus();
                    edtTxt.setError("Only Alphabetical Characters");
                }
                else
                {
                    edtTxt.requestFocus();
                    edtTxt.setError("Must have first and last name.");
                    return false;

                }
                return true;
            }
        });
    }
}
