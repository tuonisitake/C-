package com.dj.entity;

import java.util.HashSet;
import java.util.Set;

public class BanJi {
	
	private Integer bjid;//�༶id
	private String bjname;//�༶����
	private Admin admin;
	private Set<User> users=new HashSet<User>();//�ð༶�µ������û�
	
	
	
	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Integer getBjid() {
		return bjid;
	}
	public void setBjid(Integer bjid) {
		this.bjid = bjid;
	}
	public String getBjname() {
		return bjname;
	}
	public void setBjname(String bjname) {
		this.bjname = bjname;
	}
	

}
