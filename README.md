# cmdline-social
Implementation of the Codurance command line social exercise.

## To build ##
This is a maven project that requires java 8. To build run 'mvn clean package'.

## To run app ##
From command line run 'java -cp target/cmdline-social-1.0-SNAPSHOT.jar za.co.codurance.social.ui.console.App'
 * 'exit' can be entered to exit app. This also means that we cannot have a user handle of 'exit'

## Notes ##
 I sketched out some simple CRC cards and diagram to get a basic view of the problem.
 High level project structure uses Actions, Domain Services, and Repositories similar to Interaction Driven Design model.
 Following aims/purpose was:
  * to explicitly introduce concept of use case messages to drive the interaction at the high level i.e. wanted an explicit
 messag request to reveal intention of boundary calls into the actions.
  * wanted to try having the clear boundaries between Actions, Domain Services, and Repositories.
  * even though not requested wanted to keep in mind how design would cater for adding anothe kind of UI onto i.e. web instead of console. This
  impacts the boundaries of the ui controllers and actions.
  * avoid field access and rather use private getters (wanted to see if it affected readability or coding time)
 * wanted to drive bottom-up TDD as haven't practiced it in anger with current projects.
 * You could probably come up with simpler and less code if you relax some of these assumptions.
 I also wanted the commit history to be explicit and viewable but unfortunately the way I started the project was standalone using local history in IntelliJ.

Design notes:
 * Initially I was leaning towards Command objects to handle the actual execution of the use cases but ended up with
 handlers that process the input and act as controllers. This is probably due to my starting in a bottom-up approach to the functionality as
 opposed to a top-down.
 * Not too happy with the outcome of the handlers in terms of the disconnect between parsing input and controlling interaction with user.
 I say this because it makes it more difficult to see the type of inputs that can be executed and also introduces complexity in ordering of handlers
 e.g. Exhibits Positional Connascence where the order of execution of the exit handler is important so that it is not interpreted as a user handle.
 * Some of the code was put in place so that it is consistent e.g. private getters, small intention revealing methods, keep code in methods at
 same level of abstraction where possible.