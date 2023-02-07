package todoApp;

import controller.ProjectController;
import model.Project;
import util.ConnectionFactory;
import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ProjectController projectController = new ProjectController();

        Project project = new Project();
        project.setName("Projeto teste");
        project.setDescription("description");
        projectController.save(project);


    }
}
