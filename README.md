[![CircleCI](https://circleci.com/gh/berry120/JFizzy.svg?style=svg)](https://circleci.com/gh/berry120/JFizzy) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/cf82dbf02df9437d95c26377ff937127)](https://www.codacy.com/app/berry120/JFizzy?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=berry120/JFizzy&amp;utm_campaign=Badge_Grade)

# JFizzy

Everyone knows that the *correct* answer to any Java problem these days is "find a library that does it". Tasked with writing a FizzBuzz style application ~in an interview~? JFizzy is a modern Java 8 library for all things FizzBuzz related, allowing you to write good-looking, modern FizzBuzz code, that feels & looks like it's been written in 2019.

JFizzy can cope with the "standard" FizzBuzz style questions, as well as weird & wonderful variants.

 >Write a standard FizzBuzz:

    FizzBuzz.of(
            DivisibleRule.ofAll("FizzBuzz", 3, 5),
            DivisibleRule.ofAll("Fizz", 3),
            DivisibleRule.ofAll("Buzz", 5)
    ).forEachInRange(1, 100, System.out::println);
    
 >If I write a standard FizzBuzz but between 200 and 300 inclusive, and with the exception that *any* prime number should output "Pop" instead, what's the total length of the string from concatenating all the non-numeric outputs?
  
    System.out.println(
            FizzBuzz.of(
                    PrimeRule.of("Pop"),
                    DivisibleRule.ofAll("FizzBuzz", 3, 5),
                    DivisibleRule.ofAll("Fizz", 3),
                    DivisibleRule.ofAll("Buzz", 5)
            ).getResultInRange(200, 301).stream()
                    .filter(s -> !s.matches("\\d+"))
                    .mapToInt(s -> s.length())
                    .sum()
    );
    
Rules are evaluated in the order they're specified.
    
## Features

- Uses static factory methods that start with `of`, because no-one likes using public constructors in 2019;
- Uses lombok internally, because no-one should have to write any boilerplate these days;
- Allows you to pass a Java 8 `Consumer` to consume the list of results, because everyone knows the best Java programmers always use Consumers;
- Allows other rules to be specified (such as a `PrimeRule`), to try to anticipate any additional ~interview~ requirements that may spontaneously appear after the initial implementation;
- Rules can take a `Function<Integer, String>` as a parameter instead of a raw string, enabling you to use the number in your output should ~the interviewer~ you require it;
- Offers some degree of checking if you've got your rules in the wrong order. This can't be *guaranteed* in all cases of course, but it should catch most trivial cases.

## Importing

Maven:

    <dependency>
      <groupId>com.github.berry120.jfizzy</groupId>
      <artifactId>JFizzy</artifactId>
      <version>0.1</version>
    </dependency>
    
Gradle:

    compile 'com.github.berry120.jfizzy:JFizzy:0.1'
    
Ivy *(Are you serious? It's 2019! Who uses Ivy?!)*:

    <dependency org="com.github.berry120.jfizzy" name="JFizzy" rev="0.1">
	    <artifact name="JFizzy"></artifact>
    </dependency>
    
## Help, I don't use a dependency management system

Never mind 2019, are we in... 2009?! Clone the repo and build it yourself :-)

## Requirements

JFizzy requires Java 8+. As everyone knows, all non-legacy code now uses Java 8, and no-one has yet migrated to Java 9+.
    
## This seems like a joke

No, this is deadly serious. It's so serious, that we may even unit test it at some point and pretend we've done TDD.
