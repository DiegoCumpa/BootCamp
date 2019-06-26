package com.example.diego.service;

import com.example.diego.model.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

    public List<Subject> getAll();

    public Subject save(Subject subject);

    public Subject update(Subject subject, int subjectId);

    public Subject delete(Integer subjectId);

    public Optional<Subject> getOne(int subjectId);

    public Subject findId(int subjectId);

}
