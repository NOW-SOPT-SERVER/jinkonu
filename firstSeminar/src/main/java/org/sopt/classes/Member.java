package org.sopt.classes;

public class Member extends Person {

    private Part part;

    public static Member createMember(String name, int age, String sex, Part part) {
        Member member = (Member) Person.create(name, age, sex);
        member.setPart(part);

        return member;
    }

    private void setPart(Part part) {
        this.part = part;
    }
}
