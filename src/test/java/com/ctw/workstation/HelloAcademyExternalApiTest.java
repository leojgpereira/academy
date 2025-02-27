package com.ctw.workstation;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;

@QuarkusTest
@TestProfile(CtwAcademyTestProfile.class)
class HelloAcademyExternalApiTest {
    @Inject
    HelloAcademy helloAcademy;

    @Test
    @DisplayName("Saying hello to external api")
    void saying_hello_to_external_api() {
        String name = "Leandro";

        WireMock.stubFor(
                get("/external/hello")
                        .withHeader("Content-Type", containing("application/json"))
                        .willReturn(
                                ok()
                                        .withHeader("Content-Type", "application/json")
                                        .withBody("{message: \"Hello!\"}")
                        )

        );

        WireMock.stubFor(
                post("/external/hello")
                        .withRequestBody(equalTo("message"))
                        .withHeader("Content-Type", containing("application/json"))
                        .willReturn(
                                ok()
                                        .withHeader("Content-Type", "application/json")
                                        .withBody("{message: \"Hello Leandro!\"}")
                        )

        );

        String result = helloAcademy.sayHello(name);

        Assertions.assertThat(result).isEqualTo("Hello Leandro!");
    }
}