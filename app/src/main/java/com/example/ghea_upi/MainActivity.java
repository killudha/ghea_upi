package com.example.ghea_upi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 heroViewPager;
    private ViewPager2 newsViewPager;
    private NestedScrollView mainScrollView;
    private Handler sliderHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocaleHelper.loadLocale(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainScrollView = findViewById(R.id.main_scrollview);
        setupHeroSlider();
        setupNewsSlider();
        setupShortcuts();
        setupBottomNav();
        setupLanguageSwitch();
    }

    private void setupLanguageSwitch() {
        if (findViewById(R.id.btn_lang_en) != null) {
            findViewById(R.id.btn_lang_en).setOnClickListener(v -> setLocale("en"));
        }
        if (findViewById(R.id.btn_lang_id) != null) {
            findViewById(R.id.btn_lang_id).setOnClickListener(v -> setLocale("id"));
        }
    }

    private void setupShortcuts() {
        findViewById(R.id.shortcut_berita).setOnClickListener(v -> {
            mainScrollView.smoothScrollTo(0, findViewById(R.id.news_viewpager).getTop());
        });
        findViewById(R.id.shortcut_lokasi).setOnClickListener(v -> {
            mainScrollView.smoothScrollTo(0, findViewById(R.id.section_lokasi).getTop());
        });
        findViewById(R.id.shortcut_kehidupan).setOnClickListener(v -> {
            mainScrollView.smoothScrollTo(0, findViewById(R.id.section_kehidupan).getTop());
        });
    }

    private void setupBottomNav() {
        findViewById(R.id.nav_tentang).setOnClickListener(v -> {
            startActivity(new Intent(this, TentangKampusActivity.class));
            overridePendingTransition(0, 0);
        });
        findViewById(R.id.nav_fakultas).setOnClickListener(v -> {
            startActivity(new Intent(this, FakultasActivity.class));
            overridePendingTransition(0, 0);
        });
        findViewById(R.id.nav_fasilitas).setOnClickListener(v -> {
            startActivity(new Intent(this, FasilitasActivity.class));
            overridePendingTransition(0, 0);
        });
    }

    private void setLocale(String lang) {
        LocaleHelper.setLocale(this, lang);
        
        recreate();
        overridePendingTransition(0, 0);
        
        String msg = lang.equals("en") ? "Translating to English..." : "Menerjemahkan ke Indonesia...";
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void setupHeroSlider() {
        heroViewPager = findViewById(R.id.hero_viewpager);
        List<HeroItem> heroItems = new ArrayList<>();
        heroItems.add(new HeroItem(getString(R.string.hero_title), 
                getString(R.string.hero_subtitle), 
                R.drawable.hero_image_1));
        heroItems.add(new HeroItem(getString(R.string.hero_title), 
                "", 
                R.drawable.hero_image_2));

        heroViewPager.setAdapter(new HeroAdapter(heroItems));
        setupAutoSlide(heroViewPager);
    }

    private void setupNewsSlider() {
        newsViewPager = findViewById(R.id.news_viewpager);
        List<NewsItem> newsItems = new ArrayList<>();
        newsItems.add(new NewsItem(getString(R.string.news_title_1), 
                getString(R.string.news_desc_1), 
                R.drawable.media_proxy));
        newsItems.add(new NewsItem(getString(R.string.news_title_2), 
                getString(R.string.news_desc_2), 
                R.drawable.media_proxy2));
        newsItems.add(new NewsItem(getString(R.string.news_title_3), 
                getString(R.string.news_desc_3),
                R.drawable.media_proxy3));

        newsViewPager.setAdapter(new NewsAdapter(newsItems));
        setupAutoSlide(newsViewPager);
    }

    private void setupAutoSlide(ViewPager2 viewPager) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (viewPager.getAdapter() != null) {
                    int nextItem = (viewPager.getCurrentItem() + 1) % viewPager.getAdapter().getItemCount();
                    viewPager.setCurrentItem(nextItem, true);
                    sliderHandler.postDelayed(this, 5000);
                }
            }
        };
        sliderHandler.postDelayed(runnable, 5000);
    }

    static class HeroItem {
        String title, subtitle;
        int imageRes;
        HeroItem(String t, String s, int i) { title=t; subtitle=s; imageRes=i; }
    }

    class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {
        List<HeroItem> items;
        HeroAdapter(List<HeroItem> items) { this.items = items; }
        @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup p, int t) {
            return new ViewHolder(LayoutInflater.from(p.getContext()).inflate(R.layout.item_hero, p, false));
        }
        @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            HeroItem item = items.get(position);
            holder.title.setText(item.title);
            holder.image.setImageResource(item.imageRes);
            if (item.subtitle == null || item.subtitle.isEmpty()) {
                holder.subtitle.setVisibility(View.GONE);
            } else {
                holder.subtitle.setVisibility(View.VISIBLE);
                holder.subtitle.setText(item.subtitle);
            }
        }
        @Override public int getItemCount() { return items.size(); }
        class ViewHolder extends RecyclerView.ViewHolder {
            TextView title, subtitle; ImageView image;
            ViewHolder(View v) { super(v); 
                title = v.findViewById(R.id.tv_hero_title);
                subtitle = v.findViewById(R.id.tv_hero_subtitle);
                image = v.findViewById(R.id.iv_hero);
            }
        }
    }

    static class NewsItem {
        String title, desc;
        int imageRes;
        NewsItem(String t, String d, int i) { title=t; desc=d; imageRes=i; }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        List<NewsItem> items;
        NewsAdapter(List<NewsItem> items) { this.items = items; }
        @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup p, int t) {
            return new ViewHolder(LayoutInflater.from(p.getContext()).inflate(R.layout.item_news_slider, p, false));
        }
        @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            NewsItem item = items.get(position);
            holder.title.setText(item.title);
            holder.desc.setText(item.desc);
            holder.image.setImageResource(item.imageRes);
        }
        @Override public int getItemCount() { return items.size(); }
        class ViewHolder extends RecyclerView.ViewHolder {
            TextView title, desc; ImageView image;
            ViewHolder(View v) { super(v); 
                title = v.findViewById(R.id.tv_news_title);
                desc = v.findViewById(R.id.tv_news_desc);
                image = v.findViewById(R.id.iv_news_image);
            }
        }
    }
}