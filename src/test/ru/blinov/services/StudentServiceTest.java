//package ru.blinov.services;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.BDDMockito;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import ru.blinov.entities.Student;
//import ru.blinov.repositories.StudentsRepository;
//
//@RunWith(MockitoJUnitRunner.Silent.class)
//public class StudentServiceTest {
//
//    @Mock
//    private StudentsRepository studentsRepository;
//
//    @InjectMocks
//    private StudentsService service = new StudentsService();
//
//    @Test
//    public void testGetStudentById() {
//        BDDMockito.given(studentsRepository.findOneById(10L)).willReturn(new Student());
//    }
//}
