package chapter.ch02_change;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 1. 모든 메서드는 number 를 매개변수로 받아야 한다. 그 값을 유지할 내부 상태는 없다.
 * 2. 모든 메서드는 순수함수이기 때문에 public static 이다. 그렇기 때문에 자연수 분류 문제라는 범위 밖에서도 유용하다.
 * 3. 일반적이고 합리적인 변수의 사용으로 함수 수준에서의 재사용이 쉬워졌다.
 * 4. 이 코드는 캐시가 없기 때문에 반복적으로 사용하기에 비능률적이다.
 * <p>
 * 일반적으로 객체지향 시스템에서 재사용할 수 있는 가장 세밀한 요소는 클래스이다.
 * 개발자들은 소형 패키지가 재사용이 용이하다는 것을 잘 잊는다.
 * 일례로 sum 메서드는 특정한 목록 대신에 Collection<Integer> 를 받는다.
 * 이 인터페이스는 온갖 수의 컬렉션에 보편적이기 때문에, 함수 수준에서 일반적으로 재사용이 가능하다/
 */
public class 조금더함수적인자연수분류기 {

    public static boolean isFactor(final int candidate, final int number) {
        return number % candidate == 0;
    }

    public static Set<Integer> factors(final int number) {
        Set<Integer> factors = new HashSet<>();
        factors.add(1);
        factors.add(number);
        for (int i = 2; i < number; i++)
            if (isFactor(i, number))
                factors.add(i);
        return factors;
    }

    public static int aliquotSum(final Collection<Integer> factors) {
        int sum = 0;
        int targetNumber = Collections.max(factors);
        for (int n : factors) {
            sum += n;
        }
        return sum - targetNumber;
    }

    public static boolean isPerfect(final int number) {
        return aliquotSum(factors(number)) == number;
    }

    public static boolean isAbundant(final int number) {
        return aliquotSum(factors(number)) > number;
    }

    public static boolean isDeficient(final int number) {
        return aliquotSum(factors(number)) < number;
    }
}
