package org.masil.learning.tests;

import com.google.common.collect.Maps;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.messaging.MetaData;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.masil.learning.tests.IdentifierFactory.TEST_ID;

public class CommandMessageTest {


    @Test
    void create() {

        CommandMessage<String> commandMessage = GenericCommandMessage.asCommandMessage("Hi");

        assertEquals(String.class.getName(),commandMessage.getCommandName());
        assertEquals(TEST_ID,commandMessage.getIdentifier());
        assertEquals("Hi",commandMessage.getPayload());

        assertNotSame(commandMessage,commandMessage.andMetaData(Maps.newLinkedHashMap()));

        assertEquals(String.class.getName(),commandMessage.andMetaData(Maps.newLinkedHashMap()).getCommandName());
        assertEquals(TEST_ID,commandMessage.andMetaData(Maps.newLinkedHashMap()).getIdentifier());
        assertEquals("Hi",commandMessage.andMetaData(Maps.newLinkedHashMap()).getPayload());

        assertNotSame(commandMessage,commandMessage.withMetaData(Maps.newLinkedHashMap()));
    }

    @Test
    void createWithMetaData() {
        Map<String, String> metaData = Maps.newHashMap();
        metaData.put("player1", "SAETORY");
        metaData.put("player2", "BBASANG");

        CommandMessage<String> commandMessage = new GenericCommandMessage<>("Hi", metaData);

        Map<String, String> newMembers = Maps.newHashMap();
        newMembers.put("player3", "KIM");

        CommandMessage<String> replaced = commandMessage.withMetaData(newMembers);
        assertNotSame(commandMessage, replaced);
        assertEquals(MetaData.class, commandMessage.getMetaData().getClass());
        assertFalse(commandMessage.getMetaData().containsKey("player3"));
        assertFalse(replaced.getMetaData().containsKey("player1"));
        assertTrue(replaced.getMetaData().containsKey("player3"));
        assertNotSame(commandMessage.getMetaData(), replaced.getMetaData());

        CommandMessage<String> addedNewMember = commandMessage.andMetaData(newMembers);
        assertTrue(addedNewMember.getMetaData().containsKey("player1"));
        assertTrue(addedNewMember.getMetaData().containsKey("player2"));
        assertTrue(addedNewMember.getMetaData().containsKey("player3"));


    }
}
