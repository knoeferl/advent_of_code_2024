package day02;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DAY02 {
    public static void main(String[] args) throws IOException {
        List<String> lines;
        boolean useExample = !true;
        boolean verbose = !true;

        if (!useExample) {
            lines = Files.readAllLines(Path.of("src/day02/input.txt"));
        } else {
            String example = """
                    Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                    Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                    Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                    Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                    Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green""";
            lines = Arrays.stream(example.split("\n")).toList();
        }
        List<Integer> res =  new ArrayList<>();
        System.out.println("first part");
        int game = 0;
        for (var line : lines) {
            game++;
            var  l = line.substring(game < 10 ?8:9).split("[,;]");
            HashMap<String, Integer> results = new HashMap<>();
            boolean works = true;
            for (var s : l) {
                var v = s.trim().split(" ");
                String key = v[1].trim();
                String value = v[0].trim();
                int valueInt = Integer.parseInt(value);
                if(key.equals("red")){
                    if(valueInt > 12)
                    {
                        works = false;
                        break;
                    }
                }
                if(key.equals("green")){
                    if(valueInt > 13) {
                        works = false;
                        break;
                    }
                }
                if(key.equals("blue")){
                    if(valueInt > 14) {
                        works = false;
                        break;
                    }
                }

            }
            if(works)
                res.add(game);

        }
        System.out.println(res.stream().reduce(Integer::sum).get());
        System.out.println("second part");
        int result = 0;
        game = 0;
        for (var line : lines) {
            game++;
            var  l = line.substring(game < 10 ?8:9).split("[,;]");
            HashMap<String, Integer> results = new HashMap<>();
            for (var s : l) {
                var v = s.trim().split(" ");
                String key = v[1].trim();
                String value = v[0].trim();
                int valueInt = Integer.parseInt(value);
                if(results.containsKey(key)){
                    if(results.get(key) < valueInt) {
                        results.put(key, valueInt);
                    }
                } else {
                    results.put(key,valueInt);
                }
            }
            Integer red = results.getOrDefault("red", 0);
            Integer green = results.getOrDefault("green", 0);
            Integer blue = results.getOrDefault("blue", 0);
            int power = red * green * blue;
            if(verbose)
                System.out.println(red + " * " + green + " * " + blue + " = " + power);
            result += power;

        }
        System.out.println(result);
    }
}