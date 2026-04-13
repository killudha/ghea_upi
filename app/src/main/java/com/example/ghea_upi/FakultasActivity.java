package com.example.ghea_upi;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class FakultasActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fakultas);

        setupBottomNav();
    }

    private void setupBottomNav() {
        findViewById(R.id.nav_beranda).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });
        findViewById(R.id.nav_tentang).setOnClickListener(v -> {
            startActivity(new Intent(this, TentangKampusActivity.class));
        });
        findViewById(R.id.nav_penelitian).setOnClickListener(v -> {
            startActivity(new Intent(this, PenelitianActivity.class));
        });
        // Tombol Fakultas aktif (tidak perlu intent ke diri sendiri)
    }
}