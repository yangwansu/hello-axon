package org.masil.learning.tests;

public class IdentifierFactory extends org.axonframework.common.IdentifierFactory {

    static String TEST_ID = "THIS-IS- TEST-IDENTITY";

    @Override
    public String generateIdentifier() {
        return TEST_ID;
    }
}
