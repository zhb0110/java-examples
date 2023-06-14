package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

//        // TODO:一些List、Array，和迭代器的测试
//        // TODO:Array 长度不变
//        String[] abc = {"a", "b", "c"};
//        for (String test : abc) { // TODO:使用迭代器
//            System.out.println("数组Array遍历 " + test);
//        }
//        List<String> aaa = List.of(abc); // TODO:Array转List
//        String[] cba = aaa.toArray(new String[3]); // TODO:List转Array
//        for (String test : aaa) {
//            System.out.println("List aaa遍历 " + test);
//        }
//        System.out.println("List aaa长度 " + aaa.size());
////        ArrayList<String> bbb = (ArrayList<String>) aaa; // TODO:只读List，强转报错
//        ArrayList<String> bbb = new ArrayList<>(aaa); // TODO:类似使用addAll()接口，所以不影响aaa数组
//        System.out.println("List bbb长度1： " + bbb.size());
//        bbb.remove(1); // TODO:删了后会减少list的size，且下标也变了
//        for (String test : bbb) {
//            System.out.println("List bbb遍历 " + test);
//        }
//        System.out.println("List aaa长度 " + aaa.size()); // TODO:3，该处是3！
//        System.out.println("List bbb遍历 " + bbb.get(1)); // c
//        System.out.println("List bbb长度2： " + bbb.size()); // 2

//        -----------------------------------------------------------------------------

        // 构造从start到end的序列：
        final int start = 10;
        final int end = 20;
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }
        // 洗牌算法suffle可以随机交换List中的元素位置:
        Collections.shuffle(list);
        // 随机删除List中的一个元素:
        int removed = list.remove((int) (Math.random() * list.size()));


        int found = findMissingNumber(start, end, list);
        System.out.println(list.toString());
        System.out.println("missing number: " + found);
        System.out.println(removed == found ? "测试成功" : "测试失败");
    }

    /**
     * TODO: 找出从start到end的缺失的数字并返回
     */
    static int findMissingNumber(int start, int end, List<Integer> list) {
        // 思路1：
        // 先排序
        // start和end循环，内部再循环，查得缺了哪个数据
        Collections.sort(list); // TODO:sort，初级排序，List自己实现了compare，从小到大，对这一题没什么用
//        System.out.println(list.toString());

        // TODO:思路2：直接循环，然后list看是否包含该数据，使用：contains或indexOf
        int value = 0;
        for (int i = start; i < end; i++) {
            if (!list.contains(i)) {
                value = i;
            }
        }

        return value;
    }
}