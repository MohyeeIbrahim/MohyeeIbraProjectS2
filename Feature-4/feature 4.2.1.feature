Feature:As a kitchen manager,
  I want the system to fetch real-time ingredient prices from suppliers
  so that I can make cost-effective purchasing decisions.

  Scenario: Kitchen manager checks current ingredient prices
    Given the kitchen manager is on the ingredient ordering
    When they search for "Tomatoes"
    Then the system should fetch and display the latest price from all integrated suppliers

  Scenario: Kitchen manager compares supplier prices
    Given the kitchen manager has selected an ingredient
    When the system retrieves real-time pricing data
    Then the system should display prices from all available suppliers for comparison

  Scenario: Kitchen manager filters ingredients by best price
    Given the kitchen manager is viewing the supplier ingredient list
    When they apply the "Sort by Price: Low to High" filter
    Then the system should sort the list of ingredients based on current real-time prices

