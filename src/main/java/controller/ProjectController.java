package controller;


import model.Project;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectController {

    public void save(Project project) {
        String sql = "INSERT INTO projects (name," +
                "description," +
                "createdAt," +
                "updatedAt) VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Estabelecendo a conexao com o banco de dados
            connection = ConnectionFactory.getConnection();

            //Preparando a query
            statement = connection.prepareStatement(sql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));

            //Executando a query
            statement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar o projeto " + ex.getMessage());
        }finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Project project) {
        String sql = "UPDATE projects SET " +
                "name = ?, " +
                "description = ?, " +
                "createdAt = ?," +
                "updatedAt = ?" +
                "WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());

        }catch (Exception ex) {

            throw new RuntimeException("Erro ao atualizar a tarefa " + ex.getMessage());

        }
    }

    public void removeById(int projectId) {
        String sql = "DELETE FROM projects WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, projectId);

            statement.execute();
        }catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar a tarefa " + ex.getMessage());
        }finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Project> getAll() {
        String sql = "SELECT * FROM projects";

        List<Project> projects = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Project project = new Project();

                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));

                //Adiciona o contato recuperado a lista de contatos
                projects.add(project);
            }
        }catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar os projetos", ex);

        }finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        return null;
    }

}
