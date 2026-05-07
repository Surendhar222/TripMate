# **Trip Management System**

## **Requirement Gathering / Analysis:**

### **Core Features:**

* User account creation (Traveler / Admin)
* Secure login & authentication
* Create and manage trips
* View available destinations with details
* Itinerary planning (day-wise schedule)
* Budget estimation for trips
* Search vehicles (car, bike, bus)
* Filter based on price, type, availability
* Real-time availability check
* Booking & cancellation
* Search hotels by location
* Filter (price, rating, amenities)
* Room availability tracking
* Booking & cancellation
* Reviews & ratings
* Booking confirmation alerts
* Trip reminders
* Payment updates
* Manage users
* Manage vehicles
* Manage hotel listings
* View booking reports

**Tech stack: Java, SQL**

**Time : 1 Month**

**Team members: 1**

### **Admin:**

* Manage users
* Add/update/delete vehicles
* Add/update/delete hotels
* View all bookings

### **Traveler (User):**

* Register & login
* Search trips
* Book vehicles
* Book hotels
* View itinerary
* Make payments
* View booking history

**Feasibility Analysis(Next Phase):**

* Filter (price, rating, amenities)
* Room availability tracking
* Booking & cancellation
* Reviews & ratings
* Booking confirmation alerts
* Trip reminders
* Payment updates
* Manage users
* Manage vehicles
* Manage hotel listings
* View booking reports

**Model Classes** :

**User:**

id: Long

name: String

email: String

phone: String

role: Enum \- ADMIN / TRAVELER

createdTime: Long

updatedTime: Long

status: Enum \- ACTIVE / INACTIVE / BLOCKED

**UserCredentials:**

id: Long

userId: Long (FK \- User.id)

username: String

password: String

lastLoginTime: Long

**Vehicle:**

id: Long

name: String

type: Enum \- BIKE / CAR / BUS

brand: String

pricePerDay: Double

availabilityStatus: Enum \- AVAILABLE / BOOKED / MAINTENANCE

createdTime: Long

updatedTime: Long

**Hotel:**

id: Long

name: String

location: String

rating: Double

createdTime: Long

updatedTime: Long

status: Enum \- ACTIVE / INACTIVE

**Room:**

id: Long

hotelId: Long (FK \- Hotel.id)

roomType: Enum \- SINGLE / DOUBLE / SUITE

pricePerNight: Double

totalRooms: int

availableRooms: int

createdTime: Long

updatedTime: Long

**Trip:**

id: Long

userId: Long (FK \- User.id)

source: String

destination: String

startDate: Long

endDate: Long

createdTime: Long

updatedTime: Long

status: Enum \- PLANNED / ONGOING / COMPLETED / CANCELLED

**VehicleBooking:**

id: Long

userId: Long (FK \- User.id)

vehicleId: Long (FK \- Vehicle.id)

tripId: Long (FK \- Trip.id)

startDate: Long

endDate: Long

totalPrice: Double

bookingStatus: Enum \- BOOKED / CANCELLED / COMPLETED

createdTime: Long

updatedTime: Long

**HotelBooking:**

id: Long

userId: Long (FK \- User.id)

hotelId: Long (FK \- Hotel.id)

roomId: Long (FK \- Room.id)

tripId: Long (FK \- Trip.id)

checkInDate: Long

checkOutDate: Long

roomsBooked: int

totalPrice: Double

bookingStatus: Enum \- BOOKED / CANCELLED / COMPLETED

createdTime: Long

updatedTime: Long

**—-------------------Next Phase—-------------------------------------**

**Payment:**

id: Long

userId: Long (FK \- User.id)

bookingId: Long   // polymorphic reference (VehicleBooking / HotelBooking)

bookingType: Enum \- VEHICLE / HOTEL

amount: Double

paymentMethod: Enum \- UPI / CARD / NETBANKING

paymentStatus: Enum \- PENDING / SUCCESS / FAILED / REFUNDED

transactionId: String

paymentTime: Long

**Review:**

id: Long

userId: Long (FK \- User.id)

hotelId: Long (FK \- Hotel.id)

rating: int

comment: String

createdTime: Long

**Notification:**

id: Long

userId: Long (FK \- User.id)

message: String

type: Enum \- BOOKING\_CONFIRMATION / PAYMENT\_UPDATE / REMINDER

status: Enum \- SENT / PENDING / FAILED

createdTime: Long
