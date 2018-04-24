package mytest.basic;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

public class StreamTest {

    public static void main(String[] args){
        List<String> myList = Arrays.asList("a1", "a2", "a3",  "b1", "c2", "c1");
        String[] array = new String[]{"a1", "a2", "a3"};
        Arrays.asList(array);
        myList.toArray(new String[myList.size()]);

        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(s -> s.toUpperCase())
                .sorted()
                .forEach(System.out::println);

        List<Person> persons = Arrays.asList(Person.builder().name("Max").age(18).build(),
                      Person.builder().name("Peter").age(23).build(),
                      Person.builder().name("Pamela").age(23).build(),
                      Person.builder().name("Max").age(18).build());

        persons.stream().reduce((p1, p2) -> p1.age>p2.age? p1:p2).ifPresent(System.out::println);

        Person result = persons.stream().reduce(new Person("", 0), (p1, p2) -> {
            p1.age += p2.age;
            p1.name += ", " +p2.name;
            return p1;
        });

        System.out.println("result= " + result);
    }



}

@Data
@AllArgsConstructor
@Builder
class Person {
    String name;
    int age;
}
