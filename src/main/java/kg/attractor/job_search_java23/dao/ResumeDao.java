package kg.attractor.job_search_java23.dao;

import kg.attractor.job_search_java23.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Resume> getResumes() {
        String sql = "SELECT * FROM RESUMES";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public Optional<Resume> getResumeById(int id) {
        String sql = "SELECT * FROM RESUMES WHERE ID = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)
                )
        );
    }

    public List<Resume> getResumesByUserId(int userId) {
        String sql = "SELECT * FROM RESUMES WHERE APPLICANT_ID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }

    public void save(Resume resume) {
        String sql = """
            INSERT INTO RESUMES (USERNAME, SALARY, IS_ACTIVE, CREATED_DATE, UPDATE_TIME, APPLICANT_ID, CATEGORY_ID)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
                resume.getUsername(),
                resume.getSalary(),
                resume.isActive(),
                resume.getCreatedDate(),
                resume.getUpdateTime(),
                resume.getApplicantId(),
                resume.getCategoryId()
        );
    }

    public void updateResume(Resume resume) {
        String sql = """
            UPDATE RESUMES
            SET USERNAME = ?, SALARY = ?, IS_ACTIVE = ?, UPDATE_TIME = ?, CATEGORY_ID = ?
            WHERE ID = ?
        """;

        jdbcTemplate.update(sql,
                resume.getUsername(),
                resume.getSalary(),
                resume.isActive(),
                resume.getUpdateTime(),
                resume.getCategoryId(),
                resume.getId()
        );
    }

    public void deleteResumeById(int id) {
        String sql = "DELETE FROM RESUMES WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }
}