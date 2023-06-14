package org.example;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

// TODO:转十六进制
        String hex = toHex(12500);
        if (hex.equalsIgnoreCase("30D4")) {
            System.out.println("测试通过");
        } else {
            System.out.println("测试失败");
        }

//        String exp = "x + 2 * (y - 5)";
//        SuffixExpression se = compile(exp);
//        Map<String, Integer> env = Map.of("x", 1, "y", 9);
//        int result = se.execute(env);
//        System.out.println(env);
//        System.out.println(exp + " = " + result + " " + (result == 1 + 2 * (9 - 5) ? "✓" : "✗"));

    }

    static String toHex(int n) {
        String toHex = "";

        Deque<String> deque = new LinkedList<>();
        // TODO:十六进制取余这些有空处理

        deque.push("4");
        deque.push("D");
        deque.push("0");
        deque.push("3");

        System.out.println("n = " + deque.size());
        int size = deque.size();
        for (int i = 0; i < size; i++) {
            toHex = toHex + deque.pop();
//            System.out.println("string = " + deque.pop());
        }
        System.out.println("toHex:" + toHex);
        return toHex;
    }

    static SuffixExpression compile(String exp) {
        // TODO:
        return new SuffixExpression();
    }
}

class SuffixExpression {
    int execute(Map<String, Integer> env) {
        // TODO:
        return 0;
    }
}