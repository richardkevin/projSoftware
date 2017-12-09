package dac.cma.service;

import dac.cma.dao.ProjectDAO;
import dac.cma.model.Project;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectDAO projectDAO;

    public List<Project> getAllStudentProjects(Long id) {
        return projectDAO.getAllStudentProjects(id);
    }

    public void save(Project project) {
        projectDAO.save(project);
    }

    public Object getAllTeacherProjects(Long id) {
        return projectDAO.getAllTeacherProjects(id);
    }
}
