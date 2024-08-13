package net.javaguides.ems_backend.service;

import net.javaguides.ems_backend.dto.DepartmentDto;
import net.javaguides.ems_backend.entity.Department;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(Long departmentId);

    List<DepartmentDto> getAllDepartments();
    DepartmentDto updateDepartment(Long departmentId,DepartmentDto updatedDepatment);

}
