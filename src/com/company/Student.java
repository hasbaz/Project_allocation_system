package com.company;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Student {
    public int ID;
    public ArrayList<Pair<Double,Project>> projectList = new ArrayList<>();
    Random randomGen = new Random();
    public Project pojAllocated;
    //constructor taking ID in practice this could be changed to a name as well as take in other data
    public Student(int ID) {
        this.ID=ID;
    }
    /*
    This method ranks all the projects by generating a random double and paring it with a project then those pairs are sorted using a custom comparator
     */
    public void rankProjects(ArrayList<Project> projListUnranked){
        for (int i=0;i<projListUnranked.size();i++){
            projectList.add(new Pair<Double,Project>(randomGen.nextDouble(),projListUnranked.get(i)));
        }
        Collections.sort(projectList, new Comparator<Pair<Double, Project>>() {
            @Override
            public int compare(final Pair<Double,Project> o1, final Pair<Double,Project> o2) {
                return  o1.getKey().compareTo(o2.getKey());
            }
        });
    }
}
