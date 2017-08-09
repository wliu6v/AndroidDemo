package com.liuwei.knoweasy.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.liuwei.knoweasy.R;
import com.liuwei.knoweasy.base.BaseActivity;
import com.liuwei.knoweasy.color.ColorActivity;
import com.liuwei.knoweasy.dialog.DialogActivity;
import com.liuwei.knoweasy.grid.GridLayoutActivity;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends BaseActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	private MainListProvider mProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		initRecyclerView();
	}

	private void initRecyclerView() {
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(lm);

		mProvider = new MainListProvider();
		initProvider();

		MainAdapter adapter = new MainAdapter(mProvider);
		adapter.setListener(new IRecyclerViewEventListener() {
			@Override
			public void onItemClick(@NotNull View v, int pos) {
				itemClick(v, pos);
			}
		});

		recyclerView.setAdapter(adapter);
	}

	private void initProvider() {
		mProvider.addData(new DemoBean("Alert Dialog", DialogActivity.class));
		mProvider.addData(new DemoBean("Color Test", ColorActivity.class));
		mProvider.addData(new DemoBean("GridItem Demo", GridLayoutActivity.class));
	}

	private void itemClick(View v, int pos) {
		startActivity(mProvider.getIntent(this, pos));
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_camera) {
			// Handle the camera action
		} else if (id == R.id.nav_gallery) {

		} else if (id == R.id.nav_slideshow) {

		} else if (id == R.id.nav_manage) {

		} else if (id == R.id.nav_share) {

		} else if (id == R.id.nav_send) {

		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
}
