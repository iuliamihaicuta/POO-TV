// Copyright Mihaicuta Iulia, 324CA

# POO TV
---
## Program Flow

A list of currently registered users and available movies is received as
input data, along with the actions to be executed. The data is stored in
an object of class `Input`. As a result of parsing the input, three lists
are created: `users`, `movies` and `actions`.

Using the *Singleton Design Pattern* an object of the `Database` class is
created. This object will contain the parsed list of users and movies.
Also, using the same pattern an object of `CurrentPosition` class will
permanently know the position in the program: the current user, movie and
page - if applicable.

The *Factory Design Pattern* is used for creating the objects according to
their specific type for classes that extend `User` and `Action`. To deal
with unexpected situations different exceptions as `IOException` and
`IllegalArgumentException` were used.

The program support four types of actions. An error message will be displayed
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
> which extends abstract class `Action`.

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
    - `Comparator.comparing` and the method reference operator are used for sorting
- **purchase - watch - like - rate**
    - actions are allowed on `see details` page
    - using the Command Design Pattern and the class `MovieActions`, the
        correspondent action is executed
- **buy tokens - buy premium account**
    - the user's balance, tokens and status change accordingly
- **subscribe**
    - the new genre is added to the user's list

> - For each action it is checked if we are on the corespondent page.
> - Actions of type on page are handled in the `OnPageAction` class,
    > which implements the `Action` interface.

### Database Action

- **add movie**
    - a new movie is added to the database
    - subscribers are informed through a notification - class `Notification`
- **delete Movie**
    - a given movie is deleted from the database
    - users are refunded and informed through notifications
> Streams and lambda expressions are use for iterating through users

### Back Action

If there is a user logged in the system and the page was change at
least one time, the command is permitted and the program switches to
the previous page

> The implementation consists of calling the `getChangePageOutput()` function
> from class `ChangePageAction`

---
## Changes

In addition to the new functions implemented, I also made some changes to
the code from the first stage, for a better implementation. Following the
feedback, I changed `Action` from interface to abstract class and added
the action as a field so that it would no longer be always sent as an
argument. Also, by creating the database object, the list of files and
users were accessed more easily.

The page classes are no longer implemented using Singleton and I added
the Command Design Pattern to facilitate the interaction with the actions
performed to the movies - purchase, watch, like, rate.
