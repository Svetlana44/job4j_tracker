package ru.job4j.map;

import java.util.ArrayList;
import java.util.List;

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
        List<Label> labels = new ArrayList<>();
        double sumMath = 0D;
        double sumLang = 0D;
        double sumPhilosophy = 0D;
        for (Pupil pupil : pupils) {
            List<Subject> subjects = pupil.subjects();
            sumMath += subjects.get(0).score();
            sumLang += subjects.get(1).score();
            sumPhilosophy += subjects.get(2).score();
        }
        labels.add(new Label("Math", sumMath / pupils.size()));
        labels.add(new Label("Lang", sumLang / pupils.size()));
        labels.add(new Label("Philosophy", sumPhilosophy / pupils.size()));
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
        Label best = null;
        if (!labels.isEmpty()) {
            best = labels.get(0);
        }
        for (int i = 1; i < labels.size(); i++) {
            if (labels.get(i).score() > best.score()) {
                best = labels.get(i);
            }
        }
        return best;
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        double sumMath = 0D;
        double sumLang = 0D;
        double sumPhilosophy = 0D;
        for (Pupil pupil : pupils) {
            List<Subject> subjects = pupil.subjects();
            sumMath += subjects.get(0).score();
            sumLang += subjects.get(1).score();
            sumPhilosophy += subjects.get(2).score();
        }
        labels.add(new Label("Math", sumMath));
        labels.add(new Label("Lang", sumLang));
        labels.add(new Label("Philosophy", sumPhilosophy));
        Label label = labels.get(0);
        for (int i = 1; i < labels.size(); i++) {
            if (labels.get(i).score() > label.score()) {
                label = labels.get(i);
            }
        }
        return label;
    }
}