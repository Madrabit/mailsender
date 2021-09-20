package ru.madrabit.mailsender.service.fp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.madrabit.mailsender.consts.TypeOfOrganisation;
import ru.madrabit.mailsender.model.CountedDepartment;
import ru.madrabit.mailsender.model.DepartmentToFront;
import ru.madrabit.mailsender.model.OrgType;
import ru.madrabit.mailsender.repository.fp.CountedDepartmentRepository;
import springfox.documentation.annotations.Cacheable;

import java.util.*;

@Service
@Slf4j
public class DepartmentService {

    private CountedDepartmentRepository repository;
    private TypeOfOrganisation orgTypes;
    private RedisTemplate<String, String> redisTemplate;

    public DepartmentService(CountedDepartmentRepository repository,
                             TypeOfOrganisation orgTypes,
                             RedisTemplate<String, String> redisTemplate) {
        this.repository = repository;
        this.orgTypes = orgTypes;
        this.redisTemplate = redisTemplate;
    }

    public List<DepartmentToFront> findAll() {
        final List<CountedDepartment> departments = repository.findAllDeps();
        final String orgTypesFromCache = (String) redisTemplate.opsForHash().get("orgTypes", "orgTypes");
        Map<Integer, DepartmentToFront> deps = new TreeMap<>();
        for (CountedDepartment department : departments) {
            final Float depTypeNumber = department.getDepType();
            if (deps.get(department.getDepNumber()) == null) {
                deps.put(department.getDepNumber(), new DepartmentToFront(
                        department.getDepName().toLowerCase(Locale.ROOT),
                        department.getDepNumber(),
                        depTypeNumber == orgTypes.SNG ? department.getAmount() : 0L,
                        depTypeNumber == orgTypes.BANKS ? department.getAmount() : 0L,
                        depTypeNumber == orgTypes.NFO ? department.getAmount() : 0L,
                        depTypeNumber == orgTypes.BANKS_BRANCHES ? department.getAmount() : 0L,
                        false
                ));
            } else {
                final DepartmentToFront depObject = deps.get(department.getDepNumber());
                if (depTypeNumber == orgTypes.SNG) depObject.setAmountSng(department.getAmount());
                if (depTypeNumber == orgTypes.BANKS) depObject.setAmountBanks(department.getAmount());
                if (depTypeNumber == orgTypes.NFO) depObject.setAmountNfo(department.getAmount());
                if (depTypeNumber == orgTypes.BANKS_BRANCHES) depObject.setAmountBanksBranches(department.getAmount());
                deps.put(department.getDepNumber(), depObject);
            }
        }
        List<DepartmentToFront> finalList = new ArrayList<>();
        for (Map.Entry<Integer, DepartmentToFront> entry : deps.entrySet()) {
            finalList.add(entry.getValue());
        }
        return finalList;
    }

    private boolean isChecked() {
       return false;
    }


}
