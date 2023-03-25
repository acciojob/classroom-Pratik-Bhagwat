package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {
    HashMap<String,Student> studentDb = new HashMap<>();
    HashMap<String,Teacher> teacherDb = new HashMap<>();
    HashMap<String , List<String>> student_teacherDb = new HashMap<>();


    public void addStudent(Student student){
        String key = student.getName();
        studentDb.put(key,student);
    }
    public void addTeacher(Teacher teacher) {
        String key = teacher.getName();
        teacherDb.put(key,teacher);
    }
    public void addStudentTeacherPair(String student,String teacher) {
        if(student_teacherDb.containsKey(teacher)){
            List<String> teacher_list = student_teacherDb.get(teacher);
            teacher_list.add(student);
            student_teacherDb.put(teacher,teacher_list);
        }else{
            List<String> teacher_list = new ArrayList<>();
            teacher_list.add(student);
            student_teacherDb.put(teacher,teacher_list);
        }
    }
    public Student getStudentByName(String name) {
        return studentDb.get(name);
    }
    public Teacher getTeacherByName (String name) {
        return teacherDb.get(name);
    }
    public List<String> getStudentsByTeacherName(String teacher) {
        return new ArrayList<>(student_teacherDb.get(teacher));
    }
    public List<String> getAllStudents() {
        List<String> studentList = new ArrayList<>();
        for (Student s : studentDb.values()) {
            studentList.add(s.getName());
        }
        return studentList;
    }
    public void deleteTeacherByName(String teacher) {
        teacherDb.remove(teacher);
    }
    public void deleteAllTeachers() {
        for (List<String> student_list : student_teacherDb.values()) {
            for (String s : student_list) {
                studentDb.remove(s);
            }
            teacherDb.clear();
            student_teacherDb.clear();
        }
    }
}
