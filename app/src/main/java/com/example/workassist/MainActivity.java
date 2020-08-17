package com.example.workassist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        System.out.println("**********************************************************");
        myRef= database.getReference().child("Users/"+mAuth.getCurrentUser().getUid()).child("Sites");
        myRef.setValue(null);
        System.out.println(mAuth.getCurrentUser().toString()+" : "+mAuth.getUid()+" : "+mAuth.getCurrentUser().getEmail());
        System.out.println("**********************************************************");
        Toast.makeText(this, mAuth.getCurrentUser().toString()+" : "+mAuth.getUid()+" : "+mAuth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
        //                      Toast.makeText(this, myRef.toString(), Toast.LENGTH_SHORT).show();

        ((FloatingActionButton) findViewById(R.id.floatingActionButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, AddSites.class));
            }
        });
    }
    class MyFailureListener implements OnFailureListener {
        @Override
        public void onFailure(@NonNull Exception exception) {
            String errorMessage = exception.getMessage();
            // test the errorCode and errorMessage, and handle accordingly
            System.out.println(errorMessage);
        }
    }
}
/*

{
        "rules": {
        "$uid": {
        ".read": "auth !== null && auth.uid === $uid",
        ".write": "auth !== null && auth.uid === $uid"
        }
        }
        }*/
