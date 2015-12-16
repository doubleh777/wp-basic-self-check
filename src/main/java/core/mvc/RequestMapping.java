package core.mvc;

import java.util.HashMap;
import java.util.Map;

import next.controller.AnswerSaveController;
import next.controller.GetMoreQuestionsController;
import next.controller.ListController;
import next.controller.ListControllerForJsonView;
import next.controller.QuestionDeleteController;
import next.controller.QuestionSaveController;
import next.controller.ShowController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	private Map<String, Controller> mappings = new HashMap<String, Controller>();
	
	public void initMapping() {
		mappings.put("/list.next", new ListController());
		mappings.put("/show.next", new ShowController());
		mappings.put("/form.next", new ForwardController("form.jsp"));
		mappings.put("/save.next", new QuestionSaveController());
		mappings.put("/api/addanswer.next",  new AnswerSaveController());
		mappings.put("/api/list.next", new ListControllerForJsonView());
		mappings.put("/api/del.next", new QuestionDeleteController());
		mappings.put("/api/morequestions", new GetMoreQuestionsController());

		logger.info("Initialized Request Mapping!");
	}

	public Controller findController(String url) {
		return mappings.get(url);
	}

	void put(String url, Controller controller) {
		mappings.put(url, controller);
	}

}
