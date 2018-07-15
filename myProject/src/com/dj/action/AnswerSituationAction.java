package com.dj.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dj.entity.AnswerSituation;
import com.dj.service.AnswerSituationService;
import com.mysql.fabric.xmlrpc.base.Array;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AnswerSituationAction extends ActionSupport implements ModelDriven<AnswerSituation>{
	
	
	private AnswerSituationService answerSituationService;
	public void setAnswerSituationService(
			AnswerSituationService answerSituationService) {
		this.answerSituationService = answerSituationService;
	}
	//ģ������
    private AnswerSituation answerSituation=new AnswerSituation();
	public AnswerSituation getModel() {
	
		return answerSituation;
	}
	private Integer trid;
	public void setTrid(Integer trid) {
		this.trid = trid;
	}
	
	/**
	 * ����������
	 */
	public String saveAS(){
		
		String[] qid = ServletActionContext.getRequest().getParameterValues("qid");
		List<String> as = new ArrayList<String>(); 
        //ѭ������ҳ���ϱ�ѡ�еĴ�	
		for(int i=0;i<qid.length;i++){
			String answer =ServletActionContext.getRequest().getParameter(qid[i]);
			as.add(answer);
			answer = null;
		}
		for(int i=0;i<qid.length;i++){
			answerSituationService.saveAS(trid,Integer.parseInt(qid[i]),as.get(i));
		}
		//�������ʱ��
		    answerSituationService.upEndTime(trid);
		
		

		return "testend";
	}
	

}
