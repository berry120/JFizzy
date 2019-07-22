/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.fizzbuzz;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * @author Michael
 */
@EqualsAndHashCode
public class DivisibleRule implements Rule {

    @Getter
    private final String substituteText;
    @Getter
    private final Set<Integer> nums;

    private DivisibleRule(String substituteText, Set<Integer> nums) {
        this.substituteText = substituteText;
        this.nums = Collections.unmodifiableSet(nums);
    }

    public static Rule ofAll(String substitute, Integer... nums) {
        checkDuplicateEntries(nums);
        return new DivisibleRule(substitute, new HashSet<>(Arrays.asList(nums)));
    }

    @Override
    public boolean matches(int n) {
        return nums.stream().allMatch(r -> n % r == 0);

    }

    @Override
    public boolean alreadyCoveredBy(Rule rule) {
        return Optional.of(rule)
                .filter(r -> r instanceof DivisibleRule)
                .map(r -> this.getNums().containsAll(((DivisibleRule)r).getNums()))
                .orElse(false);
    }

    @Override
    public String getHumanDescription() {
        return "Substitute \'" + substituteText + "\' if divisible by " + getHumanNumList();
    }

    private String getHumanNumList() {
        return String.join(" and ", nums.stream().map(i -> Integer.toString(i)).collect(Collectors.toList()));
    }

    private static void checkDuplicateEntries(Integer[] arr) {
        if (new HashSet<>(Arrays.asList(arr)).size() != arr.length) {
            throw new IllegalArgumentException("Cannot contain duplicate parameters");
        }
    }

}
