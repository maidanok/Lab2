package com.company.model;

import com.company.logics.SortingSentence;
import com.company.logics.SortingSentenceImpl;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 27.03.2017.
 */
public class Sentence {
    private List<SentenceElement> sentence = new ArrayList<>();
    SortingSentence sortingSentence = new SortingSentenceImpl();
    //в начале первого предложения абзаца пробел не ставится
    private boolean isStartSent = true;
    //переменная которая будет сообщать элементу предложения что перед ним стоит открывающая скобка
    private boolean aheadOpeningBracked = false;
    private boolean isfirstWord = isStartSent;

    private Pattern wordPattern = Pattern.compile("(([a-zа-я]*[.][a-zа-я]*[.][a-zа-я]*[.]*))|" +
            "(8[(](\\d+))[)](\\d+-\\d+-\\d+)|" +
            "((\\w+[-_.]*)@(\\w+\\.)(\\w+)(\\.\\w+)*)|" +
            "((\\w+)([-]?)(\\w*))|(\\d+)|([№]\\w*)|([\"«»!(),.\\]\\[}{;:][!?.]?)", Pattern.UNICODE_CHARACTER_CLASS);


    public Sentence(String string, boolean youFistSentence) {
        Matcher wordMatcher = wordPattern.matcher(string);
        while (wordMatcher.find())
        {

            sentence.add(sortingSentence.whoIsWho(wordMatcher.group(),isStartSent));
            isStartSent = youFistSentence;
        }
//в конце конструктора меняем слова
        changeOfPlaces();
    }



    /*
        В данном методе будем менять первое и последнее слово местами.
        При этом будем учитывать, что в конце предложения может стоять несколько знаков препинания!!!
        так же нам надо будет поменять регистр
        потому что первое слово в предложении пишется с заглавной буквы а последнее нет
        (на имена собственные проверка не делается)
     */
    private void changeOfPlaces() {
        String firstWordStr = sentence.get(0).getValue();
        boolean needSpaseFirst = sentence.get(0).isNeedSpaseAfter();
        int j = sentence.size() - 1;

        while (!sentence.get(j).iAmWord()) {
            j--;
        }

        String lastWordStr = sentence.get(j).getValue();
        boolean needSpaseLast = sentence.get(j).isNeedSpaseAfter();

        //меняем первые буквы в слове
        StringBuilder stringBuilderLast = new StringBuilder();
        stringBuilderLast.append(lastWordStr.substring(0, 1).toUpperCase());
        stringBuilderLast.append(lastWordStr.substring(1));

        StringBuilder stringBuilderFirst = new StringBuilder();
        stringBuilderFirst.append(firstWordStr.substring(0, 1).toLowerCase());
        stringBuilderFirst.append(firstWordStr.substring(1));


        //записываем получившиеся слова и информацию о пробелах в предложение
        Word first = new Word(stringBuilderLast.toString(), needSpaseFirst);
        sentence.set(0, first);

        Word last = new Word(stringBuilderFirst.toString(), needSpaseLast);
        sentence.set(j, last);

        //добавим точку в конце если она исчезла вместе с сокращением
        if (sentence.get(sentence.size() - 1).iAmWord()) {
            Punctuation punct = new Punctuation(".", false);
            sentence.add(punct);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < sentence.size(); i++) {
            str.append(sentence.get(i).toString());
        }
        return str.toString();
    }
}
