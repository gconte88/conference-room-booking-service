# Teracode's Conference Room Booking Service

## To start the application:

1) go to application.properties file and complete the root password of mysql.
2) enable liquibase on application.properties
3) go to the root folder of the project and run mvn spring-boot:run

(Assuming that you have MYSQL)

[MYSQL](https://www.dev2qa.com/how-to-install-mysql-on-ubuntu/)

## Test it!

There is a postman_collection.json with the available endpoints.

[POSTMAN](https://www.getpostman.com/downloads/)

Ive added a few users/rooms via SQL: (TODO: creation methods)

```
INSERT INTO `USER` (`id`, `first_name`, `last_name`, `email`)
VALUES
  (1, 'David', 'Bowie', 'david@bowie.com');

INSERT INTO `USER` (`id`, `first_name`, `last_name`, `email`)
VALUES
  (2, 'Freddy', 'Mercury', 'freddy@mercury.com');

INSERT INTO `ROOM` (`id`, `name`, `seats`)
VALUES
  (1, 'Room1', 10);

INSERT INTO `ROOM` (`id`, `name`, `seats`)
VALUES
  (2, 'Room2', 30);

INSERT INTO `ROOM` (`id`, `name`, `seats`)
VALUES
  (3, 'Room3', 50);

INSERT INTO `ROOM` (`id`, `name`, `seats`)
VALUES
  (4, 'Room4', 100);
```

## Questionnaire

### A
Diagram-3

### B
Diagram-4

### C
```
  public static final int MINIMUM_BOOKING_MINUTES = 15;
  public static final int MAXIMUM_BOOKING_MINUTES = 180;
  
  /**
   * Validates that a booking does not overlap in the schedule of the other bookings of the room (not itself)
   * Validates that the start date and end date time diff is between 15 minutes and 3 hours
   *
   * @param startDate
   * @param endDate
   * @param bookingToFilter
   * @throws ValidationException if any validation is not satisfied
   */
  public void validateBooking(LocalDateTime startDate, LocalDateTime endDate, Long bookingToFilter) {

    Supplier<Stream<Booking>> supplier = () -> bookings.stream();
    boolean overlap;
    if (bookingToFilter != null) {
      overlap = supplier.get().filter(booking -> booking.getId().intValue() != bookingToFilter.intValue()).anyMatch(
          booking -> booking.getStartDate().compareTo(endDate) <= 0 && booking.getEndDate().compareTo(startDate) >= 0);
    } else {
      overlap = supplier.get().anyMatch(
          booking -> booking.getStartDate().compareTo(endDate) <= 0 && booking.getEndDate().compareTo(startDate) >= 0);
    }

    if (!bookings.isEmpty() && overlap) {
      throw new ValidationException("conference.room.service.overlapping.a.booking");
    }
    long minutes = ChronoUnit.MINUTES.between(startDate, endDate);

    if (minutes < MINIMUM_BOOKING_MINUTES || minutes > MAXIMUM_BOOKING_MINUTES) {
      throw new ValidationException("conference.room.service.booking.minutes.invalid");
    }
  }
```