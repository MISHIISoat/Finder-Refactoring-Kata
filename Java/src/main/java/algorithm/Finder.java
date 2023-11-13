package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
        Couple couple = new Couple(persons.get(i), persons.get(j));

        couples.add(couple);
      }
    }
    return couples;
  }

  private static Optional<Couple> findByExtremityAge(ExtremityAge extremityAge, List<Couple> couples) {
    final Stream<Couple> coupleStream = couples.stream();
    return switch (extremityAge) {
      case ClosestAge -> coupleStream
          .min(Comparator.comparing(Couple::getAgeDifference));
      case FarthestAges -> coupleStream
          .max(Comparator.comparing(Couple::getAgeDifference));
    };
  }
}
