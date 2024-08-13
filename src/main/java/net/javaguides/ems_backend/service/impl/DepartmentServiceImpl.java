package net.javaguides.ems_backend.service.impl;

import net.javaguides.ems_backend.dto.DepartmentDto;
import net.javaguides.ems_backend.entity.Department;
import net.javaguides.ems_backend.exception.ResourceNotFoundException;
import net.javaguides.ems_backend.mapper.DepartmentMapper;
import net.javaguides.ems_backend.repository.DepartmentRepository;
import net.javaguides.ems_backend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = Department.mapToDepartment(departmentDto);
        department = departmentRepository.save(department);

        departmentDto.setId(department.getId()); // Set the ID from the saved entity
        return departmentDto;
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {

        Department department=departmentRepository.findById(departmentId).orElseThrow(
                ()->new ResourceNotFoundException("Department is not exists with a given id: "+departmentId)
        );

        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {

        List<Department>departments=departmentRepository.findAll();
        return departments.stream().map(DepartmentMapper::mapToDepartmentDto)
                .collect(Collectors.toList());

    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepatment) {

        Department department=departmentRepository.findById(departmentId).orElseThrow(
                ()->new ResourceNotFoundException("Department is not exists with a given id: "+departmentId)
        );
        department.setDepartmentName(updatedDepatment.getDepartmentName());
        department.setDepartmentName(updatedDepatment.getDepartmentDescription());

        Department savedDepartment=departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(savedDepartment);

    }
}
