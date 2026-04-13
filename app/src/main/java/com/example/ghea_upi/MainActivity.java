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

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 heroViewPager;
    private ViewPager2 newsViewPager;
    private Handler sliderHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupHeroSlider();
        setupNewsSlider();
        setupBottomNav();
    }

    private void setupBottomNav() {
        findViewById(R.id.nav_tentang).setOnClickListener(v -> {
            startActivity(new Intent(this, TentangKampusActivity.class));
        });
        findViewById(R.id.nav_fakultas).setOnClickListener(v -> {
            startActivity(new Intent(this, FakultasActivity.class));
        });
        findViewById(R.id.nav_penelitian).setOnClickListener(v -> {
            startActivity(new Intent(this, PenelitianActivity.class));
        });
    }

    private void setupHeroSlider() {
        heroViewPager = findViewById(R.id.hero_viewpager);
        List<HeroItem> heroItems = new ArrayList<>();
        heroItems.add(new HeroItem("Melesat Bersama\nDemi UPI Gemilang", 
                "Memajukan ilmu pengetahuan, mengembangkan bakat, dan memperkuat reputasi global Indonesia melalui pendidikan dan inovasi", 
                R.drawable.hero_image_1, true));
        heroItems.add(new HeroItem("Melesat Bersama\nDemi UPI Gemilang", 
                "", 
                R.drawable.hero_image_2, false));

        heroViewPager.setAdapter(new HeroAdapter(heroItems));
        setupAutoSlide(heroViewPager);
    }

    private void setupNewsSlider() {
        newsViewPager = findViewById(R.id.news_viewpager);
        List<NewsItem> newsItems = new ArrayList<>();
        newsItems.add(new NewsItem("Targetkan Mutu Internasional, YPSL Bumi Siliwangi Perkuat Sinergi Lewat Halalbihalal", 
                "Bandung, UPI Yayasan Penyelenggara Sekolah Laboratorium (YPSL) Bumi Siliwangi menyelenggarakan Halalbihalal keluarga besar dengan tema \"Hikmah Ramadhan dan Idul Fitri untuk Memacu...", 
                R.drawable.media_proxy));
        newsItems.add(new NewsItem("UPI Raih Penghargaan Diktisaintek Berdampak 2024", 
                "Universitas Pendidikan Indonesia kembali menorehkan prestasi gemilang dengan meraih penghargaan kategori Diktisaintek Berdampak 2024...", 
                R.drawable.media_proxy2));
        newsItems.add(new NewsItem("Inovasi Pendidikan Digital UPI di Era Global", 
                "UPI terus mendorong transformasi digital dalam sistem pendidikan nasional melalui berbagai inovasi teknologi yang dikembangkan oleh civitas akademika...", 
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
                    sliderHandler.postDelayed(this, 5000); // Slide every 5 seconds
                }
            }
        };
        sliderHandler.postDelayed(runnable, 5000);
    }

    // --- Adapters & Models ---

    static class HeroItem {
        String title, subtitle;
        int imageRes;
        boolean showExtra;
        HeroItem(String t, String s, int i, boolean e) { title=t; subtitle=s; imageRes=i; showExtra=e; }
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
            if (item.showExtra) {
                holder.extraCard.setVisibility(View.VISIBLE);
                holder.subtitle.setVisibility(View.GONE);
            } else {
                holder.extraCard.setVisibility(View.GONE);
                holder.subtitle.setVisibility(View.VISIBLE);
                holder.subtitle.setText(item.subtitle);
            }
        }
        @Override public int getItemCount() { return items.size(); }
        class ViewHolder extends RecyclerView.ViewHolder {
            TextView title, subtitle; ImageView image; View extraCard;
            ViewHolder(View v) { super(v); 
                title = v.findViewById(R.id.tv_hero_title);
                subtitle = v.findViewById(R.id.tv_hero_subtitle);
                image = v.findViewById(R.id.iv_hero);
                extraCard = v.findViewById(R.id.cv_hero_extra);
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