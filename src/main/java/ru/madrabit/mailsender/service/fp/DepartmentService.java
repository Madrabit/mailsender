package ru.madrabit.mailsender.service.fp;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;
import ru.madrabit.mailsender.consts.TypeOfOrganisation;
import ru.madrabit.mailsender.model.CountedDepartment;
import ru.madrabit.mailsender.model.DepartmentToFront;
import ru.madrabit.mailsender.model.OrgType;
import ru.madrabit.mailsender.repository.fp.CountedDepartmentRepository;

import java.util.*;

@Service
public class DepartmentService {

    CountedDepartmentRepository repository;
    TypeOfOrganisation orgTypes;

    public DepartmentService(CountedDepartmentRepository repository, TypeOfOrganisation orgTypes) {
        this.repository = repository;
        this.orgTypes = orgTypes;
    }

    public List<DepartmentToFront> findAll() {
        final List<CountedDepartment> departments = repository.findAllDeps();
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
                        depTypeNumber == orgTypes.BANKS_BRANCHES ? department.getAmount() : 0L
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
}
