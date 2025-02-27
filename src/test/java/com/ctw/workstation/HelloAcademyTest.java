/*
package com.ctw.workstation;

import org.apache.commons.lang3.NotImplementedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class HelloAcademyTest {
    @Mock
    ExternalMessageService externalMessageService;

    @InjectMocks
    HelloAcademy helloAcademy;

    @Test
    void lista() {
        List<String> minhaLista = new ArrayList<>();
        List<String> minhaListaSpy = Mockito.spy(minhaLista);

        Mockito.doReturn("Leandro").when(minhaListaSpy).get(0);

        Assertions.assertThat(minhaListaSpy.get(0)).isEqualTo("Leandro");
    }

    @Test
    void lista_mock() {
        List<String> minhaLista = new ArrayList<>();
        List<String> minhaListaMock = Mockito.mock(List.class);

        Mockito.when(minhaListaMock.get(0)).thenReturn("Leandro");

        Assertions.assertThat(minhaListaMock.get(0)).isEqualTo("Leandro");
    }

    @Test
    @DisplayName("Hello from outer space with spy")
    void hello_from_outer_space_with_spy() {
        String name = "Leandro";

        HelloAcademy helloExtAcademy = new HelloAcademy();
        ExternalMessageServiceImplementation externalMessageService = new ExternalMessageServiceImplementation();

        ExternalMessageService externalMessageServiceSpy = Mockito.spy(externalMessageService);
        helloExtAcademy.externalMessageService = externalMessageServiceSpy;

        Mockito.doReturn("Hello World")
            .when(externalMessageServiceSpy).sayHelloFromOuterSpace();

        String result = helloExtAcademy.sayHello(null);

        Assertions.assertThat(result).isEqualTo("Hello Leandro from outer space");
    }

    @Test
    @DisplayName("Hello from outer space with mock")
    void hello_from_outer_space_with_mock() {
        String name = "Leandro";

        Mockito.when(externalMessageService.sayHelloFromOuterSpace())
                .thenThrow(new NotImplementedException("This feature"));

        String result = helloAcademy.sayHello(name);

        Assertions.assertThat(result).isEqualTo("Hello Leandro from outer space");
    }
}*/
