Feature: Save song files

  Scenario: Should save new song
    Given create save song request
    When user saves new song
    Then server should save new song and return a success status

  Scenario: Should not save new song if it has wrong format
    Given create song request in wrong format
    When user saves new song
    Then server should return bad request status

