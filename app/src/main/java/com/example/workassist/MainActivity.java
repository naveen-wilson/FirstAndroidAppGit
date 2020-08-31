package com.example.workassist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.workassist.models.Site;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayAdapter<String> adapterList;
    CustomSiteList list;
    ListView listView;
    private ArrayList<String> siteList;

    private ArrayList<String> locationList;

    private FirebaseAuth mAuth;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);

        siteList = new ArrayList<String>();
        locationList = new ArrayList<String>();
        listView = findViewById(R.id.ListView);

        list = new CustomSiteList(this,siteList,locationList);
        listView.setAdapter(list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),"You Selected "+siteList.get(position)+ "'s Site",Toast.LENGTH_SHORT).show();        }
        });

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        System.out.println("**********************************************************");
        myRef= database.getReference().child("Users/"+mAuth.getCurrentUser().getUid()+"/Sites");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    //String s = data.getKey()+data.getValue()+data.getRef();
                    Site s = data.getValue(Site.class);
                    siteList.add(s.getOwnerName());
                    locationList.add(s.getLocation());
                    list.notifyDataSetChanged();
                    System.out.println("**************"+data.getValue());
                    System.out.println(s.getOwnerName() + " *****:***** " + s.getLocation());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("/*********************"+databaseError);
            }
        };
myRef.addValueEventListener(postListener);


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

