package org.sopt.classes;

public class Person {

    public Person() {}
    private Person(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public static Person create(String name, int age, String sex) {
        return new Person(name, age, sex);
    }

    private String name;
    private int age;
    private String sex;

    public void walk() {
        System.out.println("사람이 걷습니다");
    }

    public static void run() {
        System.out.println("사람이 뜁니다.");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
