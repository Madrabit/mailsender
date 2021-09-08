package ru.madrabit.mailsender.service.fp;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.madrabit.mailsender.consts.TypeOfOrganisation;
import ru.madrabit.mailsender.model.CountedDepartment;
import ru.madrabit.mailsender.model.DepartmentToFront;
import ru.madrabit.mailsender.repository.fp.CountedDepartmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DepartmentServiceTest {


    @Autowired
    private DepartmentService service;

    @Test
    void findAll() {
        final List<DepartmentToFront> all = service.findAll();
        for (DepartmentToFront toFront : all) {
            System.out.println(
                    toFront.getDepNumber()  + " : "
                    + toFront.getDepName() + ", "
                            + toFront.getAmountBanks() + ", "
                            + toFront.getAmountBanksBranches() + ", "
                            + toFront.getAmountNfo() + ", "
                            + toFront.getAmountSng()
            );
        }
    }
}
