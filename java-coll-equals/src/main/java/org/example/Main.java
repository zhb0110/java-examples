package org.example;

import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<Person> list = List.of(new Person("Xiao", "Ming", 18), new Person("Xiao", "Hong", 25), new Person("Bob", "Smith", 20));
        boolean exist = list.contains(new Person("Bob", "Smith", 20));
        System.out.println(exist ? "测试成功!" : "测试失败!");
    }
}

class Person {
    String firstName;
    String lastName;
    int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * TODO: 覆写equals方法
     */
    public boolean equals(Object object) {
//        if (object instanceof Person person) { // 如果是jdk16+，则可以直接判断并强制转换，低版本不行，只能判断
        if (object instanceof Person) {
            Person person = (Person) object;
            return Objects.equals(this.firstName, person.firstName) && Objects.equals(this.lastName, person.lastName) && this.age == person.age;
        }

        return false;

    }


}