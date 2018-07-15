package com.dj.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dj.entity.Admin;
import com.dj.entity.AnswerSituation;
import com.dj.entity.BanJi;
import com.dj.entity.Question;
import com.dj.entity.TestRecord;
import com.dj.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	
	
	
//	private HibernateTemplate hibernateTemplate;
//	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//		this.hibernateTemplate = hibernateTemplate;
//	}
	
	
	//��¼
    public User loginUser(User user) {
    	
    	List<User> list = (List<User>) this.getHibernateTemplate().
    	     find("from User where username=? and password=?", user.getUsername(),user.getPassword());
		
    	//�����ѯ֮��û�н����list����û��ֵ������get���±꣩��ȡ����ֵ�������±�Խ���쳣
    	if(list != null && list.size()!=0){
    		User u = list.get(0);
    		return u;
    	}
    	
    	
		
		return null;
	}

	//����uid��ѯ���Լ�¼
	public List<TestRecord> ckTestRecord(Integer uid) {
		Integer sum=null;
		Integer xzq=null;
		Integer pdq=null;
		Integer tkq=null;
		//listtr�д�Ÿ��û��Ŀ��Լ�¼
		List<TestRecord> listtr=(List<TestRecord>) this.getHibernateTemplate().
				find("from TestRecord tr where tr.user.uid = ? order by tr.trid desc ", uid);

		List<TestRecord> list = new ArrayList<TestRecord>();
		
		//��ÿ����¼�и�����Ŀ�ķ��������ۼ�
		for(int i=0;i < listtr.size();i++){
			TestRecord tr =listtr.get(i);
			String hql = "select count(*) from AnswerSituation ast where ast.testRecord.trid = ? and ast.situation = ? and ast.question.questionType.qtid = ?";
			List<Long> xzlist = (List<Long>) this.getHibernateTemplate().
					find(hql, listtr.get(i).getTrid(),1,1);
			if(xzlist!=null && xzlist.size()>0){
				
				xzq = xzlist.get(0).intValue()*10;
			}
			List<Long> pdlist = (List<Long>) this.getHibernateTemplate().
					find(hql, listtr.get(i).getTrid(),1,2);
			if(pdlist!=null && pdlist.size()>0){
				
				pdq = pdlist.get(0).intValue()*5;
			}
			List<Long> tklist = (List<Long>) this.getHibernateTemplate().
					find(hql, listtr.get(i).getTrid(),1,3);
			if(tklist!=null && tklist.size()>0){
				
				tkq = tklist.get(0).intValue()*25;
			}
			sum=xzq+pdq+tkq;
			tr.setTrscore(sum);
			this.getHibernateTemplate().save(tr);
			list.add(tr);
		}
		
		
		
		return list;
	}

	//�鿴ѡ����������
	public List<AnswerSituation> ckxzqas(Integer trid) {
		
		//���ݿ��Լ�¼id�ҵ���֮��ص�ѡ����
		List<AnswerSituation> listas = (List<AnswerSituation>) this.getHibernateTemplate().
				find("from AnswerSituation ast where ast.testRecord.trid = ? and ast.question.questionType.qtid = ? order by ast.anid asc", trid,1);
		List<AnswerSituation> list =new ArrayList<AnswerSituation>();
		//ѭ������Question
		for(int i=0;i<listas.size();i++){
			AnswerSituation ast = listas.get(i);
			List<Question> lq = (List<Question>) this.getHibernateTemplate().
					find("from Question where qid = ?", listas.get(i).getQuestion().getQid());
			if(lq!=null && lq.size()>0){
				
				ast.setQuestion(lq.get(0));
				
			}
			list.add(ast);
		}
		
		return list;
	}

	//�鿴�ж���������
	public List<AnswerSituation> ckpdqas(Integer trid) {
		
		//���ݿ��Լ�¼id�ҵ���֮��ص��ж���
				List<AnswerSituation> listas = (List<AnswerSituation>) this.getHibernateTemplate().
						find("from AnswerSituation ast where ast.testRecord.trid = ? and ast.question.questionType.qtid = ? order by ast.anid asc", trid,2);
				List<AnswerSituation> list =new ArrayList<AnswerSituation>();
				//ѭ������Question
				for(int i=0;i<listas.size();i++){
					AnswerSituation ast = listas.get(i);
					List<Question> lq = (List<Question>) this.getHibernateTemplate().
							find("from Question where qid = ?", listas.get(i).getQuestion().getQid());
					if(lq!=null && lq.size()>0){
						
						ast.setQuestion(lq.get(0));
						
					}
					list.add(ast);
				}
				
				return list;
	}

	//�鿴�����������
	public List<AnswerSituation> cktkqas(Integer trid) {
		//���ݿ��Լ�¼id�ҵ���֮��ص������
		List<AnswerSituation> listas = (List<AnswerSituation>) this.getHibernateTemplate().
				find("from AnswerSituation ast where ast.testRecord.trid = ? and ast.question.questionType.qtid = ? order by ast.anid asc", trid,3);
		List<AnswerSituation> list =new ArrayList<AnswerSituation>();
		//ѭ������Question
		for(int i=0;i<listas.size();i++){
			AnswerSituation ast = listas.get(i);
			List<Question> lq = (List<Question>) this.getHibernateTemplate().
					find("from Question where qid = ?", listas.get(i).getQuestion().getQid());
			if(lq!=null && lq.size()>0){
				
				ast.setQuestion(lq.get(0));
				
			}
			list.add(ast);
		}
		
		return list;
	}

	//��ѯ�༶
	public BanJi findmyBanJi(Integer uid) {
		BanJi banji =null;
		Admin admin =null;
		//ͨ��uid�ҵ������༶
		List<User> listu=(List<User>) this.getHibernateTemplate().
				find("from User where uid = ?", uid);
		if(listu!= null && listu.size()>0){
			List<BanJi> listbj = (List<BanJi>) this.getHibernateTemplate().
					find("from BanJi where bjid = ?", listu.get(0).getBanJi().getBjid());
			if(listbj!=null && listbj.size()>0){
				banji = listbj.get(0);
				//�༶����Ա
				List<Admin> lista = (List<Admin>) this.getHibernateTemplate().
						find("from Admin where aid = ?", listbj.get(0).getAdmin().getAid());
				if(lista!= null && lista.size()>0){
					banji.setAdmin(lista.get(0));
					return banji;
					
					
				}
						
				
				
			}
			
			
			
		}
		
		
		
		return null;
	}

	//�޸�����
	public void updatePW(Integer uid, String newPW) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where uid = ?", uid);
		User u = null;
		if(list!= null && list.size()>0){
			
			u = list.get(0);
			
		}
		u.setPassword(newPW);
		this.getHibernateTemplate().save(u);
		
	}

	//�鿴������Ա
	public List<User> ckAllUser(Integer aid) {
		List<User> list = (List<User>) this.getHibernateTemplate()
				.find("from User u where u.banJi.admin.aid = ?", aid);
        List<User> listu =new ArrayList<User>();
        for(int i=0;i<list.size();i++){
        	User u = list.get(i);
        	List<BanJi> listbj =(List<BanJi>) this.getHibernateTemplate()
        			.find("from BanJi where bjid = ?", list.get(i).getBanJi().getBjid());
        	if(listbj!= null && listbj.size()>0){
        		
        		u.setBanJi(listbj.get(0));
        	}
        	listu.add(u);
        	
        	
        }

		
		return listu;
	}

	//ɾ���û�
	public void deleteUser(Integer uid) {
		List<User> list =(List<User>) this.getHibernateTemplate()
				.find("from User where uid = ?", uid);
		if(list!=null && list.size()>0){
			this.getHibernateTemplate().delete(list.get(0));
			
		}
		
	}

}
