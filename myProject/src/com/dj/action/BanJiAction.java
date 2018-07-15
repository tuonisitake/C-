package com.dj.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dj.entity.Admin;
import com.dj.entity.BanJi;
import com.dj.entity.User;
import com.dj.service.BanJiService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BanJiAction extends ActionSupport implements ModelDriven<BanJi>{

	private BanJiService banJiService;
	public void setBanJiService(BanJiService banJiService) {
		this.banJiService = banJiService;
	}
	//ģ������
	private BanJi bj=new BanJi();
	public BanJi getModel() {
		
		return bj;
	}
	
	private Integer uid;
	private Integer aid;
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	
	/**
	 * �鿴�༶
	 */
	public String ckbj(){
		Admin admin = (Admin) ServletActionContext.getRequest().getSession().getAttribute("admin");
		List<BanJi> listbj = banJiService.ckbj(admin.getAid());
		ActionContext.getContext().getValueStack().set("listbj", listbj);
		return "ckbj";
		
	}
	/**
	 * �鿴�༶����Ա
	 */
	public String ckBJUsers(){
		List<User> listu = banJiService.ckBJUsers(bj.getBjid());
		ActionContext.getContext().getValueStack().set("listu", listu);
		ActionContext.getContext().getValueStack().set("bjid", bj.getBjid());
		
		
		
		return "ckBJUsers";
	}
	/**
	 * ɾ���༶��Ա
	 */
	public String deleteBJUser(){
		
		banJiService.deleteBJUser(uid);
		List<User> listu = banJiService.ckBJUsers(bj.getBjid());
		ActionContext.getContext().getValueStack().set("listu", listu);
		ActionContext.getContext().getValueStack().set("bjid", bj.getBjid());
		
		
		
		
		return "ckBJUsers";
	}

}
