/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.fizzbuzz;

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

    default String substitute(int n) {
        if (matches(n)) {
            return getSubstituteFunction().apply(n);
        } else {
            return Integer.toString(n);
        }
    }

    default boolean matches(int n) {
        return getMatchPredicate().test(n);
    }

    default boolean alreadyCoveredBy(Rule anotherRule) {
        return this.equals(anotherRule);
    }

}
