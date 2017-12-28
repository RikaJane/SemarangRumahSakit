package com.rika.semarangrs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by User on 13/12/2017.
 */

public class RSBPJS extends AppCompatActivity implements RSAdapter.RSListener {
    private RSAdapter adapter;
    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsbpjs);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        String judul [] = getResources().getStringArray(R.array.judul_rs);
        Log.v("Main",judul[0]);
        String ket [] =  getResources().getStringArray(R.array.keterangan_rs);
        adapter = new RSAdapter(judul, ket, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(RSBPJS.this,DetailActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT,adapter.getJudul(position));
        intent.putExtra(POSITION,position);
        startActivity(intent);
    }
}
