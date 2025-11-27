package ru.igumnova.data_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.igumnova.data_service.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}