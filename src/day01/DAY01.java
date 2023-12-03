package day01;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DAY01 {
    public static void main(String[] args) throws IOException {
        List<String> lines;
        boolean useExample = false;
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
        char[] numbs = "0123456789".toCharArray();
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



    }
}