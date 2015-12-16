package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class QuestionDeleteController extends AbstractController {	
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuestionDao questionDao = QuestionDao.getInstance();
		long questionId = ServletRequestUtils.getLongParameter(request,"questionId");
		Question question = questionDao.findById(questionId);
		
		/* comment가 없는 경우 처리 */
		if(!hasComment(questionDao, question)){
			questionDao.deleteById(questionId);
		/* Answer writer Question writer가 같은 경우의 처리 */
		}else if(answerWriterIsSameWithQuestionWriter(questionDao, question)){
			questionDao.deleteById(questionId);
		/* 삭제할 수 없는 경우의 처리 */
		}else{
			
		}
		
		return jsonView();
	}
	
	private boolean answerWriterIsSameWithQuestionWriter(QuestionDao questionDao, Question question) {
		AnswerDao answerDao = AnswerDao.getInstance();
		List<Answer> answers = answerDao.findAllByQuestionId(question.getQuestionId());
		boolean ok = true;
		
		for(Answer answer : answers){
			if(answer.getWriter() != question.getWriter()){
				ok = false;
				break;
			}
		}
		return ok;
	}

	private boolean hasComment(QuestionDao questionDao, Question question){
		if(question.getCountOfComment() == 0) return false;
		return true;
	}

}
