# ConnectFourGame

## Description:

Connect Four Game for android platform.
This project contains implementation of game for two players and one player against AI Bot.

https://en.wikipedia.org/wiki/Connect_Four

## Build and Deploy:

* To build project you must have installed JDK 8 
* Open project in Android Studio
* Resolve gradle dependencies
* Install on test device

## Technical choices

* <b>ButterKnife</b>, because Android Data Binding can not work with Jack compiler but without Jack you can not use Java 8 lambda's. Which I'd like to try in that test project.
* <b>MVP</b>, pattern which allows separate the presentation layer from the logic without any extra dependencies. The connection between View and Presenter goes across IView and IGamePresenter interfaces. Clean architecture is not very suitable for such little project by my opinion.

## Trade-offs and what should be implemented in future

* More skilled AI. Not so dummy. For that porpuse should be implemented Alpha-Beta Pruning algo https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
* Custom View for game table, not array of ImageView's.
* Move to Android Data Binding when it would support Jack compiler.








