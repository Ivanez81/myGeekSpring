package ru.blinov.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.blinov.entities.Course;

@Repository
public interface CoursesRepository extends CrudRepository<Course, Long> {
    Course findCourseById(Long id);
}
