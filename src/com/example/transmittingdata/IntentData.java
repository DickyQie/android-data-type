package com.example.transmittingdata;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
/***
 * 
 * 接收值
 * @author zq
 *
 */
public class IntentData extends Activity {

	private TextView text1, text2;
	private int position = 1;
	private String data = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data);
		initView();
		initData();

	}

	@SuppressLint("NewApi")
	private void initData() {
		// TODO Auto-generated method stub
		if (getIntent() != null) {
			position = getIntent().getIntExtra("i", 1);
			if (position == 1) {
				data = getIntent().getStringExtra("key");
				text1.setText("基本数据类型");
				text2.setText(data);
				return;
			}
			if (position == 2) {
				String[] data1 = getIntent().getStringArrayExtra("key");
				text1.setText("数组");
				text2.setText(data1[0] + "----" + data1[1]);
				return;
			}
			if (position == 3) {
				Bundle bundle = getIntent().getBundleExtra("bundle");
				text1.setText("Bundle");
				text2.setText(bundle.getString("key", "默认为空是的值"));
				return;
			}
			if (position == 4) {
				ArrayList<String> array;
				text1.setText("List<object> 集合");
				array = getIntent().getStringArrayListExtra("key");
				text2.setText(array.get(0));
				return;
			}
			if (position == 5) {
				UserInfo user;
				text1.setText("Serializable传递对象");
				user = (UserInfo) getIntent().getSerializableExtra("key");
				text2.setText(user.getUserName() + "---" + user.getSex());
				return;
			}
			if (position == 6) {
				UserBean userBean;
				text1.setText("Parcelable传递对象");
				userBean = (UserBean) getIntent().getParcelableExtra("key");
				text2.setText(userBean.getUserName() + "---"
						+ userBean.getSex());
				return;
			}

		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		text1 = (TextView) findViewById(R.id.textView1);
		text2 = (TextView) findViewById(R.id.textView2);

	}

}
