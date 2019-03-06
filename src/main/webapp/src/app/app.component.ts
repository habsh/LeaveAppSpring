import { Component } from '@angular/core';
import { Employee } from './employee.entity';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'My Details Section';
  employee = new Employee();
}
//CREATE TABLE `EMPLOYEE` (  `EMP_ID` int(11),  `EMP_NAME` char(20) NOT NULL,  `EMP_PHONE` bigint(20) DEFAULT NULL,  `EMP_DEPT` varchar(20), 
// `EMP_MAIL` char(40) DEFAULT NULL,  `EMP_DOJ` date NOT NULL,  `LEAVE_BALANCE` int(11) NOT NULL,  `EMP_MNG_ID` int(11) DEFAULT NULL,  PRIMARY KEY (`EMP_ID`), 
// UNIQUE KEY `EMP_MAIL` (`EMP_MAIL`)) ;


