# 5th Try

`Command` 는 `Aggregate` 의 상태를 변경하고 `Aggregate`는 상태 변경에 대한 `Event` 를 전달한다. 
이 세가지가 밀접한 연관성을 가지기 때문에 기존 시도와는 다르게 하나의 모듈로 정의하고 관련 프로젝트에서 사용하도록 했다. 

각 모듈의 이름에 신경이 쓰였는데 `Aggregate` 가 존재하는 모듈은 도메인적 이름을 그대로 사용하고 이것에 대해 명령을 수행하는 모듈은 `api` 라는 postfix를 붙혔다. `Query` 를 수행하는 모듈은 `qeury-api`
postfix 를 분혔다. 

## `Tries`

- [First](https://github.com/yangwansu/hello-axon/tree/first-try) 
- [2th](https://github.com/yangwansu/hello-axon/tree/second-try) 
- [3th](https://github.com/yangwansu/hello-axon/tree/third-try) 
- [4th](https://github.com/yangwansu/hello-axon/tree/fourth-try) 
- [5th](https://github.com/yangwansu/hello-axon/tree/5th-try) 