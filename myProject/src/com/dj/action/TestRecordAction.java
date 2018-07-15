package com.dj.action;

import com.dj.entity.TestRecord;
import com.dj.service.TestRecordService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TestRecordAction extends ActionSupport implements ModelDriven<TestRecord>{
	private TestRecordService testRecordService;

	public void setTestRecordService(TestRecordService testRecordService) {
		this.testRecordService = testRecordService;
	}

	//ģ������
	private TestRecord testRecord=new TestRecord();
	public TestRecord getModel() {
		
		return testRecord;
	}
	
	//���Խ����������ʱ�䣩,��¼���Խ���ʱ��
	public String endTest(){
		
		testRecordService.endTestTime(testRecord.getTrid());
		
		
		return "endTest";
	}
	

}
