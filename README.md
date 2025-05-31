# Internal Asset Management System (Backend API) 📊

---

## Project Overview

In today's dynamic work environment, especially with hybrid and remote teams, keeping tabs on company assets is a real headache. Many businesses still rely on clunky Excel spreadsheets, leading to lost equipment, outdated info, and a whole lot of inefficiency.

This project delivers a robust **backend API** designed to cut through that chaos. It's your central hub for managing your company's entire asset inventory, allocating equipment to employees, and maintaining a complete, searchable history of who's using what and when. Get ready for enhanced visibility and total control over your valuable assets!

## ✨ Key Features (Backend API)

Here's what this system brings to the table:

### 📦 Resource & Equipment Management

* **Define Your Assets:** Easily create, read, update, and delete **Resource Types** (e.g., Laptops, Monitors, Phones, Company Cars).
* **Track Every Piece:** Full **CRUD operations for Specific Resource Instances**. Each individual item gets its own detailed profile including:
    * **Identifiers:** Inventory Number, Serial Number, Model
    * **Specs:** Technical Specifications, Purchase Date, Price
    * **Status:** Current condition (new, used, broken, in repair), Location (office, home), Availability.
* **Categorize for Clarity:** Organize resources into logical **Categories** (IT, Furniture, Tools, Vehicles, etc.) for better organization.

### 👥 Employee Management

* **Manage Your Team:** Full **CRUD for Employee Profiles**. Each employee profile includes essential info like name, email, department, and role – defining who can use what.

### 🤝 Resource Allocation

* **Assign & Reclaim Assets:** Dedicated API endpoints to **allocate a resource** to an employee (e.g., "Laptop X allocated to John Doe on May 30, 2025") and to **deallocate a resource** when it's returned.
* **Comprehensive History:** The system meticulously maintains an **allocation history** for every single resource and employee, showing who used what and when.

### 🛠️ Maintenance & Repair Tracking

* **Log Service Entries:** Easily record all service and maintenance operations for any resource.
* **Update Status:** Change a resource's status (e.g., "in repair", "repaired") as it moves through the maintenance cycle.
* **Track Costs:** Associate costs with each maintenance or repair event for better financial oversight.

### 🔒 Authentication & Authorization

* **Secure Access:** Leverages **JSON Web Tokens (JWT)** for robust API security.
* **Granular Roles:** Control access based on user roles:
    * **Employee:** Can view only their allocated resources and report issues.
    * **Manager:** Can view resources allocated to their team.
    * **IT Admin/Asset Manager:** Full administrative control over all resources and allocations.

### 🔎 Search, Filtering & Reports

* **Smart Search:** Powerful API endpoints to search for resources by model, serial number, status, or current assignee.
* **Availability Filters:** Filter resources by their availability status (available, allocated).
* **Essential Reports:** Generate crucial insights such as:
    * Resources allocated to each employee.
    * List of currently available resources.
    * Total inventory breakdown by category and status.
    * Resources flagged for maintenance.
    * Complete history of any specific resource.

### 🔔 Notifications (Future Enhancement)

* **Automated Alerts:** Future support for automated alerts for resources due for return.
* **Service Updates:** Notifications for assets entering or exiting maintenance.

## 🚀 Why This Project Belongs in Your Portfolio

This project is a standout choice for your portfolio because it doesn't just demonstrate coding skills; it showcases your ability to build practical, impactful solutions:

* **Solves a Universal Business Problem:** You're tackling a real-world pain point that almost every company faces, proving you can deliver solutions with direct business value.
* **Complex Data Modeling Mastery:** It requires designing and implementing a rich database schema with multiple interconnected entities (Resources, Employees, Allocations, Maintenance Logs, etc.) and intricate relationships.
* **Robust Business Logic:** You'll implement sophisticated logic for allocating, deallocating, maintaining historical integrity, and calculating asset availability.
* **Enterprise-Grade Security:** The granular authentication and authorization system, powered by JWT and Spring Security, highlights your strong grasp of application security.
* **Actionable Insights:** The reporting and analytics capabilities demonstrate your ability to extract and present valuable data for informed decision-making.
* **Future-Proof Design:** The built-in extensibility potential (e.g., QR codes, ticketing integration) shows you think ahead and design for scalability.

## 💻 Technologies Used (Backend Stack)

* **Programming Language:** Java
* **Framework:** Spring Boot (with Spring Data JPA, Spring Security, Spring Web)
* **Database:** MySQL
* **ORM:** Hibernate
* **Security:** Spring Security, JSON Web Tokens (JWT)
* **Build Tool:** Maven
* **Testing:** JUnit, Mockito
* **API Documentation:** SpringDoc OpenAPI (Swagger UI)

## 🚦 Getting Started

### Prerequisites

Make sure you have these installed:

* **Java Development Kit (JDK) 21**
* **Maven**
* **MySQL** database instance

### Installation & Configuration

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/YourUsername/asset-management-backend.git](https://github.com/YourUsername/irms-api.git)
    cd irms-api
    ```
2.  **Configure your database:**
    * Create a new MySQL database (e.g., `asset_db`).
    * Open `src/main/resources/application.properties` and update your database connection details:
        ```properties
        # Example for MySQL
        spring.datasource.url=jdbc:mysql://localhost:3306/asset_db?useSSL=false&serverTimezone=UTC
        spring.datasource.username=your_username
        spring.password=your_password
        spring.jpa.hibernate.ddl-auto=update # Or 'create-drop' for fresh development
        spring.jpa.show-sql=true
        ```
3.  **Build and run the application:**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

    Your application will typically start on `http://localhost:8080`.

## 📚 API Documentation

Once the backend is humming, you can explore the interactive API documentation (Swagger UI) at:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

This interface provides full details on all available endpoints, including methods, parameters, authentication requirements, and response structures.

## 🗄️ Database Schema (Conceptual)

Here's a glimpse into the core entities and their relationships:

* **`ResourceCategory`**: `ID`, `name`
* **`ResourceType`**: `ID`, `name`, `description`, `categoryId` (Many-to-One with `ResourceCategory`)
* **`Resource`**: `ID`, `resourceTypeId` (Many-to-One with `ResourceType`), inventory number, serial number, model, specifications, purchase date, price, status, location, availability.
* **`Employee`**: `ID`, name, email, department, role.
* **`Allocation`**: `ID`, `resourceId` (Many-to-One with `Resource`), `employeeId` (Many-to-One with `Employee`), allocation date, deallocation date (optional).
* **`MaintenanceLog`**: `ID`, `resourceId` (Many-to-One with `Resource`), service entry date, service exit date, problem description, repair description, cost.
* **`User`**: `ID`, username, password (for API authentication), `employeeId` (optional, if API user is linked to an `Employee` profile), roles (Many-to-Many with `Role`).
* **`Role`**: `ID`, name (e.g., ADMIN, MANAGER, EMPLOYEE).

## 💡 Possible Future Enhancements

We've got big plans! Here are some ideas for taking this project even further:

* **Barcode/QR Code Integration:** Scan assets quickly for inventory updates.
* **Asset Depreciation Module:** Track the financial depreciation of assets.
* **External ticketing system integration:** Link maintenance issues directly to helpdesk tickets.
* **Email/SMS Notifications:** Automated alerts for returns, service updates, and more.
* **Frontend Web Interface:** Build a user-friendly UI to interact with the backend.