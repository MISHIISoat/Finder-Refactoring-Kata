package algorithm;

import java.util.Comparator;
import java.util.List;

public class Couple {
    private Person oldestPerson;
    private Person youngestPerson;
    private long ageDifference;

    public Couple() {
    }

    public Couple(Person person1, Person person2) {
        List<Person> persons = List.of(person1, person2);
        this.oldestPerson = persons.stream().max(Comparator.comparing(person -> person.birthDate.getTime())).get();
        this.youngestPerson = persons.stream().min(Comparator.comparing(person -> person.birthDate.getTime())).get();
        this.ageDifference = this.youngestPerson.birthDate.getTime() - this.oldestPerson.birthDate.getTime();
    }

    public Person getOldestPerson() {
        return oldestPerson;
    }

    public Person getYoungestPerson() {
        return youngestPerson;
    }

    public long getAgeDifference() {
        return ageDifference;
    }
}
