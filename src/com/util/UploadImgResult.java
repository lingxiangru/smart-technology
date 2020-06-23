package com.util;

import java.util.Arrays;

public class UploadImgResult {
	private int errno = 0;
	private String[] data;
	public int getErrno() {
		return errno;
	}
	public void setErrno(int errno) {
		this.errno = errno;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "UploadImgResult [errno=" + errno + ", data=" + Arrays.toString(data) + "]";
	}
	
}
