import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }


        Stream<Person> minorsSTR = persons.stream();
        long minors = minorsSTR.filter(x -> x.getAge() < 18).count();

        Stream<Person> armyServiceSTR = persons.stream();
        List<String> armyService = armyServiceSTR
                .filter(x->x.getSex()==Sex.MAN)
                .filter(x->x.getAge()<27)
                .filter(x->x.getAge()>=18)
                .map(Person::getFamily)
                .toList();

        Stream<Person> higherEduSTR = persons.stream();
        List<Person> higherEdu = higherEduSTR
                .filter(x->x.getEducation()==Education.HIGHER)
                .filter(x->x.getAge()>=18)
                .filter(x-> {
                    if (x.getSex() == Sex.MAN) {
                        return x.getAge() < 65;

                    } else {
                        return x.getAge()<60;
                    }
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();

        Stream<Person> mainStream = persons.stream();

    }
}
