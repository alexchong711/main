package seedu.address.model.course;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.model.moduleinfo.ModuleInfo;

/**
 * Checks from list of modules that contains at least
 * a certain threshold
 */
public class ContainsAtLeast extends CourseRequirement {

    private static final String MESSAGE_CONSTRAINTS = "Number of modules must be at least 0!";

    public ContainsAtLeast(String description, int number, String... regexes) {
        super(description,
            new Predicate<List<ModuleInfo>>() {
                @Override
                public boolean test(List<ModuleInfo> moduleInfos) {
                    return moduleInfos.stream()
                        .filter(x -> Stream.of(regexes).anyMatch(y -> x.getCodeString().matches(y)))
                        .count() >= number;
                }
            },
            new Function<List<ModuleInfo>, String>() {
                @Override
                public String apply(List<ModuleInfo> moduleInfos) {
                    StringBuilder sb = new StringBuilder();
                    return sb.toString();
                }
            }
        );
        checkArgument(isValidNumber(number), MESSAGE_CONSTRAINTS);

        requireAllNonNull(regexes);
        requireAllNonNull(number, description, regexes);

    }

    public static boolean isValidNumber(int number) {
        return number >= 0;
    }
}
