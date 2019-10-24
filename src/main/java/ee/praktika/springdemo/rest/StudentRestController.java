package ee.praktika.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ee.praktika.springdemo.entity.Student;

@RestController
@RequestMapping( "/api" )
public class StudentRestController {

    private List<Student> theStudents;

    //define @PostConstruct to load the student data
    @PostConstruct
    public void loadData(){

        theStudents = new ArrayList<>();

        theStudents.add( new Student( "Mario", "Mario" ) );
        theStudents.add( new Student( "Luigi", "Mario" ) );
        theStudents.add( new Student( "Toad", "Mushroom" ) );
    }

    //need to define an endpoint to /students - for the get request : shall return all of the students we have.
    @GetMapping( "/students" )
    public List<Student> getStudents(){

        return theStudents;
    }

    //define endpoint for "/student/{studentId}" - return student at index
    @GetMapping( "/student/{studentId}" )
    public Student getStudent( @PathVariable int studentId ){

        //just index into the list -  keep things simple.
        return theStudents.get( studentId );
    }

}
