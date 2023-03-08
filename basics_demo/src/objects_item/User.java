package objects_item;

import java.util.Objects;

/**
 * @author Pumpkin
 * @createTime 2023/2/23 18:11
 */
public class User {
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User aUser = (User) o;
        return Objects.equals(name, aUser.name) && Objects.equals(age, aUser.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
