[![CircleCI](https://circleci.com/gh/berry120/JFizzy.svg?style=svg)](https://circleci.com/gh/berry120/JFizzy)

# JFizzy

JFizzy is a modern Java library for all things FizzBuzz related, allowing you to write *nice* FizzBuzz code, using a framework, like it's been written in 2019:

    FizzBuzz.of(
            DivisibleRule.ofAll("FizzBuzz", 3, 5),
            DivisibleRule.ofAll("Fizz", 3),
            DivisibleRule.ofAll("Buzz", 5)
    ).forEachInRange(1, 100, System.out::println);
    
Rules are evaluated in the order they're specified.
    
## Features

- Uses static factory methods that start with `of`, because no-one likes using public constructors in 2019;
- Uses lombok internally, because no-one should have to write any boilerplate these days;
- Allows you to pass a Java 8 `Consumer` to consume the list of results, because iterating over it is very old-hat;
- Allows other rules to be specified (such as a `PrimeRule`), to try to anticipate any additional ~interview~ requirements that may spontaneously appear after the initial implementation;
- Rules can take a `Function<Integer, String>` as a parameter instead of a raw string, enabling you to use the number in your output should ~the interviewer~ you require it;
- Offers some degree of checking if you've got your rules in the wrong order. This can't be *guaranteed* in all cases of course, but it should catch most trivial cases.
- Offers a way to retrieve the result as a `List<String>` rather than passing a `Consumer`, so you can look at that method declaration and feel all smug that you're not using it. (You're using Java 8 after all, who would use that?!)

## How do I use it?

Maven:

    <dependency>
      <groupId>com.github.berry120.jfizzy</groupId>
      <artifactId>JFizzy</artifactId>
      <version>0.1</version>
      <type>pom</type>
    </dependency>
    
Gradle:

    compile 'com.github.berry120.jfizzy:JFizzy:0.1'
    
Ivy *(Are you serious? It's 2019! Who uses Ivy?!)*:

    <dependency org="com.github.berry120.jfizzy" name="JFizzy" rev="0.1">
	    <artifact name="JFizzy" ext="pom"></artifact>
    </dependency>
    
## I don't use a dependency management system! Where do I get the jar?

Never mind 2019, what year is it... 2009?! You want a standalone jar, clone it and build it yourself.

## Requirements

JFizzy requires Java 8+. As everyone knows, all non-legacy code now uses Java 8, and no-one has yet migrated beyond it.
    
## Is this a joke?

No, this is deadly serious. It's so serious, that we may even unit test it at some point and pretend we've done TDD.
