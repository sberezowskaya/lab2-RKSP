package ru.igumnova.data_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.igumnova.data_service.api.StudentDataApi;
import ru.igumnova.data_service.entity.Student;
import ru.igumnova.data_service.model.StudentDataCreateRequest;
import ru.igumnova.data_service.model.StudentDataResponse;
import ru.igumnova.data_service.repository.StudentRepository;

@RestController
@RequiredArgsConstructor
public class StudentController implements StudentDataApi {

    private final StudentRepository studentRepository;

    @Override
    public ResponseEntity<StudentDataResponse> createStudentDataInData(StudentDataCreateRequest request) {
        Student student = new Student();
        student.setName(request.getFullName());
        student.setPassport(request.getPassport());

        Student savedStudent = studentRepository.save(student);

        StudentDataResponse response = new StudentDataResponse();
        response.setId(savedStudent.getId());
        response.setFullName(savedStudent.getName());
        response.setPassport(savedStudent.getPassport());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<StudentDataResponse> getStudentDataByIdFromData(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        StudentDataResponse response = new StudentDataResponse();
        response.setId(student.getId());
        response.setFullName(student.getName());
        response.setPassport(student.getPassport());

        return ResponseEntity.ok(response);
    }
}