package ru.madrabit.mailsender.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.madrabit.mailsender.dto.EmployeeDTO;
import ru.madrabit.mailsender.model.Employee;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "depName", source = "department.depName")
    @Mapping(target = "departmentNumber", source = "department.departmentNumber")
    EmployeeDTO toDto(Employee employee);
}
