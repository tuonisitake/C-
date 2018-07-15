package com.dj.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Question {
	private Integer qid;		//����id
	private String qcontent;	//��������
	private String qaoption;		//ѡ��a
	private String qboption;		//ѡ��b
	private String qcoption;		//ѡ��c
	private String qanswer;		//��
	private String qanalysis;	//����
	private String qkword;		//�ؼ���
	private String qscope;		//���ⷶΧ
	
	private String qdifficulty; //�����Ѷ�
	private Date qdate;			//�����������
	private QuestionType questionType;//������������
	private Admin admin;//�������Ա��˭
	private Set<AnswerSituation> answerSituation=new HashSet<AnswerSituation>();//�������µ����д������
	private Set<TpRelationq> tpRelationq=new HashSet<TpRelationq>();//�������µĹ�ϵ���
	
    
	public String getQscope() {
		return qscope;
	}
	public void setQscope(String qscope) {
		this.qscope = qscope;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Set<TpRelationq> getTpRelationq() {
		return tpRelationq;
	}
	public void setTpRelationq(Set<TpRelationq> tpRelationq) {
		this.tpRelationq = tpRelationq;
	}
	public Set<AnswerSituation> getAnswerSituation() {
		return answerSituation;
	}
	public void setAnswerSituation(Set<AnswerSituation> answerSituation) {
		this.answerSituation = answerSituation;
	}
	public QuestionType getQuestionType() {
		return questionType;
	}
	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public String getQaoption() {
		return qaoption;
	}
	public void setQaoption(String qaoption) {
		this.qaoption = qaoption;
	}
	public String getQboption() {
		return qboption;
	}
	public void setQboption(String qboption) {
		this.qboption = qboption;
	}
	public String getQcoption() {
		return qcoption;
	}
	public void setQcoption(String qcoption) {
		this.qcoption = qcoption;
	}
	public String getQanswer() {
		return qanswer;
	}
	public void setQanswer(String qanswer) {
		this.qanswer = qanswer;
	}
	public String getQanalysis() {
		return qanalysis;
	}
	public void setQanalysis(String qanalysis) {
		this.qanalysis = qanalysis;
	}
	public String getQkword() {
		return qkword;
	}
	public void setQkword(String qkword) {
		this.qkword = qkword;
	}
	public String getQdifficulty() {
		return qdifficulty;
	}
	public void setQdifficulty(String qdifficulty) {
		this.qdifficulty = qdifficulty;
	}
	public Date getQdate() {
		return qdate;
	}
	public void setQdate(Date qdate) {
		this.qdate = qdate;
	}
	

}
