package com.dj.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dj.entity.AnswerSituation;
import com.dj.entity.Question;
import com.dj.entity.TestRecord;


public class AnswerSituationDaoImpl extends HibernateDaoSupport implements AnswerSituationDao {

	
	

	
	
	


	//����������
	public void saveAS(AnswerSituation a) {
		this.getHibernateTemplate().save(a);
		
	}

	//��������id��trid��ѯAnswerSituation
	public AnswerSituation hqAnswerState(Integer trid, Integer qid) {
		List<AnswerSituation> list = (List<AnswerSituation>) this.getHibernateTemplate()
				.find("from AnswerSituation a where a.testRecord.trid = ? and a.question.qid = ?", trid,qid);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	//����qid��ѯ
	public Question hqQuestion(Integer qid) {
		List<Question> list = (List<Question>) this.getHibernateTemplate()
				.find("from Question where qid = ?", qid);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		
		return null;
	}

	//����trid��ѯ
	public TestRecord hqTestRecord(Integer trid) {
		List<TestRecord> list =(List<TestRecord>) this.getHibernateTemplate()
				.find("from TestRecord where trid = ?", trid);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	//��ѯ��¼
	public TestRecord findTestRecord(Integer trid) {
		List<TestRecord> t = (List<TestRecord>) this.getHibernateTemplate()
		.find("from TestRecord where trid = ?", trid);
		if(t!=null && t.size()>0){
			return t.get(0);
		}
		return null;
		
		
		
	}

	//��������¼�
	public void saveT(TestRecord t) {
		this.getHibernateTemplate().save(t);
		
	}



	


}
