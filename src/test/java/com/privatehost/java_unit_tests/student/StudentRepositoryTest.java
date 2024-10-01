package com.privatehost.java_unit_tests.student;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @Nested
    class studentRepository {

        Student student;

        @BeforeEach
        void setUp() {
            student = Student.builder()
                .name("Maria")
                .email("maria@gmail.com")
                .gender(Gender.FEMALE)
                .build();
        }

        @AfterEach
        void tearDown() {
            repository.deleteAll();
        }

        @Test
        void itShouldCheckIfStudentEmailsExists() {
            repository.save(student);
            Boolean expected = repository.selectExistsEmail(student.getEmail());
            Assertions.assertThat(expected).isTrue();
        }

        @Test
        void itShouldCheckIfStudentEmailDoesNotExists() {
            Boolean expected = repository.selectExistsEmail(student.getEmail());
            Assertions.assertThat(expected).isFalse();
        }
    }

}