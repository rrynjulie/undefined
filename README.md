# 🏬 저기어때 - 숙박 조회 및 예약, 승차권 조회 서비스
> **이용자는 숙박 조회 및 예약, 승차권 조회가 가능하고, 판매자는 숙소 및 예약 일정 관리가 가능한 서비스입니다.**<br>
> **개발 기간: 2024.06 (3주)**

<div align="center">
<img width="400" alt="저기어때 로고" src="https://i.postimg.cc/QxR6XP27/MainLogo.png">
</div>

## 🚀 프로젝트 소개


## 🛠️ 기술 스택
### Environment
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=coffeescript&logoColor=2F2625)
![Spring](https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![SpringBoot](https://img.shields.io/badge/SpringBoot-6DB33F.svg?style=for-the-badge&logo=SpringBoot&logoColor=white)
![Spring Security]
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

### Config
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

### Development
![HTML5](https://img.shields.io/badge/html5-E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6.svg?style=for-the-badge&logo=Css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![BootStrap](https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)
![JQuery](https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-000000?style=for-the-badge&logo=mybatis&logoColor=white)

### Deployment
![AWS EC2](https://img.shields.io/badge/AWS%20EC2-232F3E?style=for-the-badge&logo=amazonec2&logoColor=white)
![Amazon RDS](https://img.shields.io/badge/Amazon%20RDS-527FFF?logo=amazonrds&logoColor=fff&style=for-the-badge)

### Communication
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)

## 👥 팀 소개
|     이재혁     |     임동준     |     임예나     |     정준영     |     최영재     |     최은정     |                                                   
| :------------------------------------------------------------------------------: | :------------------------------------------------------------------------------: | :------------------------------------------------------------------------------: | :------------------------------------------------------------------------------: | :------------------------------------------------------------------------------: | :------------------------------------------------------------------------------: |
|   <img width="160px" src="https://avatars.githubusercontent.com/u/93297796?v=4" />    |                      <img width="160px" src="https://avatars.githubusercontent.com/u/93297796?v=4" />    |                   <img width="160px" src="https://avatars.githubusercontent.com/u/93297796?v=4"/>   |                   <img width="160px" src="https://avatars.githubusercontent.com/u/93297796?v=4"/>   |                   <img width="160px" src="https://avatars.githubusercontent.com/u/93297796?v=4"/>   |                   <img width="160px" src="https://avatars.githubusercontent.com/u/93297796?v=4"/>   |
|   [@Jaehyeok](https://github.com/JaeHyeokee)   |    [@Dongjoon](https://github.com/leemdongjoon2267)  | [@Yena](https://github.com/rrynjulie)  | [@Junyeong](https://github.com/jeongjunyeong)  | [@Yeongjae](https://github.com/yeongjae0113)  | [@Eunjeong](https://github.com/eunjeong0911)  |
| CRUD | CRUD | CRUD | CRUD | CRUD | CRUD |

## 🙋‍♂️ 역할 및 기여도
| 이름 | 역할 및 기여도 |
|------|--------------|
| 이재혁 | - 🚀 프로젝트 아키텍처 설계 및 개발 총괄<br>- 🎨 전체적인 UI 디자인<br>- ⚙️ AJAX를 이용한 필터 구현<br>- 💾 객실 CRUD 구현<br>- 🌐 EC2와 RDS를 활용한 배포 및 운영 환경 구축 및 관리|
| 임동준 | - 🧪 공공데이터 API를 이용한 승차권 조회<br>- 💾 숙소 CRUD 구현<br>- 🌐 EC2와 RDS를 활용한 배포 및 운영 환경 구축 및 관리|
| 임예나 | - 💾 Spring Security를 활용한 OAuth2 유저 CRUD 구현<br>- 📊 ERD 설계|
| 정준영 | - 💾 권한 CRUD 구현<br>- 💾 좋아요 CRUD 구현<br>- 📊 ERD 설계|
| 최영재 | - 🎨 JavaScript를 이용한 달력 구현<br>- ⚙️ AJAX를 이용한 필터 구현<br>- 💾 숙소 CRUD 구현<br>- 💾 후기 CRUD 구현<br>- 🧪 Google Map API를 이용한 지도 구현|
| 최은정 | - 🎨 JavaScript를 이용한 중복 처리<br>- 💾 예약 CRUD 구현<br>- 📊 ERD 설계|

## 📊 ERD (Entity-Relationship Diagram)
![ERD](https://i.postimg.cc/PJs1bWs9/ERD.png)

프로젝트의 데이터베이스는 위와 같은 ERD를 기반으로 설계되었습니다. 

- `user` 테이블 : 사용자 정보를 저장하는 테이블입니다.
- `lodging` 테이블: 숙소 정보를 저장하는 테이블입니다.
- `room` 테이블: 객실 정보를 저장하는 테이블입니다.

## 🎯 주요 기능

### 숙박 조회 및 예약
- 이용자는 날짜와 인원을 설정할 수 있습니다.
- 다양한 조건에 맞는 숙소를 조회할 수 있습니다.
- 조회한 숙소 중 마음에 드는 숙소를 예약할 수 있습니다.

### 승차권 조회
- 승차편, 날짜, 버스 등급을 설정할 수 있습니다.
- 조건에 맞는 승차권 정보를 조회할 수 있습니다.

### 판매자 숙소 및 예약 관리
- 판매자는 본인의 객실에 대한 예약 일정을 조회할 수 있습니다.
- 판매자는 본인의 숙소를 등록, 조회, 수정, 삭제할 수 있습니다.
- 판매자는 본인의 객실을 등록, 조회, 수정, 삭제할 수 있습니다.

### 관리자 회원 관리
- 관리자는 각 회원 별 권한을 부여하거나 삭제할 수 있습니다.

## 📺 화면 구성

| 메인 페이지 | 숙박 예약 |
| :-------------------------------------------: | :-------------------------------------------: |
| <img width="400" alt="메인 페이지" src=""/> | <img width="400" alt="숙박 예약" src=""/> |
| 사용자는 메인 페이지에서 . | 페이지에서는 습니다. |

| 승차권 조회 |
| :-------------------------------------------: |
| <img width="400" alt="승차권 조회" src=""/> |
| 승차권 조회 페이지에서는 있습니다. |

## 🎉 프로젝트 결과 및 성과
- 🎯 사용자의 요구사항을 반영하여 만족도를 높였습니다.
- 🌐 Spring Boot를 활용하여 안정적이고 효율적인 서버를 구축하였습니다.
- 🗄️ 대량의 데이터를 효과적으로 관리하기 위해 검색 및 조회 속도를 개선하였습니다.
- ⏰ 다.
- ✅ 하였습니다.

## 🚨 트러블슈팅 가이드
### 데이터 수집 배치 작업 실패
- 증상 : 문제가 발생하였습니다.
- 원인 : 파악되었습니다.
- 해결 : 안정성을 높였습니다.

이러한 트러블슈팅 경험을 바탕으로 서비스의 안정성과 성능을 지속적으로 개선해 나갈 수 있었습니다. 앞으로도 모니터링과 피드백을 통해 발생할 수 있는 문제를 사전에 방지하고 대비할 수 있도록 노력하겠습니다.
