package eminimki.com.JTS.Business.Services;

import eminimki.com.JTS.Business.requests.EmployeeRequests.CreateEmployeeRequest;
import eminimki.com.JTS.Business.requests.EmployeeRequests.UpdateEmployeeRequest;
import eminimki.com.JTS.Business.responses.Employee.GetAllEmployeeResponse;
import eminimki.com.JTS.Business.responses.Employee.GetByIdEmployeeResponse;
import eminimki.com.JTS.Business.responses.Employee.GetEmployeeByUsername;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeService extends UserDetailsService {
}
