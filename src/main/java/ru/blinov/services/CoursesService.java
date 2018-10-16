package ru.blinov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.blinov.entities.Course;
import ru.blinov.repositories.CoursesRepository;

@Service
public class CoursesService {

    private CoursesRepository coursesRepository;

    @Autowired
    public void setCoursesRepository(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    public CoursesService() {
    }

    public Course getCourseById(Long id) {
        return coursesRepository.findCourseById(id);
    }
}
