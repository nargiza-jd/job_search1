package kg.attractor.job_search_java23.dao.mapper;

import kg.attractor.job_search_java23.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getInt("id"))
                .username(rs.getString("username"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .phone(rs.getString("phone"))
                .profileImageUrl(rs.getString("profile_image_url"))
                .role(rs.getString("role"))
                .build();
    }
}