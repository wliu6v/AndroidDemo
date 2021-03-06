package com.liuwei.knoweasy.grid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.liuwei.knoweasy.R;
import com.liuwei.knoweasy.base.BaseActivity;
import com.liuwei.knoweasy.tool.UtilsKt;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.Utils;

/**
 * Created by liuwei on 2017/8/4.
 */
public class GridLayoutActivity extends BaseActivity {

	// region -------- View --------
	@BindView(R.id.grid_info_item_size_et)
	EditText mItemWidthEt;
	@BindView(R.id.grid_info_item_number_et)
	EditText mItemCountEt;
	@BindView(R.id.grid_info_item_inner_margin_et)
	EditText mItemMarginEt;
	@BindView(R.id.grid_info_item_refresh_btn)
	Button mRefreshBtn;
	@BindView(R.id.grid_info_recyclerview)
	RecyclerView mRecyclerView;
	// endregion

	private GridLayoutManager mGridLayoutManager;
	private GridItemDecoration mItemDecoration;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gridlayout);
		ButterKnife.bind(this);
		setTitle("Grid Layout");
		setBackable();
		initView();
	}

	private void initView() {
		refresh();
		mRefreshBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UtilsKt.hideKeyboard(v);
				refresh();
			}
		});
	}

	private void refresh() {
		int count = getNumber(mItemCountEt);
		int width = getNumber(mItemWidthEt);
		int margin = getNumber(mItemMarginEt);
		width = (int) UtilsKt.dp2px(this, width);
		margin = (int) UtilsKt.dp2px(this, margin);

		int rvWidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		Log.d("6v", "refresh: rvw=" + rvWidth + ", w=" + width + ", margin=" + margin);
		if (count > 0) {
			width = (rvWidth - margin * (count + 1)) / count;
		} else if (width > 0) {
			count = (rvWidth - margin) / (width + margin);
			Log.d("6v", "refresh: count=" + count);
		}

		final int finalCount = count;
		if (mGridLayoutManager == null) {
			mGridLayoutManager = new GridLayoutManager(this, count);
			mRecyclerView.setLayoutManager(mGridLayoutManager);
		}
		mGridLayoutManager.setSpanCount(count);
		mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				if (position < GridLayoutAdapter.headerCount) {
					return finalCount;
				} else {
					return 1;
				}
			}
		});
		if (mItemDecoration == null) {
			mItemDecoration = new GridItemDecoration();
			mRecyclerView.addItemDecoration(mItemDecoration);
		}
		mItemDecoration.setHeaderCount(GridLayoutAdapter.headerCount);
		mItemDecoration.setSpanCount(count);
		mItemDecoration.setOuterSpacing(margin);
		mItemDecoration.setVerticalSpacing(margin);
		mItemDecoration.setSpacing(margin);

		if (mRecyclerView.getAdapter() == null) {
			GridLayoutAdapter adapter = new GridLayoutAdapter();
			adapter.setDataList(UtilsKt.getStringDataArrayList());
			mRecyclerView.setAdapter(adapter);
		}
		mRecyclerView.getAdapter().notifyDataSetChanged();
	}

	private int getNumber(EditText et) {
		int num = 0;
		String text = et.getText().toString();
		if (TextUtils.isEmpty(text)) {
			return num;
		}

		try {
			num = Integer.valueOf(text);
		} catch (NumberFormatException e) {
			num = 0;
		}

		return num;
	}
}
