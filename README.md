# [Scout Stock App](https://github.com/justinlee007/ScoutStocks)

## Screen Recording

<img src="ScoutStocks.gif" alt="Screenshot">

## Overview

This is my assessment project for Scout Motors. It's a simple stock market app designed to provide
users with an overview of stock prices, detailed information about specific stocks, and the ability
to manage a list of stocks. The app features a clean and intuitive user interface, focusing on
functionality and ease of use.

This app is built using Jetpack Compose and integrates with the Polygon API to fetch real-time stock
market data. It includes features such as a stocks overview screen, detailed stock information, a
list of stocks, and an add stock functionality.

The app is structured to allow users to easily navigate between different screens, view stock
details, and manage their stock list with minimal complexity.

## Tech Stack

The app uses an MVI (Model-View-Intent) architecture pattern by having the UI observe a single flow
that represents the current state of the screen. This uni-directional data flow simplifies state
management and makes the app more predictable and easier to test.

* **Jetpack Compose**: For building the user interface.
* **Kotlin**: The programming language used for the app.
* **Coroutines**: For handling asynchronous operations and API calls.
* **ViewModel**: For managing UI-related data in a lifecycle-conscious way.
* **Navigation Component**: For managing navigation between different screens in the app.
* **Hilt**: For dependency injection to manage the app's dependencies efficiently.
* **Room**: For local data storage, if needed, to persist user preferences or stock lists.
* **Retrofit**: For making network requests to the Polygon API.
* **OkHttp**: For handling HTTP requests, responses, and caching.

## Assumptions and Issues

The requirements for the overview and list of stocks screens just mentioned "display a list of
stocks" and "show a list of stocks", so I assumed that the list of stocks is the same as the
overview of stocks. Also, the list is initialized with several values once during the first app
launch. Afterward, the list is custom based on whatever stock the user selects in the add stock
dialog.

After hitting the Polygon API limit several times, I added a `CacheInterceptor` to the OkHttp
client to cache responses for 10 minutes, regardless of server-side cache headers. This allows the
app to function without hitting the API limit too quickly, especially during development and
testing. The cache is stored in the app's internal storage, ensuring that it is not accessible to
other apps or users.

## Requirements

These are the requirements verbatim from the Coderbyte assessment:

* Use third party libraries only if necessary.
* Use the Polygon API to fetch stock market data.
* You can create a free account and generate an API key at https://polygon.io/
* Ensure you handle API keys and rate limits appropriately.
* Focus on creating a basic, clean, intuitive UI.
* Don’t stress over design; we aim to assess your app coding skills and ability to implement
  functional requirements.

### Stocks Overview Screen

* Display a list of stocks with a summary (current price, daily change) of each stock in a simple,
  easy-to-read format.
* Include a button in the top right corner of the screen to navigate to a “List of Stocks” screen.
* Open a “Detailed Stock Information” screen when tapping on a specific stock.
* Focus on presenting the essential data clearly.
* Implement a pull-to-refresh feature to update stock data
* Fetch new data every time the app starts, displaying this information in a no-frills, accessible
  manner.

### Detailed Stock Information Screen

* Present detailed information for a specific stock, such as historical data charts, volume, and
  market cap, using basic graphical representations and standard UI elements.

### List of Stocks Screen

* Show a list of stocks with names and symbols in a straightforward list view.
* Add a button in the top right corner of the screen to navigate to an “Add Stock” screen
* Implement a swipe-to-delete feature that removes a stock from the list and clears any related data
  from storage, keeping the UI simple.

### Add Stock Screen

* Present this screen modally, with a search bar at the top.
* Implement an autocomplete search feature by querying the Polygon Stocks API for stock symbols and
  names as the user types, ensuring the search results are displayed in a basic list format.
* Focus on functionality over design for displaying a list of stocks according to the current search
  query, ensuring efficient API use.

## Submission

* Provide a GitHub repository link with your solution.
* Include a README with setup instructions and any assumptions made.

Note: Focus on code quality and architecture rather than complex UI. Handle edge cases gracefully. 