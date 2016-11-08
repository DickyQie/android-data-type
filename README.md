# android-data-type
 <div class="blog-body" id="blogBody">
                                    <val data-name="blog_content_type" data-value="richtext"></val>
                    <div class='BlogContent'>
                        <p>发这篇博客主要讲一下<a href="http://lib.csdn.net/base/15" target="_blank" rel="nofollow">Android</a>中Intent中如何传值的几种方法：</p> 
<p><strong><span style="color:#B22222">1：基本数据类型,包含了</span><a href="http://lib.csdn.net/base/17" target="_blank" rel="nofollow"><span style="color:#B22222">Java</span></a><span style="color:#B22222">八种基本数据类型和CharSequece文本<br> 2：八种数据类新对应数组和CharSequece文本数组</span></strong></p> 
<p><span style="color:#B22222"><strong>3：Bundle传值</strong></span></p> 
<p><span style="color:#B22222"><strong>4：ArrayList集合&nbsp;</strong></span></p> 
<p><span style="color:#B22222"><strong>5：Serializable传递对象</strong></span></p> 
<p><span style="color:#B22222"><strong>6：Parcelable传递对象</strong></span></p> 
<p>在 main.xml 布局文件中添加六个Button控件，分别是六种传值方式。</p> 
<p>Activity代码如下：</p> 
<pre><code class="language-java">package com.example.transmittingdata;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/***
 * 
 * Intent传值包含 
 * 1：基本数据类型,包含了Java八种基本数据类型和CharSequece文本
 * 2：八种数据类新对应数组和CharSequece文本数组
 * 3：Bundle 
 * 4：ArrayList集合 5：Serializable传递对象
 * 6:Parcelable传递对象
 * 
 * @author zq
 * 
 */
public class MainActivity extends Activity implements OnClickListener {

	private String[] str = new String[] { "八种数据类新对应数组和CharSequece文本数组", "123" };
	private ArrayList&lt;String&gt; list;

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
		list = new ArrayList&lt;String&gt;();
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
</code></pre> 
<p>接收值的Activity类：</p> 
<pre><code class="language-java">public class IntentData extends Activity {

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
				ArrayList&lt;String&gt; array;
				text1.setText("List&lt;object&gt; 集合");
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
</code></pre> 
<p><span style="color:#B22222"><strong>Serializable传递对象</strong></span></p> 
<p><strong><span style="color:#008080">Serializable</span>：</strong>是序列化的意思，表示将一个对象转换成可储存或可传输的状态，对象进行Serializable序列化之后就可以通过Intent来进行Activity之间的传输了。</p> 
<pre><code class="language-java">public class UserInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sex;
	private String userName;
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


}</code></pre> 
<p>这里面的serialVersionUID需要注意一下，它的作用是序列化和反序列化时保持版本的兼容性，如果你未指定，运行时也会默认生成，在进行反序列化时只有数据和当前类的serialVersionUID相同是才能够正常的反序列化，你不指定serialVersionUID一般情况下也不会出问题，但是如果当前类发生了改变例如删掉了某个成员变量那么当前类的serialVersionUID也会出现改变，之后你对数据进行反序列化就会出现错误，这里我指定为1L，L为Long数据类型。</p> 
<p>&nbsp;</p> 
<p><span style="color:#B22222"><strong>Parcelable传递对象</strong></span></p> 
<p><span style="color:#008080"><strong>Parcelable</strong></span>的序列化原理是将一个对象进行分解，而分解后的每一部分都是Intent所支持的数据类型，因此实现了传递对象的功能。</p> 
<pre><code class="language-java">public class UserBean implements Parcelable {

	private String sex;
	private String userName;

	public UserBean() {
		// TODO Auto-generated constructor stub
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

		dest.writeString(userName);
		dest.writeString(sex);
	}

	protected UserBean(Parcel in) {
		userName = in.readString();
		sex = in.readString();
	}

	public static final Creator&lt;UserBean&gt; CREATOR = new Creator&lt;UserBean&gt;() {

		@Override
		public UserBean createFromParcel(Parcel in) {
			return new UserBean(in);
		}

		@Override
		public UserBean[] newArray(int size) {
			return new UserBean[size];
		}

	};

}
</code></pre> 
<p>可以看到通过Parcelable的实现方式是要复杂很多的，实现Parcelable接口后，需要重写writeToParcel和describeContents方法，describeContents方法直接返回0就可以了，writeToParcel方法我们需要调用Parcel对象进行数据写入，例如dest.writeString(name)，注意如果name是字符串类型就调用writeString，如果是Int类型就调用writeInt 等等。</p> 
