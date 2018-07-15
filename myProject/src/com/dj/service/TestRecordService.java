package com.dj.service;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.dj.dao.TestRecordDao;
import com.dj.entity.TestRecord;

@Transactional
public class TestRecordService {
	private TestRecordDao testRecordDao;

	public void setTestRecordDao(TestRecordDao testRecordDao) {
		this.testRecordDao = testRecordDao;
	}
    
	//���濼�Խ���ʱ��
	public void endTestTime(Integer trid) {
		TestRecord tr = testRecordDao.getTestRecord(trid);
		Date date = new Date();
		tr.setEtime(date);
		testRecordDao.saveTestRecord(tr);
	}
	

}
