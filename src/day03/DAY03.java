package day03;


import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DAY03 {
    public static void main(String[] args) throws IOException {
        List<String> lines;
        boolean useExample = true;
        boolean verbose = !true;

        if (!useExample) {
            lines = Files.readAllLines(Path.of("src/day03/input.txt"));
        } else {
            String example = """
                                467..114..
                                ...*......
                                ..35..633.
                                ......#...
                                617*......
                                .....+.58.
                                ..592.....
                                ......755.
                                ...$.*....
                                .664.598..""";
            lines = Arrays.stream(example.split("\n")).toList();
        }
        System.out.println("first part");
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            var letters = line.split("");
            StringBuilder num = new StringBuilder();
            boolean isCorrect = false;
            for (int j = 0; j < letters.length; j++) {
                if(letters[j].matches("[0-9]")) {
                    num.append(letters[j]);
                    if(isLetter(i - 1, j - 1, letters, lines)){
                        isCorrect = true;
                    }
                    if(isLetter(i - 1, j, letters, lines)){
                        isCorrect = true;
                    }
                    if(isLetter(i - 1, j + 1, letters, lines)){
                        isCorrect = true;
                    }
                    if(isLetter(i, j - 1, letters, lines)){
                        isCorrect = true;
                    }

                    if(isLetter(i + 1, j - 1, letters, lines)){
                        isCorrect = true;
                    }
                    if(isLetter(i + 1, j, letters, lines)){
                        isCorrect = true;
                    }
                    if(isLetter(i + 1, j + 1, letters, lines)){
                        isCorrect = true;
                    }
                    if(isLetter(i, j + 1, letters, lines)){
                        isCorrect = true;
                    }
                    if (j + 1 >= letters.length || !letters[j + 1].matches("[0-9]")) {
                        if(isCorrect) {
                            var numb = new BigInteger(num.toString());
                            System.out.println(numb);
                            result = result.add(numb);

                        }
                        num = new StringBuilder();
                        isCorrect = false;
                    }

                }
            }
        }
        System.out.println(result);
        System.out.println("second part");

        result = BigInteger.ZERO;
        Map<StringBuilder, Set<Map.Entry<Integer, Integer>>> possibleNumbers = Collections.synchronizedMap(new HashMap<StringBuilder, Set<Map.Entry<Integer,Integer>>>());
        for (int i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            var letters = line.split("");
            StringBuilder num = new StringBuilder();
            boolean isCorrect = false;
            for (int j = 0; j < letters.length; j++) {
                if(letters[j].matches("[0-9]")) {
                    num.append(letters[j]);
                    if(isGear(i - 1, j - 1, letters, lines,num, possibleNumbers)){
                        isCorrect = true;

                    }
                    if(isGear(i - 1, j, letters, lines, num, possibleNumbers)){
                        isCorrect = true;
                    }
                    if(isGear(i - 1, j + 1, letters, lines, num, possibleNumbers)){
                        isCorrect = true;
                    }
                    if(isGear(i, j - 1, letters, lines, num, possibleNumbers)){
                        isCorrect = true;
                    }

                    if(isGear(i + 1, j - 1, letters, lines, num, possibleNumbers)){
                        isCorrect = true;
                    }
                    if(isGear(i + 1, j, letters, lines, num, possibleNumbers)){
                        isCorrect = true;
                    }
                    if(isGear(i + 1, j + 1, letters, lines, num, possibleNumbers)){
                        isCorrect = true;
                    }
                    if(isGear(i, j + 1, letters, lines, num, possibleNumbers)){
                        isCorrect = true;
                    }
                    if (j + 1 >= letters.length || !letters[j + 1].matches("[0-9]")) {
                        if(isCorrect) {
                            var numb = new BigInteger(num.toString());
                            System.out.println(numb);
                            result = result.add(numb);

                        }
                        num = new StringBuilder();
                        isCorrect = false;
                    }

                }
        }
        }
        System.out.println(possibleNumbers);
        for (var n : possibleNumbers.keySet()) {
            Set<Map.Entry<Integer, Integer>> entrySet = possibleNumbers.get(n);
            for (var entry : entrySet) {
                for (var u : possibleNumbers.keySet()) {
                    if (!(u.compareTo(n) == 0)) {
                        Set<Map.Entry<Integer, Integer>> entries = possibleNumbers.get(u);
                        if (entries.contains(entry)) {
                            result = result.add(new BigInteger(n.toString()).add(new BigInteger(u.toString())));
                            if (entrySet.size() <= 1) {
                                possibleNumbers.remove(n);
                            } else {
                                entrySet.remove(entry);
                            }
                            if(entries.size() <= 1) {
                                possibleNumbers.remove(u);
                            } else {
                                entries.remove(entry);
                            }
                        }
                    }
                }
            }
        }


    }

    private static boolean isGear(int x, int y, String[] letters, List<String> lines, StringBuilder num, Map<StringBuilder, Set<Map.Entry<Integer, Integer>>> possibleNumbers) {
        if (isInbound(x, y, letters, lines) && lines.get(x).split("")[y].equals("*")) {
            AbstractMap.SimpleEntry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(x, y);
            if(possibleNumbers.containsKey(num)){
                possibleNumbers.get(num).add(entry);
            } else {
                HashSet<Map.Entry<Integer, Integer>> value = new HashSet<>();
                value.add(entry);
                possibleNumbers.put(num, value);
            }
            return true;
        }
        return false;
    }

    private static boolean isLetter(int x, int y, String[] letters, List<String> lines) {
        return isInbound(x, y, letters, lines) && lines.get(x).split("")[y].matches("[^0-9.]");
    }

    private static boolean isInbound(int x, int y, String[] letters, List<String> lines) {
        return x >= 0 && y >= 0 && y < letters.length && x < lines.size();
    }
}