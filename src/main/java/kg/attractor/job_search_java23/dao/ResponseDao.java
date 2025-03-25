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
        String sql = "SELECT * FROM responses";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Response.class));
    }

    public Optional<Response> getResponseById(int id) {
        String sql = "SELECT * FROM responses WHERE id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Response.class), id)
                )
        );
    }
}