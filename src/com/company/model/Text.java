package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 27.03.2017.
 */
public class Text {
    List<Sentence> text = new ArrayList<>();

    //конструктор получает текст в виде стринг билдера и разбивает его на предложения
    //после чего каждое предложение отправляет в элемент массива
    public Text(StringBuilder stringBuilder) {


        Pattern sentencePattern = Pattern.compile("[А-ЯA-Z](([.@])([а-яa-z])|([a-zа-я]*.[a-zа-я]* )" +
                "|[^?!.\\(]|\\([^\\)]*\\))*([.?!][.?!]*)",Pattern.UNICODE_CHARACTER_CLASS);
        Matcher sentenceMatcher = sentencePattern.matcher(stringBuilder);
        while (sentenceMatcher.find()){
            text.add(new Sentence(sentenceMatcher.group()));
            System.out.println(sentenceMatcher.group());
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Sentence sentence:text){
            stringBuilder.append(sentence);
        }
        return stringBuilder.toString();
    }
}