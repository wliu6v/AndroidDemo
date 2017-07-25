package com.liuwei.knoweasy.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SizeUtils;
import com.liuwei.knoweasy.R;
import com.liuwei.knoweasy.base.BaseActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by liuwei on 2017/7/18.
 */

public class DialogActivity extends BaseActivity implements AdapterView.OnItemClickListener {

	private List<String> list = new ArrayList<>();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
		setBackable();
		setTitle("Alert Dialog");

		initData();
		ListView lv = (ListView) findViewById(R.id.alert_listview);
		lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
		lv.setOnItemClickListener(this);
	}

	private int currentIndex = -1;

	private Dialog singleChoiceDemo1() {
		final String[] list = {"choice1", "choice2", "choice3"};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getMethodName());
		builder.setSingleChoiceItems(list, currentIndex, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogActivity.this, list[which], Toast.LENGTH_LONG).show();
				currentIndex = which;
				dialog.dismiss();
			}
		});
		return builder.show();
	}

	private Dialog singleChoiceDemo2() {
		AppCompatDialogFragment df = new SingleChoiceDialog2();
		df.show(getSupportFragmentManager(), "dialog");
		return null;
	}

	private void initData() {
		Method[] methods = getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (method.getReturnType().equals(Dialog.class)) {
				list.add(method.getName());
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		try {
			// this line only use for public method
			// getClass().getMethod(item, new Class[]{String.class}).invoke(this, item);

			Method method = getClass().getDeclaredMethod(list.get(position));
			method.setAccessible(true);
			method.invoke(this);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}

	public static class SingleChoiceDialog2 extends AppCompatDialogFragment implements DialogInterface.OnClickListener {

		ArrayList<String> data = new ArrayList<>();
		ArrayList<String> choiceList = new ArrayList<>();

		RadioGroup rg;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			data.addAll(Arrays.asList("data1", "data2"));
			choiceList.addAll(Arrays.asList("choice1", "choice2", "choice3"));

			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("choice 2");
			builder.setAdapter(new BaseAdapter() {
				@Override
				public int getCount() {
					return data.size() + 1;
				}

				@Override
				public Object getItem(int position) {
					return position;
				}

				@Override
				public long getItemId(int position) {
					return position;
				}

				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					if (position < data.size()) {
						TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
						tv.setText(data.get(position));
						return tv;
					} else {
						View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_choice, parent, false);
						rg = (RadioGroup) v.findViewById(R.id.single_choice_radiogroup);
						for (String s : choiceList) {
							RadioButton rb = new RadioButton(getContext());
							rb.setText(s);
							rb.setMinHeight(SizeUtils.dp2px(64));
							rg.addView(rb);
							if (s.equals("choice2")) {
								rb.setChecked(true);
							}
						}

						return v;
					}
				}
			}, this);
			return builder.create();
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			Toast.makeText(getContext(), "number : " + which, Toast.LENGTH_LONG).show();
			Toast.makeText(getContext(), "number : " + rg.getCheckedRadioButtonId(), Toast.LENGTH_LONG).show();
		}
	}
}
