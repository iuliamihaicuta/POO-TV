// Copyright Mihaicuta Iulia, 324CA

# POO TV
---
## Program Flow

A list of currently registered users and available movies is received as
input data, along with the actions to be executed. The data is stored in
an object of class `Input`. As a result of parsing the input, three lists
are created: `users`, `movies` and `actions`.

The program support two types of actions. An error message will be displayed
if the action was invalid. Otherwise, if the action requests an output, it
will be displayed.

> A new `Output` class was with several constructors (the overloading principle)
> was created to simplify displaying messages.
---
## Actions

### Change Page Action

It is checked that the page to witch we are transferring is accessible. If
the page is `see details`, it is checked whether the movie is available.

> The switching of pages is handled by the `ChangePageAction` class,
> which implements interface `Action`.

### On Page Action
- **login**
    - it is checked that the user is registered
    - change to the authentication page and display the desired message,
      or display the error
- **register**
    - checks if the user is already in the database
    - if the action is allowed, a new user is created and the login action
      takes place
- **search - filter**
    - movies that meet the criteria are displayed
    - filtering is performed using class `MovieList` methods `moviesContain()`
      and `sortMovies()`
- **purchase - watch - like - rate**
    - actions are allowed on `see details` page
    - using the `MovieActions` class, methods have been built to handle each
      individual action
- **buy tokens - buy premium account**
    - the user's balance, tokens and status change accordingly

> - For each action it is checked if we are on the corespondent page.
> - Actions of type on page are handled in the `OnPageAction` class,
    > which implements the `Action` interface.

---
## Structure of the Project

The program pages were created using Singleton Pattern Design. Using
an object from the `CurrentPosition` class, the user's current
position was stored at any time, if he was logged.

The Factory Design Pattern is used for creating the objects according to
their specific type for classes that extend/implement `User` and `Action`.


- streams la iterare prin users si movies
- functii lambda
- Comparator.comparing + method reference operator
- 