package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {
    HashMap<String,Student>  studentHashMap = new HashMap<>();
    HashMap<String,Teacher> teacherHashMap = new HashMap<>();
    Map<String, LinkedHashSet<String>> studentTeacherMap = new HashMap<>();

    public void addStudent(Student student){
        String key = student.getName();
        studentHashMap.put(key,student);
    }
    public void addTeacher(Teacher teacher) {
        String key = teacher.getName();
        teacherHashMap.put(key,teacher);
    }
    public void addStudentTeacherPair(String student,String teacher) {
        LinkedHashSet<String> students = studentTeacherMap.getOrDefault(teacher, new LinkedHashSet<>());
        students.add(student);
        studentTeacherMap.put(teacher, students);
    }
    public Student getStudentByName(String name) {
        return studentHashMap.get(name);
    }
    public Teacher getTeacherByName (String name) {
        return teacherHashMap.get(name);
    }
    public List<String> getStudentsByTeacherName(String teacher) {
        return new ArrayList<>(studentTeacherMap.get(teacher));
    }
    public List<String> getAllStudents() {
        List<String> studentList = new ArrayList<>();
        for (Student s : studentHashMap.values()) {
            studentList.add(s.getName());
        }
        return studentList;
    }
    public void deleteTeacherByName(String teacher) {
        teacherHashMap.remove(teacher);
    }
    public void deleteAllTeachers() {
        for(Set<String> ListOfStudents : studentTeacherMap.values()){
            for(String student : ListOfStudents){

                studentHashMap.remove(student);

            }
        }

        teacherHashMap.clear();
        studentTeacherMap.clear();
    }
}
