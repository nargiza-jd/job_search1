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
        String sql = "SELECT * FROM VACANCIES";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public Optional<Vacancy> getVacancyById(int id) {
        String sql = "SELECT * FROM VACANCIES WHERE id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id)
                )
        );
    }

    public void addVacancy(Vacancy vacancy) {
        String sql = "INSERT INTO VACANCIES (title, description, company, location, salary) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, vacancy.getTitle(), vacancy.getDescription(), vacancy.getCompany(), vacancy.getLocation(), vacancy.getSalary());
    }

    public void updateVacancy(Vacancy vacancy) {
        String sql = "UPDATE VACANCIES SET title = ?, description = ?, company = ?, location = ?, salary = ? WHERE id = ?";
        jdbcTemplate.update(sql, vacancy.getTitle(), vacancy.getDescription(), vacancy.getCompany(), vacancy.getLocation(), vacancy.getSalary(), vacancy.getId());
    }

    public void deleteVacancyById(int id) {
        String sql = "DELETE FROM VACANCIES WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
