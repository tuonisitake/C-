package com.dj.entity;

import java.util.Date;

public class Test {
	private Integer tid;  //����id
	private String tname;	//��������
	private Date tdate;		//����ʱ��
	private TestPaper testPaper;//����Ӧ���Ծ�
	
	public TestPaper getTestPaper() {
		return testPaper;
	}
	public void setTestPaper(TestPaper testPaper) {
		this.testPaper = testPaper;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public Date getTdate() {
		return tdate;
	}
	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}
	

}
