package com.example.kyselypalvelu.domain;

public class AnswerStatistics {
	private String text;
	private int count;
	
	public AnswerStatistics() {
		super();
	}
	
	public AnswerStatistics(String text, int count) {
		super();
		this.text = text;
		this.count = count;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "AnswerStatistics [text=" + text + ", count=" + count + "]";
	}
}
