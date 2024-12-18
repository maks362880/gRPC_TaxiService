# TaxiService

## 1. Описание проекта

TaxiService — это микросервисное приложение для управления заказами такси. Система включает в себя несколько микросервисов, каждый из которых отвечает за определенные аспекты работы сервиса. Основные компоненты системы:

- **Сервис пользователей (User Service)**: отвечает за регистрацию, аутентификацию и управление пользователями.
- **Сервис такси (Taxi Service)**: управляет автомобилями, их местоположением и статусами.
- **Сервис заказов (Order Service)**: обрабатывает заказы на такси, включая создание, обновление и отмену заказов.
- **Сервис уведомлений (Notification Service)**: отправляет пользователям уведомления о статусе их заказов (например, когда такси назначено или ожидает).
- **Сервис оплаты (Payment Service)**: управляет платежами и трансакциями.
- **Сервис геолокации (Geolocation Service)**: отвечает за определение географического положения пользователей и такси.
 
## 2. Порты сервисов:

   После запуска сервисов вы сможете получить доступ к каждому из них по следующим адресам:

   - User Service: http://localhost:8001
   - Taxi Service: http://localhost:8002
   - Order Service: http://localhost:8003
   - Notification Service: http://localhost:8004
   - Payment Service: http://localhost:8005
   - Geolocation Service: http://localhost:8006

## 3. Технологии для реализации проекта:

- **Один репозиторий для всех микросервисов (Monorepo)**
- **Spring Boot**
- **Maven**
- **gRPC**
- **Hibernate**
- **PostgreSQL**
- ***Liquibase**
- ***Docker**
- ***Kubernetes (K8s)**
- ***Apache Kafka**
- ***Helm**
- ***Istio**

*Технологии, отмеченные звездочкой(\*), являются необязательными, но желательными для использования в проекте.*

### 4. Структура проекта:
```plaintext
taxiservice/
│
├── user-service/                  # Микросервис для управления пользователями
│   ├── src/
│   ├── pom.xml                    # Maven файл
│   └── README.md                  # Документация по микросервису
│
├── taxi-service/                  # Микросервис для управления автомобилями
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
├── order-service/                 # Микросервис для обработки заказов
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
├── notification-service/          # Микросервис для отправки уведомлений
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
├── payment-service/               # Микросервис для обработки платежей
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
└── geolocation-service/           # Микросервис для определения геолокации
    ├── src/
    ├── pom.xml
    └── README.md
```
### 5. Реализация микросервисов

#### 5.1 user-service
- Функции: управление пользователями, включая регистрацию, аутентификацию и получение информации о пользователях.

#### gRPC Методы:
- **Register (RegisterRequest) returns (RegisterResponse)** - регистрация нового пользователя.
- **Login (LoginRequest) returns (LoginResponse)** - авторизация пользователя.
- **GetUser (GetUserRequest) returns (GetUserResponse)** - получение данных о пользователе.

#### 5.2 taxi-service
- Функции: управление автомобилями, их состоянием и местоположением в системе такси.

#### gRPC Методы:
- **AddTaxi (AddTaxiRequest) returns (AddTaxiResponse)** - добавление нового такси в систему.
- **UpdateTaxi (UpdateTaxiRequest) returns (UpdateTaxiResponse)** - обновление информации об уже зарегистрированном такси.
- **ListTaxis (ListTaxisRequest) returns (ListTaxisResponse)** - получение списка всех доступных такси и их информации.

#### 5.3 order-service
- Функции: создание, обновление и выполнение заказов на такси, а также отправка уведомлений пользователям о статусе заказов.

#### gRPC Методы:
- **CreateOrder(CreateOrderRequest) returns (CreateOrderResponse)** - создание нового заказа на такси.
- **UpdateOrder(UpdateOrderRequest) returns (UpdateOrderResponse)** - обновление статуса заказа на такси.
- **CancelOrder(CancelOrderRequest) returns (CancelOrderResponse)** - отмена заказа на такси.
- **SendNotification(SendNotificationRequest) returns (SendNotificationResponse)** - отправка уведомления пользователю о статусе его заказа.

#### 5.4 payment-service
- Технологии: Spring Boot, Hibernate, PostgreSQL.
- Функции: управление платежами и транзакциями, включая инициирование платежей и получение их статуса.

#### gRPC Методы:
- **InitiatePayment(InitiatePaymentRequest) returns (InitiatePaymentResponse)** - инициирование платежа для заказа.
- **GetPaymentStatus(GetPaymentStatusRequest) returns (GetPaymentStatusResponse)** - получение статуса платежа по идентификатору.

#### 5.5 geolocation-service
- Технологии: Spring Boot.
- **Функции**: определение географического положения пользователей и такси, чтобы обеспечить точное отслеживание заказов.
  
#### gRPC Методы:
- **GetUserLocation(GetUserLocationRequest) returns (GetUserLocationResponse)** - получение местоположения пользователя по идентификатору.
- **GetTaxiLocation(GetTaxiLocationRequest) returns (GetTaxiLocationResponse)** - получение местоположения такси по идентификатору.

### 6. Дополнительные аспекты

- Docker: Создание Docker контейнеров для упрощения развертывания микросервисов.
- Kubernetes (K8s): Оркестрация контейнеров и автоматизация развертывания и управления микросервисами.
- Kafka: Обмен сообщениями между микросервисами, особенно для уведомлений и событий.
- Liquibase: Управление миграциями баз данных.
- OpenAPI: Документация API для всех микросервисов.
- Helm: Упрощение развертывания в Kubernetes.
