package com.bahrini.effectivenavigation;

import com.bahrini.effectivenavigation.RtlSupportViewPager.PagerDirection;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public abstract class RtlSupportPagerAdapter extends FragmentStatePagerAdapter {

	private PagerDirection mPagerDirection;
	
	public RtlSupportPagerAdapter(FragmentManager fm, PagerDirection pagerDirection) {
		super(fm);
		mPagerDirection = pagerDirection;
	}
	
	/**
	 * Swaps the position when the pager is RTL. To swap the 
	 * @param position
	 * @return
	 */
	protected int getRtlPosition(int position) {
		if (mPagerDirection == PagerDirection.PAGER_DIRECTION_RTL)
			return getCount()-1 -position;
		else 
			return position;
	}

	@Override
	public Fragment getItem(int position) {
		return getRtlItem(getRtlPosition(position));
	}
	
	/**
	 * implement your getItem normally here !
	 * @param position
	 * @return
	 */
	abstract public Fragment getRtlItem(int position);

	/**
	 * @return the Pager Direction
	 */
	public PagerDirection getPagerDirection() {
		return mPagerDirection;
	}

	/**
	 * @param pagerDirection the Pager Direction to set
	 */
	public void setPagerDirection(PagerDirection pagerDirection) {
		this.mPagerDirection = pagerDirection;
	}

}
