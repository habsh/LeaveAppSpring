import 

public class EmployeeService() {
    private EmployeeDataService employeeService;

	public ApplyLeaveService(@Qualifier("LeaveDetailsService")EmployeeDataService employeeDataService) {
		this.employeeService = employeeDataService;
	}
}

