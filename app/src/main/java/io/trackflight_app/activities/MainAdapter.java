package io.trackflight_app.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public interface MainAdapter {
    @NonNull
    Fragment getItem(int position);

    int getCount();

    @Nullable
    CharSequence getPageTitle(int position);
}
