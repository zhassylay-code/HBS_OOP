<img width="1920" height="1080" alt="Brown and Black Minimalist Hotel Presentation" src="https://github.com/user-attachments/assets/8fee6667-b14f-46a5-b3cb-d724179223dd" />

# Welcome to the Grand Hotel!
Welcome to the **Grand Hotel Booking System** - a console-based Java application where users can check room availability, make bookings, and see booking details. This project was created as part of an Object-Oriented Programming course and represents a simple hotel booking system built step by step by our team.

## 🗂️This repository contains the source code for:

    🖥️ Console-based user interface (UI)
    🎛️ Controllers connecting UI and business logic
    🧠 Service layer with booking rules and price calculation
    🗄️ JDBC repositories for PostgreSQL database access
    🏨 Entity classes (Room, Booking, Guest)
    🚀 Main application entry point

## ✨ Project Features

    🏨 View available hotel rooms for selected dates
    📅 Book a room with date validation
    💰 Automatic price calculation
    🧾 Booking confirmation summary
    
## 🧑🏻‍💻 Our Team Members & Work Distribution

### 🗃️ Diana	Bakyt 
**Database & Infrastructure**

    ▪️PostgreSQL database setup 
    ▪️Table design (rooms, bookings, guests)
    ▪️JDBC connection
    ▪️Repository implementations (RoomRepositoryImpl, BookingRepositoryImpl)
    ▪️Application entry point (Main.java)
    ▫️Database integration and testing


### 🧩 Sanzhar	Aubakirov 
**Business Logic**
- Booking business rules
- Date validation logic
- Room availability checks
- Price calculation logic
- Custom exceptions (InvalidBookingDatesException, RoomAlreadyBookedException)
- Ensuring business logic independence from UI

### 🖥️ Assylay	Zhengisbekova 
**User Interface & Controllers**
- Console-based UI 
- User interaction flow
- Input validation
- Booking confirmation output
- UX improvements and readable console output
- Integration between UI and Service layers (BookingController, GuestController)
- ReadMe design 

## 🧱 Project Architecture Overview

The application follows a layered architecture:

    UI (ConsoleMenu)
       ↓
    Controllers
       ↓
    Services (Business Logic)
       ↓
    Repositories (JDBC)
       ↓
    Database (PostgreSQL)

## 💡 How to run the code?
1. Make sure PostgreSQL(latest version) is running
2. Create the database "hotel_booking_db"
3. Run Main.java
4. Follow the instructions in the console


    [!NOTE]
    Use the same database credentials in Main.java.
