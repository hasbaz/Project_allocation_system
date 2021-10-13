package com.company;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Lecturer {
    public int ID;
    public int maxStudents = 9;
    public ArrayList<Pair<Double,Student>> studentList = new ArrayList<>();
    public ArrayList<Student> studentRankedList = new ArrayList<>();
    public ArrayList<Pair<Project,Student>> assStudentList = new ArrayList<>();
    Random randomGen = new Random();

    public Lecturer(int ID) {
        this.ID = ID;
    }
    /*
    This method ranks all the students by generating a random double and paring it with a students then those pairs are sorted using a custom comparator
     */
    public void rankProjects(ArrayList<Student> stuListUnranked) {
        for (int i = 0; i < stuListUnranked.size(); i++) {
            studentList.add(new Pair<Double,Student>(randomGen.nextDouble(), stuListUnranked.get(i)));
        }
        Collections.sort(studentList, new Comparator<Pair<Double, Student>>() {
            @Override
            public int compare(final Pair<Double, Student> o1, final Pair<Double, Student> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        // this list is to enable finding the rank of the students with ease by using indexOf
        for (Pair<Double, Student> stuPair: studentList){
            studentRankedList.add(stuPair.getValue());
        }
    }
    /*
    This method receives a student who wants to be with a lecturer on a project.
    They are then assigned to the lecturer with their project.
    We then check if there are too many students with the lecturer and if so have a look their ranking with the lecturer and remove the lowest(person with the highest index) ranked.
    If a student has been removed from the lecturer they then must have their project removed inside of student and then removed from the project as well.
    @Returns a Student if one was kicked off a project
     */
    public Student addStu(Student stu,Project prj){
        assStudentList.add(new Pair<Project,Student>(prj,stu));
        if (assStudentList.size()>maxStudents){
            Pair<Project,Student> toRemove = assStudentList.get(0);
            for (Pair<Project,Student> projectStudentPair: assStudentList) {
                if (studentRankedList.indexOf(projectStudentPair.getValue()) < studentRankedList.indexOf(toRemove.getValue())){
                    toRemove = projectStudentPair;
                }
            }
            assStudentList.remove(toRemove);
            toRemove.getValue().pojAllocated=null;
            toRemove.getKey().studentList.remove(toRemove.getValue());
            return toRemove.getValue();
        }
        return null;
    }
}
