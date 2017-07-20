package com.liuwei.knoweasy.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

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
