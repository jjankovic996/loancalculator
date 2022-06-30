
-- -----------------------------------------------------
-- Table `loandb`.`loan_calculation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loandb`.`loan_calculation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `interest_rate` INT NULL DEFAULT NULL,
  `loan_amount` DOUBLE NULL DEFAULT NULL,
  `monthly_payment` DOUBLE NULL DEFAULT NULL,
  `number_of_payments` INT NULL DEFAULT NULL,
  `payment_frequency` VARCHAR(255) NULL DEFAULT NULL,
  `total_interest` DOUBLE NULL DEFAULT NULL,
  `uuid` VARCHAR(255) NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT NULL,
  `updated_at` TIMESTAMP NULL DEFAULT NULL,


  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

