package chapter.ch02_change;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class 명령형처리 {
    public String cleanNames(List<String> listOfNames) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < listOfNames.size(); i++) {
            if (listOfNames.get(i).length() > 1) {
                result.append(capitalizeString(listOfNames.get(i))).append(",");
            }
        }
        return result.substring(0, result.length() - 1).toString();
    }

    public String cleanNamesOfStream(List<String> names) {
        if (names == null) return "";
        return names
                .stream()
                .filter(name -> name.length() > 1)
                .map(name -> capitalizeString(name))
                .collect(Collectors.joining(","));
    }

    // 스레드 문제해결
    public String cleanNamesOfStreamP(List<String> names) {
        if (names == null) return "";
        return names
                .parallelStream()
                .filter(name -> name.length() > 1)
                .map(name -> capitalizeString(name))
                .collect(Collectors.joining(","));
    }

    private String capitalizeString(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
    }

    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        lists.add("apple");
        lists.add("banana");
        lists.add("pineapple");


        명령형처리 m = new 명령형처리();
        // System.out.println(m.cleanNames(lists));
        System.out.println(m.cleanNamesOfStream(lists));
    }
}
