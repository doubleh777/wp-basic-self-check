package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.AnswerDao;
import next.model.Answer;

public class AnswerSaveController extends AbstractController {
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AnswerDao dao = AnswerDao.getInstance();
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		String writer = ServletRequestUtils.getStringParameter(request, "writer");
		String contents = ServletRequestUtils.getStringParameter(request, "contents");
		
		dao.insert(new Answer(writer, contents, questionId));
		
		return jsonView();
	}

}
