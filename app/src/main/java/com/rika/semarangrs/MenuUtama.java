package com.rika.semarangrs;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuUtama extends AppCompatActivity {

    ImageView  masuk1;
    ImageView  masuk2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        masuk1 = (ImageView)findViewById(R.id.umum);
        masuk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent =  new Intent(MenuUtama.this,RSUmum.class);
                mainIntent.putExtra("RS", true);
                startActivity(mainIntent);
            }
        });

        masuk2 = (ImageView)findViewById(R.id.bpjs);
        masuk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(MenuUtama.this,RSUmum.class);
                mainIntent.putExtra("RS", false);
                startActivity(mainIntent);
            }
        });
    }

    //public void masuk1 (int position)
    //{
    //    startActivity(new Intent(MenuUtama.this,RSUmum.class));
    //    finish();
    //}
}
