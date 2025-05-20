package les7PageObjects.utils;

import com.github.javafaker.Faker;
import java.util.Locale;

public class RandomUtils {

    private static Faker faker = new Faker(new Locale("eng"));

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomGender() {
        return faker.options().option(
                faker.demographic().sex(),
                "Other"
        );
    }

    public static String getRandomPhone10Digits() {
        return faker.numerify("##########");
    }

}