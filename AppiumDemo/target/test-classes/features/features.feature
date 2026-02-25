Feature: Google Search
  As a web surfer, I want to search Google, so that I can learn new things.
  
  Scenario: Simple Google search
    Given Google page is open in browser
    When the search phrase "Pratapgad Fort" is entered
    And enter Key is pressed
    Then results for "Pratapgad Fort" are shown