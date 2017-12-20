package dac.cma.dao;

import dac.cma.model.Project;
import dac.cma.repository.ProjectRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDAO {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllStudentProjects(Long id) {
        List<Project> allProjects = new ArrayList<>();
        for (Project p : projectRepository.findAll()) {
            if (p.getStudent() != null && p.getStudent().getId().equals(id)) {
                allProjects.add(p);
            }
        }
        return allProjects;
    }

    public void save(Project project) {
        projectRepository.save(project);
    }

    public Object getAllTeacherProjects(Long id) {
        List<Project> allProjects = new ArrayList<>();
        for (Project p : projectRepository.findAll()) {
            if (p.getOrientador() != null && p.getOrientador().getId().equals(id)) {
                allProjects.add(p);
            }
        }
        return allProjects;
    }

    public Project findProjectById(long id) {
        return projectRepository.findOne(id);
    }  

    public void delete(Project project) {
        projectRepository.delete(project);
    }

    public List<Project> getActiveProjects() {
        List<Project> projects = new ArrayList<>();
        for (Project project : projectRepository.findAll()) {
            if (project.getStatus() == 1) {
                projects.add(project);
            }
        }
        return projects;
    }

    public void changeStatus(Project project, int status) {
        project.setStatus(status);
        projectRepository.save(project);
    }

}
