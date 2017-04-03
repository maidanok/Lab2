package com.company.logics;

import com.company.model.Punctuation;
import com.company.model.SentenceElement;
import com.company.model.Word;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pasha on 03.04.2017.
 */
public class SortingSentenceImpl implements SortingSentence{
    private Pattern punctuation = Pattern.compile("([\"!()«»,.\\]\\[}{;:])", Pattern.UNICODE_CHARACTER_CLASS);
    /*  данные паттерны необходимы для поиска знаков препинания
    по принципу выставления пробелов до и после знака.
    Перед открывающими скобками пробел надо, а после них не надо.
    Перед всеми остальными пробел не ставится.
    Например: точка или запятая.
*/
    private Pattern punctNotNeedSpaseAfter = Pattern.compile("[»,;\\]:}).!?>]");
    private Pattern openingBracket = Pattern.compile("[\"\\{\\(\\[\\<«]");
    private boolean aheadOpeningBracked = false;

    /*с помощю данного метдода собираем массив элементов предложения
 и выясняем как они должны расставлять пробелы перед собой
 по умолчанию все перед собой пробел ставят
 */
    @Override
    public SentenceElement whoIsWho(String applicant, boolean isStartSent) {
        boolean isfirstWord = isStartSent;
        Punctuation punct;
        Word word;
        Matcher findElementMather = punctuation.matcher(applicant);
        boolean firsWorldinSent=true;
        //нашли слово
        if ((applicant.length() > 2) || (!findElementMather.find())) {
            word = new Word(applicant);
            if ((aheadOpeningBracked)) {
                word.setNeedSpaseAfrer(false);//если впереди открывающая скобка то перед ней пробел не ставим
            }
            if (isfirstWord){
                word.setNeedSpaseAfrer(true);
                isfirstWord = false;
            }
            aheadOpeningBracked = false;

            return word;

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
            return punct;
        }
    }

}
