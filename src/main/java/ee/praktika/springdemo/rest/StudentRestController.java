package ee.praktika.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        //check the studend ID against list size
        if( ( studentId >= theStudents.size() ) || ( studentId < 0 ) ) {
            throw new StudentNotFoundException( "Student id not found - " + studentId );
        }
        return theStudents.get( studentId );
    }

    //Add an exception handler to this given application
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException( StudentNotFoundException exc ){

        //create the Student Error responst
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus( HttpStatus.NOT_FOUND.value() );
        error.setMessage( exc.getMessage() );
        error.setTimeStamp( System.currentTimeMillis() );

        // return the response entity
        return new ResponseEntity<>( error, HttpStatus.NOT_FOUND );
    }

    //Add an additional exception handler for any exception
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException( Exception exc ){

        //create the Student Error responst
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus( HttpStatus.BAD_REQUEST.value() );
        error.setMessage( exc.getMessage() );
        error.setTimeStamp( System.currentTimeMillis() );

        // return the response entity
        return new ResponseEntity<>( error, HttpStatus.BAD_REQUEST );
    }
}
