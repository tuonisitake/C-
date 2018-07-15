package com.dj.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dj.dao.QuestionDao;
import com.dj.entity.Admin;
import com.dj.entity.Question;
import com.dj.entity.QuestionType;
import com.dj.utils.PageBean;

@Transactional
public class QuestionService {
	private QuestionDao questionDao;
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}
	public Question ckQuestionDetail(Integer qid) {
	
		return questionDao.ckQuestionDetail(qid);
	}
	public List<QuestionType> ckQuestionType() {
		
		return questionDao.ckQuestionType();
	}
	public Question findQuestion(Integer qid) {
		
		return questionDao.findQuestion(qid);
	}
	
	//��ҳ��ѯ����Ӧ����
	public PageBean<Question> ckQuestion(Question question, int qtid, int page) {
		
		PageBean<Question> pageBean = new PageBean<Question>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ�ļ�¼��
		int limit = 3;
		pageBean.setLimit(limit);
		//�����ܵļ�¼��
		int totalCount = 0;
		totalCount = questionDao.findTotalCount(question,qtid);
		pageBean.setTotalCount(totalCount);
		//�����ܵ�ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount /limit ;
		}else{
			totalPage = totalCount / limit +1;
		}
		pageBean.setTotalPage(totalPage);
		//ÿҳ��ʾ�����ݼ���
		//��������¼��ʼ
		int begin = (page - 1) * limit;
		//����ÿҳ��¼��list����
		List<Question> list = questionDao.findQuestionList(question,qtid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public void savexzq(Question question, int qtid, int aid) {
		questionDao.savexzq(question,qtid,aid);
		
	}
	public void savepdq(Question question, int qtid, int aid) {
		questionDao.savepdq(question,qtid,aid);
		
	}
	public void deleteQ(Integer qid) {
		questionDao.deleteQ(qid);
		
	}
	public QuestionType findQT(int qtid) {
		
		return questionDao.findQT(qtid);
	}
	public Admin findA(int aid) {
		
		return questionDao.findA(aid);
	}
	public void saveq(Question q) {
		questionDao.saveq(q);
		
	}
	
	

}
