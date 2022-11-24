package com.example.daysmatter.fragment;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.daysmatter.MainActivity;


public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 4;

    private HomeFragment homeFragment;
    private BooksFragment booksFragment;
    private HistoryFragment historyFragment;
    private UserFragment userFragment;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        homeFragment=new HomeFragment();
        booksFragment=new BooksFragment();
        historyFragment=new HistoryFragment();
        userFragment=new UserFragment();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = homeFragment;
                break;
            case MainActivity.PAGE_TWO:
                fragment = booksFragment;
                break;
            case MainActivity.PAGE_THREE:
                fragment = historyFragment;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = userFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}