## 📦 OopsFire Wolf — Backend API

This is the **backend API** for the **OopsFire Wolf NFT Project**, built using **Spring Boot**.
It connects to a MySQL database, provides REST APIs to handle NFTs, tracks wallet actions, and serves static images.

<img width="1906" height="974" alt="image" src="https://github.com/user-attachments/assets/7feeecdb-f265-4c82-aa91-68e5ab0700b1" />

### 🚀 Features

- RESTful APIs to get NFTs and buy NFTs
- Connects with frontend via HTTP
- Stores wallet usage logs in MySQL
- Serves static image assets (e.g. `/assets/nfts.png`)
- Blocks bad IPs (custom rules)

### 🛠 Tech Stack

- Java 17
- Spring Boot 3.4.7
- Spring Web (REST)
- Spring Data JPA
- MySQL Database
- Maven

### 📁 Folder Structure

```
backend/
├── src/main/java/com/oopsfirewolf/nfts
│   ├── controller/
│   ├── model/
│   ├── repository/
│   ├── service/
│   └── OopsFireWolfNfTsApplication.java
├── src/main/resources/static/assets/
├── src/main/resources/application.properties
└── pom.xml
```

### ⚙️ Installation & Running (Development)

Make sure you have:

- Java 17
- Maven
- A running MySQL server (or use Docker)

#### 1️⃣ Clone this repo

```bash
git clone https://github.com/Asadullah-nadeem/digital-NFT-Backend.git
cd digital-NFT-Backend
```

#### 2️⃣ Configure MySQL in `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nftdb
spring.datasource.username=root
spring.datasource.password=your_password
```

Make sure database `nftdb` exists.

#### 3️⃣ Run the backend

```bash
mvn spring-boot:run
```

Backend will start at: `http://localhost:8080`

### 📡 API Endpoints

| Method        | Endpoint             | Description                |
| ------------- | -------------------- | -------------------------- |
| Remove it GET | `/api/nfts`        | Get all NFTs               |
| POST          | `/api/nfts/buy`    | Log buy actions (wallet)   |
| GET           | `/assets/nfts.png` | Get NFT image from backend |

### 🐳 Run with Docker

You can also run this using Docker:

```bash
docker build -t oopsfire-backend .
docker run -p 8080:8080 oopsfire-backend
```

### 🧪 Sample Buy JSON Request

```json
POST http://localhost:8080/api/nfts/buy

{
  "wallet": "0x1234567890abcdef",
  "nftId": 1,
  "accessKey": "oopsfire-secret-key"
}
```

### 👨‍💻 Author

Made with ❤️ by [Asadullah Nadeem](https://github.com/asadullah-nadeem)
