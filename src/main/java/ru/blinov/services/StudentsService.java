package ru.blinov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.blinov.entities.Student;
import ru.blinov.repositories.StudentsRepository;

@Service
public class StudentsService {
    private StudentsRepository studentsRepository;

    @Autowired
    public void setStudentsRepository(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public StudentsService() {
    }

    public Student getStudentById(Long id) {
        Student student = studentsRepository.findOneById(id);
        return student;
    }
}
