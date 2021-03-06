CREATE TABLE "USERS"(
  "ID" Number NOT NULL,
  "LOGIN" Varchar2(255 ) NOT NULL,
  "PASS" Varchar2(255 ) NOT NULL,
  "PASS_ACTIVE" Char(1 BYTE) NOT NULL,
  "ENABLED" Char(1 BYTE) NOT NULL,
  "CREATE_DATE" Date NOT NULL,
  "END_DATE" Date,
  "ROLE_ID" Number NOT NULL
)
/

-- Add keys for table USERS

ALTER TABLE "USERS" ADD CONSTRAINT "user_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE user_seq
/

CREATE OR REPLACE TRIGGER user_bir
BEFORE INSERT ON "USERS"
FOR EACH ROW

BEGIN
  SELECT user_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/

-- Table USER_ROLE

CREATE TABLE "USER_ROLE"(
  "ID" Number NOT NULL,
  "ROLE_TYPE" Varchar2(20 ) NOT NULL,
  "ROLE_DESC" Varchar2(255 )
)
/

-- Add keys for table USER_ROLE

ALTER TABLE "USER_ROLE" ADD CONSTRAINT "usr_role_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE usr_role_seq
/

CREATE OR REPLACE TRIGGER usr_role_bir
BEFORE INSERT ON "USER_ROLE"
FOR EACH ROW

  BEGIN
    SELECT usr_role_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/

-- Table EMPLOYEES

CREATE TABLE "EMPLOYEES"(
  "ID" Number NOT NULL,
  "ACC_ID" Number NOT NULL,
  "PERSON_ID" Number,
  "EMAIL" Varchar2(30 ) NOT NULL,
  "PHONE_NUMBER" Varchar2(15 ) NOT NULL,
  "FAX_NUMBER" Varchar2(15 )
)
/

-- Create indexes for table EMPLOYEES

CREATE INDEX "EMPLOYEE_PERSON_INDEX" ON "EMPLOYEES" ("PERSON_ID")
/

-- Add keys for table EMPLOYEES

ALTER TABLE "EMPLOYEES" ADD CONSTRAINT "employee_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE employee_seq
/

CREATE OR REPLACE TRIGGER employee_bir
BEFORE INSERT ON "EMPLOYEES"
FOR EACH ROW

  BEGIN
    SELECT employee_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/

-- Table CONTRACTS

CREATE TABLE "CONTRACTS"(
  "ID" Number NOT NULL,
  "CUSTOMER_ID" Number NOT NULL,
  "ISSUE_DATE" Date NOT NULL,
  "START_DATE" Date NOT NULL,
  "END_DATE" Date NOT NULL,
  "STATUS_ID" Number NOT NULL,
  "PREV_CONTRACT_ID" Number,
  "CONTRACT_NUMBER" Varchar2(30 )
)
/

-- Create indexes for table CONTRACTS

CREATE INDEX "PREV_CONTRACT_INDEX" ON "CONTRACTS" ("ID","PREV_CONTRACT_ID")
/

-- Add keys for table CONTRACTS

ALTER TABLE "CONTRACTS" ADD CONSTRAINT "contracts_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE contracts_seq
/

CREATE OR REPLACE TRIGGER contracts_bir
BEFORE INSERT ON "CONTRACTS"
FOR EACH ROW

  BEGIN
    SELECT contracts_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/

-- Table CONTRACT_SERVICE_ITEM

CREATE TABLE "CONTRACT_SERVICE_ITEM"(
  "ID" Number NOT NULL,
  "ITEM_ID" Number(10,0) NOT NULL,
  "CONTRACT_ID" Number NOT NULL,
  "PRICE" Number(18,2)
)
/

-- Add keys for table CONTRACT_SERVICE_ITEM

ALTER TABLE "CONTRACT_SERVICE_ITEM" ADD CONSTRAINT "ctr_item_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE ctr_item_seq
/

