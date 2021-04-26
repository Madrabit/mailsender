package ru.madrabit.mailsender.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.madrabit.mailsender.model.SeminarStatus;

import java.util.List;

@SpringBootTest
class StatusRepositoryTest {

    @Autowired
    private StatusRepository statusRepository;

    @Test
    @Transactional
    public void testFindByFirstNameAndLastName() {
        final List<SeminarStatus> all = statusRepository.findAll();
        for (SeminarStatus seminarStatus : all) {
            System.out.println(seminarStatus.getStatus());
        }
    }
}
