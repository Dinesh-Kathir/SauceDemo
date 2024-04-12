Feature: SauceDemo product purchase

  Scenario Outline: Verify for valid login
    Given As a user must be in login page
    When As a user enter "<username>" ,"<passowrd>"
    And clicks on login button
    Then Verify that redirected to homepage

    Examples: 
      | username      | passowrd     |
      | standard_user | secret_sauce |

@cart
  Scenario Outline: Verify Shopping cart page for overview of total amount
    Given As a user must be in login page
    When As a user enter "<username>" ,"<passowrd>"
    And clicks on login button
    And As a user clicks on menu bar and click about link
    Then verify that redirected to appropriate "<link>"
    And verify user navigated to products page by clicking back button
    When As a user select the highest price product to cart
    Then Validate User redirected to cart page by clicking cart menu
    And Verify user redirected to checkout : your information page by user clicks on checkout
    When As a user enter "<firstname>","<lastname>","<zipcode>"
    And As a user clicks on continue
    Then Verify user redirected to checkout overview and format of total

    Examples: 
      | username      | passowrd     | link                   | firstname | lastname | zipcode |
      | standard_user | secret_sauce | https://saucelabs.com/ | testUser  | test     |   00000 |

  Scenario Outline: 
    Given As a user must be in login page
    When As a user enter "<username>" ,"<passowrd>"
    And clicks on login button
    And select a sort menu and select option as "<sort>"
		Then verify that product sorted accordingly
    Examples: 
      | username      | passowrd     | sort                |
      | standard_user | secret_sauce | Name (A to Z)       |
      | standard_user | secret_sauce | Name (Z to A)       |
      | standard_user | secret_sauce | Price (low to high) |
      | standard_user | secret_sauce | Price (high to low) |
