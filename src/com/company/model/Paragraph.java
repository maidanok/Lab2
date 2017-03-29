package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 27.03.2017.
 */
public class Paragraph {
    private List<Sentence> sentences = new ArrayList<>();
    private Pattern sentencePattern = Pattern.compile("[А-ЯA-Z](([.@])([а-яa-z])|([a-zа-я]*.[a-zа-я]*(\\ ))" +
            "|[^?!.\\(]|«»\\([^\\)]*\\))*([.?!][.?!]*)",Pattern.UNICODE_CHARACTER_CLASS);


    //конструктор получает текст в виде стринг билдера и разбивает его на предложения
    //после чего каждое предложение отправляет в элемент массива
    public Paragraph(StringBuilder stringBuilder) {
        boolean firstSent = true;

        Matcher sentenceMatcher = sentencePattern.matcher(stringBuilder);
        while (sentenceMatcher.find()){
            sentences.add(new Sentence(sentenceMatcher.group(),firstSent));
            if (firstSent){
                firstSent=false;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\t");
        for (Sentence sentence: sentences){
            stringBuilder.append(sentence);
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}