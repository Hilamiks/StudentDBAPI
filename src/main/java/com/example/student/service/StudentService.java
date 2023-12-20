package com.example.student.service;

import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public Object getStudents() {
		return studentRepository.findAll();
	}
	public Object getStudents1() {
		return List.of(
				new Student(
						1L,
						"John Doe",
						"johndoe@gmail.com",
						LocalDate.of(1997,3,16)
				)
		);
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository
				.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long id) {
		if (!studentRepository.existsById(id)) {
			throw new IllegalStateException("no student with this id");
		}
		studentRepository.deleteById(id);
	}

	@Transactional
	public void updateStudent(Long id, String name, String email) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException(
						"student doesn't exist"));
		if (name != null &&
				!name.isEmpty() &&
				!Objects.equals(student.getName(),name)) {
			student.setName(name);
		}

		if (email != null &&
				!email.isEmpty() &&
				!Objects.equals(student.getEmail(),email)) {
			Optional<Student> studentOptional = studentRepository
					.findStudentByEmail(email);
			if (studentOptional.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			student.setEmail(email);
		}
	}
}
