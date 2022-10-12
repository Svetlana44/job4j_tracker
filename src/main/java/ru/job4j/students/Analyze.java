package ru.job4j.students;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {
    public static double averageScore(Stream<Pupil> stream) {
        return stream.flatMapToLong(pupil -> pupil.subjects().stream().mapToLong(subject -> subject.score())).average().getAsDouble();
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        List<Tuple> tuples = new ArrayList<>();
        stream.flatMap(pupil -> pupil.subjects().stream().map(subject -> new Tuple(pupil.name(), subject.score())))
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(tuple -> tuple.name(), Collectors.averagingDouble(t -> t.score())))
                .forEach((k, v) -> tuples.add(new Tuple((String) k, (Double) v)));

        return tuples;
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        List<Tuple> tuples = new ArrayList<>();
        stream.flatMap(pupil -> pupil.subjects().stream().map(subject -> new Tuple(subject.name(), subject.score())))
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(tuple -> tuple.name(), Collectors.averagingDouble(t -> t.score())))
                .forEach((k, v) -> tuples.add(new Tuple((String) k, (Double) v)));

        return tuples;
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        List<Tuple> tuples = maxScoreByPupil(stream);
        return tuples.stream().max(Comparator.comparingDouble(t -> t.score())).get();
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {

        List<Tuple> tuples = new ArrayList<>();
        stream.flatMap(pupil -> pupil.subjects().stream().map(subject -> new Tuple(subject.name(), subject.score())))
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(tuple -> tuple.name(), Collectors.summingDouble(t -> t.score())))
                .forEach((k, v) -> tuples.add(new Tuple((String) k, (Double) v)));
        return tuples.stream().max(Comparator.comparingDouble(t -> t.score())).get();
    }

    /*
    my method summing score of student by subject
     */
    public static List<Tuple> maxScoreByPupil(Stream<Pupil> stream) {
        List<Tuple> tuples = new ArrayList<>();
        stream.flatMap(pupil -> pupil.subjects().stream().map(subject -> new Tuple(pupil.name(), subject.score())))
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(tuple -> tuple.name(), Collectors.summingDouble(t -> t.score())))
                .forEach((k, v) -> tuples.add(new Tuple((String) k, (Double) v)));

        return tuples;
    }
}
