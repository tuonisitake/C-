package com.dj.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;



import com.dj.entity.AnswerSituation;
import com.dj.entity.Question;
import com.dj.entity.Test;
import com.dj.entity.TestPaper;
import com.dj.entity.TestRecord;
import com.dj.entity.TpRelationq;
import com.dj.entity.User;

public class TestDaoImpl extends HibernateDaoSupport implements TestDao {

	//����uid��ѯ�û�
	public User ckUser(Integer uid) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where uid = ?", uid);
		if(list!= null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}

	//�鿴������Ϣ
	public Test ckTest() {
		List<Test> list = (List<Test>) this.getHibernateTemplate().find("from Test t order by t.tdate desc", null);
		if(list!=null && list.size() > 0){
			return list.get(0);
		}
			
		
		return null;
	}

	//�����ȡ�Ծ�
	public TestPaper cqTestPaper() {
		List<TestPaper> list = (List<TestPaper>) this.getHibernateTemplate().find("from TestPaper", null);
		if(list!=null && list.size() > 0){
			//���������
			Random rd = new Random();
			int rdnum = rd.nextInt(list.size());
			return list.get(rdnum);
		}
		return null;
	}

	//���濼�Լ�¼
	public void saveTestRecord(TestRecord tr) {
		this.getHibernateTemplate().save(tr);
		
	}

	//�����û�id��ѯ���Լ�¼
	public TestRecord ckTestRecord(Integer uid) {
		List<TestRecord> list =(List<TestRecord>) this.getHibernateTemplate().find("from TestRecord tr where tr.user.uid = ? order by tr.trdate desc", uid);
		if(list!=null && list.size()>0){
			return list.get(list.size()-1);
		}
		
		return null;
	}

	//�����Ծ��ID��ȡ�������Ծ�Ķ�Ӧ��ϵ
	public List<TpRelationq> getTPQ(Integer tpid) {
		List<TpRelationq> list = (List<TpRelationq>) this.getHibernateTemplate().find("from TpRelationq tp where tp.testPaper.tpid=? order by tp.num asc",tpid);
		return list;
	}

	//�����������Ծ��Ӧ��ϵ�л�ȡ������id��������
	public Question ckQuestion(Integer qid) {
		List<Question> list =(List<Question>) this.getHibernateTemplate().find("from Question where qid = ?", qid);
		if(list!=null && list.size() > 0){
			
			return list.get(0);
		}
		return null;
	}

	//�������뿼�Լ�¼���浽�����������
	public void saveAnswerSituation(AnswerSituation as) {
		this.getHibernateTemplate().save(as);
		
	}

	//�����Ծ�ID���Ծ��������ϵ�����ҵ�ѡ����
	public List<TpRelationq> ckxzq(Integer tpid) {
		List<TpRelationq> xzqlist1 = (List<TpRelationq>) this.getHibernateTemplate().
				find("from TpRelationq tpq where tpq.testPaper.tpid = ? and tpq.question.questionType.qtid = ? order by tpq.num asc",tpid,1);
		List<TpRelationq> list = new ArrayList<TpRelationq>();
		//ͨ��ѭ����ѡ�������list������
		for(int i=0;i< xzqlist1.size();i++){
			//���鵽�ļ�¼����tpq������
			TpRelationq tpq = xzqlist1.get(i);
			Question q = new Question();
			//ͨ���Ծ�������Ĺ�ϵ�ҵ���֮��Ӧ��ѡ����
			List<Question> xzqlist2 = (List<Question>) this.getHibernateTemplate().
					find("from Question where qid = ?",xzqlist1.get(i).getQuestion().getQid());
			//���鵽��ѡ�������q
			q = xzqlist2.get(0);
			//�ڹ�ϵ������������
			tpq.setQuestion(q);
			//���й�ϵ���������list��
			list.add(tpq);
			
		}
		
		
		return list;
	}

