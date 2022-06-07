package by.vyshemirski.user.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "users")
@Data
public class User {
    @Id
    private Long id;
    private String title;
}
