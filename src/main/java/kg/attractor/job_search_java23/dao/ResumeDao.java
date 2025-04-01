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
        String sql = "SELECT * FROM RESUMES";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public Optional<Resume> getResumeById(int id) {
        String sql = "SELECT * FROM RESUMES WHERE id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)
        ));
    }

    public List<Resume> getResumesByUserId(int userId) {
        String sql = "SELECT * FROM RESUMES WHERE user_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }

    public void addResume(Resume resume) {
        String sql = "INSERT INTO RESUMES (user_id, title, category, expected_salary, telegram, email, phone, facebook, linkedin, published) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, resume.getApplicantId(), resume.getTitle(), resume.getCategory(),
                resume.getExpectedSalary(), resume.getTelegram(), resume.getEmail(),
                resume.getPhone(), resume.getFacebook(), resume.getLinkedin(), resume.isPublished());
    }

    public void updateResume(Resume resume) {
        String sql = "UPDATE RESUMES SET title = ?, category = ?, expected_salary = ?, telegram = ?, email = ?, phone = ?, facebook = ?, linkedin = ?, published = ? WHERE id = ?";
        jdbcTemplate.update(sql, resume.getTitle(), resume.getCategory(), resume.getExpectedSalary(),
                resume.getTelegram(), resume.getEmail(), resume.getPhone(),
                resume.getFacebook(), resume.getLinkedin(), resume.isPublished(), resume.getId());
    }

    public void deleteResumeById(int id) {
        String sql = "DELETE FROM RESUMES WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}