package com.rika.semarangrs;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.rika.semarangrs.model.RS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10/12/2017.
 */

public class RSUmum extends AppCompatActivity implements RSAdapter.RSListener{
    private RSAdapter adapter;
    public static final String POSITION = "position";
    private boolean isRS = true;
    private String tipe;
    private RecyclerView recyclerView;
    private List<RS> mData;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ValueEventListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsumum);
        isRS = getIntent().getBooleanExtra("RS", true);
        mDatabase = FirebaseDatabase.getInstance();
        if(isRS){
            tipe = "Umum";
            mReference = mDatabase.getReference("umum");
        }
        else{
            tipe = "BPJS";
            mReference = mDatabase.getReference("bpjs");
        }
        recyclerView = (RecyclerView) findViewById(R.id.rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchData();
        /*String judul [], ket[];
        if(isRS){
            judul = getResources().getStringArray(R.array.judul_rs);
            ket =  getResources().getStringArray(R.array.keterangan_rs);
        }
        else{
            judul = getResources().getStringArray(R.array.judul_rsbpjs);
            ket =  getResources().getStringArray(R.array.keterangan_rsbpjs);
        }
        //judul = getResources().getStringArray(R.array.judul_rs);
        //ket =  getResources().getStringArray(R.array.keterangan_rs);
        adapter = new RSAdapter(judul, ket, this);
        recyclerView.setAdapter(adapter);*/

    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(RSUmum.this,DetailActivity.class);
        //intent.putExtra(Intent.EXTRA_TEXT,adapter.getJudul(position));
        if(isRS){
            intent.putExtra("RS", true);
        }
        else{
            intent.putExtra("RS", false);
        }
        Gson gson = new Gson();
        String data = gson.toJson(mData.get(position));
        intent.putExtra(Intent.EXTRA_TEXT, data);
        //intent.putExtra(POSITION,position);
        startActivity(intent);
    }
    private void fetchData(){ //ambil data dari database
        mListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    if(dataSnapshot.getChildrenCount() > 0){
                        mData = new ArrayList<>();
                        for(DataSnapshot child : dataSnapshot.getChildren()){
                            RS data = child.getValue(RS.class);
                            mData.add(data);
                        }
                        adapter = new RSAdapter(mData ,RSUmum.this);
                        recyclerView.setAdapter(adapter);

                    }
                    else{
                        Toast.makeText(RSUmum.this,"Tidak ada RS" + tipe + "tersedia", Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    Toast.makeText(RSUmum.this,"Tidak ada RS" + tipe + "tersedia", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RSUmum.this,"Gagal mengambil data dari server", Toast.LENGTH_SHORT).show();

            }
        };
        mReference.addListenerForSingleValueEvent(mListener);
    }
}
