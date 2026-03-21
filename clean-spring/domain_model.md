# Clean Spring 도메인 모델

## Clean Spring 도메인
- 이 도메인은 무엇 무엇을 제공한다.

## 도메인 모델

### 회원
_Entity_
#### 속성
- `email`: 이메일 - ID
- `nickname`: 닉네임
- `passwordHash`: 비밀번호
- `status`: 회원 상태

#### 행위
- `constructor()`: 회원 생성: email, nickname, password, status
- `activate()`: 가입을 완료 시킨다
- `deactivate()`: 탈퇴시킨다

#### 규칙
- 회원 생성후 상태는 가입 대기(MemberStatus.PENDING)
- 일정 조건을 만족하면 가입 완료가 된다
- 가입 대기 상태에서만 가입 완료가 될 수 있다
- 가입 완료 상태(MemberStatus.ACTIVE)에서는 탈퇴할 수 있다

### 회원 상태(MemberStatus)
_Enum_

#### 상수
- `PENDING`: 가입 대기
- `ACTIVE`: 가입 완료
- `DEACTIVATED`: 탈퇴



### 강사
### 강의
### 수업
### 섹션
### 수강
### 진도

## 다이어그램

```mermaid
classDiagram
    회원 "1" -- "0..1" 강사
    강사 "1" -- "0..*" 강의
    수강 "0..*" --> "1" 회원
```
