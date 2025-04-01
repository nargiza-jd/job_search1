package kg.attractor.job_search_java23.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final DataSource dataSource;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String fetchUser = """
                SELECT email, password, enabled
                FROM users
                WHERE email = ?
                """;

        String fetchRole = """
                SELECT r.role
                FROM users u
                JOIN role r ON u.role_id = r.id
                WHERE u.email = ?
                """;

        try {
            UserDetails user = jdbcTemplate.queryForObject(
                    fetchUser,
                    new Object[]{email},
                    (rs, rowNum) -> User.builder()
                            .username(rs.getString("email"))
                            .password(rs.getString("password"))
                            .disabled(!rs.getBoolean("enabled"))
                            .authorities(jdbcTemplate.queryForList(fetchRole, new Object[]{email}, String.class).toArray(new String[0]))
                            .build()
            );

            return user;

        } catch (Exception e) {
            throw new UsernameNotFoundException("Пользователь не найден: " + email);
        }
    }
}