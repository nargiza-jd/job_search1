package kg.attractor.job_search_java23.dao.mapper;

import kg.attractor.job_search_java23.model.Vacancy;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class VacancyMapper implements RowMapper<Vacancy> {
    @Override
    public Vacancy mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Vacancy.builder()
                .id(rs.getInt("ID"))
                .username(rs.getString("USERNAME"))
                .description(rs.getString("DESCRIPTION"))
                .salary(rs.getInt("SALARY"))
                .experienceFrom(rs.getInt("EXP_FROM"))
                .experienceTo(rs.getInt("EXP_TO"))
                .isActive(rs.getBoolean("IS_ACTIVE"))
                .createdDate(rs.getTimestamp("CREATED_DATE").toLocalDateTime())
                .updateTime(rs.getTimestamp("UPDATE_TIME").toLocalDateTime())
                .categoryId(rs.getInt("CATEGORY_ID"))
                .authorId(rs.getInt("AUTHOR_ID"))
                .build();
    }
}