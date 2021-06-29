package br.com.harbitech.school.repository.dao;

import br.com.harbitech.school.course.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CourseDAO {

    private Connection connection;

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Course course) throws SQLException {
        String sql = "INSERT INTO Course (name, code_url,completion_time_in_hours,visibility,target_audience," +
                "instructor,description,developed_skills,subcategory_id)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?,(SELECT id from Subcategory where code_url = ?))";

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, course.getName());
            pstm.setString(2, course.getCodeUrl());
            pstm.setInt(3,course.getCompletionTimeInHours());
            pstm.setString(4, String.valueOf(course.getVisibility()));
            pstm.setString(5,course.getTargetAudience());
            pstm.setString(6,course.getInstructor());
            pstm.setString(7, course.getDescription());
            pstm.setString(8,course.getDevelopedSkills());
            pstm.setString(9, course.getSubCategory().getCodeUrl());
            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    course.setId(rst.getLong(1));
                }
            }
        }
    }

    public void delete(String urlCode) throws SQLException{
        String sql = "DELETE FROM Course WHERE code_url = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstm.setString(1, urlCode);
            pstm.execute();
        }
    }

    public void upgradeToPublicVisibility(){
        String sql = "UPDATE FROM Course WHERE code_url = ?";
    }

}

