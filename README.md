# Vacation Booking Application

**Author:** Tanner Abraham

A full-stack vacation booking web application built with **Spring Boot** (Java) on the backend and **Angular** on the frontend, backed by a **MySQL** database.

---

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Database Setup](#database-setup)
- [Backend Setup](#backend-setup)
- [Frontend Setup](#frontend-setup)
- [API Endpoints](#api-endpoints)
- [Application Flow](#application-flow)
- [Features](#features)

---

## Overview

This application allows users to browse vacation packages, select excursions, manage customer accounts, and complete bookings with an order tracking number. It is designed as a WGU D288 Back-End Programming assessment project.

---

## Tech Stack

| Layer     | Technology                          |
|-----------|-------------------------------------|
| Frontend  | Angular 14, Angular Material, TypeScript |
| Backend   | Spring Boot 4, Spring Data JPA, Spring Data REST |
| Database  | MySQL 8                             |
| Build     | Maven (backend), Angular CLI (frontend) |
| ORM       | Hibernate / JPA                     |
| Other     | Lombok, Jackson                     |

---

## Project Structure

```
├── backend/
│   └── booking/               # Spring Boot application
│       ├── src/main/java/com/vacation/booking/
│       │   ├── bootstrap/     # Data seeding on startup
│       │   ├── config/        # REST configuration (CORS, pagination, ID exposure)
│       │   ├── controller/    # Checkout REST controller
│       │   ├── dao/           # JPA repositories
│       │   ├── entity/        # JPA entity classes
│       │   └── service/       # Business logic + DTOs
│       └── src/main/resources/
│           └── application.properties
├── frontend/                  # Angular application
│   └── src/app/
│       ├── model/             # TypeScript models and DTOs
│       ├── services/          # Shared data service (PurchaseDataService)
│       └── views/             # Page components
│           ├── vacation/
│           ├── vacation-detail/
│           ├── excursion/
│           ├── excursion-detail/
│           ├── cart/
│           ├── cart-summary/
│           ├── order-confirmation/
│           ├── view-customer/
│           ├── add-customer/
│           └── edit-customer/
├── database/
│   └── create_and_populate_db.sql   # Full database setup script
└── docs/
    └── INSTRUCTIONS.md
```

---

## Prerequisites

- **Java 25+**
- **Maven 3.9+** (or use the included `mvnw` wrapper)
- **Node.js & npm** (for Angular CLI)
- **Angular CLI 14** — `npm install -g @angular/cli@14`
- **MySQL 8**
- **MySQL Workbench** (recommended)

---

## Database Setup

1. Open **MySQL Workbench** and connect to your local MySQL instance.
2. Go to **File → Open SQL Script** and select:
   ```
   database/create_and_populate_db.sql
   ```
3. Click the **lightning bolt** icon to execute the script.

This script will:
- Drop and recreate the `full-stack-ecommerce` database
- Create all tables (`countries`, `divisions`, `customers`, `vacations`, `excursions`, `carts`, `cart_items`, `excursion_cartitem`)
- Seed all reference data (countries, divisions, vacations, excursions)
- Insert a demo customer and cart
- Create a database user `ecommerceapp` with the required permissions

> **Note:** Run this script after each testing session to restore a clean state.

**Default database credentials (application):**

| Setting  | Value                  |
|----------|------------------------|
| URL      | `localhost:3306/full-stack-ecommerce` |
| Username | `root`                 |
| Password | `12345`                |

These can be changed in `backend/booking/src/main/resources/application.properties`.

---

## Backend Setup

1. Navigate to the backend directory:
   ```bash
   cd backend/booking
   ```

2. Build and run using Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
   Or on Windows:
   ```cmd
   mvnw.cmd spring-boot:run
   ```

3. The API will be available at: `http://localhost:8080/api`

On startup, `BootStrapData` will automatically seed 5 sample customers if they don't already exist.

---

## Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   ng serve
   ```

4. Open your browser to: [http://localhost:4200](http://localhost:4200)

> The backend must be running before starting the frontend.

---

## API Endpoints

The Spring Data REST base path is `/api`. The following resources are exposed:

| Resource     | Path               | Methods               |
|--------------|--------------------|-----------------------|
| Vacations    | `/api/vacations`   | GET                   |
| Excursions   | `/api/excursions`  | GET                   |
| Customers    | `/api/customers`   | GET, POST, PUT        |
| Divisions    | `/api/divisions`   | GET                   |
| Countries    | `/api/countries`   | GET                   |
| Carts        | `/api/carts`       | GET                   |
| Cart Items   | `/api/cart-items`  | GET                   |
| **Checkout** | `/api/checkout/purchase` | **POST**        |

### Checkout Request Body (`POST /api/checkout/purchase`)

```json
{
  "customer": {
    "id": 1,
    "firstName": "Jane",
    "lastName": "Doe",
    "address": "123 Main St",
    "postal_code": "10001",
    "phone": "555-000-1111"
  },
  "cart": {
    "package_price": 1175.00,
    "party_size": 2,
    "status": "ordered"
  },
  "cartItems": [
    {
      "vacation": { "id": 1 },
      "excursions": [{ "id": 3 }]
    }
  ]
}
```

**Response:**
```json
{
  "orderTrackingNumber": "550e8400-e29b-41d4-a716-446655440000"
}
```

---

## Application Flow

```
Vacation List → Vacation Detail → Excursion List → Excursion Detail
     ↓
 Add to Cart (PurchaseDataService accumulates selections)
     ↓
  Cart Summary (review customer info + items + total)
     ↓
  Order Confirmation (POST to /api/checkout/purchase → tracking number)
```

Customer management is available separately via the sidebar **person** icon, allowing you to view, add, and edit customers before beginning a booking.

---

## Features

- Browse vacation packages with images and pricing
- View and select optional excursions per vacation
- Cart with adjustable party size and dynamic pricing
- Customer management (view, add, edit)
- Country and division (state/province) dropdowns with cascading filter
- Checkout with order tracking number generation
- Bootstrap data seeding on application startup
- CORS configured for `http://localhost:4200`
