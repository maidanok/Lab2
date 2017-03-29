package com.company.test;

import com.company.model.Sentence;

/**
 * Created by Pasha on 28.03.2017.
 */
public class TestSentence {
    public static void main(String[] args){
        String stringBuilder=new String("Начальное dsfd 4545 №45 и.т. [fdg] состояние дефис-дефис (как быть со скобками) объекта 8(029)647-3454-34, типа Matcher не S@sda.rsd определено!!");
        Sentence sentence = new Sentence(stringBuilder,true);

        System.out.println(stringBuilder);
        System.out.println("ПРОВЕРКА");
        System.out.println(sentence);
    }
}
