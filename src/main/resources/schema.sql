-- schema.sql

-- Создаем таблицу Users
CREATE TABLE Users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL
);

-- Создаем таблицу Roles
CREATE TABLE Roles (
                       id SERIAL PRIMARY KEY,
                       role_name VARCHAR(50) NOT NULL
);

-- Создаем таблицу для связи many-to-many между Users и Roles
CREATE TABLE UserRoles (
                           user_id INTEGER REFERENCES Users(id) ON DELETE CASCADE,
                           role_id INTEGER REFERENCES Roles(id) ON DELETE CASCADE,
                           PRIMARY KEY (user_id, role_id)
);

-- Создаем таблицу Posts с one-to-many связью с Users
CREATE TABLE Posts (
                       id SERIAL PRIMARY KEY,
                       user_id INTEGER REFERENCES Users(id) ON DELETE CASCADE,
                       content TEXT NOT NULL
);
