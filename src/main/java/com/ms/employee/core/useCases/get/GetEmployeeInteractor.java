package com.ms.employee.core.useCases.get;

import com.ms.employee.core.domain.Employee;
import com.ms.employee.core.exceptions.notFound.EmployeeNotFoundException;
import com.ms.employee.core.gateways.EmployeeGateways;
import com.ms.employee.core.useCases.BaseEmployeeInteractor;

public class GetEmployeeInteractor extends BaseEmployeeInteractor {

    public GetEmployeeInteractor(EmployeeGateways gateway) {
        super(gateway);
    }

    public Employee execute(String id) throws EmployeeNotFoundException
    {
        Employee emp = gateway.getById(id);
        if (emp == null) throw new EmployeeNotFoundException();
        return emp;
    }
}
