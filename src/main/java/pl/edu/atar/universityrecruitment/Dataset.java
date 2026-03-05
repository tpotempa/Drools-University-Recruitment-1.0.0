package pl.edu.atar.universityrecruitment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dataset {

    public Dataset() {
    }

    public List<Candidate> getUniversityCandidates() {

        return new ArrayList<Candidate>(Arrays.asList(
                new Candidate(1L, "Anna", "Kowalewska", 120.0, null, "Informatyka", Boolean.TRUE, "Female", "Grammar"),
                new Candidate(2L, "Jacek", "Nowak", 110.0, null, "Informatyka", Boolean.FALSE, "Male", "Technical"),
                new Candidate(3L, "Ewa", "Wiśniowa", 35.0, null, "Elektrotechnika", Boolean.FALSE, "Female", "Technical"),
                new Candidate(4L, "Karol", "Gruszka", 135.0, null, "Automatyka i robotyka", Boolean.TRUE, "Male", "Technical"),
                new Candidate(5L, "Kinga", "Poziomka", 45.0, null, "Elektrotechnika", Boolean.TRUE, "Female", "Grammar"),
                new Candidate(6L, "Maja", "Czereśnia", 50.0, null, "Pielęgniarstwo", Boolean.TRUE, "Female", "Grammar"),
                new Candidate(7L, "Karol", "Dąb", 90.0, null, "Psychologia", Boolean.FALSE, "Male", "Grammar"),
                new Candidate(8L, "Ewelina", "Jastrząb", 60.0, null, "Pielęgniarstwo", Boolean.FALSE, "Female", "Grammar")));
    }

    public List<Candidate> getUniversityCandidatesWithSubjects() {

        ArrayList<ExamSubjectResult> esr = new Dataset().getExamSubjectResult();

        return new ArrayList<Candidate>(Arrays.asList(
                new Candidate(1L, "Anna", "Kowalewska", new ArrayList<>(esr.subList(0, 5)), null, "Informatyka", Boolean.FALSE, "Female", "Grammar"),
                new Candidate(2L, "Jacek", "Nowak", new ArrayList<>(esr.subList(5, 8)), null, "Informatyka", Boolean.FALSE, "Male", "Technical"),
                new Candidate(3L, "Ewa", "Wiśniowa", new ArrayList<>(esr.subList(8, 12)), null, "Elektrotechnika", Boolean.FALSE, "Female", "Technical"),
                new Candidate(4L, "Karol", "Gruszka", new ArrayList<>(esr.subList(12, 16)), null, "Automatyka i robotyka", Boolean.TRUE, "Male", "Technical"),
                new Candidate(5L, "Kinga", "Poziomka", new ArrayList<>(esr.subList(16, 20)), null, "Elektrotechnika", Boolean.TRUE, "Female", "Grammar"),
                new Candidate(6L, "Maja", "Czereśnia", new ArrayList<>(esr.subList(20, 25)), null, "Pielęgniarstwo", Boolean.TRUE, "Female", "Grammar"),
                new Candidate(7L, "Karol", "Dąb", new ArrayList<>(esr.subList(25, 28)), null, "Psychologia", Boolean.FALSE, "Male", "Grammar"),
                new Candidate(8L, "Ewelina", "Jastrząb", new ArrayList<>(esr.subList(0, 0)), null, "Pielęgniarstwo", Boolean.FALSE, "Female", "Grammar")));
    }

    public ArrayList<ExamSubjectResult> getExamSubjectResult() {

        return new ArrayList<ExamSubjectResult>(Arrays.asList(
                new ExamSubjectResult("język polski", "podstawowy", 35D),
                new ExamSubjectResult("język angielski", "podstawowy", 55D),
                new ExamSubjectResult("informatyka", "podstawowy", 95D),
                new ExamSubjectResult("matematyka", "rozszerzony", 80D),
                new ExamSubjectResult("biologia", "podstawowy", 77D),
                new ExamSubjectResult("język polski", "podstawowy", 44D),
                new ExamSubjectResult("matematyka", "rozszerzony", 100D),
                new ExamSubjectResult("język angielski", "podstawowy", 33D),
                new ExamSubjectResult("język polski", "podstawowy", 87D),
                new ExamSubjectResult("matematyka", "rozszerzony", 56D),
                new ExamSubjectResult("język angielski", "podstawowy", 44D),
                new ExamSubjectResult("fizyka", "rozszerzony", 31D),
                new ExamSubjectResult("język polski", "podstawowy", 59D),
                new ExamSubjectResult("matematyka", "podstawowy", 72D),
                new ExamSubjectResult("język angielski", "podstawowy", 33D),
                new ExamSubjectResult("informatyka", "rozszerzony", 88D),
                new ExamSubjectResult("język polski", "podstawowy", 99D),
                new ExamSubjectResult("matematyka", "rozszerzony", 44D),
                new ExamSubjectResult("język angielski", "podstawowy", 77D),
                new ExamSubjectResult("chemia", "rozszerzony", 100D),
                new ExamSubjectResult("język polski", "podstawowy", 80D),
                new ExamSubjectResult("matematyka", "rozszerzony", 46D),
                new ExamSubjectResult("język angielski", "podstawowy", 45D),
                new ExamSubjectResult("biologia", "rozszerzony", 80D),
                new ExamSubjectResult("chemia", "rozszerzony", 68D),
                new ExamSubjectResult("język polski", "rozszerzony", 90D),
                new ExamSubjectResult("matematyka", "podstawowy", 80D),
                new ExamSubjectResult("język angielski", "podstawowy", 90D)));
    }
}