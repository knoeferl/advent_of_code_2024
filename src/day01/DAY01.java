package day01;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DAY01 {
    public static void main(String[] args) throws IOException {
        List<String> lines;
        boolean useExample = !true;
        boolean verbose = true;

        if (!useExample) {
            lines = Files.readAllLines(Path.of("src/day01/input.txt"));
        } else {
            String example = """
                                1abc2
                                pqr3stu8vwx
                                a1b2c3d4e5f
                                treb7uchet""";
            lines = Arrays.stream(example.split("\n")).toList();
        }

        System.out.println("first part");
        HashMap<String, String> numbers = new HashMap<>();
        numbers.put("one", "1");
        numbers.put("two", "2");
        numbers.put("three", "3");
        numbers.put("four", "4");
        numbers.put("five", "5");
        numbers.put("six" , "6");
        numbers.put("seven", "7");
        numbers.put("eight", "8");
        numbers.put("nine", "9");
        numbers.put("zero", "0");

        char[] numbs = String.join("", numbers.values()).toCharArray();
        List<Integer> results = new ArrayList<>();
        for (var line : lines) {
            System.out.println(line);
            String first = "";
            String last = "";
            for (int i = 0; i < line.length(); i++) {
                boolean found = false;
                for (char num : numbs) {
                    if (line.charAt(i) == num) {
                        first =  Character.toString(line.charAt(i));
                        found = true;
                        System.out.println("found first: " + first);
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            for (int i = line.length()-1; i >= 0; i--) {
                boolean found = false;
                for (char num : numbs) {
                    if (line.charAt(i) == num) {
                        last =  Character.toString(line.charAt(i));
                        System.out.println("found last: " + last);
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }

            }
            results.add(Integer.valueOf(  first + last));
        }

        System.out.println(results.stream().reduce(Integer::sum).get());

        System.out.println("part 2");


        if (!useExample) {
            System.out.println("");
        } else {
            String example = """
                    two1nine
                    eightwothree
                    abcone2threexyz
                    xtwone3four
                    4nineeightseven2
                    zoneight234
                    7pqrstsixteen""";
            lines = Arrays.stream(example.split("\n")).toList();
        }



        results = new ArrayList<>();
        for (var line : lines) {
            System.out.println(line);
            String first = "";
            String last = "";
            for (int i = 0; i < line.length(); i++) {
                boolean found = false;
                for (var num : numbers.values()) {
                    if (line.substring(i).startsWith(num)) {
                        first =  num;
                        found = true;

                        break;
                    }
                }
                for (var numStr : numbers.keySet()){
                    if(line.substring(i).startsWith(numStr)){
                        first = numbers.get(numStr);
                        found = true;
                        break;
                    }
                }
                if (found) {
                    System.out.println("found first: " + first);
                    break;
                }
            }
            for (int i = line.length(); i >= 0; i--) {
                boolean found = false;
                String substring = line.substring(0, i);
                System.out.println(substring);
                for (var num : numbers.values()) {
                    if (substring.endsWith(num)) {
                        last =  num;
                        found = true;

                        break;
                    }
                }
                for (var numStr : numbers.keySet()){
                    if(substring.endsWith(numStr)){
                        last = numbers.get(numStr);
                        found = true;
                        break;
                    }
                }
                if (found) {
                    System.out.println("found last: " + last);
                    break;
                }

            }
            results.add(Integer.valueOf(  first + last));
        }

        System.out.println(results.stream().reduce(Integer::sum).get());

    }
}