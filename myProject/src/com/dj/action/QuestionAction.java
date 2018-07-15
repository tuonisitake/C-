package com.dj.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;

import com.dj.entity.Admin;
import com.dj.entity.Question;
import com.dj.entity.QuestionType;
import com.dj.service.QuestionService;
import com.dj.utils.ExcelUtil;
import com.dj.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author lenovo
 *
 */
public class QuestionAction extends ActionSupport implements ModelDriven<Question>{

	private QuestionService questionService;
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	//ģ������
	private Question question=new Question();
	public Question getModel() {
		
		return question;
	}
	
	private int page;
	public void setPage(int page) {
		this.page = page;
	}
	private int qtid;
	
	
	public void setQtid(int qtid) {
		this.qtid = qtid;
	}
	
	private int aid;
	
	public void setAid(int aid) {
		this.aid = aid;
	}
	private String qscopee;
	
	public void setQscopee(String qscopee) {
		this.qscopee = qscopee;
	}
	private String qdifficultye;
	
	public void setQdifficultye(String qdifficultye) {
		this.qdifficultye = qdifficultye;
	}
	private File upload;
	private String uploadFileName;
	
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	//�鿴��ϸ����
	public String ckQuestionDetail(){
		
		Question questionDetail = questionService.ckQuestionDetail(question.getQid());
		ActionContext.getContext().getValueStack().set("questionDetail", questionDetail);
		if(questionDetail.getQuestionType().getQtid() == 1){
			return "ckxzQuestion";
		}else if(questionDetail.getQuestionType().getQtid() == 2){
			return "ckpdQuestion";
		}else{
			return "cktkQuestion";
		}

	}
	//����Ա�鿴��ϸ����
	public String ckAdminQuestionDetail(){
		Question questionDetail = questionService.ckQuestionDetail(question.getQid());
		ActionContext.getContext().getValueStack().set("questionDetail", questionDetail);
		if(questionDetail.getQuestionType().getQtid() == 1){
			return "ckaxzQuestion";
		}else if(questionDetail.getQuestionType().getQtid() == 2){
			return "ckapdQuestion";
		}else{
			return "ckatkQuestion";
		}
		
	}
	//������ѯ����ҳ��
	public String ckQuestionPage(){
		//�ҵ���������
		List<QuestionType> listqt = questionService.ckQuestionType();
		
		ActionContext.getContext().getValueStack().set("listqt", listqt);

		return "ckQuestionPage";
	}
	
	//��ѯ����
	public String ckQuestion(){
		
//		question.setQscope(qscopee);
//		question.setQdifficulty(qdifficultye);
		if("ȫ��".equals(question.getQscope())){
			ActionContext.getContext().getValueStack().set("qscopee", question.getQscope());
		}
		if("ȫ��".equals(question.getQdifficulty())){
			ActionContext.getContext().getValueStack().set("qdifficultye", question.getQdifficulty());
		}
		ActionContext.getContext().getValueStack().set("qcontente", question.getQcontent());
		//�ҵ���������������
		PageBean<Question> pb = questionService.ckQuestion(question,qtid,page);
		//�����Ϸ������������ݴ�ŵ�ֵջ�У����뵽��ʾ����
		ActionContext.getContext().getValueStack().set("pb", pb);

		ActionContext.getContext().getValueStack().set("qtid", qtid);
//		ActionContext.getContext().getValueStack().set("qscopee", qscopee);
//		ActionContext.getContext().getValueStack().set("qdifficultye", qdifficultye);
		
		
		
		
		
		return "ckQuestion";
	}
	
	//���ѡ����
	public String addxzq(){
		Date date =new Date();
		question.setQdate(date);
		questionService.savexzq(question,qtid,aid);
		
		return "addxzq";
	}
	
	//����ж���
	public String addpdq(){
		Date date = new Date();
		question.setQdate(date);
		questionService.savepdq(question,qtid,aid);
		
		return "addpdq";
	}
	
	//��������
	public String addtkq(){
		
		Date date = new Date();
		question.setQdate(date);
		questionService.savepdq(question, qtid, aid);
		return "addtkq";
	}
	
	//ɾ������
	public String deleteQuestion(){
		questionService.deleteQ(question.getQid());
		List<QuestionType> listqt = questionService.ckQuestionType();
		ActionContext.getContext().getValueStack().set("listqt", listqt);
		
		
		
		return "ckQuestion";
	}
	/**
	 * ��������ѡ����
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String uploadxzq() throws FileNotFoundException, IOException{
		
		
		
		//InputStream is = ServletActionContext.getRequest().getInputStream();
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(upload));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		 
		
		HSSFSheet hssfSheet = wb.getSheetAt(0);
		QuestionType qt = questionService.findQT(qtid);
		Admin a = questionService.findA(aid);
		if(hssfSheet!=null){
			for(int rowNum=1;rowNum<=hssfSheet.getLastRowNum();rowNum++){
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if(hssfRow == null){
					continue;
				}
				
				Question q = new Question();
				q.setQcontent(ExcelUtil.formatCell(hssfRow.getCell(0)));
				q.setQaoption(ExcelUtil.formatCell(hssfRow.getCell(1)));
				q.setQboption(ExcelUtil.formatCell(hssfRow.getCell(2)));
				q.setQcoption(ExcelUtil.formatCell(hssfRow.getCell(3)));
				q.setQanswer(ExcelUtil.formatCell(hssfRow.getCell(4)));
				q.setQanalysis(ExcelUtil.formatCell(hssfRow.getCell(5)));
				q.setQkword(ExcelUtil.formatCell(hssfRow.getCell(6)));
				q.setQdifficulty(ExcelUtil.formatCell(hssfRow.getCell(7)));
				q.setQscope(ExcelUtil.formatCell(hssfRow.getCell(8)));
				q.setQuestionType(qt);
				q.setAdmin(a);
				q.setQdate(new Date());
				questionService.saveq(q);
				
			}
		}
		
		
		return "uploadxzq";
	}

}
