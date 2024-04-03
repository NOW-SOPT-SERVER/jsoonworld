package main.java.org.sopt;

import main.java.org.sopt.person.Person;
import main.java.org.sopt.person.PersonBuilder;

public class Main {
    public static void main(String[] args) {
        //Person 클래스의 객체 생성
        Person person = new Person();
        //Person 클래스 내부 메소드 호출
        person.walk();
        System.out.println();

        Person person1 = new Person("권장순", 26, "male");
        System.out.println(person1.getName());

        System.out.println(person1.getName()); //결과 : 권장순

        person1.setName("YB/권장순");

        System.out.println(person1.getName()); //결과 : YB/권장순

        Person personWithBuilder = new
                PersonBuilder()
                .name("권장순")
                .age(26)
                .sex("male")
                .build();
    }
}
