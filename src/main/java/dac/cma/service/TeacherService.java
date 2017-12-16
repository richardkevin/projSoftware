package dac.cma.service;

import dac.cma.dao.TeacherDAO;
import dac.cma.model.Student;
import dac.cma.model.Teacher;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherDAO teacherDAO;

    public Teacher login(String username, String password) {
        Teacher t = teacherDAO.findTeacherByUsername(username);

        if (t != null && t.getPassword().equals(password)) {
            return t;
        }
        return null;
    }

    public void addTeacher(Teacher teacher) {
        teacherDAO.addTeacher(teacher);
    }

    public void assignScore(float nota) {
        
    }

    public void associteStudent(Student student) {
        
    }

    public void scheduleExaminingBoard() {
//        dia, hora, Banca banca
    }

    public void inviteMember(Teacher teacher) {
        
    }

    public boolean confirmInvite(Boolean resposta) {
        return true;
    }

    public void registerProject() {
        
    }

    public List<Teacher> getAllTeachers() {
        Teacher admin = teacherDAO.findTeacherByUsername("Admin");
        List<Teacher> teachers = teacherDAO.getAllTeachers();
        teachers.remove(admin);
        return teachers;
    }
}
