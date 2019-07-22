/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.berry.fizzbuzz;

/**
 *
 * @author Michael
 */
public interface Rule {
    
    boolean matches(int n);
    
    String substitute(int n);
    
    String getHumanDescription();
    
    default boolean alreadyCoveredBy(Rule anotherRule) { return this.equals(anotherRule); }
    
}
