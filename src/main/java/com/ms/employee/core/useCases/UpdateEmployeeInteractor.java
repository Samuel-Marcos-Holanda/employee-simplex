package com.ms.employee.core.useCases;

import com.ms.employee.core.DTO.EmployeeRequestDTO;
import com.ms.employee.core.domain.Employee;
import com.ms.employee.core.exceptions.others.BadUpdateException;
import com.ms.employee.core.gateways.EmployeeGateways;

public class UpdateEmployeeInteractor extends BaseEmployeeInteractor {

    public UpdateEmployeeInteractor(EmployeeGateways gateway) {
        super(gateway);
    }
    
    public Employee execute(String id, EmployeeRequestDTO employeeDTO) throws Exception
    {
        Employee emp = gateway.updateEmployee(id, employeeDTO);
        if (emp == null) throw new BadUpdateException();
        return emp;
    }
}
