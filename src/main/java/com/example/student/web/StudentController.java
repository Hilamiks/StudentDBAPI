package com.example.student.web;

import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public Object getStudents() {
		return studentService.getStudents();
	}

	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}

	@DeleteMapping(path = "/{studentID}")
	public void deleteStudent(@PathVariable("studentID") Long id) {
		studentService.deleteStudent(id);
	}

	@PutMapping(path = "{studentId}")
	public void updateStudent(
			@PathVariable("studentId") Long id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		studentService.updateStudent(id,name,email);
	}
}
