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
    public void setNeedSpaseAfrer(boolean needSpaseAfrer) {
        this.needSpaseAfrer = needSpaseAfrer;
    }

    @Override
    public boolean isNeedSpaseAfter() {
        return needSpaseAfrer;
    }





    @Override
    public String toString() {
        String str = "";
        if (isNeedSpaseAfter()) {
            str += " ";
        }

        for (int i = 0; i < word.size(); i++) {
            str += word.get(i);
        }

        return str;
    }
}
