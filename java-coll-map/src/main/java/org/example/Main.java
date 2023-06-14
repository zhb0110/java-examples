package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<Student> list = List.of(new Student("Bob", 78), new Student("Alice", 85), new Student("Brush", 66),
                new Student("Newton", 99));
        var holder = new Students(list);
        System.out.println(holder.getScore("Bob") == 78 ? "测试成功!" : "测试失败!");
        System.out.println(holder.getScore("Alice") == 85 ? "测试成功!" : "测试失败!");
        System.out.println(holder.getScore("Tom") == -1 ? "测试成功!" : "测试失败!");
    }
}

class Students {
    List<Student> list;
    Map<String, Integer> cache;

    Students(List<Student> list) {
        this.list = list;
        cache = new HashMap<>();
    }

    int getScore(String name) {
        // 先在Map中查找:
        Integer score = this.cache.get(name);
        if (score == null) {
            // TODO:
            // TODO:方法1，每次都查  方法2：第一次循环都查了，放在缓存中，但是首次开销大
            boolean mark = false;
            for (Student student : list) {
                if (Objects.equals(student.name, name)) {
                    this.cache.put(name, student.score);
                    mark = true;
                    break;
                }
            }
            if (mark) {
                return this.cache.get(name);
            }
        }
        return score == null ? -1 : score.intValue();
    }

    // 尴尬，这个方法没看到
//    Integer findInList(String name) {
//        for (var ss : this.list) {
//            if (ss.name.equals(name)) {
//                return ss.score;
//            }
//        }
//        return null;
//    }
}

class Student {
    String name;
    int score;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
}