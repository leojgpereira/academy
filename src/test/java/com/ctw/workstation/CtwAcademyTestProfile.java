package com.ctw.workstation;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.List;

public class CtwAcademyTestProfile implements QuarkusTestProfile {
    @Override
    public List<TestResourceEntry> testResources() {
        return List.of(
                new TestResourceEntry(CtwAcademyResource.class),
                new TestResourceEntry(WiremockResource.class)
        );
    }
}
