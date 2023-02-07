package org.example;

import controller.ProjectController;
import model.Project;

public class Main {
    public static void main(String[] args) {
        ProjectController projectController = new ProjectController();

        Project project = new Project();
        project.setName("Projeto teste");
        project.setDescription("description");
        projectController.save(project);
    }
}