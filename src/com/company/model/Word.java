package com.company.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 27.03.2017.
 */
public class Word extends SentenceElement {

    List<Character> word = new ArrayList<>();
    boolean needSpaseAfrer = true;

    public Word(String str) {
        for (int i = 0; i < str.length(); i++) {
            word.add(str.charAt(i));
        }
    }


    @Override
    public boolean iAmWord() {
        return true;
    }

    @Override
    public void setNeedSpaseAfrer(boolean needSpaseAfrer) {
        this.needSpaseAfrer = needSpaseAfrer;
    }

    @Override
    public boolean isNeedSpaseAfter() {
        return needSpaseAfrer;
    }

    @Override
    String getValue() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word.size(); i++) {
            stringBuilder.append(word.get(i));
        }
        return stringBuilder.toString();
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (isNeedSpaseAfter()) {
            str.append(" ") ;
        }

        for (int i = 0; i < word.size(); i++) {
            str.append(word.get(i));
        }

        return str.toString();
    }

}
