package com.dj.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;



import com.dj.entity.Question;
import com.dj.entity.QuestionType;
import com.dj.entity.TestPaper;
import com.dj.entity.TestRecord;
import com.dj.entity.TpRelationq;
import com.dj.entity.User;
import com.dj.service.TestPaperService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TestPaperAction extends ActionSupport implements ModelDriven<TestPaper>{
	private TestPaperService testPaperService;
	public void setTestPaperService(TestPaperService testPaperService) {
		this.testPaperService = testPaperService;
	}
	//ģ������
	private TestPaper testPaper=new TestPaper();
	public TestPaper getModel() {
		
		return testPaper;
	}
	private Integer uid;
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	private Integer aid;

	public void setAid(Integer aid) {
		this.aid = aid;
	}
	private Integer qid;

	public void setQid(Integer qid) {
		this.qid = qid;
	}
	
	
	//�鿴�Ծ�
	public String ckTestPaper(){
		List<TestPaper> list = testPaperService.ckTestPaper(aid);
		ActionContext.getContext().getValueStack().set("list", list);
		
		return "ckTestPaper";
	}
	//�鿴�Ծ�����
	public String ckTPQuestion(){
		
		List<TpRelationq> list = testPaperService.ckTPQuestion(testPaper.getTpid());
		ActionContext.getContext().getValueStack().set("list", list);
		
		
		return "ckTPQuestion";
	}
	/**
	 * ɾ���Ծ�
	 */
	public String deleteTestPaper(){
		testPaperService.deleteTestPaper(testPaper.getTpid());
		
		return "deleteTestPaper";
	}
	
	//***********************����Ծ�
	public String addTestPaper(){
		Date date =new Date();
		testPaper.setTpdate(date);
		//�����Ծ������Ϣ
		testPaperService.szTestPaper(testPaper,aid);
		//�����ȡѡ����
		List<Question> listxz = testPaperService.cqXZQ();
		List<Question> listpd = testPaperService.cqPDQ();
		List<Question> listtk = testPaperService.cqTKQ();
		ActionContext.getContext().getValueStack().set("listxz", listxz);
		ActionContext.getContext().getValueStack().set("listpd", listpd);
		ActionContext.getContext().getValueStack().set("listtk", listtk);

		testPaperService.saveTestPaper(testPaper);
		ActionContext.getContext().getValueStack().set("testPaper", testPaper);
		
		
		
		return "addTestPaper";
	}
	/**
	 * ����Ծ�ʵ��
	 */
	public String tjTestPaperSX(){
		
		
		String[] qids = ServletActionContext.getRequest().getParameterValues("question");
		for(int i=0;i<qids.length;i++){
			
			
			testPaperService.tjTestPaperSX(testPaper.getTpid(),Integer.parseInt(qids[i]));
		}
		
		
		
		
		return "tjTestPaperSX";
	}
	

}
