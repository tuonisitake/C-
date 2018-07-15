package com.dj.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dj.entity.TestRecord;

public class TestRecordDaoImpl extends HibernateDaoSupport implements
		TestRecordDao {

	//�õ����Լ�¼
	public TestRecord getTestRecord(Integer trid) {
		List<TestRecord> list = (List<TestRecord>) this.getHibernateTemplate().
				find("from TestRecord where trid = ?", trid);
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		
		return null;
	}

	//���濼�Լ�¼
	public void saveTestRecord(TestRecord tr) {
		this.getHibernateTemplate().save(tr);
		
	}
	
	
	

}
