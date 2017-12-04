package com.liuwei.knoweasy.grid;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuwei.knoweasy.R;
import com.liuwei.knoweasy.tool.UtilsKt;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuwei on 2017/8/7.
 */

public class GridLayoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	int TYPE_DATA = 0;
	int TYPE_HEADER = 1;
	int TYPE_MEASURE = 2;

	List<String> dataList = Arrays.asList("Licensed", "under", "the", "Apache", "Version",
			"2.0", "you", "may", "not", "use", "this", "file", "except", "in", "compliance", "with",
			"the", "You", "may", "obtain", "a", "copy", "of", "the", "License", "at", "Unless",
			"required", "by", "applicable", "law", "or", "agreed", "to", "in", "distributed", "under",
			"the", "License", "is", "distributed", "on", "an", "IS", "WITHOUT", "WARRANTIES", "OR",
			"CONDITIONS", "OF", "ANY", "either", "express", "or", "See", "the", "License", "for", "the",
			"specific", "language", "governing", "permissions", "limitations", "under", "the");

	int headerCount = 1;

	@Override
	public int getItemViewType(int position) {
		if (position == 3)
			return TYPE_MEASURE;
		return position < headerCount ? TYPE_HEADER : TYPE_DATA;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		RecyclerView.ViewHolder vh;
		if (viewType == TYPE_HEADER) {
			vh = new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_header, parent, false));
		} else {
			if (viewType == TYPE_MEASURE) {
				vh = new GridViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_item2, parent, false));
			} else {
				vh = new GridViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_item3, parent, false));
			}
		}
		return vh;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof GridViewHolder) {
			int color = Color.argb(255, 17 * position % 255, 71 * position % 255, 193 * position % 255);
			holder.itemView.setBackgroundColor(color);

			((GridViewHolder) holder).tv.setText(dataList.get(position - headerCount));
			((GridViewHolder) holder).tv.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), UtilsKt.isLighterColor(color) ? android.R.color.black : android.R.color.white));
		}
	}

	@Override
	public int getItemCount() {
		return headerCount + dataList.size();
	}

	public static class GridViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.grid_item_textview)
		TextView tv;

		public GridViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

	public static class HeaderViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.grid_header_text)
		TextView tv;

		public HeaderViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
