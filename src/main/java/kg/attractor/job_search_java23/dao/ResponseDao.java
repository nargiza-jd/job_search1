package kg.attractor.job_search_java23.dao;

import kg.attractor.job_search_java23.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResponseDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Response> getResponses() {
        String sql = "SELECT * FROM response";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Response.class));
    }

    public Optional<Response> getResponseById(int id) {
        String sql = "SELECT * FROM response WHERE id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Response.class), id)
                )
        );
    }

    public void save(Response response) {
        String sql = """
            INSERT INTO response (resume_id, vacancy_id, applicant_id, employer_id)
            VALUES (?, ?, ?, ?)
        """;
        jdbcTemplate.update(
                sql,
                response.getResumeId(),
                response.getVacancyId(),
                response.getApplicantId(),
                response.getEmployerId()
        );
    }

    public void deleteResponseById(int id) {
        String sql = "DELETE FROM response WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}