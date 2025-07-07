# PARKMATE-USER-SERVICE

ParkMate 프로젝트의 사용자 관련 기능을 담당하는 마이크로서비스입니다.

## 1. 프로젝트 개요

본 서비스는 ParkMate 애플리케이션의 핵심적인 사용자 정보를 관리합니다. 회원 가입, 프로필 관리, 차량 정보, 즐겨찾기 등 사용자 중심의 기능을 제공하며, 다른 마이크로서비스와 연동하여 일관된 사용자 경험을 보장합니다.

## 2. 주요 기능

- **사용자 관리**:
    - 일반 회원 가입 및 소셜 로그인
    - 사용자 프로필 정보 조회 및 수정
    - 사용자 포인트 조회
- **차량 관리**:
    - 사용자 차량 정보 등록 및 조회
    - 등록된 차량 정보 삭제
    - 기본 차량 설정
- **즐겨찾기 관리**:
    - 특정 주차장을 즐겨찾기로 추가
    - 사용자별 즐겨찾기 목록 조회
    - 즐겨찾기 삭제
- **메시지 큐 연동**:
    - Kafka를 통해 다른 서비스(리뷰 등)에서 발생하는 이벤트 수신 및 처리
    - 사용자 정보 변경 시 관련 이벤트를 발행하여 데이터 동기화

## 3. 기술 스택

- **Framework**: Spring Boot
- **Language**: Java
- **Build Tool**: Gradle
- **Database**: JPA (Hibernate)
- **Messaging**: Apache Kafka
- **API Documentation**: Swagger

## 4. 실행 방법

### 환경 설정

- Java 11 이상
- Gradle

### 로컬 실행

1.  **레포지토리 클론**:
    ```bash
    git clone https://github.com/3-park-mate/PARKMATE-USER-SERVICE.git
    cd PARKMATE-USER-SERVICE
    ```

2.  **프로젝트 빌드**:
    ```bash
    ./gradlew build
    ```

3.  **애플리케이션 실행**:
    ```bash
    java -jar build/libs/userservice-0.0.1-SNAPSHOT.jar
    ```

### Docker 실행

프로젝트 루트 디렉토리에서 아래 명령어를 실행하여 Docker 컨테이너로 서비스를 실행할 수 있습니다.

```bash
docker-compose -f docker-compose-user.yml up -d
```

## 5. API 엔드포인트

API 명세는 Swagger를 통해 확인하실 수 있습니다. 애플리케이션 실행 후 아래 URL로 접속하세요.

- `http://localhost:8080/swagger-ui/index.html`

### 주요 API 목록

#### 사용자 (Users)

- `POST /users/register`: 회원 가입
- `POST /users/register/social`: 소셜 회원 가입
- `GET /users/{userId}`: 사용자 정보 조회
- `PATCH /users/{userId}`: 사용자 정보 수정
- `GET /users/{userId}/point`: 사용자 포인트 조회

#### 차량 (User Vehicle)

- `POST /users/vehicles`: 차량 등록
- `GET /users/{userId}/vehicles`: 등록된 모든 차량 조회
- `DELETE /users/vehicles/{vehicleId}`: 차량 삭제
- `PATCH /users/vehicles/{vehicleId}/default`: 기본 차량 설정

#### 즐겨찾기 (User Favorite)

- `POST /users/favorites`: 즐겨찾기 추가
- `GET /users/{userId}/favorites`: 즐겨찾기 목록 조회
- `DELETE /users/favorites/{favoriteId}`: 즐겨찾기 삭제

#### 내부 통신 (Internal)

- `GET /internal/users/{userId}/name`: (다른 서비스용) 사용자 이름 조회