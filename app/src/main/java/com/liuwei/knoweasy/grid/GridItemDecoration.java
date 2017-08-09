package com.liuwei.knoweasy.grid;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liuwei on 2017/8/7.
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {
	private int spanCount;
	private int spacing;
	private int verticalSpacing;
	private int headerCount;
	private int outerSpacing = -1;

	private Drawable mDivider = null;

	public GridItemDecoration() {

	}

	public void setHeaderCount(int headerCount) {
		this.headerCount = headerCount;
	}

	public void setSpanCount(int spanCount) {
		this.spanCount = spanCount;
	}

	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}

	public void setOuterSpacing(int outerSpacing) {
		this.outerSpacing = outerSpacing;
	}

	public void setVerticalSpacing(int verticalSpacing) {
		this.verticalSpacing = verticalSpacing;
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
							   RecyclerView.State state) {
		int position = parent.getChildAdapterPosition(view);
		if (position < headerCount) {
			// Header should has double vertical spacing above grid item.
			outRect.set(0, 0, 0, 0);
		} else {
			int column = (position - headerCount) % spanCount;

			if (outerSpacing == -1) {
				outRect.left = spacing - column * spacing / spanCount;
				outRect.right = (column + 1) * spacing / spanCount;
			} else {
				outRect.left = (column * spacing + (spanCount - 2 * column) * outerSpacing)
						/ spanCount;
				outRect.right = ((spanCount - column - 1) * spacing + (2 * (column + 1) - spanCount)
						* outerSpacing)
						/ spanCount;
			}

			if (position - headerCount < spanCount) {
				outRect.top = verticalSpacing;
			}
			outRect.bottom = verticalSpacing;
		}
	}
}
