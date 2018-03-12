package com.liuwei.knoweasy.pagerrecyclerview;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuwei.knoweasy.R;
import com.liuwei.knoweasy.base.BaseActivity;
import com.liuwei.knoweasy.grid.GridLayoutAdapter;
import com.liuwei.knoweasy.tool.UtilsKt;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by liuwei on 2018/2/9.
 */

public class PagerRecyclerActivity extends BaseActivity {

	private int mCount = 0;
	private PagerRecyclerAdapter mAdapter;

	// region -------- View --------
	@BindView(R.id.pager_recycler_view)
	ViewPager mViewPager;

	@BindView(R.id.pager_indicator)
	CircleIndicator indicator;

	@BindView(R.id.item_count)
	TextView mItemCountTv;

	@BindView(R.id.add_item)
	View mAddItem;

	@BindView(R.id.remove_item)
	View mRemoveItem;

	@BindView(R.id.refresh)
	View mRefresh;
	// endregion

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pager_recyclerview_layout);
		ButterKnife.bind(this);
		setBackable();
		setTitle("Pager RecyclerView Demo");

		mAdapter = new PagerRecyclerAdapter(this);
		mViewPager.setAdapter(mAdapter);
		indicator.setViewPager(mViewPager);
		mAdapter.registerDataSetObserver(indicator.getDataSetObserver());
	}

	@OnClick(R.id.add_item)
	void addItem() {
		mCount += 2;
		mItemCountTv.setText("Item Count : " + mCount);
	}

	@OnClick(R.id.remove_item)
	void removeItem() {
		if (mCount > 2) {
			mCount -= 2;
		} else {
			mCount = 0;
		}

		mItemCountTv.setText("Item Count : " + mCount);
	}

	@OnClick(R.id.refresh)
	void refresh() {
		ArrayList<String> allList = UtilsKt.getStringDataArrayList();
		ArrayList<String> subList = new ArrayList<>(allList.subList(0, mCount));
		mAdapter.setDataList(subList);

		ViewGroup.LayoutParams lp = mViewPager.getLayoutParams();
		lp.height = subList.size() == 0 ? 0 : subList.size() <= 3 ? 450 : 900;
		mViewPager.setLayoutParams(lp);
	}
}
