package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class ListController extends AbstractController {
	
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QuestionDao questionDao = QuestionDao.getInstance();
		List<Question> questions;
		
		questions = questionDao.findAllByPage(QuestionDao.INITIAL_OFFSET, QuestionDao.INTIAL_LIMIT);
		
		ModelAndView mav = jstlView("list.jsp");
		mav.addObject("questions", questions);
		return mav;
	}
}
