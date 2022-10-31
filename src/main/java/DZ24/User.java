package DZ24;


import com.github.javafaker.Faker;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class User implements Comparable<User> {

    private final String name;
    private final int age;
    private final String email;
    private final String gender;
    public User() {
        Faker faker = new Faker();
        this.name = faker.name().firstName();
        this.age = faker.random().nextInt(1,50);
        this.email = faker.internet().emailAddress();
        this.gender = String.valueOf(randomGender());

    }

    public static void main(String[] args) {
        Map<Object, List<User>> users = Stream.generate(User::new)
                .limit(20)
                .filter(user -> user.age > 18)
                .sorted()
                .filter(user -> !(user.email == null))
                .collect(Collectors.groupingBy(User::getGender));

        System.out.println(users.toString());
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return this.name;
                /*"Ops{" +
                "name='" + name + '\'' +
                ", age=" + age + '\'' + ", email=" + email +'\'' + ", gender=" + gender +
                '}';*/

    }

    public int compareTo(User u) {
        return 0;
    }

    private enum Gender {MALE,FEMALE}

    private static final List<Gender> VALUES =
            Collections.unmodifiableList(Arrays.asList(Gender.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Gender randomGender()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

