package com.dj.entity;

import java.util.HashSet;
import java.util.Set;

public class QuestionType {
	private Integer qtid;    //��������id
	private String qtname;	 //������������
	private Integer qtscore;	 //�������ͷ���
	private Set<Question> questions=new HashSet<Question>();//�������µ���������
	
	
	public Set<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	public Integer getQtid() {
		return qtid;
	}
	public void setQtid(Integer qtid) {
		this.qtid = qtid;
	}
	public String getQtname() {
		return qtname;
	}
	public void setQtname(String qtname) {
		this.qtname = qtname;
	}
	public Integer getQtscore() {
		return qtscore;
	}
	public void setQtscore(Integer qtscore) {
		this.qtscore = qtscore;
	}
	

}
