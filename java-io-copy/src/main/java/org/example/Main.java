package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage:\n  java CopyFile.java <source> <target>");
            System.exit(1);
        }
        copy(args[0], args[1]);
    }

    static void copy(String source, String target) throws IOException {
        // 友情提示：测试时请使用无关紧要的文件
        // TODO:
    }
}