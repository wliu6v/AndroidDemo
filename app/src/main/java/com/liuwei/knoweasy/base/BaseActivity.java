package com.liuwei.knoweasy.base;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by liuwei on 2017/7/18.
 */

public class BaseActivity extends AppCompatActivity {
	boolean backable = false;

	protected void setBackable() {
		ActionBar bar = getSupportActionBar();
		if (bar == null) {
			return;
		}

		bar.setDisplayHomeAsUpEnabled(true);
		backable = true;
	}

	protected void setToolbar(View v) {
		if (v instanceof Toolbar) {
			setSupportActionBar((Toolbar) v);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				if (backable) {
					finish();
				}
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
