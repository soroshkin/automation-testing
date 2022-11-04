package automation.testing.e2e.step;

import automation.IntegrationTest;
import automation.testing.e2e.common.StoryContext;
import com.epam.microservices.automation.model.SaveSongResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SaveSongStep extends IntegrationTest {

  private ResponseEntity<SaveSongResponse> saveSongResponse;

  private HttpEntity<ByteArrayResource> httpEntity;

  @Given("create save song request")
  public void createSongRequest() throws IOException {
    InputStream givenSongStream = getClass().getResourceAsStream("/test.mp3");
    ByteArrayResource givenInputStream = new ByteArrayResource(givenSongStream.readAllBytes());
    HttpHeaders givenHeaders = new HttpHeaders();
    givenHeaders.add(HttpHeaders.CONTENT_TYPE, "audio/mpeg");
    httpEntity = new HttpEntity<>(givenInputStream, givenHeaders);
  }

  @Given("create song request in wrong format")
  public void createWrongSongRequest() throws IOException {
    ByteArrayResource givenInputStream = new ByteArrayResource(new byte[]{1});
    HttpHeaders givenHeaders = new HttpHeaders();
    givenHeaders.add(HttpHeaders.CONTENT_TYPE, "audio/mpeg");
    httpEntity = new HttpEntity<>(givenInputStream, givenHeaders);
  }

  @When("user saves new song")
  public void userSavesNewSong() {
    saveSongResponse = testRestTemplate.postForEntity(GATEWAY_URL + "/resources", httpEntity, SaveSongResponse.class);
    Optional.ofNullable(saveSongResponse.getBody().getId())
      .ifPresent((id) -> StoryContext.putInContext("id", id.toString()));
  }

  @Then("server should save new song and return a success status")
  public void serverShouldSaveSong() {
    assertThat(saveSongResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(saveSongResponse.getBody().getId()).isEqualTo(Integer.parseInt(StoryContext.getFromContext("id")));
  }

  @Then("server should return bad request status")
  public void serverShouldReturnBadRequest() {
    assertThat(saveSongResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }
}
