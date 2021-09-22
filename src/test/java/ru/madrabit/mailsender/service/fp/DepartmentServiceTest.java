package ru.madrabit.mailsender.service.fp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import ru.madrabit.mailsender.model.DepartmentToFront;

import java.util.List;
import java.util.Objects;

@SpringBootTest
class DepartmentServiceTest {


    @Autowired
    private DepartmentService service;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void findAll() {
        final List<DepartmentToFront> all = service.findAll();
        for (DepartmentToFront toFront : all) {
            System.out.println(
                    toFront.getDepNumber() + " : "
                            + toFront.getDepName() + ", "
                            + toFront.getAmountBanks() + ", "
                            + toFront.getAmountBanksBranches() + ", "
                            + toFront.getAmountNfo() + ", "
                            + toFront.getAmountSng()
            );
        }
    }

    @Test
    void testRedis() {
        final String depsFromCache = (String) redisTemplate.opsForHash().get("deps", "deps");
        final String[] split = Objects.requireNonNull(depsFromCache).split(",");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