CREATE OR REPLACE TRIGGER ctr_item_bir
BEFORE INSERT ON "CONTRACT_SERVICE_ITEM"
FOR EACH ROW

  BEGIN
    SELECT ctr_item_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/

-- Table CUSTOMERS

CREATE TABLE "CUSTOMERS"(
  "ID" Number NOT NULL,
  "ACC_ID" Number,
  "PHONE_NUMBER" Varchar2(15 ),
  "EMAIL" Varchar2(30 ) NOT NULL,
  "PERSON_ID" Number
)
/

-- Create indexes for table CUSTOMERS

CREATE INDEX "CUSTOMER_INDEX" ON "CUSTOMERS" ("ACC_ID")
/

CREATE INDEX "CUSTOMER_PERSON_INDEX" ON "CUSTOMERS" ("PERSON_ID")
/

-- Add keys for table CUSTOMERS

ALTER TABLE "CUSTOMERS" ADD CONSTRAINT "customers_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE customers_seq
/

CREATE OR REPLACE TRIGGER customers_bir
BEFORE INSERT ON "CUSTOMERS"
FOR EACH ROW

  BEGIN
    SELECT customers_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/

-- Table PRODUCTS

CREATE TABLE "PRODUCTS"(
  "ID" Number NOT NULL,
  "CODE" Varchar2(30 ) NOT NULL,
  "NAME" Varchar2(50 ) NOT NULL,
  "DESC" Varchar2(255 ),
  "PRICE" Number(10,2) NOT NULL
)
/

-- Add keys for table PRODUCTS

ALTER TABLE "PRODUCTS" ADD CONSTRAINT "products_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE products_seq
/

CREATE OR REPLACE TRIGGER products_bir
BEFORE INSERT ON "PRODUCTS"
FOR EACH ROW

  BEGIN
    SELECT products_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/

-- Table SERVICES

CREATE TABLE "SERVICES"(
  "ID" Number(10,0) NOT NULL,
  "TYPE_ID" Number NOT NULL,
  "CODE" Varchar2(30 ) NOT NULL,
  "NAME" Varchar2(50 ) NOT NULL,
  "DESC" Varchar2(255 ),
  "PRICE" Number(10,2) NOT NULL
)
/

-- Add keys for table SERVICES

ALTER TABLE "SERVICES" ADD CONSTRAINT "services_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE services_seq
/

CREATE OR REPLACE TRIGGER services_bir
BEFORE INSERT ON "SERVICES"
FOR EACH ROW

  BEGIN
    SELECT services_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/
-- Table BILLINGS

CREATE TABLE "BILLINGS"(
  "ID" Number NOT NULL,
  "ATTRIBUTE_ID" Number NOT NULL,
  "CONTRACT_ITEM_ID" Number NOT NULL,
  "DESCRIPTION" Varchar2(255 ),
  "DATE" Date NOT NULL
)
/

-- Add keys for table BILLINGS

ALTER TABLE "BILLINGS" ADD CONSTRAINT "billings_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE billings_seq
/

CREATE OR REPLACE TRIGGER billings_bir
BEFORE INSERT ON "BILLINGS"
FOR EACH ROW

  BEGIN
    SELECT billings_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/

-- Table COMPLAINTS

CREATE TABLE "COMPLAINTS"(
  "ID" Number NOT NULL,
  "CONTRACT_ID" Number NOT NULL,
  "DESC" Varchar2(1000 ) NOT NULL
)
/

-- Add keys for table COMPLAINTS

ALTER TABLE "COMPLAINTS" ADD CONSTRAINT "complaints_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE complaints_seq
/

CREATE OR REPLACE TRIGGER complaints_bir
BEFORE INSERT ON "COMPLAINTS"
FOR EACH ROW

  BEGIN
    SELECT complaints_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/

-- Table CLIENT_REGISTRATION

CREATE TABLE "CLIENT_REGISTRATION"(
  "CLIENT_ID" Number NOT NULL,
  "EMP_ID" Number NOT NULL,
  "REGISTRATION_DATE" Timestamp(6) NOT NULL
)
/

