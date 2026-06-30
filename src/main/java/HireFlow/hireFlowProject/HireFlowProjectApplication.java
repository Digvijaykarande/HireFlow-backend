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
===============================================================================
                             H I R E F L O W   A P I
===============================================================================

Base URL:
http://localhost:8080/api

===============================================================================
AUTHENTICATION
===============================================================================

POST    /api/auth/register              Register new user
POST    /api/auth/login                 Login user

===============================================================================
USER APIs
===============================================================================

GET     /api/users                      Get all users
GET     /api/users/me                   Get logged-in user
GET     /api/users/{userId}             Get user by ID

POST    /api/users                      Create user
PUT     /api/users/profile              Update profile
DELETE  /api/users/{userId}             Delete user

===============================================================================
COMPANY APIs
===============================================================================

POST    /api/companies                  Create company

Example Body:
{
    "name": "TechNova",
    "description": "Software Company",
    "industry": "IT",
    "location": "Pune",
    "companySize": 150
}

GET     /api/companies                  Get all companies
GET     /api/companies/{companyId}      Get company by ID
PUT     /api/companies/{companyId}      Update company
DELETE  /api/companies/{companyId}      Delete company

===============================================================================
JOB APIs
===============================================================================

POST    /api/jobs                       Create job

Example Body:
{
    "companyId": "685abc123",
    "title": "Java Backend Developer",
    "description": "Spring Boot Developer",
    "skills": ["Java", "Spring Boot", "MongoDB"],
    "location": "Pune",
    "salaryMin": 600000,
    "salaryMax": 1200000,
    "status": "OPEN"
}

GET     /api/jobs                       Get all jobs
GET     /api/jobs/{jobId}               Get job by ID
PUT     /api/jobs/{jobId}               Update job
DELETE  /api/jobs/{jobId}               Delete job

GET     /api/jobs/search?keyword=java   Search jobs
GET     /api/jobs/location/{location}   Filter by location
GET     /api/jobs/status/{status}       Filter by status

===============================================================================
APPLICATION APIs
===============================================================================

POST    /api/applications               Apply for a job

Example Body:
{
    "jobId": "685abc123",
    "coverLetter": "I am interested",
    "resumeUrl": "resume.pdf"
}

GET     /api/applications/my                    My applications
GET     /api/applications/job/{jobId}           Applications for a job (Recruiter)
PUT     /api/applications/{applicationId}/status Update application status

===============================================================================
INTERVIEW APIs
===============================================================================

GET     /api/interviews/my              My interviews
GET     /api/interviews/job/{jobId}     Interviews for job (Recruiter)

PUT     /api/interviews/{id}/complete   Mark interview completed
PUT     /api/interviews/{id}/cancel     Cancel interview

===============================================================================
FILE UPLOAD APIs
===============================================================================

POST    /api/upload/resume              Upload resume
POST    /api/upload/profile-picture     Upload profile picture
POST    /api/upload/company-logo        Upload company logo

===============================================================================
DASHBOARD APIs
===============================================================================

GET     /api/dashboard/recruiter


Returns:
- Total Jobs
- Active Jobs
- Total Applications
- Scheduled Interviews
- Total Hires

GET     /api/dashboard/candidate

Returns:
- Jobs Applied
- Interviews
- Offers
- Saved Jobs
- Recent Applications

===============================================================================
SAVED JOB APIs
===============================================================================

POST    /api/saved-jobs/{jobId}         Save job
GET     /api/saved-jobs                 Get saved jobs
DELETE  /api/saved-jobs/{jobId}         Remove saved job

===============================================================================
AUTHENTICATION
===============================================================================

Except:

POST    /api/auth/register
POST    /api/auth/login

All APIs require:

Authorization: Bearer <JWT_TOKEN>

===============================================================================
ROLES
===============================================================================


| Method | Endpoint                          | Description                   |
| ------ | --------------------------------- | ----------------------------- |
| GET    | `/api/notifications`              | Get my notifications          |
| GET    | `/api/notifications/unread-count` | Get unread notification count |
| PUT    | `/api/notifications/{id}/read`    | Mark notification as read     |
| DELETE | `/api/notifications/{id}`         | Delete notification           |



===============================================================================
Notifications
===============================================================================

GET     /api/notifications

GET     /api/notifications/unread-count

PUT     /api/notifications/{id}/read

PUT     /api/notifications/read-all

DELETE  /api/notifications/{id}

DELETE  /api/notifications

for frontend : npm install sockjs-client stompjs
===============================================================================
ROLES
===============================================================================

Candidate
- Manage profile
- Apply for jobs
- Save jobs
- View interviews

Recruiter
- Manage companies
- Manage jobs
- View applications
- Schedule interviews

Admin
- Full access (if implemented)

===============================================================================
HTTP STATUS CODES
===============================================================================

200  OK
201  Created
400  Bad Request
401  Unauthorized
403  Forbidden
404  Not Found
500  Internal Server Error

===============================================================================
*/
