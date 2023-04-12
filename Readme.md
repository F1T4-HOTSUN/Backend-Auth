# BE AUTH README(지금은 사라진..)

Tags: Auth, BE

# 👁️‍🗨️ 개요

인증 인가를 담당하는 서버입니다

모든 서버는 해당 서버를 거쳐 인증, 인가 과정을 거치고, 모든 트래픽을 제어하는 API Gateway 역할을 담당합니다. 

istio 를 도입하면서 이러한 역할을 Istio 에 위임하고 지금은 사라진 비운의 서버입니다.

<br>

# 🌆 기능 및 아키텍처


초기 아키텍처는 해당 서버에서 인증,인가 절차를 거치고 트래픽을 뒷단의 서버들에게 넘겨주는 형태로 아키텍처를 구상하였습니다. 

Auth 는 Spring Security 를 통해 인증인가 처리를 하였습니다. 

(Refresh + Access 토큰 방식을 이용하여 구현) 

1. 로그인 요청
    
    로그인 요청이  member 서버로 요청을 보내 먼저 아이디, 비밀번호의 유효성을 확인합니다. 해당 과정에 성공하면 member 서버는 토큰을 생성하기 위한 정보들을  Auth 서버에 반환하여 줍니다(memberId, Role)
    
    <br>
    
2. member 서버로부터 받은 정보를 기반으로 Refresh 토큰과 Access 토큰을 발급합니다. ( x-authorization , r-authorization)
    
    ![Untitled](https://user-images.githubusercontent.com/80504636/231368445-52aa4ef0-2dd4-4d91-a9e5-73c9612eb9fb.png)
    
    <br>

3. 발급한 토큰을 response Header 에 담아 클라이언트에게 보냅니다.
    
    ![Untitled](https://user-images.githubusercontent.com/80504636/231368448-afb14b6c-4021-4418-ad0c-45381536b3e9.png)

<br>

4. 이후 회원만 접근 가능한 API로 요청이 온다면 Spring Security 가 헤더의 토큰값의 유효성을 확인하고, 트래픽을 통제합니다.
    
    (유효하지 않은 토큰의 경우 트래픽을 막고 401 에러를 클라이언트에게 반환합니다.)
    
    ![Access Token 과 Refresh Token 의 인증 절차 코드 일부 발췌](https://user-images.githubusercontent.com/80504636/231368452-70d26eae-3b24-4109-8b65-ed0b47e88940.png)
    
    Access Token 과 Refresh Token 의 인증 절차 코드 일부 발췌
    

하지만 Istio 를 도입하고 난 뒤에 해당 서버가 하는 역할은 Istio 가 대신하게 되었습니다. 

따라서 토큰 발급 기능을 member 서버로 넘기고, 해당서버는 없어졌습니다.

<br>

# 🪵 개발환경

- Java 11

- Spring Boot

- Spring Security

- Spring Cloud Open Feign, Spring Data JPA , Spring Data Redis

- Redis - 7.0.10

<br>

# 💬 회고

## 프로젝트 진행시 주안점

- Spring Security 를 통한 인증, 인가 처리와 스프링 필터에 대한 이해도를 올리고자 하였습니다.

- MSA구조에 대한 이해도를 올리기 위해 아키텍처 구성 등에 초점을 맞춰 진행하고자 하였습니다.

<br>

## 한계점 및 개선 사항

- Istio 를 사용하면서 해당서버는 폐기되었습니다. Istio 를 고려한 설계를 초기단계부터 하였으면 이라는 아쉬움이 남습니다.

- gRPC 와 같은  MSA 구조에 더 최적화된 프로토콜을 사용하지 못한점이 아쉬움으로 남습니다.