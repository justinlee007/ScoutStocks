# Scout Stock App

## Requirements

* Use third party libraries only if necessary.
* Use the Polygon API to fetch stock market data.
* You can create a free account and generate an API key at https://polygon.io/
* Ensure you handle API keys and rate limits appropriately.
* Focus on creating a basic, clean, intuitive UI.  
* Don’t stress over design; we aim to assess your app coding skills and ability to implement functional requirements.

### Stocks Overview Screen

* Display a list of stocks with a summary (current price, daily change) of each stock in a simple, easy-to-read format.
* Include a button in the top right corner of the screen to navigate to a “List of Stocks” screen.
* Open a “Detailed Stock Information” screen when tapping on a specific stock.
* Focus on presenting the essential data clearly.
* Implement a pull-to-refresh feature to update stock data
* Fetch new data every time the app starts, displaying this information in a no-frills, accessible manner.

### Detailed Stock Information Screen

* Present detailed information for a specific stock, such as historical data charts, volume, and market cap, using basic graphical representations and standard UI elements.

### List of Stocks Screen

* Show a list of stocks with names and symbols in a straightforward list view.
* Add a button in the top right corner of the screen to navigate to an “Add Stock” screen
* Implement a swipe-to-delete feature that removes a stock from the list and clears any related data from storage, keeping the UI simple.

### Add Stock Screen

* Present this screen modally, with a search bar at the top.
* Implement an autocomplete search feature by querying the Polygon Stocks API for stock symbols and names as the user types, ensuring the search results are displayed in a basic list format.
* Focus on functionality over design for displaying a list of stocks according to the current search query, ensuring efficient API use.

## Submission

* Provide a GitHub repository link with your solution.
* Include a README with setup instructions and any assumptions made.

Note: Focus on code quality and architecture rather than complex UI. Handle edge cases gracefully. 