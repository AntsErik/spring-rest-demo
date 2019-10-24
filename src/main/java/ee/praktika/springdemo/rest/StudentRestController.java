package ee.praktika.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ee.praktika.springdemo.entity.Student;

@RestController
@RequestMapping( "/api" )
public class StudentRestController {

    //need to define an endpoint to /students - for the get request : shall return all of the students we have.
    @GetMapping( "/students" )
    public List<Student> getStudents(){

        List<Student> theStudents = new ArrayList<>();

        theStudents.add( new Student( "Mario", "Mario" ) );
        theStudents.add( new Student( "Luigi", "Mario" ) );
        theStudents.add( new Student( "Toad", "Mushroom" ) );

        return null;
    }

}
