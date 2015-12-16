package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import next.model.Question;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class QuestionDao {
	
	public static final int INITIAL_OFFSET = 0;
	public static final int INTIAL_LIMIT = 5;
	public static final int DEFALUT_LIMIT = 10;

	
	private static QuestionDao instance;
	private QuestionDao(){};
	
	public static QuestionDao getInstance(){
		if(instance == null) return new QuestionDao();
		return instance;
	}

	public void insert(Question question) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfComment) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, 
				question.getWriter(), 
				question.getTitle(), 
				question.getContents(),
				new Timestamp(question.getTimeFromCreateDate()), 
				question.getCountOfComment());
	}
	
	public List<Question> findAllByPage(int lastQuestionId, int limit) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT questionId, writer, title, createdDate, countOfComment FROM QUESTIONS "
				+ "ORDER BY questionId DESC LIMIT ?, ?";
		
		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"), null,
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfComment"));
			}
			
		};
		
		int offset = lastQuestionId;
		return jdbcTemplate.query(sql, rm, offset, limit);
	}
	
	public void deleteById(long questionId){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "DELETE FROM QUESTIONS WHERE questionId = ?";
		
		jdbcTemplate.update(sql, questionId);
	}

	public Question findById(long questionId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfComment FROM QUESTIONS "
				+ "WHERE questionId = ?";
		
		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"),
						rs.getString("writer"), rs.getString("title"),
						rs.getString("contents"),
						rs.getTimestamp("createdDate"),
						rs.getInt("countOfComment"));
			}
			
		};
		
		return jdbcTemplate.queryForObject(sql, rm, questionId);
	}

}
