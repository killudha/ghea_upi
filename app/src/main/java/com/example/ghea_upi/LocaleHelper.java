package com.example.ghea_upi;

import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

public class LocaleHelper {
    
    public static void setLocale(Context context, String lang) {
        // Standardize: Indonesian internal code is "in", but "id" is also accepted.
        // AppCompatDelegate handles persistence automatically if configured in Manifest.
        String langCode = lang.equals("id") ? "in" : lang;
        LocaleListCompat appLocale = LocaleListCompat.forLanguageTags(langCode);
        AppCompatDelegate.setApplicationLocales(appLocale);
    }

    public static void loadLocale(Context context) {
        // With AppCompatDelegate.setApplicationLocales and autoStoreLocales=true in Manifest,
        // AppCompat automatically restores the locale. No manual loading is strictly required,
        // but we provide this method to satisfy the activity lifecycle calls.
    }
}