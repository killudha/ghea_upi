package com.example.ghea_upi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class TentangKampusActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_kampus);

        setupSocialMediaLinks();
        setupBottomNav();
    }

    private void setupSocialMediaLinks() {
        findViewById(R.id.iv_facebook).setOnClickListener(v -> openUrl("https://www.facebook.com/UnivPendidikanIndonesia"));
        findViewById(R.id.iv_twitter).setOnClickListener(v -> openUrl("https://twitter.com/upi_official"));
        findViewById(R.id.iv_instagram).setOnClickListener(v -> openUrl("https://www.instagram.com/upiofficial"));
        findViewById(R.id.iv_youtube).setOnClickListener(v -> openUrl("https://www.youtube.com/user/UPITV"));
        findViewById(R.id.iv_tiktok).setOnClickListener(v -> openUrl("https://www.tiktok.com/@upiofficial"));
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private void setupBottomNav() {
        findViewById(R.id.nav_beranda).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });
        findViewById(R.id.nav_fakultas).setOnClickListener(v -> {
            startActivity(new Intent(this, FakultasActivity.class));
        });
        findViewById(R.id.nav_penelitian).setOnClickListener(v -> {
            startActivity(new Intent(this, PenelitianActivity.class));
        });
    }
}