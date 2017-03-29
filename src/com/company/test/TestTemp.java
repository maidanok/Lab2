package com.company.test;

import java.util.stream.Stream;

/**
 * Created by Pasha on 29.03.2017.
 */
public class TestTemp {
    public static void main(String[] args){
        String text = "Just a few words.";
        Stream<String> upperWords = Stream.of(text.split("[\\P{L}]+")).map(w ->w.substring(0,1).toUpperCase()+w.substring(1,w.length()));
        upperWords.forEach(System.out::println);
    }
}
