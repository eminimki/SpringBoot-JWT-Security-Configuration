package eminimki.com.JTS.Business.Manager;

import eminimki.com.JTS.Business.Rules.EmployeeBusinessRules;
import eminimki.com.JTS.Business.Services.EmployeeService;
import eminimki.com.JTS.Business.Services.LogService;
import eminimki.com.JTS.Business.requests.EmployeeRequests.CreateEmployeeRequest;
import eminimki.com.JTS.Business.requests.EmployeeRequests.UpdateEmployeeRequest;
import eminimki.com.JTS.Business.responses.Employee.GetAllEmployeeResponse;
import eminimki.com.JTS.Business.responses.Employee.GetByIdEmployeeResponse;
import eminimki.com.JTS.Business.responses.Employee.GetEmployeeByUsername;
import eminimki.com.JTS.Common.Utilities.Mapper.ModelMapperService;
import eminimki.com.JTS.DataAccess.EmployeeRepository;
import eminimki.com.JTS.Entities.Employee;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapperService modelMapperService;
    private EmployeeBusinessRules employeeBusinessRules;
    private BCryptPasswordEncoder passwordEncoder;
    private LogService logService;



    //Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> user = employeeRepository.findByUsername(username);
        Employee employee = user.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new EmployeeDetails(employee); // Employee nesnesini EmployeeDetails'e dönüştür
    }


}
