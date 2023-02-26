package chapter.ch02_change;

import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;

public class 자바8을사용한자연수분류기 {

    // 함수형 자바의 범위는 경게를 포함하지 않는다.
    public static IntStream factorsOf(int number) {
        return range(1, number + 1)
                .filter(potential -> number % potential == 0);
    }
    
    public static int aliquotSum(int number) {
        return factorsOf(number).sum() - number;
    }

    public static boolean isPerfect(int number) {
        return aliquotSum(number) == number;
    }

    public static boolean isAbundant(int number) {
        return aliquotSum(number) > number;
    }

    public static boolean isDeficient(int number) {
        return aliquotSum(number) < number;
    }
}
