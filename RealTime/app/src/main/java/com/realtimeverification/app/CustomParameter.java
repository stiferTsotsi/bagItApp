package com.realtimeverification.app;

/**
 * Created by vaal on 3/20/2015.
 */
public class CustomParameter {
	private String uid;
	private String xx;

	public CustomParameter(String uid, String xx) {
		setUid(uid);
		setXx(xx);
	}

	public String getXx() {
		return xx;
	}

	public void setXx(String xx) {
		this.xx = xx;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
