package ru.blinov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.blinov.entities.Course;
import ru.blinov.entities.Student;
import ru.blinov.services.CoursesService;
import ru.blinov.services.StudentCourseService;
import ru.blinov.services.StudentsService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
@Transactional
public class StudentsController {
    private StudentsService studentsService;
    private StudentCourseService studentCourseService;
    private CoursesService coursesService;

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @Autowired
    public void setStudentCourseService(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    public StudentsController() {
    }

    // http://localhost:8189/students/list
    @RequestMapping("/list")
    @Transactional
    public String showStudentsList(Model model) {
        List<Student> allStudents = studentsService.getAllStudentsList();
        model.addAttribute("studentsList", allStudents);
        return "students-list";
    }

    // http://localhost:8189/students/stream/2/edu
    @RequestMapping(path = "stream/{id}/edu", method = RequestMethod.GET)
    public String getCoursesByStudentIdStream(Model model, @PathVariable("id") Long id) {
        Student studentById = studentsService.getStudentById(id);
        List<Course> coursesList = coursesService.getAllCourses();
        List<Course> resultCoursesList = coursesList.stream().filter(course ->
                studentById.getCourses().stream().noneMatch(studentCourse ->
                        studentCourse.getId().equals(course.getId())))
                .collect(Collectors.toList());
        model.addAttribute("coursesByStudentId", studentById.getCourses());
        model.addAttribute("coursesList", resultCoursesList);
        return "courses-by-student-id";
    }

    // http://localhost:8189/students/2/edu
    @RequestMapping(path = "/{id}/edu", method = RequestMethod.GET)
    public String getCoursesByStudentIdHQL(Model model, @PathVariable("id") Long id) {
        Student studentById = studentsService.getStudentById(id);
        List<Course> studentCourses = studentById.getCourses();
        if (studentCourses.isEmpty()) {
            List<Course> resultCoursesList = coursesService.getAllCourses();
            model.addAttribute("coursesByStudentId", studentCourses);
            model.addAttribute("coursesList", resultCoursesList);
        } else {
            List<Course> resultCoursesList = coursesService.getAvailableCoursesForStudent(studentCourses);
            model.addAttribute("coursesByStudentId", studentCourses);
            model.addAttribute("coursesList", resultCoursesList);
        }
        return "courses-by-student-id";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        Student student = new Student();
        student.setName("Unknown");
        model.addAttribute("student", student);
        return "add-student-form";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String showAddForm(Student student) {
        studentsService.mergeStudent(student);
        return "redirect:/students/list";
    }

    @RequestMapping(path = "/{studentId}/edu/add/{courseId}", method = RequestMethod.GET)
    public String addStudentCourse(@PathVariable("studentId") Long studentId,
                                   @PathVariable("courseId") Long courseId) {
        Student studentById = studentsService.getStudentById(studentId);
        studentById.getCourses().add(coursesService.getCourseById(courseId));
        studentsService.mergeStudent(studentById);
        return "redirect:/students/" + studentId + "/edu";
    }

    @RequestMapping(path = "/{studentId}/edu/remove/{courseId}", method = RequestMethod.GET)
    public String removeStudentCourse(@PathVariable("studentId") Long studentId,
                                   @PathVariable("courseId") Long courseId) {
        Student studentById = studentsService.getStudentById(studentId);
        studentById.getCourses().remove(coursesService.getCourseById(courseId));
        studentsService.mergeStudent(studentById);
        return "redirect:/students/" + studentId + "/edu";
    }

//    @RequestMapping(path="/{id}/edu", method= RequestMethod.GET)
//    public String getStudentByIdFromPath(Model model, @PathVariable("id") Long id) {
//        Student studentById = studentsService.getStudentById(id);
//        model.addAttribute("studentById", studentById);
//        return "student-by-id";
//    }

//    // http://localhost:8189/students/showStudentById?id=2
//    @RequestMapping(path = "/showStudentById", method = RequestMethod.GET)
//    public String showStudentById(Model model, @RequestParam Long id) {
//        Student student = studentsService.getStudentById(id);
//        model.addAttribute("student", student);
//        return "student-by-id";
//    }

//    // http://localhost:8189/students/showStudents
//    @RequestMapping("/showStudents")
//    public String showStudents(Model model) {
//        List<Long> studentIdList = studentCourseService.getAllStudents();
//        List<Student> studentList = new ArrayList<>();
//        for (int i = 0; i < studentIdList.size(); i++) {
//            studentList.add(studentsService.getStudentById(studentIdList.get(i)));
//        }
//        model.addAttribute("studentList", studentList);
//        return "students-list";
//    }

//    @RequestMapping("/showForm")
//    public String showSimpleForm(Model model) {
//        Student student = new Student();
//        model.addAttribute("student", student);
//        return "student-form";
//    }
//
//    @RequestMapping("/processForm")
//    public String processForm(@ModelAttribute("student") Student student) {
//        System.out.println(student.getFirstName() + " " + student.getLastName());
//        return "student-form-result";
//    }
//
//    @RequestMapping(path="/getStudentById", method= RequestMethod.GET)
//    @ResponseBody
//    public Student getStudentById(@RequestParam int id) {
//        Student student = studentsService.getStudentById(new Long(id));
//        return student;
//    }
//
//    @RequestMapping(path="/getStudentById/{sid}", method= RequestMethod.GET)
//    @ResponseBody
//    public Student getStudentByIdFromPath(@PathVariable("sid") int id) {
//        Student student = studentsService.getStudentById(new Long(id));
//        return student;
//    }
}
