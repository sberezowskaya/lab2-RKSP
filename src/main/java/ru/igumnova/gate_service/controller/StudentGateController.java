package ru.igumnova.gate_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.igumnova.gate_service.api.StudentGateApi;
import ru.igumnova.gate_service.model.StudentGateCreateRequest;
import ru.igumnova.gate_service.model.StudentGateResponse;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")

    public class StudentGateController implements StudentGateApi {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<StudentGateResponse> createStudent(StudentGateCreateRequest request) {
        String url = "http://localhost:8083/students";

        // Отправляем POST запрос в data-service
        StudentGateResponse response = restTemplate.postForObject(
                url,
                request,
                StudentGateResponse.class
        );

        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<StudentGateResponse> getStudentById(Long id) {
        String url = "http://localhost:8083/students/" + id;

        try {
            // Отправляем GET запрос в data-service
            StudentGateResponse response = restTemplate.getForObject(
                    url,
                    StudentGateResponse.class
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Если студент не найден, возвращаем 404
            return ResponseEntity.status(404).build();
        }
    }
}
