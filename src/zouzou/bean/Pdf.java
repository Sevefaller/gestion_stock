package zouzou.bean;

import java.sql.Date;

public class Pdf {
	private String a;
	private String b;
	private int c;
	private Date d;
	
	public Pdf() {}

	public Pdf(String a, String b, int c, Date d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}
	
	
	
}
