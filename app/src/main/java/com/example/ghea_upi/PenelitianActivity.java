package com.example.ghea_upi;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class PenelitianActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penelitian);

        findViewById(R.id.nav_beranda).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
        findViewById(R.id.nav_tentang).setOnClickListener(v -> {
            startActivity(new Intent(this, TentangKampusActivity.class));
            finish();
        });
        findViewById(R.id.nav_fakultas).setOnClickListener(v -> {
            startActivity(new Intent(this, FakultasActivity.class));
            finish();
        });
    }
}