package com.bahrini.pager.pager;

import com.bahrini.pager.pager.RtlSupportViewPager.PagerDirection;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

public abstract class RtlSupportPagerAdapter extends FragmentStatePagerAdapter {

	private PagerDirection mPagerDirection;
	
	protected int mCutOfPage;

	protected Fragment mPrimaryItem;

	public RtlSupportPagerAdapter(FragmentManager fm, PagerDirection pagerDirection) {
		super(fm);
		mPagerDirection = pagerDirection;
	}
	
	/**
	 * Swaps the position when the pager is RTL. To swap the 
	 * @param position
	 * @return
	 */
	protected int getMappedPosition(int position) {
		if (mPagerDirection == PagerDirection.PAGER_DIRECTION_RTL)
			return getCount()-1 -position;
		else 
			return position;
	}

	@Override
	public Fragment getItem(int position) {
		return getLtrItem(getMappedPosition(position));
	}
	
	/**
	 * implement your getItem normally here !
	 * @param position
	 * @return
	 */
	abstract public Fragment getLtrItem(int position);

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
	
	/**
	 * @param cutOfPage the cutOfPage to set
	 */
	public void setCutOfPage(int cutOfPage) {
		this.mCutOfPage = cutOfPage;
	}

	
	@Override
    public int getItemPosition(Object object) {
        if (object == mPrimaryItem /*||(mCutOfPage >= (getCount()-1 index of last page in LTR) && mPagerDirection == PagerDirection.PAGER_DIRECTION_LTR) || 
        		(mCutOfPage <= (0 index of last page in RTL) && mPagerDirection == PagerDirection.PAGER_DIRECTION_RTL)*/) {
            // Re-use the current fragment (its position never changes)
            return POSITION_UNCHANGED;
        }

        return POSITION_NONE;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        try{
        	mPrimaryItem = (Fragment) object;
        }
        catch(Exception ex){
        	// TODO:
        }        
    }
    
    public Fragment getCurrentFragment() {
		return mPrimaryItem;
	}

}
