package com.example.workassist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    FirebaseDatabase database;
    DatabaseReference myRef;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        myRef= database.getReference("Users");
        myRef.setValue(null);
        Toast.makeText(this, myRef.toString(), Toast.LENGTH_SHORT).show();

        ((FloatingActionButton) findViewById(R.id.floatingActionButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, AddSites.class));
            }
        });
    }
}