package dac.cma.dao;

import dac.cma.model.Teacher;
import dac.cma.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDAO {
    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher findTeacherByUsername(String username) {
        for (Teacher teacher : teacherRepository.findAll()) {
            if (teacher.getUsername().equals(username)) {
                return teacherRepository.findOne(teacher.getId());
            }
        }
        return null;
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

}
