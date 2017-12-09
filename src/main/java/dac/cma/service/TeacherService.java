package dac.cma.service;

import dac.cma.dao.TeacherDAO;
import dac.cma.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherDAO teacherDAO;

    public Teacher efetuaLogin(String username, String password) {
        Teacher t = teacherDAO.findTeacherByUsername(username);

        if (t != null && t.getPassword().equals(password)) {
            return t;
        }
        return null;
    }

}
