package com.dj.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dj.dao.TestDao;
import com.dj.entity.AnswerSituation;
import com.dj.entity.Question;
import com.dj.entity.Test;
import com.dj.entity.TestPaper;
import com.dj.entity.TestRecord;
import com.dj.entity.TpRelationq;
import com.dj.entity.User;

@Transactional
public class TestService {
	private TestDao testDao;

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	public User ckUser(Integer uid) {
		
		return testDao.ckUser(uid);
	}

	public Test ckTest() {
		
		return testDao.ckTest();
	}

	public TestPaper cqTestPaper() {
		
		return testDao.cqTestPaper();
	}

	public void saveTestRecord(TestRecord tr) {
		testDao.saveTestRecord(tr);
		
	}

	public TestRecord ckTestRecord(Integer uid) {
		
		return testDao.ckTestRecord(uid);
	}
	
    //ͨ��id������Ϳ��Լ�¼���浽�����������
	public void saveQTR(TestPaper tp, TestRecord tr1) {
		//��ȡ�������Ծ�Ķ�Ӧ��ϵ����
		List<TpRelationq> list = testDao.getTPQ(tp.getTpid());
		Question q =null;//����һ������q��������ʼֵΪ��
		for(int i=0;i<list.size();i++){
			AnswerSituation as =new AnswerSituation();
			as.setTestRecord(tr1);
			as.setSituation(3);
			//����id�ҳ���������
			q = testDao.ckQuestion(list.get(i).getQuestion().getQid());

			
			as.setQuestion(q);
			//����
			testDao.saveAnswerSituation(as);
			
			
		}
		
	}

	public List<TpRelationq> ckxzq(Integer tpid) {
		
		return testDao.ckxzq(tpid);
	}

	public List<TpRelationq> ckpdq(Integer tpid) {
		
		return testDao.ckpdq(tpid);
	}

	public List<TpRelationq> cktkq(Integer tpid) {
	
		return testDao.cktkq(tpid);
	}

	public List<Question> ckxz(String qscope) {
		
		return testDao.ckxz(qscope);
	}

	public List<Question> ckpd(String qscope) {
		
		return testDao.ckpd(qscope);
	}

	public List<Question> cktk(String qscope) {
		
		return testDao.cktk(qscope);
	}

	public Question findQ(Integer qid) {
		
		return testDao.findQ(qid);
	}


	

}
