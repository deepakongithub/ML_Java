INSERT INTO ROLES(NAME)VALUES('ROLE_USER');
INSERT INTO ROLES(NAME)VALUES('ROLE_MODERATOR');
INSERT INTO ROLES(NAME)VALUES('ROLE_ADMIN');
INSERT INTO USERS(CREATED_AT, EMAIL, ENABLED, FULL_NAME, MODIFIED_AT, PASSWORD, USERNAME)
VALUES('2024-05-07 20:15:34.474199', 'test@test.com', 'TRUE', 'fullname', '2024-05-07 20:15:34.474239', '$2a$10$1xymSEt/ziehzM6pm/Usx.QJzjIyL3ybPrq5/Kl5DxjI4Egg05SQu', 'test@test.com');
INSERT INTO USERS(CREATED_AT, EMAIL, ENABLED, FULL_NAME, MODIFIED_AT, PASSWORD, USERNAME)
VALUES('2024-05-07 20:15:34.474199', 'test1@test.com', 'TRUE', 'fullname', '2024-05-07 20:15:34.474239', '$2a$10$1xymSEt/ziehzM6pm/Usx.QJzjIyL3ybPrq5/Kl5DxjI4Egg05SQu', 'test1@test.com');
INSERT INTO USERS(CREATED_AT, EMAIL, ENABLED, FULL_NAME, MODIFIED_AT, PASSWORD, USERNAME)
VALUES('2024-05-07 20:15:34.474199', 'test2@test.com', 'TRUE', 'fullname', '2024-05-07 20:15:34.474239', '$2a$10$1xymSEt/ziehzM6pm/Usx.QJzjIyL3ybPrq5/Kl5DxjI4Egg05SQu', 'test2@test.com');
INSERT INTO USER_ROLES(USER_ID, ROLE_ID)VALUES(1, 3);
INSERT INTO USER_ROLES(USER_ID, ROLE_ID)VALUES(2, 3);
INSERT INTO USER_ROLES(USER_ID, ROLE_ID)VALUES(3, 2);