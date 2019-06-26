package com.example.diego.service;

import com.example.diego.model.Teacher;
import com.example.diego.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public List<Teacher> getAll(){
        return (List<Teacher>) teacherRepository.findAll();
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher update(Teacher teacher, int teacherId) {
        teacherRepository.findById(teacherId).ifPresent((p) -> {
            teacher.setTeacherId(teacherId);
            teacherRepository.save(teacher);
        });
        return teacher;
    }

    @Override
    public Teacher delete(Integer teacherId) {
        teacherRepository.findById(teacherId).ifPresent((p) -> {
            teacherRepository.deleteTeacher(teacherId);
        });
        return null;
    }


}
