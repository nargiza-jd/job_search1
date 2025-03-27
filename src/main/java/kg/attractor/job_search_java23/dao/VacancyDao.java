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
        String sql = "SELECT * FROM vacancies";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public Optional<Vacancy> getVacancyById(int id) {
        String sql = "SELECT * FROM vacancies WHERE id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id)
                )
        );
    }

    public void save(Vacancy vacancy) {
        String sql = """
            INSERT INTO vacancies (
                title, description, salary, category,
                company, location, experience_from, experience_to,
                published, company_id
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(
                sql,
                vacancy.getTitle(),
                vacancy.getDescription(),
                vacancy.getSalary(),
                vacancy.getCategory(),
                vacancy.getCompany(),
                vacancy.getLocation(),
                vacancy.getExperienceFrom(),
                vacancy.getExperienceTo(),
                vacancy.isPublished(),
                vacancy.getCompanyId()
        );
    }

    public void updateVacancy(Vacancy vacancy) {
        String sql = """
            UPDATE vacancies SET
                title = ?,
                description = ?,
                salary = ?,
                category = ?,
                company = ?,
                location = ?,
                experience_from = ?,
                experience_to = ?,
                published = ?,
                company_id = ?
            WHERE id = ?
        """;

        jdbcTemplate.update(
                sql,
                vacancy.getTitle(),
                vacancy.getDescription(),
                vacancy.getSalary(),
                vacancy.getCategory(),
                vacancy.getCompany(),
                vacancy.getLocation(),
                vacancy.getExperienceFrom(),
                vacancy.getExperienceTo(),
                vacancy.isPublished(),
                vacancy.getCompanyId(),
                vacancy.getId()
        );
    }

    public void deleteVacancyById(int id) {
        String sql = "DELETE FROM vacancies WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}