-- Create indexes for table CLIENT_REGISTRATION

CREATE INDEX "CLIENT_REG_INDEX" ON "CLIENT_REGISTRATION" ("EMP_ID")
/

-- Table PAYMENTS

CREATE TABLE "PAYMENTS"(
  "ID" Number NOT NULL,
  "CONTRACT_ID" Number NOT NULL,
  "START_DATE" Date NOT NULL,
  "END_DATE" Date NOT NULL,
  "AMOUNT" Number(18,2) NOT NULL,
  "PAYMENT_DATE" Date NOT NULL,
  "INTERESTS" Number(18,2)
)
/

-- Add keys for table PAYMENTS

ALTER TABLE "PAYMENTS" ADD CONSTRAINT "payments_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE payments_seq
/

CREATE OR REPLACE TRIGGER payments_bir
BEFORE INSERT ON "PAYMENTS"
FOR EACH ROW

  BEGIN
    SELECT payments_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/
-- Table CONTRACT_PRODUCT_ITEM

CREATE TABLE "CONTRACT_PRODUCT_ITEM"(
  "CONTRACT_ITEM_ID" Number NOT NULL,
  "PRODUCT_ID" Number NOT NULL,
  "AMOUNT" Number
)
/

-- Table SERVICE_TYPES

CREATE TABLE "SERVICE_TYPES"(
  "ID" Number NOT NULL,
  "TYPE" Varchar2(30 ) NOT NULL,
  "DESCRIPTION" Varchar2(255 )
)
/

-- Add keys for table SERVICE_TYPES

ALTER TABLE "SERVICE_TYPES" ADD CONSTRAINT "service_types_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE service_types_seq
/

CREATE OR REPLACE TRIGGER service_types_bir
BEFORE INSERT ON "SERVICE_TYPES"
FOR EACH ROW

  BEGIN
    SELECT service_types_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/
-- Table CONTRACT_STATUS

CREATE TABLE "CONTRACT_STATUS"(
  "ID" Number NOT NULL,
  "STATUS" Varchar2(30 ) NOT NULL,
  "DESC" Varchar2(255 )
)
/

-- Add keys for table CONTRACT_STATUS

ALTER TABLE "CONTRACT_STATUS" ADD CONSTRAINT "ctr_status_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE ctr_status_seq
/

CREATE OR REPLACE TRIGGER ctr_status_bir
BEFORE INSERT ON "CONTRACT_STATUS"
FOR EACH ROW

  BEGIN
    SELECT ctr_status_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/

-- Table BILLING_ENTRY

CREATE TABLE "BILLING_ENTRY"(
  "BILLING_ID" Number NOT NULL,
  "TYPE_ID" Number NOT NULL,
  "VALUE" Varchar2(255 ) NOT NULL
)
/

-- Table BILLING_ENTRY_TYPE

CREATE TABLE "BILLING_ENTRY_TYPE"(
  "ID" Number NOT NULL,
  "NAME" Varchar2(50 ) NOT NULL
)
/

-- Add keys for table BILLING_ENTRY_TYPE

ALTER TABLE "BILLING_ENTRY_TYPE" ADD CONSTRAINT "billing_entry_type_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE billing_entry_type_seq
/

CREATE OR REPLACE TRIGGER billing_entry_type_bir
BEFORE INSERT ON "BILLING_ENTRY_TYPE"
FOR EACH ROW

  BEGIN
    SELECT billing_entry_type_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/
-- Table MAIL_SETTINGS

CREATE TABLE "MAIL_SETTINGS"(
  "ID" Number NOT NULL,
  "USER_ID" Number NOT NULL,
  "ADDRESS" Varchar2(255 ) NOT NULL,
  "PASSWORD" Varchar2(255 ) NOT NULL,
  "HOST" Varchar2(60 ) NOT NULL,
  "SMTP_PORT" Number NOT NULL
)
/

