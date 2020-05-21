package invalid.bt.spring_address_book.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.test.context.ActiveProfilesResolver;

public class TestProfileResolver implements ActiveProfilesResolver {
    @Override
    public String @NotNull [] resolve(@NotNull Class<?> testClass) {
        return new String[]{getProfile()};
    }

    private String getProfile() {
        final String ci = System.getProperty("CI");

        if (ci == null || !ci.equals("1")) {
            return "local-db"; // local db for faster start/stop cycles while testing/developing
        }

        // Run on testcontainer otherwise
        return "default"; // ./mvnw test -DCI=1
    }
}
