package com.ctw.workstation.team.boundary;

import com.ctw.workstation.CtwAcademyResource;
import com.ctw.workstation.CtwAcademyTestProfile;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.entity.dto.request.CreateTeamRequest;
import com.ctw.workstation.team.entity.dto.request.UpdateTeamRequest;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestHTTPEndpoint(TeamResource.class)
@TestProfile(CtwAcademyTestProfile.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TeamResourceTest {
    private UUID teamId;

    @Test
    @Order(1)
    @DisplayName("Adding team")
    void addingTeam() {
        CreateTeamRequest createTeamRequest = new CreateTeamRequest(
                "STARS",
                "PLANT",
                "LISBON"
        );

        Team team = given()
                .contentType(ContentType.JSON)
                .body(createTeamRequest)
        .when()
                .post()
        .then()
            .assertThat()
            .statusCode(Response.Status.CREATED.getStatusCode())
            .body("id", is(notNullValue(UUID.class)))
            .body("name", equalTo("STARS"))
            .body("product", equalTo("PLANT"))
            .body("defaultLocation", equalTo("LISBON"))
            .extract()
            .as(Team.class);

        teamId = team.getId();
    }

    @Test
    @Order(2)
    @DisplayName("Updating a team")
    void updatingTeam() {
        UpdateTeamRequest updateTeamRequest = new UpdateTeamRequest(
                "Stars",
                "Plant",
                "Lisbon"
        );

        given()
            .contentType(ContentType.JSON)
            .pathParams("id", teamId)
            .body(updateTeamRequest)
        .when()
            .put("{id}")
        .then()
            .assertThat()
            .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

    @Test
    @Order(3)
    @DisplayName("Retrieving team by id")
    void retrievingTeamById() {
        given()
            .contentType(ContentType.JSON)
            .pathParams("id", teamId)
        .when()
            .get("/{id}")
        .then()
            .assertThat()
            .statusCode(Response.Status.OK.getStatusCode())
            .body("id", equalTo(teamId.toString()))
            .body("name", equalTo("Stars"))
            .body("product", equalTo("Plant"))
            .body("defaultLocation", equalTo("Lisbon"));
    }

    @Test
    @Order(4)
    @DisplayName("Retrieving all teams")
    void retrievingAllTeams() {
        List<Team> teams = given()
            .contentType(ContentType.JSON)
        .when()
            .get()
        .then()
            .assertThat()
            .statusCode(Response.Status.OK.getStatusCode())
            .extract()
            .as(new TypeRef<List<Team>>() {});
    }

    @Test
    @Order(5)
    @DisplayName("Deleting team by id")
    void deletingTeamById() {
        given()
            .contentType(ContentType.JSON)
            .pathParams("id", teamId)
        .when()
            .delete("/{id}")
        .then()
            .assertThat()
            .statusCode(Response.Status.OK.getStatusCode());
    }
}