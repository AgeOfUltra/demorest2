package org.example.repo;

import org.example.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo {
    Connection con  =null;
    public StudentRepo() {
        String url ="jdbc:mysql://localhost:3306/restdb";
        String username = "root";
        String password = "123456789";
        try {
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection(url,username,password) ;
        } catch (SQLException e) {
            System.out.println("Failed to connect to mysql server!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "select * from student";
        try (Statement stmt = con.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setLang(rs.getString("lang"));
                    students.add(student);
                }
            }
        }catch (SQLException e) {
            System.out.println("Error at executing statement");
            System.out.println(e.getMessage());
        }

        return students;
    }
    public Student getStudentById(int id) {
        Student student = null;
        String sql = "select * from student where id = " + id;
        try (Statement stmt = con.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setLang(rs.getString("lang"));
                }
            }
        }catch (SQLException e) {
            System.out.println("Error at executing statement");
            System.out.println(e.getMessage());
        }

        return student;
    }

    public void insertStudent(Student student){
        String query ="Insert into student (id,name,lang) values(?,?,?)";
        try(PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getLang());
            stmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Error at executing statement");
            System.out.println(e.getMessage());
        }
    }
    public void updateStudent(Student student){
        String query ="Update student set name =?, lang =? where id =?";
        try(PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getLang());
            stmt.setInt(3, student.getId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Error at executing statement");
            System.out.println(e.getMessage());
        }
    }
    public void deleteStudentById(int id)  {
        String query = "Delete from student where id = ?";
        try(PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setInt(1,id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error at executing delete statement");
            System.out.println(e.getMessage());
        }
    }
}
