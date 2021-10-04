package ru.madrabit.mailsender.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.madrabit.mailsender.consts.TypeOfOrganisation;
import ru.madrabit.mailsender.model.CountedDepartment;
import ru.madrabit.mailsender.model.DepartmentToFront;
import ru.madrabit.mailsender.repository.fp.CountedDepartmentRepository;
import ru.madrabit.mailsender.service.DepartmentService;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final CountedDepartmentRepository repository;

    @Override
    public List<DepartmentToFront> findAll() {
        final List<CountedDepartment> departments = repository.findAllDeps();
        Map<Integer, DepartmentToFront> deps = new TreeMap<>();
        for (CountedDepartment department : departments) {
            final Float depTypeNumber = department.getDepType();
            if (deps.get(department.getDepNumber()) == null) {
                deps.put(department.getDepNumber(), new DepartmentToFront(
                        department.getDepName().toLowerCase(Locale.ROOT),
                        department.getDepNumber(),
                        depTypeNumber == TypeOfOrganisation.SNG ? department.getAmount() : 0L,
                        depTypeNumber == TypeOfOrganisation.BANKS ? department.getAmount() : 0L,
                        depTypeNumber == TypeOfOrganisation.NFO ? department.getAmount() : 0L,
                        depTypeNumber == TypeOfOrganisation.BANKS_BRANCHES ? department.getAmount() : 0L
                ));
            } else {
                final DepartmentToFront depObject = deps.get(department.getDepNumber());
                if (depTypeNumber == TypeOfOrganisation.SNG) {
                    depObject.setAmountSng(department.getAmount());
                }
                if (depTypeNumber == TypeOfOrganisation.BANKS) {
                    depObject.setAmountBanks(department.getAmount());
                }
                if (depTypeNumber == TypeOfOrganisation.NFO) {
                    depObject.setAmountNfo(department.getAmount());
                }
                if (depTypeNumber == TypeOfOrganisation.BANKS_BRANCHES) {
                    depObject.setAmountBanksBranches(department.getAmount());
                }
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
