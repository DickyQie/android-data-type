package com.example.transmittingdata;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/***
 * 
 * Intent传值包含 1：基本数据类型,包含了Java八种基本数据类型和CharSequece文本
 * 2：八种数据类新对应数组和CharSequece文本数组 3：Bundle 4：ArrayList集合 5：Serializable传递对象
 * 6:Parcelable传递对象
 * 
 * @author zq
 * 
 */
public class MainActivity extends Activity implements OnClickListener {

	private String[] str = new String[] { "八种数据类新对应数组和CharSequece文本数组", "123" };
	private ArrayList<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();

	}

	private void initView() {
		// TODO Auto-generated method stub
		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);
		findViewById(R.id.button3).setOnClickListener(this);
		findViewById(R.id.button4).setOnClickListener(this);
		findViewById(R.id.button5).setOnClickListener(this);
		findViewById(R.id.button6).setOnClickListener(this);
		list = new ArrayList<String>();
		list.add("List集合");
		list.add("Value");

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MainActivity.this, IntentData.class);
		switch (v.getId()) {
		case R.id.button1:
			intent.putExtra("i", 1);
			intent.putExtra("key", "基本数据类型,包含了Java八种基本数据类型和CharSequece文本");
			break;
		case R.id.button2:
			intent.putExtra("i", 2);
			intent.putExtra("key", str);
			break;
		case R.id.button3:
			Bundle bundle = new Bundle();
			bundle.putString("key", "Bundle传值");
			intent.putExtra("i", 3);
			intent.putExtra("bundle", bundle);
			break;
		case R.id.button4:
			intent.putExtra("i", 4);
			intent.putStringArrayListExtra("key", list);
			break;
		case R.id.button5:
			UserInfo user = new UserInfo();
			user.setSex("男");
			user.setUserName("白子画");
			intent.putExtra("i", 5);
			intent.putExtra("key", user);
			break;

		case R.id.button6:
			intent.putExtra("i", 6);
			UserBean userBean = new UserBean();
			userBean.setSex("女");
			userBean.setUserName("花千骨");
			intent.putExtra("key", userBean);
			break;

		default:
			break;
		}
		startActivity(intent);
	}

}
