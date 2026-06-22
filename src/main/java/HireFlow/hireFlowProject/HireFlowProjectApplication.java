package HireFlow.hireFlowProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HireFlowProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HireFlowProjectApplication.class, args);
	}

}


/*
 
 POST /api/auth/register
 POST /api/auth/login
 GET /api/users
 GET /api/users/{userId}       Get User By Id
 
 POST /api/users			   Create User
 
 
 PUT /api/users					update user
 DELETE /api/users/{userId} 	delete user
 
 
 POST /api/companies 			create company
 {
  "name":"TechNova",
  "description":"Software Company",
  "industry":"IT",
  "location":"Pune",
  "companySize":150
}

GET /api/companies 				get all companies
GET /api/companies/{companyId}
PUT /api/companies/{companyId}
DELETE /api/companies/{companyId}



POST /api/jobs					create job
{
  "companyId":"685abc123",
  "title":"Java Backend Developer",
  "description":"Spring Boot Developer",
  "skills":["Java","Spring Boot","MongoDB"],
  "location":"Pune",
  "salaryMin":600000,
  "salaryMax":1200000,
  "status":"OPEN"
}


GET /api/jobs
GET /api/jobs/{jobId}
PUT /api/jobs/{jobId}
DELETE /api/jobs/{jobId}
GET /api/jobs/search?keyword=java
GET /api/jobs/location/{location}
GET /api/jobs/status/{status}


POST /api/applications 				 apply for job
{
  "jobId":"685abc123",
  "coverLetter":"I am interested",
  "resumeUrl":"resume.pdf"
}

GET /api/applications/my 				view applied jobs
GET /api/applications/job/{jobId}  (only recruiter can see)
PUT /api/applications/{applicationId}/status


GET /api/interviews/my         
GET /api/interviews/job/{jobId}  (only recruiter can see)
PUT /api/interviews/{id}/complete
PUT /api/interviews/{id}/cancel

 */

