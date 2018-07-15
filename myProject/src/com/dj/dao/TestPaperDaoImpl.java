package com.dj.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dj.entity.Admin;
import com.dj.entity.Question;
import com.dj.entity.QuestionType;
import com.dj.entity.TestPaper;
import com.dj.entity.TpRelationq;
import com.dj.entity.User;

public class TestPaperDaoImpl extends HibernateDaoSupport implements
		TestPaperDao {

    //����uid��ѯ�û�
	public User ckUser(Integer uid) {
		
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where uid = ?", uid);
		if(list!= null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}

	//����aid�鿴�Ծ�
	public List<TestPaper> ckTestPaper(Integer aid) {
		List<TestPaper> list = (List<TestPaper>) this.getHibernateTemplate().
				find("from TestPaper tp where tp.admin.aid = ?", aid);
		
		return list;
	}

	//�鿴���Ծ�����
	public List<TpRelationq> ckTPQuestion(Integer tpid) {
		List<TpRelationq> listtrq = (List<TpRelationq>) this.getHibernateTemplate().
				find("from TpRelationq tpq where tpq.testPaper.tpid = ? order by tpq.num asc", tpid);
		List<TpRelationq> list =new ArrayList<TpRelationq>();
		for(int i=0;i<listtrq.size();i++){
			
			TpRelationq tpq = listtrq.get(i);
			Question q = new Question();
			List<Question> listq = (List<Question>) this.getHibernateTemplate().
					find("from Question where qid = ?", listtrq.get(i).getQuestion().getQid());
			q=listq.get(0);
			tpq.setQuestion(q);
			list.add(tpq);
		}
		return list;
	}

	//�����Ծ������Ϣ
	public void szTestPaper(TestPaper testPaper, Integer aid) {
		List<Admin> a = (List<Admin>) this.getHibernateTemplate().
				find("from Admin where aid = ?", aid);
		if(a!=null && a.size()>0){
			testPaper.setAdmin(a.get(0));
		}
		
	}

	//��ȡ������������
	public List<QuestionType> ckAllQT() {
		List<QuestionType> list = (List<QuestionType>) this.getHibernateTemplate().
				find("from QuestionType", null);
		return list;
	}

	//��ȡ�Ծ�
	public TestPaper getTestPaper() {
		List<TestPaper> list = (List<TestPaper>) this.getHibernateTemplate().
				find("from TestPaper tp order by tp.tpid desc", null);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
    
	//�����ȡѡ����
	public List<Question> cqXZQ() {
		List<Question> listxz = (List<Question>) this.getHibernateTemplate()
				.find("from Question q where q.questionType.qtid = ?", 1);
		List<Question> list = new ArrayList<Question>();
		Question q = null;
		Random rand = new Random();
		int[] r = new int[6];
		int randnum = 0 ;
		int i=0;
		while(i<6){
			randnum = rand.nextInt(listxz.size());
			boolean flag = true;
			for(int j=0;j<6;j++){
				if(randnum==r[j]){
					flag=false;
					break;
				}
			}
			if(flag){
				r[i]=randnum;
				q = listxz.get(randnum);
				list.add(q);
				i++;
			}
			
		}
//		for(int i=0;i<6;i++){
//			
//			
//		    randnum = rand.nextInt(listxz.size());
//
//			q = listxz.get(randnum);
//			list.add(q);
//			
//			
//		}
		
		return list;
	}

	//�����ȡ�ж���
	public List<Question> cqPDQ() {
		
		List<Question> listpd = (List<Question>) this.getHibernateTemplate()
				.find("from Question q where q.questionType.qtid = ?", 2);
		List<Question> list = new ArrayList<Question>();
		Question q = null;
		Random rand = new Random();
	
		int[] r = new int[3];
		int randnum = 0 ;
		int i=0;
		while(i<3){
			randnum = rand.nextInt(listpd.size());
			boolean flag = true;
			for(int j=0;j<3;j++){
				if(randnum==r[j]){
					flag=false;
					break;
				}
			}
			if(flag){
				r[i]=randnum;
				q = listpd.get(randnum);
				list.add(q);
				i++;
			}
			
		}
//		for(int i=0;i<3;i++){
//			
//			
//		    randnum = rand.nextInt(listpd.size());
//
//			q = listpd.get(randnum);
//			list.add(q);
//			
//			
//		}
		
		return list;
	}

	//�����ȡ�����
	public List<Question> cqTKQ() {
		List<Question> listtk = (List<Question>) this.getHibernateTemplate()
				.find("from Question q where q.questionType.qtid = ?", 3);
		Random rand = new Random();
		int randnum = rand.nextInt(listtk.size());
		List<Question> list =new ArrayList<Question>();
		Question q = listtk.get(randnum);
		list.add(q);
		
		return list;
	}

	//�����Ծ���Ϣ
	public void saveTestPaper(TestPaper testPaper) {
		this.getHibernateTemplate().save(testPaper);
		
	}

	//���Ծ���Ϣ��ӵ���ϵ����
	public void tjTestPaperSX(Integer tpid, Integer qid) {
		TpRelationq tpq = new TpRelationq();
		//�����Ծ�id��ѯ�Ծ�
		List<TestPaper> listtp = (List<TestPaper>) this.getHibernateTemplate()
				.find("from TestPaper where tpid = ?", tpid);
		if(listtp!=null && listtp.size()>0){
			tpq.setTestPaper(listtp.get(0));
			
		}
		//��������id��ѯ����
		List<Question> listq = (List<Question>) this.getHibernateTemplate()
				.find("from Question where qid = ?", qid);
		if(listq!=null && listq.size()>0){
			tpq.setQuestion(listq.get(0));
		}
		this.getHibernateTemplate().save(tpq);
		
	}

	//ɾ���Ծ�
	public void deleteTestPaper(Integer tpid) {
		List<TestPaper> listtp = (List<TestPaper>) this.getHibernateTemplate()
				.find("from TestPaper where tpid = ?", tpid);
		if(listtp!=null && listtp.size()>0){
			this.getHibernateTemplate().delete(listtp.get(0));
		}
		List<TpRelationq> listt = (List<TpRelationq>) this.getHibernateTemplate()
				.find("from TpRelationq tpq where tpq.testPaper.tpid = ?", tpid);
		if(listt!=null && listt.size()>0){
			for(int i=0;i<listt.size();i++){
				this.getHibernateTemplate().delete(listt.get(i));
			}
			
		}
		
	}

	


}
