package dac.cma.service;

import dac.cma.dao.StudentDAO;
import dac.cma.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentDAO studentDAO;

    public Student efetuaLogin(String username, String password){
        Student s = studentDAO.findStudentByUsername(username);

        if (s != null && s.getPassword().equals(password)) {
            return s;
        }
        return null;
    }

    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }
}
