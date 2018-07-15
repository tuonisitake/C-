package com.dj.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dj.entity.AnswerSituation;
import com.dj.entity.BanJi;
import com.dj.entity.TestRecord;
import com.dj.entity.User;
import com.dj.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//ģ������
	private User user =new User();
	public User getModel() {
		
		return user;
	}
	
	private Integer trid;

	public void setTrid(Integer trid) {
		this.trid = trid;
	}
	
	private String newPW;

	public void setNewPW(String newPW) {
		this.newPW = newPW;
	}
	
	private Integer aid;
	


	public void setAid(Integer aid) {
		this.aid = aid;
	}


	public String login(){

		User userExist=userService.login(user);
		
		//�ж�
		if(userExist != null)
		{
			//ʹ��session����״̬��¼
			HttpServletRequest request= ServletActionContext.getRequest();
			request.getSession().setAttribute("user", userExist);
			return "loginsuccess";
		} else {
			//ʧ��
			return "login";
		}
	
		
		
	}
	
	
	//�鿴���Լ�¼
	public String ckTestRecord(){
		List<TestRecord> list = userService.ckTestRecord(user.getUid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "ckTestRecord";
	}
	
	//�鿴�������
	public String ckAnswerSituation(){
		//�鿴ѡ����������
		List<AnswerSituation> xzqas = userService.ckxzqas(trid);
		//�鿴�ж���������
		List<AnswerSituation> pdqas = userService.ckpdqas(trid);
		//�鿴�����������
	    List<AnswerSituation> tkqas = userService.cktkqas(trid);
		ActionContext.getContext().getValueStack().set("xzqas", xzqas);
		ActionContext.getContext().getValueStack().set("pdqas", pdqas);
		ActionContext.getContext().getValueStack().set("tkqas", tkqas);
		
		return "ckAnswerSituation";
	}
	
	//�鿴�ҵİ༶
	public String myBanJi(){
		BanJi banJi = userService.findmyBanji(user.getUid());
		ActionContext.getContext().getValueStack().set("banJi", banJi);
		
		
		
		return "myBanJi";
	}
	
	//�޸�����
	public String updatePW(){
		
		userService.updatePW(user.getUid(),newPW);
		
		
		return "updatePW";
	}
	/**
	 * �鿴������Ա
	 */
	public String ckAllUser(){
		
		List<User> listu = userService.ckAllUser(aid);
		ActionContext.getContext().getValueStack().set("listu", listu);
		
		
		return "ckAllUser";
	}
	/**
	 * ɾ����Ա
	 */
	public String deleteUser(){
		userService.deleteUser(user.getUid());
		List<User> listu = userService.ckAllUser(aid);
		ActionContext.getContext().getValueStack().set("listu", listu);
		
		
		return "ckAllUser";
	}
	/**
	 * ע��
	 */
	public String sessionDestroy(){
		ServletActionContext.getRequest().getSession().invalidate();
		
		
		return "sessionDestroy";
	}


}
