@Ghost
Feature: Login

  Background: Navigate to website
    Given Navigate to Ghost Admin
    When I am successfully logged in
      | raulsvelazquez@hotmail.com | Raul745631_ |

  @home
    @0001
  Scenario: Display home screen
    Then I verify that I am logged in

  @home
    @0002
  Scenario: Publish a post
    When I create the post
    Then I verify that the post was created