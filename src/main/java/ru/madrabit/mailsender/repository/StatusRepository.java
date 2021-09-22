package ru.madrabit.mailsender.repository;

import org.springframework.data.repository.CrudRepository;
import ru.madrabit.mailsender.model.SeminarStatus;

import java.util.List;

public interface StatusRepository extends CrudRepository<SeminarStatus, Integer> {
    List<SeminarStatus> findAll();
}
