package me.osean.effective_java.chapter_01.item_03.singleton_pattern_07;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Person {
    LocalDate birthday;

    public Person() {
        this.birthday = LocalDate.now();
    }

    public Person(LocalDate birthday) {
        this.birthday = birthday;
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    public int getAge() {
        return LocalDate.now().getYear() - this.birthday.getYear();
    }

    @Override
    public String toString() {
        return "Person{" +
                "나이 =" + this.getAge() + "세" +
                '}';
    }

    public static void main(String[] args) {
        List<LocalDate> dates = Arrays.asList(
                LocalDate.of(1995, 3, 4),
                LocalDate.of(2000, 1, 1),
                LocalDate.of(1998, 10, 7),
                LocalDate.of(1968, 12, 12)
        );
        Function<LocalDate, Person> personApplyBirthday = Person::new;
        List<Person> people = dates.stream().map(personApplyBirthday).collect(Collectors.toList());

        System.out.printf("정렬 전 사람들 : %s\n", people);
        people.sort(Person::compareByAge);
        System.out.printf("정렬 후 사람들 : %s\n", people);

        // 기본 생성자를 이용해서 Map 에 적용하는 방법
        Supplier<Person> bornSupplier = Person::new;
        IntFunction<Person> bornFunction = index -> bornSupplier.get();
        List<Person> newBabies = IntStream.range(0, 10)
                .mapToObj(bornFunction)
                .collect(Collectors.toList());
        System.out.printf("%s명\n", newBabies.size());
    }
}
