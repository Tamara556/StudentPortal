package com.example.studentPortal.service;

import com.example.studentPortal.db.DBConnectionProvider;
import com.example.studentPortal.model.Lesson;
import com.example.studentPortal.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServise {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private LessonService lessonService = new LessonService();

    public void add(Student student) {
        String sql = "INSERT INTO student(name,surname,email,phone,lesson_id) VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getPhone());
            preparedStatement.setInt(5, student.getLesson().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student";
        List<Student> students = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Student student = Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getString("phone"))
                        .lesson(lessonService.getLessonById(resultSet.getInt("lesson_id")))
                        .build();
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


}
