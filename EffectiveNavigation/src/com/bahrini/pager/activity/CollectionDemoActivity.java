/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bahrini.pager.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bahrini.pager.pager.RtlSupportPagerAdapter;
import com.bahrini.pager.pager.RtlSupportViewPager;
import com.bahrini.pager.pager.RtlSupportViewPager.PagerDirection;
import com.bahrini.pager.tabs.SlidingTabLayout;
import com.bahrini.pager.R;

public class CollectionDemoActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments representing each object in a collection. We use a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter} derivative,
	 * which will destroy and re-create fragments as needed, saving and
	 * restoring their state in the process. This is important to conserve
	 * memory and is a best practice when allowing navigation between objects in
	 * a potentially large collection.
	 */
	DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;

	/**
	 * The {@link android.support.v4.view.ViewPager} that will display the
	 * object collection.
	 */
	RtlSupportViewPager mViewPager;
	SlidingTabLayout mSlidingTabLayout;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection_demo);

		/*
		 * Our DemoCollectionPagerAdapter adapter.
		 * 
		 * -> to change the direction you should change PagerDirection.PAGER_DIRECTION_RTL to be PagerDirection.PAGER_DIRECTION_LTR
		 */
		mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(
				getSupportFragmentManager(), PagerDirection.PAGER_DIRECTION_RTL); 

		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.
//		actionBar.setDisplayHomeAsUpEnabled(true);

		/*
		 * View Pager implementation 
		 */
		mViewPager = (RtlSupportViewPager) findViewById(R.id.pager);

		mViewPager.setAdapter(mDemoCollectionPagerAdapter);
		
		mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
		/*mSlidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.tabview_text);*/
		mSlidingTabLayout.setViewPager(mViewPager);
		
		/*
		 * to disable swiping on 3rd page (index 2).
		 */
//		mViewPager.blockSwipeTo(1);
		
//		mViewPager.
		
		/*
		 * 
		 */
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.activity_collection_demo, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.switch_ltr:
	            switchLTR();
	            return true;
	        case R.id.switch_rtl:
	        	switchRTL();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}



	private void switchRTL() {
		mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(
				getSupportFragmentManager(), PagerDirection.PAGER_DIRECTION_RTL); 
		mViewPager.setAdapter(mDemoCollectionPagerAdapter);
		mSlidingTabLayout.setViewPager(mViewPager);
	}

	private void switchLTR() {
		mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(
				getSupportFragmentManager(), PagerDirection.PAGER_DIRECTION_LTR); 
		mViewPager.setAdapter(mDemoCollectionPagerAdapter);
		mSlidingTabLayout.setViewPager(mViewPager);
	}

	/**
	 * {@link RtlSupportPagerAdapter} adapter implementation
	 */
	public class DemoCollectionPagerAdapter extends
			RtlSupportPagerAdapter {

		public DemoCollectionPagerAdapter(FragmentManager fm, PagerDirection pagerDirection) {
			super(fm, pagerDirection);
		}

		@Override
		public int getCount() {
			return 4;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			int mapedPosition = position;
			
			if (getPagerDirection() == PagerDirection.PAGER_DIRECTION_RTL)
				mapedPosition = (getCount() - position -1);
				
			switch (mapedPosition) {
			case 0:
				return getString(R.string.tr_page_1_title);

			case 1:
				return getString(R.string.tr_page_2_title);
				
			case 2:
				return getString(R.string.tr_page_3_title);
				
			case 3:
				return getString(R.string.tr_page_4_title);
				
			default:
				return "Not Supported";
			}
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.example.android.effectivenavigation.RtlPagerAdapter#getRtlItem(int)
		 * 
		 * We should implement the getRtlItem() not getItem(), to use the directions controls. 
		 */
		@Override
		public Fragment getLtrItem(int position) {
			Fragment fragment;
			Bundle args;
			
			switch (position) {
			case 0:
				fragment = new DemoObjectFragment();
				args = new Bundle();
				args.putInt(DemoObjectFragment.ARG_OBJECT, 1111); // Our object is
																	// just an
																	// integer :-P
				args.putString(DemoObjectFragment.ARG_FRAGMENT_NAME, "1111");
				fragment.setArguments(args);
				break;

			case 1:
				fragment = new DemoObjectFragment();
				args = new Bundle();
				args.putInt(DemoObjectFragment.ARG_OBJECT, 2222); // Our object is
																	// just an
																	// integer :-P
				args.putString(DemoObjectFragment.ARG_FRAGMENT_NAME, "2222");
				fragment.setArguments(args);
				break;
				
			case 2:
				fragment = new DemoObjectFragment();
				args = new Bundle();
				args.putInt(DemoObjectFragment.ARG_OBJECT, 3333); // Our object is
																	// just an
																	// integer :-P
				args.putString(DemoObjectFragment.ARG_FRAGMENT_NAME, "3333");
				fragment.setArguments(args);
				break;
				
			case 3:
				fragment = new DemoObjectFragment();
				args = new Bundle();
				args.putInt(DemoObjectFragment.ARG_OBJECT, 4444); // Our object is
																	// just an
																	// integer :-P
				args.putString(DemoObjectFragment.ARG_FRAGMENT_NAME, "4444");
				fragment.setArguments(args);
				break;
				
			default:
				fragment = new DemoObjectFragment();
				args = new Bundle();
				args.putInt(DemoObjectFragment.ARG_OBJECT, -1); // Our object is
																	// just an
																	// integer :-P
				args.putString(DemoObjectFragment.ARG_FRAGMENT_NAME, "ERROR");
				fragment.setArguments(args);
				break;
			}

			return fragment;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DemoObjectFragment extends Fragment {

		public static final String ARG_FRAGMENT_NAME = "fragment_name";
		public static final String ARG_OBJECT = "object";
		private String fragmentName;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_collection_object, container, false);
			Bundle args = getArguments();
			
			((TextView) rootView.findViewById(android.R.id.text1))
					.setText(Integer.toString(args.getInt(ARG_OBJECT)));
			
			setFragmentName(args.getString(ARG_FRAGMENT_NAME));
			
			return rootView;
		}

		public String getFragmentName() {
			return fragmentName;
		}

		public void setFragmentName(String fragmentName) {
			this.fragmentName = fragmentName;
		}
		
		
	}
}
