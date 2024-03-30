package org.sopt;

import org.sopt.classes.Part;
import org.sopt.classes.Person;
import org.sopt.classes.PersonBuilder;

public class Main {
    public static void main(String[] args) {
        Person person = Person.create("konu", 26, "male");
        System.out.println("person.getName() = " + person.getName());
        person.setName("abcde");
        System.out.println("person.getName() = " + person.getName());

        Person person2 = new PersonBuilder()
                .name("konu")
                .age(26)
                .sex("male")
                .build();

        System.out.println(Part.IOS.name());
    }
}