package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Finder {
    private final List<Person> persons;

    public Finder(List<Person> persons) {
        this.persons = persons;
    }

    public Couple find(ExtremityAge extremityAge) {
        List<Couple> couples = new ArrayList<>();

        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                Couple couple = new Couple();
                if (persons.get(i).birthDate.getTime() < persons.get(j).birthDate.getTime()) {
                    couple.oldestPerson = persons.get(i);
                    couple.youngestPerson = persons.get(j);
                } else {
                    couple.oldestPerson = persons.get(j);
                    couple.youngestPerson = persons.get(i);
                }
                couple.ageDifference = couple.youngestPerson.birthDate.getTime() - couple.oldestPerson.birthDate.getTime();
                couples.add(couple);
            }
        }

        if (couples.size() < 1) {
            return new Couple();
        }

        Couple couple = couples.get(0);
        for (Couple a : couples) {
            switch (extremityAge) {
            case ClosestAge:
                if (a.ageDifference < couple.ageDifference) {
                    couple = a;
                }
                break;

            case FarthestAges:
                if (a.ageDifference > couple.ageDifference) {
                    couple = a;
                }
                break;
            }
        }

        return couple;
    }
}
