package com.liuwei.knoweasy.innerRecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liuwei.knoweasy.R;
import com.liuwei.knoweasy.base.BaseActivity;

/**
 * Created by liuwei on 2017/11/16.
 */

public class RecyclerViewActivity extends BaseActivity {

	private RecyclerView mRecyclerView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_outer_recyclerview_layout);
		setBackable();
		setTitle("Inner RecyclerView Demo");

		mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setAdapter(new OuterRecyclerAdapter());
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mRecyclerView.getAdapter().notifyDataSetChanged();
	}
}
