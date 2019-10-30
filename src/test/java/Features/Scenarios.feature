@Sanity
Feature: Business Scenarios

  # if some steps are common between the scenarios, they can be added to Background
  Scenario: No two movies should have the same image
    Given the endpoint details "TC03" from "Home" Sheet "In order to verify that no two movies have same image"
    When the "get movies" req is Executed
    Then user should get Expected Status code "200"
    And Verify that no two movies has same image

  Scenario: All poster links are valid
    Given the endpoint details "TC04" from "Home" Sheet "In order to verify that all poster paths are valid"
    When the "get movies" req is Executed
    Then user should get Expected Status code "200"
    And Verify whether the poster paths are valid

  Scenario: Movies should be sorted in correct order
    Given the endpoint details "TC05" from "Home" Sheet "In order to verify movies are sorted correctly"
    When the "get movies" req is Executed
    Then user should get Expected Status code "200"
    And Verify whether the movies sorted correctly

  Scenario: The sum of genereIDs should be greater than 400 for no more than 7 movies
    Given the endpoint details "TC06" from "Home" Sheet "In order to verify movies with sum of genere IDs greater than 400 not more than 7"
    When the "get movies" req is Executed
    Then user should get Expected Status code "200"
    And Verify whether the movies with sum of genere IDs greater than 400 not more than 7

  Scenario: At leaset one movie has palindrome
    Given the endpoint details "TC07" from "Home" Sheet "In order to verify at least one movie title has a palindrome"
    When the "get movies" req is Executed
    Then user should get Expected Status code "200"
    And Verify at leaset one movie has a palindrome
    
  Scenario: Two movie titles are subsets of other titles
    Given the endpoint details "TC08" from "Home" Sheet "In order to verify at least two titles are subset of other"
    When the "get movies" req is Executed
    Then user should get Expected Status code "200"
    And Verify at leaset two titles are subset of the other