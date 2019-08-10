CREATE TABLE `USER` (
  `id`         BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name`  VARCHAR(255) NOT NULL,
  `email`      VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE INDEX IDX_EMAIL
  ON `USER` (`email`);

CREATE TABLE `ROOM` (
  `id`         BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(255) NOT NULL,
  `seats`      INT          NOT NULL DEFAULT 10,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE INDEX IDX_ROOM_SEATS
  ON `ROOM` (`seats`);

CREATE TABLE `BOOKING` (
  `id`              BIGINT(20) NOT NULL AUTO_INCREMENT,
  `start_date`      TIMESTAMP  NOT NULL,
  `end_date`        TIMESTAMP  NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `ROOM_BOOKING_RELATION` (
  `room_id`    BIGINT(20) NOT NULL,
  `booking_id` BIGINT(20) NOT NULL,
  FOREIGN KEY ROOM_BOOKING_ID (`room_id`) REFERENCES `ROOM` (`id`),
  FOREIGN KEY BOOKING_ROOM_ID (`booking_id`) REFERENCES `BOOKING` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `USER_BOOKING_RELATION` (
  `user_id`    BIGINT(20) NOT NULL,
  `booking_id` BIGINT(20) NOT NULL,
  FOREIGN KEY USER_BOOKING_ID (`user_id`) REFERENCES `USER` (`id`),
  FOREIGN KEY BOOKING_USER_ID (`booking_id`) REFERENCES `BOOKING` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

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

