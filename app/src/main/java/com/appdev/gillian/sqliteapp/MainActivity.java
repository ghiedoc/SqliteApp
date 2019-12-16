package com.appdev.gillian.sqliteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    EditText etName, etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etNumber = (EditText) findViewById(R.id.etNumber);
    }

    public void submitBtn(View v){
        String name = etName.getText().toString().trim();
        String cell = etNumber.getText().toString().trim();

        try
        {
            ContactsDB db = new ContactsDB(this);
            db.open();
            db.createEntry(name, cell);
            db.close();
            Toast.makeText(MainActivity.this, "Successfully saved!", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etNumber.setText("");
        }
        catch(android.database.SQLException e)
        {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void showBtn(View v){
        startActivity(new Intent(this, Data.class));
    }

    public void updateBtn(View v){
        try
        {
            ContactsDB db = new ContactsDB(this);
            db.open();
            db.updateEntry("1", "Joanna", "23568897602");
            db.close();
            Toast.makeText(MainActivity.this, "Successfully updated!", Toast.LENGTH_SHORT).show();
        }
        catch(android.database.SQLException e)
        {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void delBtn(View v){
        try
        {
            ContactsDB db = new ContactsDB(this);
            db.open();
            db.deleteEntry("1");
            db.close();
            Toast.makeText(MainActivity.this, "Successfully deleted!", Toast.LENGTH_SHORT).show();
        }
        catch(android.database.SQLException e)
        {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
