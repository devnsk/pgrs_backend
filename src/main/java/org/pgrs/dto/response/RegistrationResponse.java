package org.pgrs.dto.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse  {
	private String studentFirstName;
	private String studentParentFirstName;
	private String message;

//	public RegistrationResponse(String firstName, String firstName2, String string) {
//		// TODO Auto-generated constructor stub
//		this.studentFirstName = firstName;
//		this.studentParentFirstName = firstName2;
//		this.message = string;
//	}
	public String getStudentFirstName() {
		return studentFirstName;
	}
	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}
	public String getStudentParentFirstName() {
		return studentParentFirstName;
	}
	public void setStudentParentFirstName(String studentParentFirstName) {
		this.studentParentFirstName = studentParentFirstName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
