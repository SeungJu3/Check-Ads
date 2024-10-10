# 

## Model
www.msaez.io/#/153048693/storming/Check-Ads

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- user
- reservation
- consulting
- message
- feedback
- report


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- user
```
 http :8088/users id="id" name="name" field="field" compName="compName" compNo="compNo" compTel="compTel" compAddr="compAddr" 
```
- reservation
```
 http :8088/reservations id="id" request="request" product="product" prodCnt="prodCnt" category="category" subCategory="subCategory" date="date" status="status" userId="userId" 
```
- consulting
```
 http :8088/consultings id="id" date="date" resId="resId" managerId="managerId" feedbackId="feedbackId" reportId="reportId" userId="userId" 
```
- message
```
```
- feedback
```
 http :8088/feedbacks id="id" constId="constId" content="content" userId="userId" 
```
- report
```
 http :8088/reports id="id" constId="constId" content="content" managerId="managerId" 
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

---
# ChecK-Ads

[ChecK-Ads 이미지]

고객에게는 신속한 컨설팅을,    
컨설턴트에게는 손쉬운 고객관리를 제공하는   

K-Ads 컨설팅 통합 관리 플랫폼 "ChecK-Ads"


## 1. 서비스 기획

### 1-1. K-Ads란

[k-ads 이미지]

"잠재고객을 진짜 고객으로!"   

K-Ads는 kt의 메시지 기반 타겟 광고/마케팅 서비스입니다.

kt가 보유한 방대한 통신 Big Data 기반으로,
고객의 관심사, 라이프 스타일 등
이용패턴을 분석하여 광고지수화하고,
다양한 업종분류체계로 광고주 맞춤형 타겟 정교화하여,
효과적인 마케팅을 제공합니다.

### 1-2. AS-IS TO-BE

[AS-IS 이미지]

사람들 간의 소통으로 지연되는 컨설팅 진행 과정을,   

[TO-BE 이미지] 

<b>ChecK-Ads</b>를 통해 고객과 컨설턴트의 원활한 의사소통을 도울 것입니다.

## 2. 아키텍쳐 설계

### 2-1. 요구사항 분석

[요구사항 이미지]

고객의 <U>일정이 지연되고 답답하다는 페인포인트</U>와, 컨설턴트의 <u>고객 관리의 효율화라는 요구사항</u>을 해결하고 위한 ChecK-Ads 서비스를 기획하였습니다.

### 2-2. 기능 명세서

[기능명세서 이미지]

고객과 컨설턴트의 요구사항을 고려하여, 크게 고객/일정/관리자 페이지를 구성하였습니다.

고객은 견적을 등록하고 컨설팅을 요청할 수 있고, 컨설턴트는 고객 정보와 컨설팅 일정 관리를 할 수 있습니다. 

### 2-3. 서비스 시나리오

[서비스 시나리오 이미지]

서비스 시나리오는 다음과 같습니다.

1. 고객이 상품을 선택하여 을 등록한다.
2. 견적 등록과 함께 컨설팅을 요청한다.
3. 컨설팅이 요청되면 컨설팅 예약 내역이 전달된다. -> 고객/관리자
4. 고객이 예약을 변경 및 취소할 수 있다.
5. 예약 사항이 취소될 경우 취소 내역(Message)이 전달된다.
6. 관리자는 고객 페이지에서 고객별 컨설팅 기록을 조회할 수 있다.
7. 컨설팅 후 고객은 피드백을, 관리자는 보고서를 작성할 수 있다.
8. 고객과 관리자는 피드백 및 보고서를 수정/삭제할 수 있다.
9. 고객과 관리자는 히스토리 페이지에서 작성된 피드백 및 보고서를 조회할 수 있다.

## 3. 기능 구현

### 3-1. 분석/설계

[as-is to-be 이미지]

MSA로 설계하여 기존의 직무 중심의 팀에서 서비스 중심의 팀으로 구조화하여 보다 독립적으로 서비스를 구현할 수 있습니다.

### 3-2. Event Storming

#### 1) 이벤트 도출

[이벤트 나열]

서비스 시나리오를 기반으로 1차적으로 이벤트를 도출하여 나열하였습니다.

[부적격 이벤트 탈락]

이후 서비스의 목적에 부합하지 않은 부적격 이벤트를 탈락하여 이벤트를 구성하였습니다.

#### 2) 초기 모형 완성

[1차 모형 완성]

Actor와 Command, Aggregate 등을 함께 배치한 후, Bounded Context로 묶어 트랜잭션이 유지되어야 하는 단위로 구분하였습니다.

#### 3) 컨택스트 매핑

[컨택스트 매핑]

초기 모형에서 서비스가 독립적으로 분리되지 않은 부분을 보완하여 재매핑하였습니다.

#### 4) 요구사항 검증

[요구사항 검증]

시나리오 기반으로 요구사항을 검증하며 개선된 모형을 최종 검증하였습니다.

기존에는 모든 데이터를 CQRS를 통해 시각화하는 것으로 기획하였으나, 비용의 최적화를 위해 각 Aggregate에서 ReadModel을 통해 조회하는 것으로 개선하였습니다.


