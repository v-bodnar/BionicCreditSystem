CREATE TABLE `credit`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `credit`.`currency` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `short_title` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC),
  UNIQUE INDEX `short_title_UNIQUE` (`short_title` ASC));

CREATE TABLE `credit`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_role` INT NOT NULL,
  `username` CHAR(16) NOT NULL,
  `password` CHAR(41) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `middle_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `inn` BIGINT(10) NOT NULL,
  `income` FLOAT NULL,
  `id_currency` INT NOT NULL,
  `address` VARCHAR(500) NULL,
  `email` VARCHAR(45) NOT NULL,
  `enabled` bit NOT NULL DEFAULT 1,
  `registration_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `role_idx` (`id_role` ASC),
  INDEX `currency_idx` (`id_currency` ASC),
  UNIQUE INDEX `inn_UNIQUE` (`inn` ASC),
  CONSTRAINT `users_role`
    FOREIGN KEY (`id_role`)
    REFERENCES `credit`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `users_currency`
    FOREIGN KEY (`id_currency`)
    REFERENCES `credit`.`currency` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `credit`.`credit_programs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_currency` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `min_sum` FLOAT NOT NULL,
  `max_sum` FLOAT NOT NULL,
  `period` INT NOT NULL,
  `year_percent` FLOAT NOT NULL,
  `full_description` VARCHAR(1000) NULL,
  `short_description` VARCHAR(1000) NULL,
  `date_created` DATETIME NOT NULL,
  `user_created` INT NOT NULL,
  `enabled` bit NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `currency_idx` (`id_currency` ASC),
  CONSTRAINT `credit_programs_currency`
    FOREIGN KEY (`id_currency`)
    REFERENCES `credit`.`currency` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `user_idx` (`user_created` ASC),
  CONSTRAINT `credit_programs_user_created`
    FOREIGN KEY (`user_created`)
    REFERENCES `credit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `credit`.`credit_requests` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_user` INT NOT NULL,
  `id_credit_program` INT NOT NULL,
  `id_currency` INT NOT NULL,
  `files` VARCHAR(1000) NULL,
  `description` VARCHAR(1000) NULL,
  `sum` INT NOT NULL,
  `card_number` BIGINT NOT NULL UNIQUE,
  `request_status` bit NOT NULL DEFAULT 0,
  `date_requested` DATETIME NOT NULL,
  `date_applied` DATETIME NULL,
  `manager_applied` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_idx` (`id_user` ASC),
  INDEX `credit_program_idx` (`id_credit_program` ASC),
  INDEX `currency_idx` (`id_currency` ASC),
  INDEX `manager_idx` (`manager_applied` ASC),
  CONSTRAINT `credit_request_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `credit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `credit_request_credit_program`
    FOREIGN KEY (`id_credit_program`)
    REFERENCES `credit`.`credit_programs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `credit_request_currency`
    FOREIGN KEY (`id_currency`)
    REFERENCES `credit`.`currency` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `credit_request_manager_applied`
    FOREIGN KEY (`manager_applied`)
    REFERENCES `credit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `credit`.`accounts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `card_number` BIGINT(16) NOT NULL UNIQUE,
  `id_user` INT NOT NULL,
  `id_currency` INT NOT NULL,
  `balance` FLOAT NOT NULL DEFAULT 0,
  `min_balance` FLOAT NOT NULL DEFAULT -1000,
  PRIMARY KEY (`id`),
  INDEX `user_idx` (`id_user` ASC),
  INDEX `currency_idx` (`id_currency` ASC),
  CONSTRAINT `account_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `credit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `account_currency`
    FOREIGN KEY (`id_currency`)
    REFERENCES `credit`.`currency` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `credit`.`credits` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_user` INT NOT NULL,
  `id_account` INT NULL,
  `id_credit_request` INT NULL,
  `id_credit_program` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_idx` (`id_user` ASC),
  INDEX `credit_program_idx` (`id_credit_program` ASC),
  INDEX `credit_request_idx` (`id_credit_request` ASC),
  CONSTRAINT `credit_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `credit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `credit_program`
    FOREIGN KEY (`id_credit_program`)
    REFERENCES `credit`.`credit_requests` (`id_credit_program`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,  
  CONSTRAINT `credit_credit_request`
    FOREIGN KEY (`id_credit_request`)
    REFERENCES `credit`.`credit_requests` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `credit`.`transactions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_currency` INT NOT NULL,
  `id_account_recipient` INT NOT NULL,
  `id_user_recipient` INT NOT NULL,
  `id_account_sender` INT NOT NULL,
  `id_user_sender` INT NOT NULL,
  `sum` FLOAT NOT NULL,
  `date` DATETIME NOT NULL,
  `description` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`),
  INDEX `currency_idx` (`id_currency` ASC),
  INDEX `account_recipient_idx` (`id_account_recipient` ASC),
  INDEX `user_recipient_idx` (`id_user_recipient` ASC),
  INDEX `account_sender_idx` (`id_account_sender` ASC),
  INDEX `user_sender_idx` (`id_user_sender` ASC),
  CONSTRAINT `transactions_currency`
    FOREIGN KEY (`id_currency`)
    REFERENCES `credit`.`currency` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `transactions_account_recipient`
    FOREIGN KEY (`id_account_recipient`)
    REFERENCES `credit`.`accounts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `transactions_user_recipient`
    FOREIGN KEY (`id_user_recipient`)
    REFERENCES `credit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `transactions_account_sender`
    FOREIGN KEY (`id_account_sender`)
    REFERENCES `credit`.`accounts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `transactions_user_sender`
    FOREIGN KEY (`id_user_sender`)
    REFERENCES `credit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO `credit`.`roles`
(`title`)
VALUES
('admin');

INSERT INTO `credit`.`roles`
(`title`)
VALUES
('manager');

INSERT INTO `credit`.`roles`
(`title`)
VALUES
('client');

INSERT INTO `credit`.`currency`
(`title`,
`short_title`)
VALUES
('Hryvna',
'UAH');

INSERT INTO `credit`.`currency`
(`title`,
`short_title`)
VALUES
('Euro',
'EUR');

INSERT INTO `credit`.`currency`
(`title`,
`short_title`)
VALUES
('US Dollar',
'USD');