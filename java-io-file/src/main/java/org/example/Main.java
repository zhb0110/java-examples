package org.example;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File currentDir = new File(".");
        listDir(currentDir.getCanonicalFile());
    }

    static void listDir(File dir) {
        // TODO: 递归打印所有文件和子文件夹的内容
        File[] fs = dir.listFiles();
        if (fs != null) {
            for (File f : fs) {
                System.out.println(f.getName());
            }
        }
    }
}