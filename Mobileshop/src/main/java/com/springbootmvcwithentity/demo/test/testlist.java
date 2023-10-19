package com.springbootmvcwithentity.demo.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class testlist {
    public static void main(String[] args) {
        List<String> listString = new LinkedList<>();
        listString.add("1");
        listString.add("2");
        listString.add("3");
        listString.add("4");
        System.out.println(listString.toString());
        System.out.println(listString.contains("1"));
    }
}
