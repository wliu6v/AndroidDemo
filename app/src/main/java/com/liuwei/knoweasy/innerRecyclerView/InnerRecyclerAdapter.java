package com.liuwei.knoweasy.innerRecyclerView;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.liuwei.knoweasy.R;

/**
 * Created by liuwei on 2017/11/16.
 */

public class InnerRecyclerAdapter extends RecyclerView.Adapter<InnerRecyclerAdapter.InnerViewHolder> {

	private RecyclerView mRecyclerView;

	public InnerRecyclerAdapter(RecyclerView recyclerView) {
		mRecyclerView = recyclerView;
	}

	@Override
	public InnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_item2, parent, false);
		return new InnerViewHolder(v);
	}

	@Override
	public void onBindViewHolder(InnerViewHolder holder, int position) {
		holder.bind(getItemWidth(holder.itemView.getContext()));
		holder.tvSquare.setText(position + "S");
		holder.tvBottom.setText("bottom");
	}

	@Override
	public int getItemCount() {
		return 10;
	}

	private int getItemWidth(Context context) {
//		int parentWidth = mRecyclerView.getMeasuredWidth();
		int parentWidth = getScreenSize(context).x;
		Log.d("6v", "getItemWidth: " + parentWidth);
		int margin = 15;
		int showNum = 4;
		int itemWidth = (int) ((parentWidth - margin * (showNum + 1)) / (showNum + 0.3));
		return itemWidth;
	}

	public static Point getScreenSize(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point out = new Point();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			display.getSize(out);
		} else {
			int width = display.getWidth();
			int height = display.getHeight();
			out.set(width, height);
		}
		return out;
	}


	public static class InnerViewHolder extends RecyclerView.ViewHolder {

		TextView tvSquare;
		TextView tvBottom;
		private int currentWidth = -1;

		public InnerViewHolder(View itemView) {
			super(itemView);
			tvSquare = itemView.findViewById(R.id.grid_item_textview);
			tvBottom = itemView.findViewById(R.id.grid_item_bottom);
		}

		public void bind(int width) {
			if (currentWidth != width) {
				currentWidth = width;
				itemView.setLayoutParams(new RecyclerView.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
			}
		}
	}
}
