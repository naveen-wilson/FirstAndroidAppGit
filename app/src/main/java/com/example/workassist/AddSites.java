package com.example.workassist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.workassist.models.Site;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddSites extends AppCompatActivity implements OnClickListener {
    //ArrayAdapter<String> adapterList;
    //ArrayAdapter<String> adapterSpinner;
    //Button add;
    Button clear;
    //ArrayList<String> list;
    ListView listWork;
    EditText location;
    private FirebaseAuth mAuth;
    EditText ownerName;
    EditText sqFeet;
    EditText remarks;
    Button submit;
    //Spinner works;
    Toolbar toolbar;
    FirebaseDatabase database;
    DatabaseReference myRef;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_add_sites);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        remarks = (EditText) findViewById(R.id.remarks);
        this.ownerName = (EditText) findViewById(R.id.ownerName);
        this.location = (EditText) findViewById(R.id.location);
        this.sqFeet = (EditText) findViewById(R.id.sqFeet);
        //this.listWork = (ListView) findViewById(R.id.listWork);
        //this.works = (Spinner) findViewById(R.id.addWork);
        //this.add = (Button) findViewById(R.id.button2);
        this.clear = (Button) findViewById(R.id.clear);
        this.submit = (Button) findViewById(R.id.submit);
        //this.list = new ArrayList<>();
        //adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.workList));
        //this.works.setAdapter(this.adapterSpinner);
        //this.list.add("The Works you add, will be Listed Here !!");
        //adapterList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.list);
        //this.listWork.setAdapter(adapterList);
        //this.add.setOnClickListener(this);
        this.submit.setOnClickListener(this);
        this.clear.setOnClickListener(this);



    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                this.database = FirebaseDatabase.getInstance();
                myRef= database.getReference("Users/"+mAuth.getCurrentUser().getUid()).child("Sites");
                Site site = new Site();
                site.setLocation(location.getText().toString());
                site.setOwnerName(ownerName.getText().toString());
                site.setSqFeet(Float.valueOf(sqFeet.getText().toString()));
                site.setRemarks(remarks.getText().toString());
                myRef.push().setValue(site)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    // Write was successful!
                    // ...
                    AddSites.this.startActivity(new Intent(AddSites.this, MainActivity.class));
                    finish();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Write failed
                            // ...
                            Toast.makeText(AddSites.this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    });

                return;
//            case R.id.button2 :
//                this.list.add(this.works.getSelectedItem().toString());
//                this.adapterList.notifyDataSetChanged();
//                //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//                return;
            case R.id.clear:
                ownerName.getText().clear();
                location.getText().clear();
                sqFeet.getText().clear();
                remarks.getText().clear();
                //adapterList.clear();
                return;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected value: ");
                sb.append(view.getId());
                throw new IllegalStateException(sb.toString());
        }
    }
}