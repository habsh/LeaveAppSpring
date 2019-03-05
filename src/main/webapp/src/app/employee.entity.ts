import { Leave } from './leave.entity';

export class Employee {


    empId: number;
    empName: string;
    empPhone: number;
    empDept: string;
    empMail: string;
    empDoj: Date;
    leaveBalance: number;
    empMngId: number;


    leaves: Array<Leave>
}