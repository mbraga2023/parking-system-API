# User, Car, and ParkingHistory Diagram

This diagram shows the relationships between `User`, `Car`, and `ParkingHistory` in our system.

```mermaid
classDiagram
    class User {
        +String name
        +String phone
        +String email
        +List<Car> cars
    }

    class Car {
        +String manufacturer
        +String model
        +String color
        +int year
        +String plate
        +String type
        +List<ParkingHistory> history
    }

    class ParkingHistory {
        +String checkin
        +String checkout
    }

    User "1" -- "0..*" Car : owns
    Car "1" -- "0..*" ParkingHistory : has
