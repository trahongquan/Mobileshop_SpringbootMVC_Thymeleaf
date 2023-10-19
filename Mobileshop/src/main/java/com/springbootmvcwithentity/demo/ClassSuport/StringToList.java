package com.springbootmvcwithentity.demo.ClassSuport;

import java.util.ArrayList;
import java.util.List;

public class StringToList {
    /*public static void main(String[] args) {
        String inputString = "[23, 25, 26]";
        List<Integer> numberList = stringToList(inputString);

        // In danh sách số
        for (Integer number : numberList) {
            System.out.println(number);
        }
    }*/

    public /*static*/ List<String> StringToList(String input) {
        List<String> result = new ArrayList<>();

        // Xóa ký tự "[" và "]" từ chuỗi
        String cleanedInput = input.replace("[", "").replace("]", "");

        // Tách các số bằng dấu phẩy
        String[] seris = cleanedInput.split(", ");

        // Chuyển các số từ kiểu String sang kiểu Integer và thêm vào danh sách
        for (String seri : seris) {
            result.add(seri);
        }

        return result;
    }

    public /*static*/ List<Integer> StringToListINT(String input) {
        List<Integer> result = new ArrayList<>();

        // Xóa ký tự "[" và "]" từ chuỗi
        String cleanedInput = input.replace("[", "").replace("]", "");

        // Tách các số bằng dấu phẩy
        String[] numbers = cleanedInput.split(", ");

        // Chuyển các số từ kiểu String sang kiểu Integer và thêm vào danh sách
        for (String number : numbers) {
            result.add(Integer.parseInt(number));
        }

        return result;
    }
}

