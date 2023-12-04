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

  public Couple find(FilterCategory filterCategory) {
    List<Couple> couples = getCouples();

    final Stream<Couple> coupleStream = couples.stream();
    return (switch (filterCategory) {
      case ClosestAges -> findClosestAges(coupleStream);
      case FarthestAges -> findFarthestAges(coupleStream);
      case ClosestNameToA -> coupleStream
          .min(Comparator.comparing(Couple::getClosestNameToA));
    }).orElse(new Couple());
  }

  private static Optional<Couple> findFarthestAges(Stream<Couple> coupleStream) {
    return coupleStream
        .max(Comparator.comparing(Couple::getAgeDifference));
  }

  private static Optional<Couple> findClosestAges(Stream<Couple> coupleStream) {
    return coupleStream
        .min(Comparator.comparing(Couple::getAgeDifference));
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

}
