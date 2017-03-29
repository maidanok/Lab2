package com.company.model;

import java.util.ArrayList;
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
    по принципу выставления пробелов до и после знака
    перед открывающими скобками пробел надо а после них не надо
    перед всеми остальными пробел не ставится
    например точка или запятая
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
    }

    /*с помощю данного метдода собираем массив элементов предложения
     и выясняем как они должны расставлять пробелы перед собой
     по умолчанию все перед собой пробел ставят
     */

    private void whoIsWho(String applicant) {
        String findElement = applicant;
        Punctuation punct;
        Word word;
        Matcher findElementMather = punctuation.matcher(findElement);
        //нашли слово
        if ((findElement.length()>2)||(!findElementMather.find())){
            word=new Word(findElement);
            if (aheadOpeningBracked){
                word.setNeedSpaseAfrer(false);//если впереди открывающая скобка то перед ней пробел не ставим
            }
            aheadOpeningBracked=false;
            sentence.add(word);

        // нашли пунктуацию
        }else {
            punct=new Punctuation(findElement);
            //выясняем к какому типу пуктуации элемент относится
            Matcher needSpase=punctNotNeedSpaseAfter.matcher(findElement);
            if (needSpase.find()){
                punct.setNeedSpaseAfrer(false);
            }
            Matcher findOpeningBracket = openingBracket.matcher(findElement);
            if (findOpeningBracket.find()){
                aheadOpeningBracked=true;
            }else {
                aheadOpeningBracked=false;
            }
            sentence.add(punct);
        }
    }

    @Override
    public String toString() {
        String str="";
        for (int i=0; i<sentence.size();i++){
            str+=sentence.get(i).toString();
        }
        return str;
    }
}
