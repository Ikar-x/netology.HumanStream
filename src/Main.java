import java.util.*;
import java.util.stream.Collectors;

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

        long result = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        System.out.println("Количество несовершеннолетних: " + result);

        List<Person> recruitList = persons.stream()
                .filter(person -> person.getAge() > 18 && person.getAge() < 27)
                .collect(Collectors.toList());

        System.out.println("Список призывников: ");
        for(Person p : recruitList){
            System.out.print(p.getFamily() + ", ");
        }
        System.out.println("***");

        List<Person> workHumanList = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() > 18)
                .filter(person -> (person.getAge() < 60 && person.getSex() == Sex.WOMAN)||(person.getAge() < 65 && person.getSex() == Sex.MAN))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Список образованных работяг: ");
        for(Person p : workHumanList){
            System.out.println(p.toString());
        }
    }
}