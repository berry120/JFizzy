package org.berry.jfizzy;

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

    private final List<Rule> rules;

    /**
     * Our constructor is private; we use static initialisers. It's 2019 and
     * public constructors are no longer trendy.
     */
    private FizzBuzz(List<Rule> rules) {
        checkDuplicateRules(rules);
        this.rules = Collections.unmodifiableList(rules);
    }

    /**
     * Create a new FizzBuzz using a number of rules.
     */
    public static FizzBuzz of(Rule... rules) {
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
                                .flatMap(r -> r.substitute(i))
                                .orElse(Integer.toString(i))
                )
                .forEach(consumer);
    }

    private static void checkDuplicateRules(List<Rule> rules) {
        if (rules.size() < 2) {
            return;
        }
        for (int i = 1; i < rules.size(); i++) {
            Rule rule = rules.get(i);
            List<Rule> sublist = rules.subList(0, i);
            for(int j=0 ; j<sublist.size() ; j++) {
                Rule testRule = sublist.get(j);
                if (rule.alreadyCoveredBy(testRule)) {
                    throw new IllegalArgumentException("\"" + rule.getHumanDescription() + "\" will never execute, already covered by \"" + testRule.getHumanDescription() + "\" at position " + j);
                }
            }
        }
    }

}
