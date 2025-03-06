package org.example;

import java.util.ArrayList;
import java.util.List;

public class QuestionsList {
    private List<Question> questions;

    public QuestionsList() {
        this.questions = new ArrayList<>();

        questions.add(new Question("В языке Java переменные типа boolean могут принимать значения",
                List.of("true, false", "правда, ложь", "ложь, истина", "success, failed"),
                0));
        questions.add(new Question("В языке Java для отрицания(инверсии) логического выражения используется оператор:",
                List.of("!", "!=", "~", "not"),
                1));
        questions.add(new Question("Строковые переменные a и b правильно сравниваются в выражении:",
                List.of(" a == b", "a.equals(b)", "a.length() == b.length()", "a.toUpperCase() == b.toUpperCase"),
                1));
        questions.add(new Question("Правильно скомпилируется вариант",
                List.of("int x = 3.14;", "int x = (int) 3.14:", "int x = (double) 3.14;", "int x = (float) 3.14;"),
                2));
        questions.add(new Question("Какой из примитивных типов данных есть в языке Java?",
                List.of("bool", "shar", "char", "varchar"),
                2));
        questions.add(new Question("В классе объявили переменную String s, но еще не присвоили ей значение. Она содержит?",
                List.of("\"\"", "0", "null", "nill"),
                2));
        questions.add(new Question("Новый объект в Java создается с помощью ключевого слова",
                List.of("create", "alloc", "new", "return"),
                2));
        questions.add(new Question("Какой из этих принципов есть в ООП?",
                List.of("Инкорпорация", "Перфекционизм", "Абстракционизм", "Наследование"),
                3));
        questions.add(new Question("В каком из вариантов правильно описано наследование класса Собака и класса Волк?",
                List.of("class Волк extends Собака", "class Собака extends Волк", "class Волк implements Собака", "class Собака implements Волк"),
                1));
        questions.add(new Question("Какой из вариантов содержит примитивные типы языка Java?",
                List.of("int, real, string", "int, integer, string", "int, double, real, string", "int, double, float"),
                3));
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
