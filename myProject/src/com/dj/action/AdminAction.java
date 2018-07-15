package com.dj.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dj.entity.Admin;
import com.dj.entity.AnswerSituation;
import com.dj.entity.TestRecord;
import com.dj.entity.User;
import com.dj.service.AdminService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminAction extends ActionSupport implements ModelDriven<Admin>{
	
	private AdminService adminService;
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	//ģ������
	private Admin admin=new Admin();
	public Admin getModel() {
		
		return admin;
	}
	//������
	private String newPW;
	
	public void setNewPW(String newPW) {
		this.newPW = newPW;
	}
	private Integer trid;
	

	public void setTrid(Integer trid) {
		this.trid = trid;
	}
	private Integer uid;
	

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String login(){
		
		
		
        Admin adminExist=adminService.login(admin);
		
		//�ж�
		if(adminExist != null)
		{
			//ʹ��session����״̬��¼
			HttpServletRequest request= ServletActionContext.getRequest();
			request.getSession().setAttribute("admin", adminExist);
			return "loginsuccess";
		} else {
			//ʧ��
			return "login";
		}
	}
	
	/***
	 * �鿴�ҵ���Ϣ
	 */
	public String ckMyInfo(){

		return "ckMyInfo";
	}
	/**
	 * �޸�����
	 */
	public String updatePW(){
		
		return "updatePW";
	}
	/**
	 * �޸�����ʵ��
	 */
	public String updatePWSX(){
		adminService.updatePWSX(admin.getAid(),newPW);
		
		return "updatePWSX";
	}
	/**
	 * ɾ�����Լ�¼
	 */
	public String deleteTestRecord(){
		adminService.deleteTestRecord(trid);
		
		return "deleteTestRecord";
	}
	/**
	 * �鿴�û��Ŀ��Լ�¼
	 */
	public String ckTestRecord(){
		List<TestRecord> list = adminService.ckTestRecord(uid);
		ActionContext.getContext().getValueStack().set("list", list);
		return "ckTestRecord";
		
		
	}
	/**
	 * �鿴�û��Ĵ������
	 */
	public String ckAnswerSituation(){
		
		//�鿴ѡ����������
				List<AnswerSituation> xzqas = adminService.ckxzqas(trid);
				//�鿴�ж���������
				List<AnswerSituation> pdqas = adminService.ckpdqas(trid);
				//�鿴�����������
			    List<AnswerSituation> tkqas = adminService.cktkqas(trid);
				ActionContext.getContext().getValueStack().set("xzqas", xzqas);
				ActionContext.getContext().getValueStack().set("pdqas", pdqas);
				ActionContext.getContext().getValueStack().set("tkqas", tkqas);
		
		return "ckAnswerSituation";
	}
	
	/**
	 * ע��
	 */
	public String sessionDestory(){
		
		ServletActionContext.getRequest().getSession().invalidate();
		
		return "sessionDestory";
	}

	
	
}
