package ru.madrabit.mailsender.service;

import ru.madrabit.mailsender.model.DepartmentToFront;

import java.util.List;

public interface DepartmentService {

    List<DepartmentToFront> findAll();

}
