package com.company;

import java.util.ArrayList;




public class Project {
    public int ID;
    public int maxStudents = 5;
    public Lecturer lec;
    ArrayList<Student> studentList = new ArrayList<>();
    public Project(int ID, Lecturer lec) {
        this.ID=ID;
        this.lec = lec;
    }
    /*
    This method receives a student who wants a project.
    They are then assigned to the project.
    We then check if there are too many students on a project and if so have a look their ranking with the lecturer and remove the lowest(person with the highest index) ranked.
    If a student has been removed from the project they are then returned.
    however, if no student was removed we add the student to the lecturer where they might be over subscribed and kick a student who is returned.
    @Returns a Student if one was kicked off a project
     */
    public Student assStudent(Student stu){
        studentList.add(stu);
        stu.pojAllocated=this;
        if (studentList.size() > maxStudents){
            Student removeStu = studentList.get(0);
            //check students ranking find the lowest ranked
            for (Student student: studentList) {
                if (lec.studentRankedList.indexOf(student)>lec.studentRankedList.indexOf(removeStu)){
                    removeStu=student;
                }
            }
            removeStu.pojAllocated=null;
            studentList.remove(removeStu);
            return removeStu;
        }
        //add student to lecturer
        return lec.addStu(stu,this);
    }
}
