package com.example.dcube.swamptest;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference users;
    private DatabaseReference patientName;
    private DatabaseReference medicine;

    EditText medName;
    EditText patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        medName = (EditText) findViewById(R.id.medName);
        patient = (EditText) findViewById(R.id.patientName);

    }

    public void button(View view){

        final String name = patient.getText().toString();
        final DatabaseReference userRef  = mDatabase.child("Patient").child(name);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (!dataSnapshot.hasChild(name)) {

                    userRef.child("Name").setValue(name);
                    userRef.child("Medicine").setValue(medName.getText().toString());

                }

                Toast.makeText(DataActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
