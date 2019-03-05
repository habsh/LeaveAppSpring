import { Employee } from "./employee.entity";

export class Leave {

	leaveID: number;

	employee: Employee;
	
	startDate: Date;
	endDate: Date;
	numDays: number;
	leaveType: string;
	reasons: string;
	managerComments: string;
	appliedOn: Date;
	leaveStatus: string;
}