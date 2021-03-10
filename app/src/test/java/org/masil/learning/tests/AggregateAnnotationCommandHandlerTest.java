package org.masil.learning.tests;

import com.google.common.collect.Sets;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.modelling.command.AggregateAnnotationCommandHandler;
import org.axonframework.modelling.command.Repository;
import org.axonframework.modelling.command.inspection.AggregateMetaModelFactory;
import org.axonframework.modelling.command.inspection.AggregateModel;
import org.axonframework.modelling.command.inspection.AnnotatedAggregateMetaModelFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(value = {MockitoExtension.class})
public class AggregateAnnotationCommandHandlerTest {

    @Mock
    Repository<String> repository;

    @Test
    void name() throws Exception {
        AggregateAnnotationCommandHandler.Builder<String> builder = AggregateAnnotationCommandHandler.builder();

        AggregateModel<String> aggregateModel = AnnotatedAggregateMetaModelFactory.inspectAggregate(String.class);


        AggregateAnnotationCommandHandler<String> a = builder
                .repository(repository)
                .aggregateModel(aggregateModel)
                //.aggregateType(String.class)
                .build();

        Set<String> commandNames = a.supportedCommandNames();

        Assertions.assertEquals(Sets.newHashSet(), commandNames);

        CommandMessage<?> aCommandMessage = GenericCommandMessage.asCommandMessage("Hi");

        //a.handle(aCommandMessage);
    }
}
