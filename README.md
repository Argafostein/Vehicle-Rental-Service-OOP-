🚗 Vehicle Rental Management System
A robust, object-oriented Vehicle Rental System designed to handle vehicle inventories, customer rentals, pricing calculations, and return processing. This project demonstrates core Object-Oriented Programming (OOP) principles including Abstraction, Encapsulation, Inheritance, and Polymorphism.

📑 Table of Contents
Features

OOP Principles Applied

Class Diagram & Architecture

Getting Started

Prerequisites

Installation & Setup

Usage Example

Project Structure

Future Improvements

✨ Features
🚘 Vehicle Management: Add, update, and categorize vehicles (e.g., Cars, Motorcycles, Trucks/SUVs).

👤 Customer & User Profiles: Manage customer credentials and rental history.

📋 Rental Workflow: Process vehicle bookings, set rental durations, and update availability status in real-time.

💰 Dynamic Price Calculation: Calculate daily rates, late return fees, and vehicle-specific surcharges (e.g., insurance, driver fees).

🔄 Vehicle Return & Billing: Generate itemized invoices upon vehicle return.

🧩 OOP Principles Applied
Encapsulation: Vehicle attributes (e.g., isRented, dailyRate, licensePlate) are kept private and accessed strictly via getter/setter methods to protect system state.

Inheritance: Base class Vehicle is extended by specific child classes (Car, Motorcycle, Truck) to reuse common code while defining specific attributes.

Polymorphism: The calculateRentalCost(int days) method is overridden across different vehicle subclasses to apply custom rate multipliers and fee structures dynamically.

Abstraction: Interfaces/Abstract classes (e.g., Rentable) define mandatory behavior without exposing complex internal implementation details.
