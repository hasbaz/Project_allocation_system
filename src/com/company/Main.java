package com.company;

import java.util.ArrayList;
import static sun.swing.MenuItemLayoutHelper.max;

public class Main {
    // declare variables used for generation of projects students and Lecturers
    public static final int NUMBER_OF_STUDENTS = 400;
    public static final int NUMBER_OF_PROJECTS = 83;
    public static final int NUMBER_OF_LEC = 70;

    public static void main(String[] args) {
        ArrayList<Student> studentList = new ArrayList<>();
        ArrayList<Project> projectList = new ArrayList<>();
        ArrayList<Lecturer> lecList = new ArrayList<>();
        //generate students
        for (int i =0; i<NUMBER_OF_STUDENTS;i++){
            studentList.add(new Student(i));
        }
        //generate Lectures
        for (int i =0; i<NUMBER_OF_LEC;i++){
            lecList.add(new Lecturer(i));
        }
        //generate projects and assign them to a lecturer
        for (int i =0; i<NUMBER_OF_PROJECTS;i++){
            int lec = i;
            if (i >= NUMBER_OF_LEC){
                lec = i - NUMBER_OF_LEC;
            }
            projectList.add(new Project(i,lecList.get(lec)));
        }
        //get all the students to rank the projects
        for (int i =0; i<studentList.size(); i++){
            studentList.get(i).rankProjects(projectList);
        }

        //get all the lecturers to rank the students
        for (int i =0; i<lecList.size();i++){
            lecList.get(i).rankProjects(studentList);
        }
        //assign the projects and print out the assignments
        printAssignments(assignProjects(studentList),projectList);

    }
    public static boolean assignProjects(ArrayList<Student> studentList) {
        boolean fail = false; //flag for if assignment fails
        //work through list of students assigning them projects
        while (studentList.size() > 0) {
            Student stu = studentList.remove(0); //student at the top of the list is removed
            //if there are no projects left for this student break and set fail flag
            if (stu.projectList.size() == 0) {
                fail = true;
                break;
            }
            //assign student who took off the top of the list to a project and get possible student who was removed from that project
            Student studentToAdd = stu.projectList.remove(0).getValue().assStudent(stu);
            //add removed student back to project list
            if (studentToAdd != null) studentList.add(studentToAdd);
        }
        //return if assignment was successful
        return fail;
    }

    public static void printAssignments(boolean fail,ArrayList<Project> projectList){
        // if failed tell us so
        if (fail) {
            System.out.println("Allocation not successfully");
        } else {
            //loop to print out assignments and keep track of worst assignment
            int worst = 0;
            for (Project prj : projectList) {
                System.out.println("Allocated to project " + prj.ID + " are :");
                for (Student stu : prj.studentList) {
                    worst = max(worst, projectList.size() - stu.projectList.size());
                    //print out student and where it was in their list by looking at full size of projects vs their current list
                    System.out.print(" Student " + stu.ID + " this was their " + (projectList.size() - stu.projectList.size()));
                }
                System.out.println(" ");
            }
            //tell us the worst case
            System.out.println("the worst assignment was :"+ worst);
        }
    }
}