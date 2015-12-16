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

public class GetMoreQuestionsController extends AbstractController {
		
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuestionDao dao = QuestionDao.getInstance();
		int lastQuestionId = ServletRequestUtils.getIntParameter(request, "lastQuestionId");
		
		List<Question> questions = dao.findAllByPage(lastQuestionId, QuestionDao.DEFALUT_LIMIT);
		
		return jsonView();
	}

}
