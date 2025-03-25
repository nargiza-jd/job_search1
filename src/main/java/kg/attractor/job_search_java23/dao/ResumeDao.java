package kg.attractor.job_search_java23.dao;

import kg.attractor.job_search_java23.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Resume> getResumes() {
        String sql = "SELECT * FROM resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public Optional<Resume> getResumeById(int id) {
        String sql = "SELECT * FROM resumes WHERE id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)
        ));
    }

    public List<Resume> getResumesByUserId(int userId) {
        String sql = "SELECT * FROM resumes WHERE user_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }

    public void addResume(Resume resume) {
        String sql = "INSERT INTO resumes (user_id, title, description) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, resume.getUserId(), resume.getTitle(), resume.getDescription());
    }

    public void updateResume(Resume resume) {
        String sql = "UPDATE resumes SET title = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, resume.getTitle(), resume.getDescription(), resume.getId());
    }

    public void deleteResumeById(int id) {
        String sql = "DELETE FROM resumes WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}