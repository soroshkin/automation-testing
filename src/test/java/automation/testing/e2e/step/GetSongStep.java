package automation.testing.e2e.step;

import automation.IntegrationTest;
import automation.testing.e2e.common.StoryContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class GetSongStep extends IntegrationTest {

  private ResponseEntity<String> getSongResponse;

  private HttpEntity<String> httpEntity;

  @Given("create get song request")
  public void createGetSongRequest() {
    HttpHeaders givenHeaders = new HttpHeaders();
    givenHeaders.add(HttpHeaders.RANGE, "bytes=0-5");
    httpEntity = new HttpEntity<>(givenHeaders);
  }

  @When("user requests created song")
  public void userGetsSong() {
    getSongResponse = testRestTemplate.exchange(GATEWAY_URL + "/resources/{id}", HttpMethod.GET, HttpEntity.EMPTY, String.class, StoryContext.getFromContext("id"));
  }

  @When("user requests part of created song")
  public void userGetsSongPartly() {
    getSongResponse = testRestTemplate.exchange(GATEWAY_URL + "/resources/{id}", HttpMethod.GET, httpEntity, String.class, StoryContext.getFromContext("id"));
  }

  @Then("server should return song and success status")
  public void serverShouldReturnSong() {
    assertThat(getSongResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(getSongResponse.getBody()).isNotNull();
  }

  @Then("server should return part of the song and success status")
  public void serverShouldReturnSongPartly() {
    assertThat(getSongResponse.getStatusCode()).isEqualTo(HttpStatus.PARTIAL_CONTENT);
    assertThat(getSongResponse.getBody()).isNotNull();
  }
}
