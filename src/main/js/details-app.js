function Employee(id, firstName, lastName, email, mobileNumber, dateJoined, department, leaveBal, picture) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.mobileNumber = mobileNumber;
    this.dateJoined = dateJoined;
    this.department = department;
    this.leaveBal = leaveBal;
    this.picture = picture;
}

var tom = new Employee("1848", "Tom", "Landry", "tlandry@hexaware.com", "703-843-2122", "6-3-1990", "sales", "5", "placeholder");

document.table([tom]);