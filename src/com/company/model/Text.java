package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** один из вариантов([+="\w ,.:;!?-«»]*-*[+="\w ,.:;!?-«»]*)
 * Created by Pasha on 29.03.2017.
 */
public class Text {
    private List<Paragraph> paragraphs = new ArrayList<>();
    private Pattern paragraphsPattern = Pattern.compile("(.*)(\\n)",Pattern.UNICODE_CHARACTER_CLASS);

    public Text (StringBuilder stringBuilder){
        Matcher paragraphsMatcher = paragraphsPattern.matcher(stringBuilder);
        while (paragraphsMatcher.find()){
            StringBuilder str =new StringBuilder(paragraphsMatcher.group());
        paragraphs.add(new Paragraph(str));

        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Paragraph paragraph:paragraphs){
            stringBuilder.append(paragraph);
        }
        return stringBuilder.toString();
    }
}
