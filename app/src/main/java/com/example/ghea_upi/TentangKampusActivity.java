package com.example.ghea_upi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TentangKampusActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocaleHelper.loadLocale(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_kampus);

        setupSocialMediaLinks();
        setupBottomNav();
        setupLanguageSwitch();
    }

    private void setupSocialMediaLinks() {
        if (findViewById(R.id.iv_facebook) != null) {
            findViewById(R.id.iv_facebook).setOnClickListener(v -> openUrl("https://www.facebook.com/UnivPendidikanIndonesia"));
        }
        if (findViewById(R.id.iv_twitter) != null) {
            findViewById(R.id.iv_twitter).setOnClickListener(v -> openUrl("https://twitter.com/upi_official"));
        }
        if (findViewById(R.id.iv_instagram) != null) {
            findViewById(R.id.iv_instagram).setOnClickListener(v -> openUrl("https://www.instagram.com/upiofficial"));
        }
        if (findViewById(R.id.iv_youtube) != null) {
            findViewById(R.id.iv_youtube).setOnClickListener(v -> openUrl("https://www.youtube.com/@TVUPIDIGITAL"));
        }
        if (findViewById(R.id.iv_tiktok) != null) {
            findViewById(R.id.iv_tiktok).setOnClickListener(v -> openUrl("https://www.tiktok.com/@upi.official"));
        }
    }

    private void openUrl(String url) {
        Toast.makeText(this, "Redirecting...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private void setupBottomNav() {
        findViewById(R.id.nav_beranda).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            overridePendingTransition(0, 0);
        });
        findViewById(R.id.nav_fakultas).setOnClickListener(v -> {
            startActivity(new Intent(this, FakultasActivity.class));
            finish();
            overridePendingTransition(0, 0);
        });
        findViewById(R.id.nav_fasilitas).setOnClickListener(v -> {
            startActivity(new Intent(this, FasilitasActivity.class));
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