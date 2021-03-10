package org.axonframework.modelling.command.inspection;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.common.AxonConfigurationException;
import org.axonframework.eventhandling.GenericEventMessage;
import org.axonframework.messaging.annotation.MessageHandlingMember;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;
import org.axonframework.modelling.command.AggregateVersion;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.axonframework.modelling.command.inspection.AnnotatedAggregateMetaModelFactory.inspectAggregate;
import static org.junit.jupiter.api.Assertions.*;

public class AggregateModelTest {


    @Test
    void when_type_of_aggregate_is_null_the_builder_throws_npe() {
        assertThrows(NullPointerException.class, ()-> inspectAggregate(null));
    }

    @Test
    void the_default_to_the_simple_name_of_aggregate_s_type() {

        class Foo1 { }
        assertEquals(Foo1.class.getSimpleName(), inspectAggregate(Foo1.class).type());

        @AggregateRoot class Foo2 { }
        assertEquals(Foo2.class.getSimpleName(), inspectAggregate(Foo2.class).type());

        @AggregateRoot(type = "This_is_type") class Foo3 { }
        assertEquals("This_is_type", inspectAggregate(Foo3.class).type());
    }


    @Test
    void the_version_must_be_convertible_to_Long() {

        class Foo { }
        assertNull(inspectAggregate(Foo.class).getVersion(new Foo()));

        @Getter @AllArgsConstructor
        class Foo1 { @AggregateVersion private UUID version; }
        assertThrows(ClassCastException.class ,()-> inspectAggregate(Foo1.class).getVersion(new Foo1(UUID.randomUUID())));

        @AggregateRoot
        @Getter @AllArgsConstructor
        class Foo2 { @AggregateVersion private Long version;}
        assertEquals(1L, inspectAggregate(Foo2.class).getVersion(new Foo2(1L)));
    }

    @Test
    void get_the_version_from_aggregate_root() {
        @AggregateRoot
        @Getter @AllArgsConstructor
        class Foo2 { @AggregateVersion private Long version;}
        @AggregateRoot
        @Getter @AllArgsConstructor
        class Foo3 {
            private Foo2 foo2;
            @AggregateVersion private Long version;

        }
        assertEquals(2L, inspectAggregate(Foo3.class).getVersion(new Foo3(new Foo2(1L), 2L)));
    }

    @Test
    void get_identifier() {
        @AggregateRoot
        @Getter @AllArgsConstructor
        class Foo { @AggregateIdentifier private Long id;}

        assertEquals("id", inspectAggregate(Foo.class).routingKey());
        assertEquals(1L, inspectAggregate(Foo.class).getIdentifier(new Foo(1L)));
    }

    @Test
    void identity_type_should_override_Object_toString() {
        class FooIdentifier { /* This does not override toString*/}
        @AggregateRoot
        @Getter @AllArgsConstructor
        class Foo {
            @AggregateIdentifier
            private FooIdentifier id;
        }

        try {
            inspectAggregate(Foo.class).getIdentifier(new Foo(new FooIdentifier()));
            fail();
        } catch (AxonConfigurationException e) {
            assertEquals("Aggregate identifier type " +
                            "[org.axonframework.modelling.command.inspection.AggregateModelTest$1FooIdentifier] " +
                            "should override Object.toString()",
                    e.getMessage());
        }


        @EqualsAndHashCode
        class BarIdentifier {
            @Override
            public String toString() {
                return "Override this one";
            }
        }
        @AggregateRoot
        @Getter @AllArgsConstructor
        class Bar {
            @AggregateIdentifier
            private BarIdentifier id;
        }

        assertEquals("id", inspectAggregate(Bar.class).routingKey());
        assertEquals(new BarIdentifier(), inspectAggregate(Bar.class).getIdentifier(new Bar(new BarIdentifier())));
    }

    @Getter
    @AllArgsConstructor
    public static class CreateFooCommand {
        @TargetAggregateIdentifier
        private UUID id;

    }

    @Getter
    @NoArgsConstructor
    @AggregateRoot
    public static class Foo {
        @AggregateIdentifier
        private UUID id;

        @CommandHandler
        public Foo(CreateFooCommand command){

        }
    }

    @Test
    void commandHandlers() {


        AggregateModel<Foo> model = inspectAggregate(Foo.class);
        assertEquals(1, model.commandHandlers().size());

        MessageHandlingMember<? super Foo> messageHandlingMember = model.commandHandlers().get(0);
        assertFalse(messageHandlingMember.canHandle(null));
        assertFalse(messageHandlingMember.canHandle(new GenericEventMessage<>("String payload")));
        assertFalse(messageHandlingMember.canHandle(new GenericEventMessage<>(new CreateFooCommand(UUID.randomUUID()))));
        assertFalse(messageHandlingMember.canHandle(new GenericCommandMessage<>("String payload")));
        assertTrue(messageHandlingMember.canHandle(new GenericCommandMessage<>(new CreateFooCommand(UUID.randomUUID()))));

        //assertNull(model.modelOf(Foo.class));

    }
}