	//�����Ծ�ID���Ծ��������ϵ�����ҵ��ж���
	public List<TpRelationq> ckpdq(Integer tpid) {
		List<TpRelationq> pdqlist1 = (List<TpRelationq>) this.getHibernateTemplate().
				find("from TpRelationq tpq where tpq.testPaper.tpid = ? and tpq.question.questionType.qtid = ? order by tpq.num asc", tpid,2);
		List<TpRelationq> list = new ArrayList<TpRelationq>();
		for(int i=0;i< pdqlist1.size();i++){
			//���鵽�ļ�¼����tpq������
			TpRelationq tpq = pdqlist1.get(i);
			Question q = new Question();
			//ͨ���Ծ�������Ĺ�ϵ�ҵ���֮��Ӧ���ж���
			List<Question> pdqlist2 = (List<Question>) this.getHibernateTemplate().
					find("from Question where qid = ?",pdqlist1.get(i).getQuestion().getQid());
			//���鵽���ж������q
			q = pdqlist2.get(0);
			//�ڹ�ϵ������������
			tpq.setQuestion(q);
			//���й�ϵ���������list��
			list.add(tpq);
		}
		
		
		return list;
	}

	//�����Ծ�ID���Ծ��������ϵ�����ҵ������
	public List<TpRelationq> cktkq(Integer tpid) {
		List<TpRelationq> tkqlist1 = (List<TpRelationq>) this.getHibernateTemplate().
				find("from TpRelationq tpq where tpq.testPaper.tpid = ? and tpq.question.questionType.qtid = ? order by tpq.num asc", tpid,3);
		List<TpRelationq> list = new ArrayList<TpRelationq>();
		for(int i=0;i< tkqlist1.size();i++){
			//���鵽�ļ�¼����tpq������
			TpRelationq tpq = tkqlist1.get(i);
			Question q = new Question();
			//ͨ���Ծ�������Ĺ�ϵ�ҵ���֮��Ӧ�������
			List<Question> tkqlist2 = (List<Question>) this.getHibernateTemplate().
					find("from Question where qid = ?",tkqlist1.get(i).getQuestion().getQid());
			//���鵽����������q
			q = tkqlist2.get(0);
			//�ڹ�ϵ������������
			tpq.setQuestion(q);
			//���й�ϵ���������list��
			list.add(tpq);
		}
		return list;
	}

	//�������ⷶΧ�ҳ����ʵ�ѡ����
	public List<Question> ckxz(String qscope) {
		
			List<Question> listxz = (List<Question>) this.getHibernateTemplate()
					.find("from Question q where q.questionType.qtid = ? and q.qscope = ?",1,qscope);
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
			
			return list;
		
		
	}

	//�������ⷶΧ�ҳ����ʵ��ж���
	public List<Question> ckpd(String qscope) {
		List<Question> listxz = (List<Question>) this.getHibernateTemplate()
				.find("from Question q where q.questionType.qtid = ? and q.qscope = ?",2,qscope);
		List<Question> list = new ArrayList<Question>();
		Question q = null;
		Random rand = new Random();		
		int[] r = new int[3];
		int randnum = 0 ;
		int i=0;
		while(i<3){
			randnum = rand.nextInt(listxz.size());
			boolean flag = true;
			for(int j=0;j<3;j++){
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
		
		return list;
	}

	//���
	public List<Question> cktk(String qscope) {
		List<Question> listtk = (List<Question>) this.getHibernateTemplate()
				.find("from Question q where q.questionType.qtid = ? and q.qscope = ?",3,qscope);
		Random rand = new Random();
		int randnum = rand.nextInt(listtk.size());
		List<Question> list =new ArrayList<Question>();
		Question q = listtk.get(randnum);
		list.add(q);
		
		return list;
	}

	//����qid��������
	public Question findQ(Integer qid) {
		List<Question> list = (List<Question>) this.getHibernateTemplate().
		    find("from Question where qid = ?", qid);
		if(list!=null && list.size()>0){
			Question q = list.get(0);
			return q;
		}
		
		return null;
	}

}
