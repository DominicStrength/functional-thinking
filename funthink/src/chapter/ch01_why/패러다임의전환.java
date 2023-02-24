package chapter.ch01_why;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 텍스트 파일을 읽고, 가장 많이 사용된 단어들을 찾고, 그 단어들과 빈도를 정렬된 목록으로 출력하라
 */
public class 패러다임의전환 {
    // Set 은 객체를 중복해서 저장할 수 없다.
    private Set<String> NON_WORDS = new HashSet<>() {{
        add("the"); add("and"); add("of"); add("to"); add("a");
        add("i");   add("it");  add("in"); add("or"); add("is");
        add("d");   add("s");   add("as"); add("so"); add("but");
        add("be");
    }};

    /**
     * TreeMap 은 Map 인터페이스를 상속받고 있으며 HashMap 과 동일하게 사용 가능 (firstEntry, firstKey, lastEntry, lastKey 등)
     * Matcher 은 Pattern 클래스를 받아 대상 문자열과 패턴이 일치하는 부분을 찾거나 전체 일치 여부 등을 판별하기 위해 사용된다.
     *            Patten.compile 은 주어진 정규식을 갖는 패턴을 생성
     *            Matcher.matcher 은 패턴에 매칭할 문자열을 입력해 Matcher 를 생성
     */
    public Map wordFreq(String words) {
        TreeMap<String, Integer> wordMap = new TreeMap<String, Integer>();
        Matcher m = Pattern.compile("\\w+").matcher(words);

        while (m.find()) { // 패턴과 일치하는 다음 문자열을 찾는다 있으면 true
            String word = m.group().toLowerCase(); // Matcher.group() 은 매치와 일치하는 문자열을반환

            if (!NON_WORDS.contains(word)) { // 리스트에 일치하는 문자열이 없다면
                if (wordMap.get(word) == null) wordMap.put(word, 1); // 해당 문자열을 추가 (key, value)
                else wordMap.put(word, wordMap.get(word) + 1); // 같은 문자열이 있으면
            }// end if contains
        }// end while

        return wordMap;
    }

    public Map wordFreqInJava8(String words) {
        TreeMap<String, Integer> wordMap = new TreeMap<>();
        regexToList(words, "\\w+").stream()
                .map(w -> w.toLowerCase())
                .filter(w -> !NON_WORDS.contains(w))
                .forEach(w -> wordMap.put(w, wordMap.getOrDefault(w, 0) + 1));
        return wordMap;
    }

    private List<String> regexToList(String words, String regex) {
        List wordList = new ArrayList<>();
        Matcher m = Pattern.compile(regex).matcher(words);
        while (m.find()) wordList.add(m.group());
        return wordList;
    }
}
