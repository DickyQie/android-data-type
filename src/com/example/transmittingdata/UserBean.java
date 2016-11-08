package com.example.transmittingdata;

import android.os.Parcel;
import android.os.Parcelable;

public class UserBean implements Parcelable {

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

	public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {

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
