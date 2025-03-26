package kg.attractor.job_search_java23.dao;

import kg.attractor.job_search_java23.dao.mapper.UserMapper;
import kg.attractor.job_search_java23.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final KeyHolder keyHolder = new GeneratedKeyHolder();

    public List<User> getUsers() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public Optional<User> getUserById(int id) {
        String sql = "SELECT * FROM USERS WHERE id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new UserMapper(), id)
                )
        );
    }

    public void create(User user) {
        String sql = "INSERT INTO USERS(username, password) VALUES (:username, :password)";
        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("username", user.getUsername())
                        .addValue("password", user.getPassword())
        );
    }

    public int createAndReturnId(User user) {
        String sql = "INSERT INTO USERS(username, password) VALUES (?, ?)";

        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPassword());
                    return ps;
                }, keyHolder
        );
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public List<User> findByName(String name) {
        String sql = "SELECT * FROM USERS WHERE username ILIKE ?";
        return jdbcTemplate.query(sql, new UserMapper(), "%" + name + "%");
    }

    public List<User> findByPhone(String phone) {
        String sql = "SELECT * FROM USERS WHERE phone LIKE ?";
        return jdbcTemplate.query(sql, new UserMapper(), "%" + phone + "%");
    }

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM USERS WHERE email = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new UserMapper(), email)
                )
        );
    }

    public void updateUser(User user) {
        String sql = "UPDATE USERS SET username = ?, email = ?, phone = ?, profile_image_url = ?, password = ?, role = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPhone(), user.getProfileImageUrl(), user.getPassword(), user.getRole(), user.getId());
    }
}