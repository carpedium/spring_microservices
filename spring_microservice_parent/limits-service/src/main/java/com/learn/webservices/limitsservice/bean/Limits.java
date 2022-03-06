package com.learn.webservices.limitsservice.bean;

public class Limits {

	private int maximum;
	private int minimum;
	
	public Limits( ) {
	}
	
	public Limits(int max, int min) {
		super();
		this.maximum = max;
		this.minimum = min;
	}

	public int getMax() {
		return maximum;
	}

	public void setMax(int max) {
		this.maximum = max;
	}

	public int getMin() {
		return minimum;
	}

	public void setMin(int min) {
		this.minimum = min;
	}
	
	
	
}
