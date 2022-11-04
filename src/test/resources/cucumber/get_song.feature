Feature: Get song files

  Scenario: Should get full song
    Given create save song request
    When user saves new song
    Then server should save new song and return a success status
    When user requests created song
    Then server should return song and success status

  Scenario: Should get song partly
    Given create save song request
    When user saves new song
    Then server should save new song and return a success status
    Given create get song request
    When user requests part of created song
    Then server should return part of the song and success status