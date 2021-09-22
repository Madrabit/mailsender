package ru.madrabit.mailsender.repository.fp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.madrabit.mailsender.model.CountedDepartment;

import java.util.List;

@SpringBootTest
class CountedDepartmentRepositoryTest {

    @Autowired
    private CountedDepartmentRepository repository;

    @Test
    void findAllDeps() {
        final List<CountedDepartment> list = repository.findAllDeps();
        for (CountedDepartment countedDepartment : list) {
            System.out.println(
                    countedDepartment.getDepNumber() + " : "
                            + countedDepartment.getDepType() + " : "
                            + countedDepartment.getAmount()
            );
        }
    }
}
