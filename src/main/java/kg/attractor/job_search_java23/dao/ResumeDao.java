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
        String sql = "select * from resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public Optional<Resume> getResumeById(int id) {
        String sql = "select * from resumes where id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)
                )
        );
    }
}