-- Add keys for table MAIL_SETTINGS

ALTER TABLE "MAIL_SETTINGS" ADD CONSTRAINT "mail_settings_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE mail_settings_seq
/

CREATE OR REPLACE TRIGGER mail_settings_bir
BEFORE INSERT ON "MAIL_SETTINGS"
FOR EACH ROW

  BEGIN
    SELECT mail_settings_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/

-- Table MAIL_SETTINGS_PROPERTIES

CREATE TABLE "MAIL_SETTINGS_PROPERTIES"(
  "ID" Number NOT NULL,
  "SETTINGS_ID" Number NOT NULL,
  "KEY" Varchar2(80 ) NOT NULL,
  "VALUE" Varchar2(80 ) NOT NULL
)
/

-- Add keys for table MAIL_SETTINGS_PROPERTIES

ALTER TABLE "MAIL_SETTINGS_PROPERTIES" ADD CONSTRAINT "mail_settings_properties_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE mail_settings_properties_seq
/

CREATE OR REPLACE TRIGGER mail_settings_properties_bir
BEFORE INSERT ON "MAIL_SETTINGS_PROPERTIES"
FOR EACH ROW

  BEGIN
    SELECT mail_settings_properties_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/
-- Table PERSON

CREATE TABLE "PERSON"(
  "ID" Number NOT NULL,
  "FIRST_NAME" Varchar2(30 ) NOT NULL,
  "LAST_NAME" Varchar2(30 ) NOT NULL,
  "BIRTH_DATE" Date NOT NULL,
  "COUNTRY" Varchar2(30 ) NOT NULL,
  "PROVINCE" Varchar2(30 ),
  "COUNTY" Varchar2(30 ),
  "DISTRICT" Varchar2(30 ),
  "STREET" Varchar2(30 ),
  "NUMBER" Varchar2(15 ) NOT NULL,
  "LOCAL_NUMBER" Varchar2(15 ),
  "ZIP_CODE" Varchar2(10 ),
  "POST_OFFICE" Varchar2(30 )
)
/

-- Add keys for table PERSON

ALTER TABLE "PERSON" ADD CONSTRAINT "person_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE person_seq
/

CREATE OR REPLACE TRIGGER person_bir
BEFORE INSERT ON "PERSON"
FOR EACH ROW

  BEGIN
    SELECT person_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/

-- Table PAYMENT_NOTIFICATION

CREATE TABLE "PAYMENT_NOTIFICATION"(
  "ID" Number NOT NULL,
  "PAYMENT_ID" Number NOT NULL
)
/

-- Add keys for table PAYMENT_NOTIFICATION

ALTER TABLE "PAYMENT_NOTIFICATION" ADD CONSTRAINT "payment_notification_pk" PRIMARY KEY ("ID")
/

CREATE SEQUENCE payment_notification_seq
/

CREATE OR REPLACE TRIGGER payment_notification_bir
BEFORE INSERT ON "PAYMENT_NOTIFICATION"
FOR EACH ROW

  BEGIN
    SELECT payment_notification_seq.NEXTVAL
    INTO   :new.id
    FROM   dual;
  END;
/

-- Create relationships section -------------------------------------------------

ALTER TABLE "USERS" ADD CONSTRAINT "Has role" FOREIGN KEY ("ROLE_ID") REFERENCES "USER_ROLE" ("ID")
/

ALTER TABLE "EMPLOYEES" ADD CONSTRAINT "Has account" FOREIGN KEY ("ACC_ID") REFERENCES "USERS" ("ID")
/

ALTER TABLE "CUSTOMERS" ADD CONSTRAINT "Has client account" FOREIGN KEY ("ACC_ID") REFERENCES "USERS" ("ID")
/

ALTER TABLE "CLIENT_REGISTRATION" ADD CONSTRAINT "Is registered" FOREIGN KEY ("CLIENT_ID") REFERENCES "CUSTOMERS" ("ID")
/

