package kg.attractor.job_search_java23.dao;

import kg.attractor.job_search_java23.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VacancyDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Vacancy> getVacancies() {
        String sql = "select * from vacancies";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public Optional<Vacancy> getVacancyById(int id) {
        String sql = "select * from vacancies where id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id)
                )
        );
    }
}