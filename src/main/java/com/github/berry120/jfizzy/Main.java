/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.berry120.jfizzy;

/**
 *
 * @author Michael
 */
public class Main {

    public static void main(String[] args) {
    FizzBuzz.of(
            DivisibleRule.ofAll("FizzBuzz", 3, 5),
            DivisibleRule.ofAll("Fizz", 3),
            DivisibleRule.ofAll("Buzz", 5)
    ).forEachInRange(1, 100, System.out::println);
        
    }

}
