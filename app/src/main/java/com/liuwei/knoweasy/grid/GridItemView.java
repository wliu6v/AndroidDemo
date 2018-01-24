package com.liuwei.knoweasy.grid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by liuwei on 2017/11/6.
 */

public class GridItemView extends FrameLayout {

	public GridItemView(@NonNull Context context) {
		this(context, null);
	}

	public GridItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GridItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {

	}
}
