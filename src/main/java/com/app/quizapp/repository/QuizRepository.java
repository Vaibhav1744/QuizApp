package com.app.quizapp.repository;


import com.app.quizapp.model.QuizQuestion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;
import java.util.Optional;

@Repository
public class QuizRepository {

    private final JdbcTemplate jdbcTemplate;

    public QuizRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<QuizQuestion> rowMapper = (rs, rowNum) -> {
        QuizQuestion q = new QuizQuestion();
        q.setId(rs.getInt("id"));
        q.setQuestion(rs.getString("question"));
        q.setOptionA(rs.getString("option_a"));
        q.setOptionB(rs.getString("option_b"));
        q.setOptionC(rs.getString("option_c"));
        q.setOptionD(rs.getString("option_d"));
        q.setCorrectOption(rs.getString("correct_option"));
        return q;
    };

    public List<QuizQuestion> findAll() {
        return jdbcTemplate.query("SELECT * FROM quiz_question", rowMapper);
    }

    public Optional<QuizQuestion> findById(int id) {
        List<QuizQuestion> list =
                jdbcTemplate.query("SELECT * FROM quiz_question WHERE id=?", rowMapper, id);
        return list.stream().findFirst();
    }

    public void save(QuizQuestion q) {
        jdbcTemplate.update(
                "INSERT INTO quiz_question(question, option_a, option_b, option_c, option_d, correct_option) VALUES (?,?,?,?,?,?)",
                q.getQuestion(),
                q.getOptionA(),
                q.getOptionB(),
                q.getOptionC(),
                q.getOptionD(),
                q.getCorrectOption()
        );
    }

    public void saveResult(String username, int score, int total) {
        jdbcTemplate.update(
                "INSERT INTO quiz_result(username, score, total) VALUES (?,?,?)",
                username, score, total
        );
    }
}
