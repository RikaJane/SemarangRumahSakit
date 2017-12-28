package com.rika.semarangrs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rika.semarangrs.model.RS;

import java.util.ArrayList;

/**
 * Created by User on 11/12/2017.
 */

public class DetailActivity extends AppCompatActivity {
    private ArrayList<Integer> mImageID;
    private String latitude, longitude;
    private boolean isRS = true;
    private RS mData;
    private String tipe;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailumum);
        isRS = getIntent().getBooleanExtra("RS", true);
        if(isRS){
            tipe = "Umum";
        }
        else{
            tipe = "BPJS";
        }
        ActionBar toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        convertData();
        //addImageID();
        //bindData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void  convertData(){
        Intent intent = getIntent();
        String data = intent.getStringExtra(Intent.EXTRA_TEXT);
        Gson gson = new Gson();
        mData = gson.fromJson(data, new TypeToken<RS>(){}.getType());
        TextView tv_judul = (TextView) findViewById(R.id.judul);
        TextView tv_info = (TextView) findViewById(R.id.info);
        TextView tv_keterangan = (TextView) findViewById(R.id.keterangan);
        ImageView image = (ImageView) findViewById(R.id.gambar);
        getSupportActionBar().setTitle("RS " + tipe);
        tv_judul.setText(mData.getNama());
        tv_info.setText(mData.getDetail());
        tv_keterangan.setText(mData.getFasilitas());
        Glide.with(this).load(mData.getGambar()).into(image);
        String lokasi = mData.getKoordinat();
        if (lokasi != null) {
            String koordinat[] = lokasi.split(",");
            latitude = koordinat[0];
            longitude = koordinat[1];
        }

    }
    private void bindData() {
        int position;
        String judul;
        String info[] = getResources().getStringArray(R.array.detail_rs);
        String keterangan[] = getResources().getStringArray(R.array.fasilitas_rs);
        String lokasi[];
        if (isRS) {
            lokasi = getResources().getStringArray(R.array.koordinat_umum);
        }
        else{
            lokasi = getResources().getStringArray(R.array.koordinat_bpjs);
        }

        //lokasi = getResources().getStringArray(R.array.koordinat_umum);

        Intent intent = getIntent();
        judul = intent.getStringExtra(Intent.EXTRA_TEXT);
        position = intent.getIntExtra(RSUmum.POSITION, 0);
        if (lokasi[position].length() > 0) {
            String koordinat[] = lokasi[position].split(",");
            latitude = koordinat[0];
            longitude = koordinat[1];
        }
        TextView tv_judul = (TextView) findViewById(R.id.judul);
        TextView tv_info = (TextView) findViewById(R.id.info);
        TextView tv_keterangan = (TextView) findViewById(R.id.keterangan);
        ImageView image = (ImageView) findViewById(R.id.gambar);
        tv_judul.setText(judul);
        tv_info.setText(info[position]);
        tv_keterangan.setText(keterangan[position]);
        image.setImageResource(mImageID.get(position));

    }

    private void addImageID() {
        mImageID = new ArrayList<>();
        mImageID.add(R.drawable.satu1);
        mImageID.add(R.drawable.satu1);
        mImageID.add(R.drawable.satu1);
        mImageID.add(R.drawable.satu1);
        mImageID.add(R.drawable.satu1);
        mImageID.add(R.drawable.satu1);
        mImageID.add(R.drawable.satu1);
        mImageID.add(R.drawable.satu1);
    }

    public void goToMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("latitude", Double.parseDouble(latitude));
        intent.putExtra("longitude", Double.parseDouble(longitude));
        startActivity(intent);
    }
}
