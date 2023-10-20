package algorithm;

import java.util.*;

public class Finder {
  private final List<Person> persons;

  public Finder(List<Person> persons) {
    this.persons = persons;
  }

  public Couple find(ExtremityAge extremityAge) {
    List<Couple> couples = getCouples();

    return findByExtremityAge(extremityAge, couples).orElse(new Couple());
  }

  private List<Couple> getCouples() {
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
    return couples;
  }

  private static Optional<Couple> findByExtremityAge(ExtremityAge extremityAge, List<Couple> couples) {
    switch (extremityAge) {
      case ClosestAge -> {
        return couples.stream()
            .sorted(Comparator.comparing((c) -> c.ageDifference))
            .findFirst();
      }
      case FarthestAges -> {
        return couples.stream()
            .sorted( Comparator.comparing((c) -> c.ageDifference))
            .sorted(Collections.reverseOrder())
            .findFirst();
      }
    }

    return Optional.empty();
  }
}
