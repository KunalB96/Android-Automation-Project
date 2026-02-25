Feature: Google Multiple Search

  Scenario Outline: Search multiple queries in Google

    Given Google page is open in browser
    When the search phrase "<query>" is entered
    And enter Key is pressed
    Then results should be displayed

    Examples:
      | query                           |
      | Pratapgad Fort                  |
      | latest news in India            |
      | What is Artificial Intelligence |
