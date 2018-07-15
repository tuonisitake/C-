package com.dj.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TestPaper {
	private Integer tpid;	//�Ծ�id
	private Date tpdate;	//����ʱ��
	private Integer tptime;	//����ʱ��
	private Admin admin;//������
	private Set<TpRelationq> tpRelationq=new HashSet<TpRelationq>();//���Ծ������й�ϵ
	private Set<TestRecord> testRecord=new HashSet<TestRecord>();//���Ծ��µ����м�¼���
	private Set<Test> test=new HashSet<Test>();//���Ծ��µĶ�ο���
	
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Set<Test> getTest() {
		return test;
	}
	public void setTest(Set<Test> test) {
		this.test = test;
	}
	public Set<TestRecord> getTestRecord() {
		return testRecord;
	}
	public void setTestRecord(Set<TestRecord> testRecord) {
		this.testRecord = testRecord;
	}
	public Set<TpRelationq> getTpRelationq() {
		return tpRelationq;
	}
	public void setTpRelationq(Set<TpRelationq> tpRelationq) {
		this.tpRelationq = tpRelationq;
	}
	public Integer getTpid() {
		return tpid;
	}
	public void setTpid(Integer tpid) {
		this.tpid = tpid;
	}
	public Date getTpdate() {
		return tpdate;
	}
	public void setTpdate(Date tpdate) {
		this.tpdate = tpdate;
	}
	public Integer getTptime() {
		return tptime;
	}
	public void setTptime(Integer tptime) {
		this.tptime = tptime;
	}
	

}
