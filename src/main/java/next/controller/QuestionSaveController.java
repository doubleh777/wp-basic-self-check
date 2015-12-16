package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.QuestionDao;
import next.model.Question;

public class QuestionSaveController extends AbstractController {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuestionDao dao = QuestionDao.getInstance();

		String writer = ServletRequestUtils.getStringParameter(request, "writer");
		String title = ServletRequestUtils.getStringParameter(request, "title");
		String contents = ServletRequestUtils.getStringParameter(request, "contents");

		dao.insert(new Question(writer, title, contents));
		
		return jstlView("redirect:list.next");
	}
	
}
