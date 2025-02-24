## CHANGE LOG

#### 1-1) 프로젝트 환경 구성
✅ Spring Boot 환경 구성

#### 1-2) DB 연동
✅ MySQL DB 연동 및 JPA Dependency 추가 

---

#### 2-1) JPA를 활용한 기본 CRUD 구현
✅ lombok & simple JPA 구현

#### 2-2) Swagger 문서와 JPA 추가 구현
✅ Swagger 연동

✅ JPA w/ relationship


#### 2-3) Kakao Social Login 1
✅ 회원 등록

✅ 카카오 소셜 로그인 지원

#### 2-4) Kakao Social Login 2
✅ 카카오 소셜 로그인 정보 확인

✅ 임직원 카카오 닉네임 정보 등록

✅ 확인된 정보가 등록된 유저의 정보인지 검증

---

##### 3-1) JWT, JUNIt
✅ JWT 토큰 유틸 및 Junit 테스트 작성

#### 3-2) Spring Security
✅ Spring Security 적용

✅ 토큰 검증 로직 추가

#### 3-3) RBAC
✅ API 별 RBAC 설정 추가

----

#### 4-1) App2App 1
- IT팀 담당자는 시스템의 API를 등록 가능


- IT팀 담당자는 캘린더 시스템이 회의실 예약 시스템의 예약 기능에 접근이 가능하도록 허용


- IT팀 담당자는 회의실 시스템이 캘린터 시스템의 사용자 캘린더 조회 접근이 가능하도록 허용

✅ 시스템 생성 및 시스템 별 기능을 API로 등록

- 캘린더 시스템 : 캘린더 일정 조회, 일정 추가, 일정 삭제

- 회의실 시스템 : 회의실 예약 조회, 예약 생성, 예약 삭제

- 휴가 시스템 : 휴가 조회, 휴가 생성, 휴가 삭제

✅ 시스템 별 권한 세팅

- 캘린더 시스템이 회의실 예약 시스템의 예약 기능에 접근 가능하도록 허용

- 회의실 시스템은 캘린더 시스템의 사용자 캘린더 조회 접근이 가능하게 허용

#### 4-3) App2App 2
✅ app2app 토큰 발급
✅ app2app 토큰 검증

---
#### 5-1) K8S 배포 1
✅ multi 환경을 위한 application properties 설정
✅ 환경 구축
✅ MySQL k8s 클러스터 배포


#### 5-2) K8S 배포 1
✅ k8s 용 application properties 설정
✅ docker image 만들기
✅ docker image push
✅ deployment yaml 추가 및 적용

---

#### 6-1) redis setUp
✅ local redis setUp
✅ Spring Application에 redis 연동

####  6-2) threshold 기능 적용
✅ threshold, throttling(알고리즘: token bucket) 기능 적용