## Articles Headlines App
Create a proof of concept for an App that displays trend articles and allow the user to save news for reading later.

## Requirements
- App to be built with a tab bar main navigation containing 3 tabs: **Headlines, Sources, Saved.**
- **Headlines**: screen to display a list of headlines based on the user-selected sources, each row should display (title, description, author and thumbnail picture)
  - Tapping on a row should open the headline URL to read the full article within the app.
  - When viewing an article the user should be able to save it for reading later.
- **Sources**: screen to display the list of available sources for articles, the user should be able to select multiple sources and the selection should persist user sessions.
- **Saves**: screen to display a list of previous saved headlines, tapping a row should open the article for reading, same way as the Headlines screen.
  - User should be able to delete previously saved articles
  - Integrate the app with [https://newsapi.org/](https://newsapi.org/) API to fetch data (register to get your API Key)
- Design and implement an app that can achieve all of the goals described above
- Design and implement the UI with user experience in mind
- Structure the app in a reasonable architecture, keeping in mind:
  - Good OO design
  - A simple and concise code base (e.g. avoid unnecessary complexity/over engineering)
- Feel free to use any libraries that help to deliver the app
- it is not expected to have a fancy user interface, well layout structure will be the key element to be evaluated.

## Technologies and Methodologies Expectations
- Highly modular
- Use of modern language: Kotlin
- ConstrainLayout
- Light weight, smart code
- Good use of MVC, MVVM preferable
- Reuse of components
- RxKotlin would be a big bonus
- Android Jetpack Architecture Components (viewModel, LiveData, navigation component, etc) would be a big bomnus

## Points of consideration
- Pagination is not required, API will return only the latest 10 articles per source
- The API doesn’t provide a way to fetch articles from multiple sources in one request, you will have to wrap multiple requests based on the user’s selected sources e.g.:
  - If the user has selected 3 sources you will have to wrap 3 requests and display the 30 results back in the headlines screen
- Documentation for the API [https://newsapi.org/#documentation](https://newsapi.org/#documentation)
- Filter sources to be only for English language

## Allocated time
- Please provide submission no more than 3 days after received.
- Submissions will only be accepted via GitHub or Bitbucket.

-----------------------------------------------------------------------------------------------------------------------------------------------

**Note from Sweny Bagrecha - Android Developer**

- Made use of Clean Architecture structure
- Design Architecture Used: MVVM
- Implemented tab bar main navigation containing 3 tabs: **Headlines, Sources, Saved.**
- The clean Architecture includes: **Repository, Usecase, Domain, Fragments(UI), Adapters, ViewModels**
- Reusuable functions are included in **Utils**
- Used Safe args to send data as part of navigation between two fragments
- Made use of **BaseActivity, BaseViewModel, BaseFragment** for handling few things such as **ProgressBase, APIerrors**
- Null checks have been taken inconsideration
- I have put Method level comments as well.
- Due to short of time, given that only a day was provided in-order to build the whole app. There
    are few things that are pending which includes **Save icon on Heading screen, selected items o Heading screen**
- For pending items, I have added TODOs
- Please go throught the whole project, run the app, you would be happy to see :)
