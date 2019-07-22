/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.fizzbuzz;

import java.util.function.Function;

/**
 *
 * @author Michael
 */
public interface Rule {
    
    boolean matches(int n);
    
    Function<Integer, String> getSubstituteFunction();

    String getHumanDescription();
    
    default String substitute(int n) {
        if (matches(n)) {
            return getSubstituteFunction().apply(n);
        } else {
            return Integer.toString(n);
        }
    }
    
    default boolean alreadyCoveredBy(Rule anotherRule) { return this.equals(anotherRule); }
    
}
