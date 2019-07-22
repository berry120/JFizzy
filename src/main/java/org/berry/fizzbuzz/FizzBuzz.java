package org.berry.fizzbuzz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Michael
 */
@EqualsAndHashCode
public class FizzBuzz {

    private final List<FizzBuzzRule> rules;

    /**
     * Our constructor is private; we use static initialisers. It's 2019 and
     * public constructors are no longer trendy.
     */
    private FizzBuzz(List<FizzBuzzRule> rules) {
        checkDuplicateRules(rules);
        this.rules = Collections.unmodifiableList(rules);
    }

    /**
     * Create a new FizzBuzz object using
     *
     * @param rules
     * @return
     */
    public static FizzBuzz of(FizzBuzzRule... rules) {
        return new FizzBuzz(Arrays.asList(rules));
    }

    public List<String> getResultInRange(int startInclusive, int endExclusive) {
        List<String> ret = new ArrayList<>();
        forEachInRange(startInclusive, endExclusive, ret::add);
        return ret;
    }

    public void forEachInRange(int startInclusive, int endExclusive, Consumer<? super String> consumer) {
        IntStream.range(startInclusive, endExclusive)
                .mapToObj(
                        i -> rules.stream()
                                .filter(r -> r.matches(i))
                                .findFirst()
                                .map(r -> r.substitute(i))
                                .orElse(Integer.toString(i))
                )
                .forEach(consumer);
    }

    private static void checkDuplicateRules(List<FizzBuzzRule> rules) {
        if (rules.size() < 2) {
            return;
        }
        for (int i = 1; i < rules.size(); i++) {
            FizzBuzzRule rule = rules.get(i);
            rules.subList(0, i).forEach(testRule -> {
                if (rule.alreadyCoveredBy(testRule)) {
                    throw new IllegalArgumentException("\"" + rule.getHumanDescription() + "\" will never execute, already covered by \"" + testRule.getHumanDescription() + "\"");
                }
            });
        }
    }

}
