/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.berry120.jfizzy;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author Michael
 */
public interface Rule {

    Function<Integer, String> getSubstituteFunction();

    Predicate<Integer> getMatchPredicate();

    String getHumanDescription();

    default Optional<String> substitute(int n) {
        if (matches(n)) {
            return Optional.of(getSubstituteFunction().apply(n));
        } else {
            return Optional.empty();
        }
    }

    default boolean matches(int n) {
        return getMatchPredicate().test(n);
    }

    default boolean alreadyCoveredBy(Rule anotherRule) {
        return this.equals(anotherRule);
    }

}
