package http;

import lombok.Getter;

@Getter
public class User
{
    private final Long id;
    private final String name;

    public User() {
        this.name = "";
        this.id = null;
    }

    public User(Long id, String name) {
        this.name = name;
        this.id = id;
    }
}