ALTER TABLE "CLIENT_REGISTRATION" ADD CONSTRAINT "Registers" FOREIGN KEY ("EMP_ID") REFERENCES "EMPLOYEES" ("ID")
/

ALTER TABLE "CONTRACTS" ADD CONSTRAINT "Contract" FOREIGN KEY ("CUSTOMER_ID") REFERENCES "CUSTOMERS" ("ID")
/

ALTER TABLE "PAYMENTS" ADD CONSTRAINT "Has payments" FOREIGN KEY ("CONTRACT_ID") REFERENCES "CONTRACTS" ("ID")
/

ALTER TABLE "CONTRACTS" ADD CONSTRAINT "Has previous" FOREIGN KEY ("PREV_CONTRACT_ID") REFERENCES "CONTRACTS" ("ID")
/

ALTER TABLE "CONTRACT_SERVICE_ITEM" ADD CONSTRAINT "Has items" FOREIGN KEY ("CONTRACT_ID") REFERENCES "CONTRACTS" ("ID")
/

ALTER TABLE "CONTRACT_SERVICE_ITEM" ADD CONSTRAINT "Has services" FOREIGN KEY ("ITEM_ID") REFERENCES "SERVICES" ("ID")
/

ALTER TABLE "SERVICES" ADD CONSTRAINT "Match service" FOREIGN KEY ("TYPE_ID") REFERENCES "SERVICE_TYPES" ("ID")
/

ALTER TABLE "CONTRACT_PRODUCT_ITEM" ADD CONSTRAINT "Match product" FOREIGN KEY ("PRODUCT_ID") REFERENCES "PRODUCTS" ("ID")
/

ALTER TABLE "CONTRACT_PRODUCT_ITEM" ADD CONSTRAINT "Has product item" FOREIGN KEY ("CONTRACT_ITEM_ID") REFERENCES "CONTRACT_SERVICE_ITEM" ("ID")
/

ALTER TABLE "CONTRACTS" ADD CONSTRAINT "Has status" FOREIGN KEY ("STATUS_ID") REFERENCES "CONTRACT_STATUS" ("ID")
/

ALTER TABLE "COMPLAINTS" ADD CONSTRAINT "Has Complaints" FOREIGN KEY ("CONTRACT_ID") REFERENCES "CONTRACTS" ("ID")
/

ALTER TABLE "BILLINGS" ADD CONSTRAINT "Has billings" FOREIGN KEY ("CONTRACT_ITEM_ID") REFERENCES "CONTRACT_SERVICE_ITEM" ("ID")
/

ALTER TABLE "BILLING_ENTRY" ADD CONSTRAINT "Has billing value" FOREIGN KEY ("BILLING_ID") REFERENCES "BILLINGS" ("ID")
/

ALTER TABLE "BILLING_ENTRY" ADD CONSTRAINT "Has billing type" FOREIGN KEY ("TYPE_ID") REFERENCES "BILLING_ENTRY_TYPE" ("ID")
/

ALTER TABLE "MAIL_SETTINGS" ADD CONSTRAINT "Has Account" FOREIGN KEY ("USER_ID") REFERENCES "USERS" ("ID")
/

ALTER TABLE "MAIL_SETTINGS_PROPERTIES" ADD CONSTRAINT "Has Properties" FOREIGN KEY ("SETTINGS_ID") REFERENCES "MAIL_SETTINGS" ("ID")
/

ALTER TABLE "EMPLOYEES" ADD CONSTRAINT "Employee has person data" FOREIGN KEY ("PERSON_ID") REFERENCES "PERSON" ("ID")
/

ALTER TABLE "CUSTOMERS" ADD CONSTRAINT "Customer has person data" FOREIGN KEY ("PERSON_ID") REFERENCES "PERSON" ("ID")
/

ALTER TABLE "PAYMENT_NOTIFICATION" ADD CONSTRAINT "Has notification" FOREIGN KEY ("PAYMENT_ID") REFERENCES "PAYMENTS" ("ID")
/

