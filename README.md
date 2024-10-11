# ChecK-Ads

![image](https://github.com/user-attachments/assets/3b029b92-a9c1-450f-b830-571b694e430d)

고객에게는 신속한 컨설팅을, 컨설턴트에게는 손쉬운 고객관리를 제공하는   
K-Ads 컨설팅 통합 관리 플랫폼 "ChecK-Ads"을 기획하였습니다.

직무변경 이전에 제안수행부에서 상품 컨설팅을 담당하며 느꼈던 불편함을 이번 프로젝트를 통해 해결해보고자 하였고, 담당했던 상품 중 하나인 K-Ads를 주제로 진행하였습니다.

## Model
[www.msaez.io/#/153048693/storming/Check-Ads](https://www.msaez.io/#/57468957/storming/project-final)

## 서비스 기획

### K-Ads란

![image](https://enterprise.kt.com/entpf/images/product/webResource/P_PD_AI_BD_004_wide-banner_m.jpg)

"잠재고객을 진짜 고객으로!"   

K-Ads는 kt의 메시지 기반 타겟 광고/마케팅 서비스입니다.

kt가 보유한 방대한 통신 Big Data 기반으로,
고객의 관심사, 라이프 스타일 등
이용패턴을 분석하여 광고지수화하고,
다양한 업종분류체계로 광고주 맞춤형 타겟 정교화하여,
효과적인 마케팅을 제공합니다.

### AS-IS TO-BE

![image](https://github.com/user-attachments/assets/bb5c279c-8ba0-44da-9efd-d6b4f58d0a96)

"사람들 간의 소통으로 지연되는 컨설팅 진행 과정"

컨설팅을 진행하였을때, 대부분의 소통이 영업대표와의 전화 또는 이메일로 이루어졌습니다. 때문에 빠른 일정을 잡고 싶은 고객과, 고객의 정보가 필요한 컨설턴트 사이의 소통이 지연되고는 했습니다.

![image](https://github.com/user-attachments/assets/fd09eef2-99d0-47d1-b8f6-0e8fe3544169)


<b>ChecK-Ads</b>를 통해 고객과 컨설턴트의 원활한 의사소통을 도울 것입니다.

## 아키텍쳐 설계

### 요구사항 분석

![image](https://github.com/user-attachments/assets/5dd96a31-5809-47f5-be17-24faef24159a)

고객의 <U>일정이 지연되고 답답하다는 페인포인트</U>와, 컨설턴트의 <u>고객 관리의 효율화라는 요구사항</u>을 해결하고 위한 ChecK-Ads 서비스를 기획하였습니다.
- 고객
  - 고객은 컨설팅을 요청한다.
  - 고객은 원하는 일자에 컨설팅 확정을 신속하게 받고 싶다.
- 컨설턴트
  - 컨설턴트는 고객의 요구사항을 정확하게 파악하고 싶다.
  - 컨설턴트는 전체 고객 관리를 하는 도구가 필요하다.
  - 컨설턴트는 전체 컨설팅 일정 관리가 필요하다.

### 기능 명세서

![image](https://github.com/user-attachments/assets/c10bda1f-a452-455b-a265-9d9d8dd5beb6)

고객과 컨설턴트의 요구사항을 고려하여, 크게 고객/일정/관리자 페이지를 구성하였습니다.

고객은 견적을 등록하고 컨설팅을 요청할 수 있고, 컨설턴트는 고객 정보와 컨설팅 일정 관리를 할 수 있습니다. 

### 서비스 시나리오

![image](https://github.com/user-attachments/assets/368fd1f4-2ad7-4351-aa14-8dd2548e677c)

서비스 시나리오는 다음과 같습니다.
- <b>기능적 요구사항 </b>
  1. 고객이 상품을 선택하여 을 등록한다.
  1. 견적 등록과 함께 컨설팅을 요청한다.
  1. 컨설팅이 요청되면 고객과 컨설턴트에게 컨설팅 예약 내역이 전달된다.
  1. 고객은 예약을 변경 및 취소할 수 있다.
  1. 예약 사항이 취소될 경우 관리자에게 취소 내역(Message)이 전달된다.
  1. 컨설팅이 요청되면 컨설턴트가 배정된다.
  1. 컨설턴트가 배정을 확정하면 컨설팅 일정이 생성된다.
  1. 컨설팅이 완료되면 컨설턴트는 컨설팅 상태를 업데이트한다.
  1. 컨설팅이 완료되면 고객은 피드백, 컨설턴트는 보고서 작성을 요청받는다.
  1. 관리자는 고객 페이지에서 고객별 컨설팅 기록을 조회할 수 있다.
  1. 컨설팅 후 피드백과 보고서가 작성되지 않으면 작성 요청 알림을 받는다.
  1. 고객과 관리자는 피드백 및 보고서를 수정/삭제할 수 있다.
  1. 고객과 관리자는 히스토리 페이지에서 작성된 피드백 및 보고서를 조회할 수 있다.

- <b>비기능적 요구사항</b>
  - 트랜잭션
    - 컨설턴트가 배정되지 않거나 컨설턴트가 확정하지 않은 컨설틴은 진행될 수 없다.
  - 장애격리
    - 메시지 전송 기능이 수행되지 않더라도 컨설팅 요청은 365일 24시간 받을 수 있어야 한다.
    - 예약시스템이 과중되면 고객의 요청을 잠시동안 받지 않고 잠시 후에 하도록 유도한다.
  - 성능
    - 모든 컨설팅 요청에 대한 예약 및 컨설팅 진행 상태를 확인할 수 있어야 한다.
    - 컨설팅의 진행 상태가 바뀔 때마다 메시지로 알림을 줄 수 있어야 한다.
    - 피드백 및 보고서 작성 상태가 바뀔 때마다 스케줄러가 관리 상태를 확인할 수 있어야 한다.

### 체크포인트

- 분석 설계

  - 이벤트스토밍:

    - 스티커 색상별 객체의 의미를 제대로 이해하여 헥사고날 아키텍처와의 연계 설계에 적절히 반영하고 있는가?
    - 각 도메인 이벤트가 의미있는 수준으로 정의되었는가?
    - 어그리게잇: Command와 Event 들을 ACID 트랜잭션 단위의 Aggregate 로 제대로 묶었는가?
    - 기능적 요구사항과 비기능적 요구사항을 누락 없이 반영하였는가?

  - 서브 도메인, 바운디드 컨텍스트 분리
    - 팀별 KPI 와 관심사, 상이한 배포주기 등에 따른  Sub-domain 이나 Bounded Context 를 적절히 분리하였고 그 분리 기준의 합리성이 충분히 설명되는가?
    - 적어도 3개 이상 서비스 분리
    - 폴리글랏 설계: 각 마이크로 서비스들의 구현 목표와 기능 특성에 따른 각자의 기술 Stack 과 저장소 구조를 다양하게 채택하여 설계하였는가?
    - 서비스 시나리오 중 ACID 트랜잭션이 크리티컬한 Use 케이스에 대하여 무리하게 서비스가 과다하게 조밀히 분리되지 않았는가?

  - 컨텍스트 매핑 / 이벤트 드리븐 아키텍처
    - 업무 중요성과  도메인간 서열을 구분할 수 있는가? (Core, Supporting, General Domain)
    - Request-Response 방식과 이벤트 드리븐 방식을 구분하여 설계할 수 있는가?
    - 장애격리: 서포팅 서비스를 제거 하여도 기존 서비스에 영향이 없도록 설계하였는가?
    - 신규 서비스를 추가 하였을때 기존 서비스의 데이터베이스에 영향이 없도록 설계(열려있는 아키택처)할 수 있는가?
    - 이벤트와 폴리시를 연결하기 위한 Correlation-key 연결을 제대로 설계하였는가?
- 구현
  - [DDD] 분석단계에서의 스티커별 색상과 헥사고날 아키텍처에 따라 구현체가 매핑되게 개발되었는가?
    - Entity Pattern 과 Repository Pattern 을 적용하여 JPA 를 통하여 데이터 접근 어댑터를 개발하였는가
    - 분석단계에서의 유비쿼터스 랭귀지 (업무현장에서 쓰는 용어) 를 사용하여 소스코드가 서술되었는가?
  - Request-Response 방식의 서비스 중심 아키텍처 구현
    - 마이크로 서비스간 Request-Response 호출에 있어 대상 서비스를 어떠한 방식으로 찾아서 호출 하였는가? (Service Discovery, REST, FeignClient)

  - 이벤트 드리븐 아키텍처의 구현
    - 카프카를 이용하여 PubSub 으로 하나 이상의 서비스가 연동되었는가?
    - Correlation-key: 각 이벤트 건 (메시지)가 어떠한 폴리시를 처리할때 어떤 건에 연결된 처리건인지를 구별하기 위한 Correlation-key 연결을 제대로 구현 하였는가?
    - Message Consumer 마이크로서비스가 장애상황에서 수신받지 못했던 기존 이벤트들을 다시 수신받아 처리하는가?
    - Scaling-out: Message Consumer 마이크로서비스의 Replica 를 추가했을때 중복없이 이벤트를 수신할 수 있는가

## 기능 구현

### 분석/설계

![image](https://github.com/user-attachments/assets/aced1f72-a735-420a-8a35-c50ea6f98b5a)

![image](https://github.com/user-attachments/assets/0499f047-4f3a-45da-a002-cab69a76071e)


MSA로 설계하여 기존의 직무 중심의 팀에서 서비스 중심의 팀으로 구조화하여 보다 독립적으로 서비스를 구현할 수 있습니다.

### Event Storming

#### 이벤트 도출

![image](https://github.com/user-attachments/assets/5dd7154b-231b-4878-925d-a7c18e898c39)

서비스 시나리오를 기반으로 1차적으로 이벤트를 도출하여 나열하였습니다.

![image](https://github.com/user-attachments/assets/e7ffdb3b-78c1-4f70-8c60-8fae8df9fee4)

이후 서비스의 목적에 부합하지 않은 부적격 이벤트를 탈락하여 이벤트를 구성하였습니다.

#### 초기 모형 완성

![image](https://github.com/user-attachments/assets/a3580447-28cc-438f-b012-046c600d7dea)


Actor와 Command, Aggregate 등을 함께 배치한 후, Bounded Context로 묶어 트랜잭션이 유지되어야 하는 단위로 구분하였습니다.

#### 컨택스트 매핑

![image](https://github.com/user-attachments/assets/a3bc4cbc-1a67-42af-a949-b516c3304d7b)

초기 모형에서 서비스가 독립적으로 분리되지 않은 부분을 보완하여 재매핑하였습니다.

#### 요구사항 검증

![image](https://github.com/user-attachments/assets/746dd99c-e63f-4fdd-bc0e-254447ecb639)


시나리오 기반으로 요구사항을 검증하며 개선된 모형을 최종 검증하였습니다.

기존에는 모든 데이터를 CQRS를 통해 시각화하는 것으로 기획하였으나, 비용의 최적화를 위해 각 Aggregate에서 ReadModel을 통해 조회하는 것으로 개선하였습니다.

#### 연결성 수정

![image](https://github.com/user-attachments/assets/4c4c394d-a788-4714-9824-33302dc7b24f)


consulting과 feedback/report aggregate 간의 연결성을 보완하기 위해, 컨설팅이 생성되면 상태 변경이라는 policy가 동작하여 연결되도록 수정하였습니다.

#### 스케줄러 추가

![image](https://github.com/user-attachments/assets/ac72636b-0614-4d17-afcb-d934d149affe)


피드백과 보고서가 작성되지 않는 경우, 알림을 통해 각각 고객과 컨설턴트에게 요청하기 위해 상태를 관리하는 스케줄러를 추가하였습니다.


## API 게이트웨이
gateway 스프링부트 App을 추가 후 application.yaml내에 각 마이크로 서비스의 routes 를 추가하고 gateway 서버의 포트를 8080 으로 설정함

- application.yaml 예시
```
spring:
  profiles: default
  cloud:
    gateway:
#<<< API Gateway / Routes
      routes:
        - id: user
          uri: http://localhost:8082
          predicates:
            - Path=/users/**, 
        - id: reservation
          uri: http://localhost:8083
          predicates:
            - Path=/reservations/**, 
        - id: consulting
          uri: http://localhost:8084
          predicates:
            - Path=/consultings/**, 
        - id: message
          uri: http://localhost:8085
          predicates:
            - Path=, 
        - id: feedback
          uri: http://localhost:8086
          predicates:
            - Path=/feedbacks/**, 
        - id: report
          uri: http://localhost:8087
          predicates:
            - Path=/reports/**, 
        - id: frontend
          uri: http://localhost:8080
          predicates:
            - Path=/**
```
Kubernetes용 Deployment.yaml 을 작성하고 Kubernetes에 Deploy를 생성함
- Deployment.yaml 예시

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: gateway
  labels:
    app: gateway
spec:
  replicas: 1
  selector:
    matchLabels:
      app: gateway
  template:
    metadata:
      labels:
        app: gateway
    spec:
      containers:
        - name: gateway
          image: seungju030/gateway:241008
          ports:
            - containerPort: 8080

```

- Kubernetes용 Service.yaml을 작성하고 Kubernetes에 Service/LoadBalancer을 생성하여 Gateway 엔드포인트를 확인함.
```
apiVersion: v1
kind: Service
metadata:
  name: gateway
  labels:
    app: gateway
spec:
  ports:
    - port: 8080
      targetPort: 8080
  selector:
    app: gateway
  type: LoadBalancer
```
  
- 프론트-API게이트웨이 연결
  
  ![프론트-API게이트웨이 연결](https://github.com/user-attachments/assets/29fdc938-0da9-4541-9632-8a338bcde6e4)

- Kubernetes에 생성된 Deploy. 확인
  
  ![deploy](https://github.com/user-attachments/assets/ec70ca41-a999-4cfe-9f90-93ab0525ee53)

- Service 및 엔드포인트 확인
  
  ![svc](https://github.com/user-attachments/assets/70e294ec-bee4-4d27-8c2a-0c949669b506)

  
## UI 화면 

### 메인화면

![image](https://github.com/user-attachments/assets/ec5c8817-03a0-4d25-a96f-7327f13d484c)

### 알림 기능 

![image](https://github.com/user-attachments/assets/58196233-4139-44c3-8f70-90624f18663f)

### 고객 화면 

![image](https://github.com/user-attachments/assets/56081849-4e97-41bd-85e0-aef6292fc575)

### 관리자 화면 

![image](https://github.com/user-attachments/assets/18f60c0c-cf00-49d0-9e53-b4efe40bc0fc)

![image](https://github.com/user-attachments/assets/d8a805e5-2150-44b3-b0f5-ec0ff39b1647)

### 보고서 작성 및 피드백 작성 화면

![image](https://github.com/user-attachments/assets/624542db-2958-46fe-9a02-0d242c2f1f4f)


## 기대효과
![image](https://github.com/user-attachments/assets/790f339b-2937-45b5-a832-a124a08654a2)
