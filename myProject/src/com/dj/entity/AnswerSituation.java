package com.dj.entity;

public class AnswerSituation {
	private Integer anid;//�������id
	private Integer situation;//�������
	private Question question;//�ô��������������������
	private TestRecord testRecord;//�������Լ�¼
	
	
	
	
	
	public TestRecord getTestRecord() {
		return testRecord;
	}
	public void setTestRecord(TestRecord testRecord) {
		this.testRecord = testRecord;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Integer getAnid() {
		return anid;
	}
	public void setAnid(Integer anid) {
		this.anid = anid;
	}
	public Integer getSituation() {
		return situation;
	}
	public void setSituation(Integer situation) {
		this.situation = situation;
	}

}
