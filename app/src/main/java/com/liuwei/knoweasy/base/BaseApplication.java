package com.liuwei.knoweasy.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by liuwei on 2017/7/20.
 */

public class BaseApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Utils.init(this);
	}
}
