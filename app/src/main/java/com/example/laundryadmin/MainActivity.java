package com.example.laundryadmin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

	private SectionPagerAdapter adapter;
	private TabLayout tabLayout;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		viewPager = findViewById(R.id.pager);
		tabLayout = findViewById(R.id.tabs);

		viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager()));
		tabLayout.setupWithViewPager(viewPager);
	}

	public class SectionPagerAdapter extends FragmentPagerAdapter {

		public SectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
				case 0:
					return new AddLaundryFragment();
				case 1:
					return new LaundryCompleteFragment();
				case 2:
				default:
					return new LaundryGivenFragment();
			}
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
				case 0:
					return getString(R.string.add_order);
				case 1:
					return getString(R.string.order_complete);
				case 2:
				default:
					return getString(R.string.order_given);
			}
		}
	}
}
