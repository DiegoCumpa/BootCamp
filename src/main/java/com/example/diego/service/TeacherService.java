package com.example.diego.service;

import com.example.diego.model.Teacher;

import java.util.List;

public interface TeacherService {

    public List<Teacher> getAll();

    public Teacher save(Teacher teacher);

    public Teacher update(Teacher teacher, int teacherId);

    public Teacher delete(Integer teacherId);


}
