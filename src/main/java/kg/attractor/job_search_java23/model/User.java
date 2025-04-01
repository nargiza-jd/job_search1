package kg.attractor.job_search_java23.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String profileImageUrl;
    private int roleId;

    @Override
    public String toString() {
        return String.format("Пользователь: %s (Role ID: %d)", username, roleId);
    }
}