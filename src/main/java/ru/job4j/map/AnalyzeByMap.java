package ru.job4j.map;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double sumScore = 0D;
        int index = 0;
        for (Pupil pupil : pupils) {
            List<Subject> subjects = pupil.subjects();
            double sumScorePupil = 0D;
            for (Subject subject : subjects) {
                index++;
                sumScorePupil += subject.score();
            }
            sumScore += sumScorePupil;
        }
        return sumScore / index;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double sumScorePupil = 0;
            List<Subject> subjects = pupil.subjects();
            for (Subject subject : subjects) {
                sumScorePupil += subject.score();
            }
            labels.add(new Label(pupil.name(), sumScorePupil / pupil.subjects().size()));
        }
        return labels;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> temp = new LinkedHashMap();
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            List<Subject> subjects = pupil.subjects();
            for (Subject subject : subjects) {
                if (!temp.containsKey(subject.name())) {
                    temp.put(subject.name(), subject.score());
                } else {
                    temp.replace(subject.name(), subject.score() + temp.get(subject.name()));
                }
            }
        }
        temp.forEach((k, v) -> labels.add(new Label(k, v / pupils.size())));
        return labels;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double sumScore = 0D;
            for (Subject subject : pupil.subjects()) {
                sumScore += subject.score();
            }
            labels.add(new Label(pupil.name(), sumScore));
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        Map<String, Integer> temp = new LinkedHashMap();
        for (Pupil pupil : pupils) {
            List<Subject> subjects = pupil.subjects();
            subjects.forEach(subject ->
                    temp.merge(subject.name(),
                            subject.score(),
                            Integer::sum));
        }
        temp.forEach((k, v) -> labels.add(new Label(k, v)));
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }
}