package com.liuwei.knoweasy.innerRecyclerView;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuwei.knoweasy.R;

/**
 * Created by liuwei on 2017/11/16.
 */
public class OuterRecyclerAdapter extends RecyclerView.Adapter<OuterRecyclerAdapter.OuterViewHolder> {

	@Override
	public OuterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inner_recyclerview_layout, parent, false);
		return new OuterViewHolder(v);
	}

	@Override
	public void onBindViewHolder(OuterViewHolder holder, int position) {
		holder.bindData();
	}

	@Override
	public int getItemCount() {
		return 10;
	}

	static class OuterViewHolder extends RecyclerView.ViewHolder {
		RecyclerView mRecyclerView;
		InnerRecyclerAdapter mAdapter;

		public OuterViewHolder(View itemView) {
			super(itemView);
			mRecyclerView = itemView.findViewById(R.id.innerRecyclerView);

			mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
			mAdapter = new InnerRecyclerAdapter(mRecyclerView);
			SnapHelper snapHelper = new LinearSnapHelper();
			snapHelper.attachToRecyclerView(mRecyclerView);

			mRecyclerView.setNestedScrollingEnabled(false);
			mRecyclerView.setHasFixedSize(true);

			mRecyclerView.setAdapter(mAdapter);

			mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
				@Override
				public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
					super.getItemOffsets(outRect, view, parent, state);
					int marginPx = 15;

					int position = parent.getChildAdapterPosition(view);
					outRect.left = position == 0 ? marginPx * 2 : marginPx;
					outRect.right = position == parent.getAdapter().getItemCount() - 1 ? marginPx * 2 : marginPx;
				}
			});
		}

		public void bindData() {
//			mAdapter.notifyDataSetChanged();
			mRecyclerView.setAdapter(mAdapter);
		}
	}
}
