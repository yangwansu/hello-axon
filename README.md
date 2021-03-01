# Third Try

세번째 시도에서는 command/event 모듈과 query 모듈을 나누어 진행했다.

서로 간에 Event 를 공유해야해서 별도 share library 가 필요해보인다. 본 시도에서는 동일한 코드를 복사해서 진행해보았다. 

그러나 잘 동작 안했다.

잘 동작 안한 이유는 Aggregate 에     `@AggregateIdentifier` 을 빠트리면 아래와 같은 로그를 본게 된다. 

```log
o.a.c.gateway.DefaultCommandGateway      \
: Command 'org.masil.hello.axon.inventory.Commands$CreateInventoryCommand' resulted in org.axonframework.commandhandling.CommandExecutionException(Aggregate identifier must be non-null after applying an event. \
Make sure the aggregate identifier is initialized at the latest when handling the creation event.)
```