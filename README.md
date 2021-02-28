# Second Try

## 작성 순서 

1. Command 
2. Event 
3. Aggregate  
4. Query 
5. View
6. Controller


## [첫 번째 시도](https://github.com/yangwansu/hello-axon/tree/first-try) 와 다른점

하나의 Class 안에 여러 [Command](app/src/main/java/org/masil/hello/axon/inventory/Commands.java) 와 Event 를 같이 넣었다. 연관된 Command 들이 한눈에 들어와서 보기가 편했다.

상위 레벨 패키지를 어떻게 가져 갈지 고민이 되었다. command 와 query view 를 서로 분리했다. 

[CreateInventoryCommand](app/src/main/java/org/masil/hello/axon/inventory/Commands.java) 에 `@TargetAggregateIdentifier` 을 제거 했더니 아래 에러(does not contain a routing key.)를 확인 할 수 있었다. 
```log
o.a.c.gateway.DefaultCommandGateway      :\
 Command 'org.masil.hello.axon.inventory.Commands$CreateInventoryCommand' resulted in org.axonframework.axonserver.connector.command.AxonServerCommandDispatchException\
 (The command [org.masil.hello.axon.inventory.Commands$CreateInventoryCommand] does not contain a routing key.)
```

Controller 역시 Command 와 Query 로 분리 했다. 

[Aggregate](app/src/main/java/org/masil/hello/axon/inventory/Inventory.java)  에 기본생성자가 필요하다고 해서 제거 시 어떤 오류가 나는지 확인 해보았으나 잘됨!


`@CommandHandler`, `@EventSourcingHandler`, `@QueryHandler` 는 private 접근자를 가져도 잘 동작한다. 

`@EventHandler` 는 private 접근자로 동작 하지 않는다. default 접근자는 잘 동작한다. 








