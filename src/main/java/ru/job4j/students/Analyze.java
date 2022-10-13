package ru.job4j.students;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {
    public static double averageScore(Stream<Pupil> stream) {
        return stream
                .flatMapToLong(pupil -> pupil.subjects()
                        .stream()
                        .mapToLong(Subject::score))
                .average()
                .getAsDouble();
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream.flatMap(pupil -> pupil.subjects().stream().map(subject -> new Tuple(pupil.name(), subject.score()))).toList()
                .stream()
                .collect(Collectors.groupingBy(Tuple::name, Collectors.averagingDouble(Tuple::score)))
                .entrySet().stream()
                .map(entry -> new Tuple(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream.flatMap(pupil -> pupil.subjects().stream().map(subject -> new Tuple(subject.name(), subject.score())))
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(Tuple::name, Collectors.averagingDouble(Tuple::score)))
                .entrySet().stream()
                .map(entry -> new Tuple(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream.flatMap(pupil -> pupil.subjects().stream().map(subject -> new Tuple(pupil.name(), subject.score()))).toList()
                .stream()
                .collect(Collectors.groupingBy(Tuple::name, Collectors.summingDouble(Tuple::score)))
                .entrySet().stream()
                .map(entry -> new Tuple(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList())
                .stream().max(Comparator.comparingDouble(Tuple::score)).get();
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream.flatMap(pupil -> pupil.subjects().stream().map(subject -> new Tuple(subject.name(), subject.score())))
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(Tuple::name, Collectors.summingDouble(Tuple::score)))
                .entrySet().stream()
                .map(entry -> new Tuple(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList())
                .stream().max(Comparator.comparingDouble(Tuple::score)).get();
    }
}
