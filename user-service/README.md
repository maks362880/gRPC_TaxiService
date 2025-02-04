# User Service

## 1. Описание

User Service является частью архитектуры проекта TaxiService, разработанного для управления пользователями такси-сервиса. Этот микросервис отвечает за регистрацию, аутентификацию и управление пользователями, обеспечивая необходимые API для взаимодействия с другими микросервисами в системе.

## 2. Установка

### 2.1. Предварительные требования

Перед началом убедитесь, что у вас установлены следующие инструменты:
- Java JDK 18 или выше
- Maven
- Docker (опционально, для контейнеризации)
- PostgreSQL (или другой СУБД по вашему выбору)

### 2.2. Клонирование репозитория

Сначала клонируйте репозиторий проекта:

```bash
git clone <URL_репозитория>
cd taxiservice/user-service
```

### 2.3. Сборка проекта

Используйте Maven для сборки проекта:

mvn clean install

## 3. Запуск сервиса

### 3.1. Локальный запуск

Для запуска User Service локально используйте следующую команду:

mvn spring-boot:run

### 3.2. Запуск в Docker

Если вы хотите запустить сервис в Docker, убедитесь, что у вас есть Dockerfile в папке user-service. Затем выполните команду:

docker build -t user-service .
docker run -p 8001:8001 user-service

## 4. Конфигурация

Конфигурация приложения находится в файле src/main/resources/application.properties. Вы можете настроить подключение к базе данных и другие параметры.

Пример подключения к PostgreSQL:

spring.datasource.url=jdbc:postgresql://localhost:5432/taxiservice
spring.datasource.username=your_username
spring.datasource.password=your_password

## 5. Реализация микросервисов

### 5.1. User Service

User Service реализует функции управления пользователями, включая регистрацию, аутентификацию и получение информации о пользователях.

#### gRPC Методы:
- Register (RegisterRequest) returns (RegisterResponse): регистрация нового пользователя.
- Login (LoginRequest) returns (LoginResponse): авторизация пользователя.
- GetUser (GetUserRequest) returns (GetUserResponse): получение данных о пользователе.

## 6. Технологии для реализации проекта

- Spring Boot
- Maven
- gRPC
- Hibernate
- PostgreSQL
- *Liquibase
- *Docker
- *Kubernetes (K8s)
- *Apache Kafka
- *Helm
- *Istio

Технологии, отмеченные звездочкой (*), являются необязательными, но желательными для использования в проекте.

## 7. Структура проекта

user-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── maks362880/
│   │   │           └── userservice/
│   │   │               ├── UserServiceApplication.java
│   │   │               ├── controller/
│   │   │               ├── service/
│   │   │               ├── repository/
│   │   │               ├── model/
│   │   │               ├── config/
│   │   │               └── grpc/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/
│   │           └── changelog/
│   │               └── db.changelog-master.xml
│   └── test/
│       └── java/
│           └── com/
│               └── maks362880/
│                   └── userservice/
└── pom.xml

## 8. Дополнительные аспекты

- **Docker**: Создание Docker контейнеров для упрощения развертывания микросервисов.
- **Kubernetes (K8s)**: Оркестрация контейнеров и автоматизация развертывания и управления микросервисами.
- **Apache Kafka**: Обмен сообщениями между микросервисами, особенно для уведомлений и событий.
- **Liquibase**: Управление миграциями баз данных.
- **OpenAPI**: Документация API для всех микросервисов.
- **Helm**: Упрощение развертывания в Kubernetes.

## 9. Тестирование

Unit-тесты для сервиса написаны с использованием JUnit и находятся в папке `src/test/java`. Чтобы запустить тесты, выполните команду:

```bash
mvn test
```

## 10. Лицензия

Проект лицензирован под MIT License. Подробности смотрите в файле LICENSE.

## 11. Контакты

Для вопросов и предложений вы можете обратиться по следующему адресу:
