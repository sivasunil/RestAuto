@Sanity
Feature: Login
  In order to verify the Endpoints of Login

  # if some steps are common between the scenarios, they can be added to Background
  Scenario: In order to verify GET Request for Login Page
    Given the Initial Setup is done for Test Condition "TC01" from "Home" Sheet "In order to verify POST Request for Login Page"
    When the "get" Request is Executed
    Then user should get Expected Status code "200" and Response should match with "HTTP/1.1 200 OK"

  Scenario: In order to verify POST Request for Login Page
    Given the Initial Setup is done for Test Condition "TC02" from "Home" Sheet "In order to verify GET Request for Login Page"
    When the "post" Request is Executed
    Then user should get Expected Status code "200" and Response should match with "HTTP/1.1 200 OK"

  Scenario: In order to verify GET Request for Login Page
    Given the Initial Setup is done for Test Condition "TC09" from "Home" Sheet "In order to verify GET Request for Login Page"
    When the "get" Request is Executed
    Then user should get Expected Status code "404" and Response should match with "Not Found"

  Scenario: In order to verify POST Request for Login Page
    Given the Initial Setup is done for Test Condition "TC10" from "Home" Sheet "In order to verify GET Request for Login Page"
    When the "post" Request is Executed
    Then user should get Expected Status code "404" and Response should match with "Not Found"

  Scenario: In order to verify POST Request for Login Page
    Given the Initial Setup is done for Test Condition "TC11" from "Home" Sheet "In order to verify GET Request for Login Page"
    When the "post" Request is Executed
    Then user should get Expected Status code "400" and Response should match with "Bad Request"
