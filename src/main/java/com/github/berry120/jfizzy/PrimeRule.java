/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.berry120.jfizzy;

import java.math.BigInteger;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * @author Michael
 */
@EqualsAndHashCode
public class PrimeRule implements Rule {
    
    @Getter
    private final Predicate<Integer> matchPredicate;
    @Getter
    private final Function<Integer, String> substituteFunction;

    private PrimeRule(Function<Integer, String> substituteFunction) {
        this.substituteFunction = substituteFunction;
        this.matchPredicate = n -> new BigInteger(Integer.toString(n)).isProbablePrime(100);
    }

    public static Rule of(String substitute) {
        return PrimeRule.of(n -> substitute);
    }

    public static Rule of(Function<Integer, String> substituteFunction) {
        return new PrimeRule(substituteFunction);
    }
    
    @Override
    public boolean matches(int n) {
        return new BigInteger(Integer.toString(n)).isProbablePrime(100);
    }

    @Override
    public String getHumanDescription() {
        return "Substitute if prime";
    }
    
    @Override
    public boolean alreadyCoveredBy(Rule rule) {
        return Optional.of(rule)
                .filter(r -> r instanceof PrimeRule)
                .isPresent();
    }

}
