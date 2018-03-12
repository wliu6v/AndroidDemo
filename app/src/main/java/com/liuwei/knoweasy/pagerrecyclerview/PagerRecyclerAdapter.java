package com.liuwei.knoweasy.pagerrecyclerview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.liuwei.knoweasy.grid.GridLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwei on 2018/3/1.
 */

public class PagerRecyclerAdapter extends PagerAdapter {
	private Context context;

	private ArrayList<String> dataList = new ArrayList<>();
	private ArrayList<RecyclerView> mViewList = new ArrayList<>();

	public PagerRecyclerAdapter(Context context) {
		this.context = context;
	}

	public void setDataList(ArrayList<String> dataList) {
		this.dataList = dataList;

		ArrayList<RecyclerView> newViewList = new ArrayList<>();

		for (int i = 0; i < getCount(); i++) {
			List<String> data = dataList.subList(i * 6, Math.min((i + 1) * 6, dataList.size()));

			if (i < mViewList.size()) {
				RecyclerView prevView = mViewList.get(i);
				if (prevView.getTag() != null && prevView.getTag().equals(data.hashCode())) {
					newViewList.add(prevView);
					continue;
				}
			}

			RecyclerView view = getRecyclerView();
			view.setTag(data.hashCode());
			((GridLayoutAdapter) view.getAdapter()).setDataList(data);
			newViewList.add(view);
		}

		mViewList = newViewList;

		notifyDataSetChanged();
	}

	private RecyclerView getRecyclerView() {
		RecyclerView recyclerView = new RecyclerView(context);

		GridLayoutAdapter adapter = new GridLayoutAdapter();
		GridLayoutManager layoutManager = new GridLayoutManager(
				context, 3, LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(adapter);
		return recyclerView;
	}

	@Override
	public int getCount() {
		if (dataList.size() == 0) {
			return 0;
		}
		return (dataList.size() - 1) / 6 + 1;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View v = mViewList.get(position);
		container.addView(v);
		return v;
	}

	@Override
	public int getItemPosition(Object object) {
		if (mViewList.contains(object)) {
			return POSITION_UNCHANGED;
		} else {
			return POSITION_NONE;
		}
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}
