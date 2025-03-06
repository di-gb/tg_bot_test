package org.example;

import java.util.ArrayList;
import java.util.List;

public class QuestionsList {
    private List<Question> questions;

    public QuestionsList() {
        this.questions = new ArrayList<>();

        // Original questions
        questions.add(new Question("В языке Java переменные типа boolean могут принимать значения",
                List.of("true, false", "правда, ложь", "ложь, истина", "success, failed"), 0));
        questions.add(new Question("В языке Java для отрицания(инверсии) логического выражения используется оператор:",
                List.of("!", "!=", "~", "not"), 0));
        questions.add(new Question("Строковые переменные a и b правильно сравниваются в выражении:",
                List.of("a == b", "a.equals(b)", "a.length() == b.length()", "a.toUpperCase() == b.toUpperCase"), 1));
        questions.add(new Question("Правильно скомпилируется вариант",
                List.of("int x = 3.14;", "int x = (int) 3.14:", "int x = (double) 3.14;", "int x = (float) 3.14;"), 1));
        questions.add(new Question("Какой из примитивных типов данных есть в языке Java?",
                List.of("bool", "shar", "char", "varchar"), 2));
        questions.add(new Question("В классе объявили переменную String s, но еще не присвоили ей значение. Она содержит?",
                List.of("\"\"", "0", "null", "nill"), 2));
        questions.add(new Question("Новый объект в Java создается с помощью ключевого слова",
                List.of("create", "alloc", "new", "return"), 2));
        questions.add(new Question("Какой из этих принципов есть в ООП?",
                List.of("Инкорпорация", "Перфекционизм", "Абстракционизм", "Наследование"), 3));
        questions.add(new Question("В каком из вариантов правильно описано наследование класса Собака и класса Волк?",
                List.of("class Волк extends Собака", "class Собака extends Волк", "class Волк implements Собака", "class Собака implements Волк"), 1));
        questions.add(new Question("Какой из вариантов содержит примитивные типы языка Java?",
                List.of("int, real, string", "int, integer, string", "int, double, real, string", "int, double, float"), 3));
        // Additional 20 questions
        questions.add(new Question("Какой метод вызывается при запуске программы Java?",
                List.of("start()", "init()", "main()", "run()"), 2));
        questions.add(new Question("Какой модификатор доступа делает класс доступным только внутри пакета?",
                List.of("private", "protected", "default", "public"), 2));
        questions.add(new Question("Какой оператор используется для создания массива?",
                List.of("new", "create", "array", "declare"), 0));
        questions.add(new Question("Какой тип данных используется для хранения целых чисел?",
                List.of("float", "double", "int", "char"), 2));
        questions.add(new Question("Какой метод используется для преобразования строки в число?",
                List.of("parseInt()", "toString()", "valueOf()", "convert()"), 0));
        questions.add(new Question("Какой метод используется для сравнения строк без учета регистра?",
                List.of("equalsIgnoreCase()", "equals()", "compareTo()", "contains()"), 0));
        questions.add(new Question("Какой метод используется для получения длины строки?",
                List.of("size()", "length()", "count()", "getLength()"), 1));
        questions.add(new Question("Какой метод используется для добавления элемента в список?",
                List.of("add()", "insert()", "push()", "append()"), 0));
        questions.add(new Question("Какой интерфейс реализует коллекции для хранения уникальных элементов?",
                List.of("List", "Set", "Map", "Queue"), 1));
        questions.add(new Question("Какой метод используется для удаления всех элементов из коллекции?",
                List.of("removeAll()", "clear()", "delete()", "erase()"), 1));
        questions.add(new Question("Какой метод используется для проверки наличия элемента в коллекции?",
                List.of("contains()", "exists()", "find()", "search()"), 0));
        questions.add(new Question("Какой метод используется для получения первого элемента из очереди?",
                List.of("poll()", "peek()", "pop()", "first()"), 0));
        questions.add(new Question("Какой метод используется для получения последнего элемента из стека?",
                List.of("pop()", "peek()", "last()", "top()"), 0));
        questions.add(new Question("Какой метод используется для сортировки коллекции?",
                List.of("sort()", "order()", "arrange()", "sequence()"), 0));
        questions.add(new Question("Какой метод используется для преобразования коллекции в массив?",
                List.of("toArray()", "toObject()", "toList()", "toCollection()"), 0));
        questions.add(new Question("Какой метод используется для объединения двух коллекций?",
                List.of("addAll()", "merge()", "concat()", "join()"), 0));
        questions.add(new Question("Какой метод используется для разделения строки по разделителю?",
                List.of("split()", "divide()", "separate()", "partition()"), 0));
        questions.add(new Question("Какой метод используется для замены подстроки в строке?",
                List.of("replace()", "swap()", "exchange()", "modify()"), 0));
        questions.add(new Question("Какой метод используется для обрезки пробелов в строке?",
                List.of("trim()", "strip()", "cut()", "removeSpaces()"), 0));
        questions.add(new Question("Какой метод используется для преобразования строки в верхний регистр?",
                List.of("toUpperCase()", "upper()", "capitalize()", "big()"), 0));
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
