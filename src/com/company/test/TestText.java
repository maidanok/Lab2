package com.company.test;

import com.company.model.Paragraph;

/**
 * Created by Pasha on 28.03.2017.
 */
public class TestText {
    public static void main(String [] args){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Начальное в. состояние объекта 8(011)647-34-34 типа Matcher не SwrW@sda.rsd  определено и.т.п.");
        stringBuilder.append("Попытка (xnj nj d crj,rf)вызвать ing_asup@luchesa.by какой-либо метод класса для извлечения информации о " +
                "найденном т.п. соответствии КАПС приведет к и.т.д. возникновению ошибки и.т.д.");
        stringBuilder.append("Для .того [квадратные скобки] чтобы начать работу с объектом Matcher, нужно вызвать один из его методов!?");

        Paragraph paragraph = new Paragraph(stringBuilder);
        System.out.println(paragraph);
    }
}
