package dac.cma.service;

import dac.cma.model.Project;
import dac.cma.repository.ProjectRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllStudentProjects(Long id) {
        List<Project> allProjects = new ArrayList<>();
        for (Project p : projectRepository.findAll()) {
            if (p.getStudent().getId() == id) {
                allProjects.add(p);
            }
        }
        return allProjects;
    }
}
