package dac.cma.dao;

import dac.cma.model.Student;
import dac.cma.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO {
    @Autowired
    private StudentRepository studentRepository;

    public Student findStudentByUsername(String username) {
        for (Student student : studentRepository.findAll()) {
            if (student.getUsername().equals(username)) {
                return studentRepository.findOne(student.getId());
            }
        }
        return null;
    }
}
