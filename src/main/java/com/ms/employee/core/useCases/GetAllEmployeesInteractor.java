package com.ms.employee.core.useCases;

import com.ms.employee.core.domain.Employee;
import com.ms.employee.core.exceptions.EmployeeNotFound;
import com.ms.employee.core.gateways.EmployeeGateways;

import java.util.List;

public class GetAllEmployeesInteractor extends BaseEmployeeInteractor{
    public GetAllEmployeesInteractor(EmployeeGateways gateway) {
        super(gateway);
    }

    public List<Employee> execute() throws EmployeeNotFound
    {
        List<Employee> listEmp = gateway.getAll();
        if (listEmp == null || listEmp.isEmpty()) throw new EmployeeNotFound();
        return listEmp;
    }
}
