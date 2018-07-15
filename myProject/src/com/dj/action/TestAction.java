package com.dj.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dj.entity.Question;
import com.dj.entity.Test;
import com.dj.entity.TestPaper;
import com.dj.entity.TestRecord;
import com.dj.entity.TpRelationq;
import com.dj.entity.User;
import com.dj.service.TestService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TestAction extends ActionSupport implements ModelDriven<Test>{
	private TestService testService;

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	//ģ������
	private Test test =new Test();
	public Test getModel() {

		return test;
	}
	
	
	private Integer uid;
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	//ҳ�������뿼�Ժ�ʼ����
	   public String startTest(){
			
			 Test t = testService.ckTest();
			 User user = testService.ckUser(uid);
			 ActionContext.getContext().getValueStack().set("user", user);
			 TestRecord tr = new TestRecord();
			 tr.setUser(user);
			 tr.setTrdate(t.getTdate());
			 tr.setTrname(t.getTname());
			 //��ʼ�����ȡ�Ծ�
			 TestPaper tp = testService.cqTestPaper();
			 ActionContext.getContext().getValueStack().set("tp", tp);
			 Date stime =new Date();
			 tr.setTestPaper(tp);
			 tr.setStime(stime);
			 //���濼�Լ�¼
			 testService.saveTestRecord(tr);
			 //��ѯ���Լ�¼
			 //TestRecord tr1 = testService.ckTestRecord(uid);
			 //ͨ��id������Ϳ��Լ�¼���浽�����������
			 testService.saveQTR(tp,tr);
			 List<TpRelationq> listxzq=testService.ckxzq(tp.getTpid());
			 List<TpRelationq> listpdq=testService.ckpdq(tp.getTpid());
			 List<TpRelationq> listtkq=testService.cktkq(tp.getTpid());
			 ActionContext.getContext().getValueStack().set("listxzq", listxzq);
			 ActionContext.getContext().getValueStack().set("listpdq", listpdq);
			 ActionContext.getContext().getValueStack().set("listtkq", listtkq);
			 ActionContext.getContext().getValueStack().set("tr", tr);
			
			
			return "startTest";
		}
	   
	   /**
	    * ��Ԫ����
	    */
	   public String unitTest(){
		   String qscope = ServletActionContext.getRequest().getParameter("qscope");
		   //��ѯ���Ϸ�Χ������
		   List<Question> listxz = testService.ckxz(qscope);
		   List<Question> listpd = testService.ckpd(qscope);
		   List<Question> listtk = testService.cktk(qscope);
		   ActionContext.getContext().getValueStack().set("listxz", listxz);
		   ActionContext.getContext().getValueStack().set("listpd", listpd);
		   ActionContext.getContext().getValueStack().set("listtk", listtk);
		   
		   
		   
		   return "unitTest";
	   }
	   
	   /**
	    * ��������������ʾ��������
	    */
	   public String showResult(){
		   
		    String[] qid = ServletActionContext.getRequest().getParameterValues("qid");
			List<String> as = new ArrayList<String>(); 
	        //ѭ������ҳ���ϱ�ѡ�еĴ�	
			for(int i=0;i<qid.length;i++){
				String answer =ServletActionContext.getRequest().getParameter(qid[i]);
				as.add(answer);
				answer = null;
			}
			Question q = null;
			List<Question> listq = new ArrayList<Question>();
			//�ҳ���������������
			for(int i=0;i<qid.length;i++){
				q = testService.findQ(Integer.parseInt(qid[i]));
				listq.add(q);
				q = null ;
			}
			//�������
			int sum = 0;
			for(int i=0;i<6;i++){
				if(as.get(i).equals(listq.get(i).getQanswer())){
					sum+=10;
				}
			}
			for(int i=6;i<9;i++){
				if(as.get(i).equals(listq.get(i).getQanswer())){
					sum+=5;
				}
			}
			if(as.get(9).equals(listq.get(9).getQanswer())){
				sum+=25;
			}
			ActionContext.getContext().getValueStack().set("listq", listq);
			ActionContext.getContext().getValueStack().set("sum", sum);
			ActionContext.getContext().getValueStack().set("as", as);
			
		   
		   return "showResult";
	   }
	

}
