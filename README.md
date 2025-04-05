
# 📌 Grievance System - Backend

The Parent-Student Grievance System is a Spring Boot–based backend application designed to streamline communication between students, parents, and educational institutions regarding academic and administrative issues.

This backend system provides secure APIs for registering users (students and parents), submitting grievances, tracking their status, and enabling administrators or authorities to manage and resolve issues efficiently.

It is built following RESTful principles and integrates with a relational database (e.g., PostgreSQL) for persistent data management.


## 👨‍💻 Authors

- [devnsk](https://github.com/devnsk)
- [dibya-prakash-bal](https://github.com/dibya-prakash-bal)
- [mukteswar](https://github.com/mukteswarAI)
- [Jeet-Codes](https://github.com/Jeet-Codes)


## ✨ Key Features

#### 🔐 User Registration & Authentication
- Supports registration for students and parents.
- Secure password handling and role-based access control.

#### 📝 Grievance Submission
- Students/parents can lodge grievances.
- Grievances include type, description, and metadata.

#### 📊 Grievance Management
- Admins or assigned authorities can view, respond to, and resolve grievances.
- Track grievance status: `Pending`, `In Progress`, `Resolved`.

#### 📬 Notification Ready
- Easily extendable for email/SMS notifications upon status change or resolution.

#### 📚 RESTful APIs
- Clean, well-structured API endpoints for easy frontend integration.

#### 🛡️ Security
- JWT-based authentication (optional extension).
- Spring Security configuration for endpoint protection.

## 📘 Entity Relationship Diagram (ERD)

The diagram below represents the relationships between key entities like `User`, `Grievance`, and `Role` in the system:

![ER Diagram](https://github.com/user-attachments/assets/9db4aa64-da45-4e41-bffc-5ee3956092fa)
## 📚 API Reference

### 🧑‍🎓 Student & Parent Registration API

**Endpoint:**  
`POST http://localhost:8088/api/students/register`

**Request Body:**

```json
{
  "studentFirstName": "debasis",
  "studentLastName": "Doe",
  "studentEmail": "debasis.doe@example.com",
  "registrationNumber": "CUTM2024004",
  "department": "Computer Science",
  "batch": "2024-2028",
  "dateOfBirth": "2005-06-10",
  "address": "123 Main Street, Bhubaneswar, Odisha - 751024",
  "parentFirstName": "nsk",
  "parentLastName": "Doe",
  "parentEmail": "nsk.doe@example.com",
  "parentContactNumber": "9876543210",
  "relationship": "Father",
  "password": "Test@2024"
}
```
**Response :**

```json
{
  "studentFirstName": "debasis",
  "studentParentFirstName": "nsk",
  "message": "Student and parent registered successfully"
}
