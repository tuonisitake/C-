package com.dj.entity;

public class TpRelationq {
	private Integer rid;	//�Ծ��������Ӧ��ϵ��id
	private Integer num;	//�Ծ�����������
	private Question question;//����������
	private TestPaper testPaper;//�������Ծ�
	
	
	public TestPaper getTestPaper() {
		return testPaper;
	}
	public void setTestPaper(TestPaper testPaper) {
		this.testPaper = testPaper;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

}
