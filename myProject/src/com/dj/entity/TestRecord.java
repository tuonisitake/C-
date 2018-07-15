package com.dj.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TestRecord {
	private Integer trid;	//���Լ�¼id
	private String trname;	//��������
	private Date trdate;		//��������
	private Date stime;		//��ʼ����ʱ��
	private Date etime;		//����ʱ��
	private Integer trscore;	//���Գɼ�
	private TestPaper testPaper;//�������Ծ�
	private User user;//�������û�
	private Set<AnswerSituation> answerSituation=new HashSet<AnswerSituation>();//�ÿ��Լ�¼�µĴ������
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public TestPaper getTestPaper() {
		return testPaper;
	}
	public void setTestPaper(TestPaper testPaper) {
		this.testPaper = testPaper;
	}
	public Set<AnswerSituation> getAnswerSituation() {
		return answerSituation;
	}
	public void setAnswerSituation(Set<AnswerSituation> answerSituation) {
		this.answerSituation = answerSituation;
	}
	public Integer getTrid() {
		return trid;
	}
	public void setTrid(Integer trid) {
		this.trid = trid;
	}
	public String getTrname() {
		return trname;
	}
	public void setTrname(String trname) {
		this.trname = trname;
	}
	public Date getTrdate() {
		return trdate;
	}
	public void setTrdate(Date trdate) {
		this.trdate = trdate;
	}
	public Date getStime() {
		return stime;
	}
	public void setStime(Date stime) {
		this.stime = stime;
	}
	public Date getEtime() {
		return etime;
	}
	public void setEtime(Date etime) {
		this.etime = etime;
	}
	public Integer getTrscore() {
		return trscore;
	}
	public void setTrscore(Integer trscore) {
		this.trscore = trscore;
	}


}
