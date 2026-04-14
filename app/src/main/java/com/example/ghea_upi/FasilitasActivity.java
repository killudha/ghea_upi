package com.example.ghea_upi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FasilitasActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocaleHelper.loadLocale(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasilitas);

        setupBottomNav();
        setupLanguageSwitch();
    }

    private void setupBottomNav() {
        findViewById(R.id.nav_beranda).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            overridePendingTransition(0, 0);
        });
        findViewById(R.id.nav_tentang).setOnClickListener(v -> {
            startActivity(new Intent(this, TentangKampusActivity.class));
            finish();
            overridePendingTransition(0, 0);
        });
        findViewById(R.id.nav_fakultas).setOnClickListener(v -> {
            startActivity(new Intent(this, FakultasActivity.class));
            finish();
            overridePendingTransition(0, 0);
        });
    }

    private void setupLanguageSwitch() {
        if (findViewById(R.id.btn_lang_en) != null) {
            findViewById(R.id.btn_lang_en).setOnClickListener(v -> setLocale("en"));
        }
        if (findViewById(R.id.btn_lang_id) != null) {
            findViewById(R.id.btn_lang_id).setOnClickListener(v -> setLocale("id"));
        }
    }

    private void setLocale(String lang) {
        LocaleHelper.setLocale(this, lang);
        
        recreate();
        overridePendingTransition(0, 0);
        
        String msg = lang.equals("en") ? "Translating to English..." : "Menerjemahkan ke Indonesia...";
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}