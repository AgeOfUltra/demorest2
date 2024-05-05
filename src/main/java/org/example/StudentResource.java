package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.model.Student;
import org.example.repo.StudentRepo;

import java.util.List;

@Path("students")
public class StudentResource {
    StudentRepo repo= new StudentRepo();
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Student> getAllStudents(){
        return repo.getAllStudents();
    }
    @GET
    @Path("student/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Student getStudent(@PathParam("id") int id){
        return repo.getStudentById(id);
    }
    @POST
    @Path("student")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Student addStudent(Student student){
        System.out.println(student.toString());
        repo.insertStudent(student);
        return student;
    }
    @PUT
    @Path("student")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Student updateStudent(Student student){
        System.out.println(student.toString());
        if(repo.getStudentById(student.getId())==null){
            repo.insertStudent(student);
        }else {
            repo.updateStudent(student);
        }
        return student;
    }
    @DELETE
    @Path("student/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Student deleteStudent(@PathParam("id") int id){
        Student student = repo.getStudentById(id);
        if(student.getId()!=0){
            repo.deleteStudentById(student.getId());
        }
        return student;
    }
}
