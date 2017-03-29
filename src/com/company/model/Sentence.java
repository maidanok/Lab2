package com.company.model;

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

    private Pattern wordPattern = Pattern.compile("(([a-zа-я]*[.][a-zа-я]*[.][a-zа-я]*[.]*))|" +
            "(8[(](\\d+))[)](\\d+-\\d+-\\d+)|" +
            "((\\w+[-_.]*)@(\\w+\\.)(\\w+)(\\.\\w+)*)|" +
            "((\\w+)([-]?)(\\w*))|(\\d+)|([№]\\w*)|([!(),.\\]\\[}{;:][!?.]*)", Pattern.UNICODE_CHARACTER_CLASS);

    private Pattern punctuation = Pattern.compile("([!(),.\\]\\[}{;:])", Pattern.UNICODE_CHARACTER_CLASS);

    /*  данные паттерны необходимы для поиска знаков препинания
        по принципу выставления пробелов до и после знака.
        Перед открывающими скобками пробел надо, а после них не надо.
        Перед всеми остальными пробел не ставится.
        Например: точка или запятая.
    */
    private Pattern punctNotNeedSpaseAfter = Pattern.compile("[,;\\]:}).!?>]");
    private Pattern openingBracket = Pattern.compile("[\\{\\(\\[\\<]");

    //переменная которая будет сообщать элементу предложения что перед ним стоит открывающая скобка
    private boolean aheadOpeningBracked = false;


    private boolean isStartSent = true;


    public Sentence(String string) {
        Matcher wordMatcher = wordPattern.matcher(string);
        while (wordMatcher.find()) {
            this.whoIsWho(wordMatcher.group());
        }
        changeOfPlaces();
    }

    /*с помощю данного метдода собираем массив элементов предложения
     и выясняем как они должны расставлять пробелы перед собой
     по умолчанию все перед собой пробел ставят
     */

    private void whoIsWho(String applicant) {
        Punctuation punct;
        Word word;
        Matcher findElementMather = punctuation.matcher(applicant);
        //нашли слово
        if ((applicant.length() > 2) || (!findElementMather.find())) {
            word = new Word(applicant);
            if (aheadOpeningBracked) {
                word.setNeedSpaseAfrer(false);//если впереди открывающая скобка то перед ней пробел не ставим
            }
            aheadOpeningBracked = false;
            sentence.add(word);

            // нашли пунктуацию
        } else {
            punct = new Punctuation(applicant);
            //выясняем к какому типу пуктуации элемент относится
            Matcher needSpase = punctNotNeedSpaseAfter.matcher(applicant);
            if (needSpase.find()) {
                punct.setNeedSpaseAfrer(false);
            }
            Matcher findOpeningBracket = openingBracket.matcher(applicant);
            if (findOpeningBracket.find()) {
                aheadOpeningBracked = true;
            } else {
                aheadOpeningBracked = false;
            }
            sentence.add(punct);
        }
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
        Word first = new Word(stringBuilderLast.toString());
        first.setNeedSpaseAfrer(needSpaseFirst);
        sentence.set(0, first);

        Word last = new Word(stringBuilderFirst.toString());
        last.setNeedSpaseAfrer(needSpaseLast);
        sentence.set(j, last);
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
