# codingChallengeMM
This is the solution for the coding challenge given by message media


## Pre-requisites

JDK 1.8.x 

Technologies/ Libraries used

    Java 8
    Spring boot
    Mockito
    AssertJ
    Gradle wrapper 
    
## How to run the application 

Go to the project root directory and run following commands
###### To build the application
    ./gradlew build    or just gradle build
    
once build, execute the main method of 
au.com.mm.codingChallenge.CodingChallengeApplication.java  

It will prompt the below
./gradlew build    or just gradle build
###### It will prompt the below
    This will fetch the data stored in the resource folder
    Enter a valid time stamp in  mm:ss format
###### This will be one of the example 
    This will fetch the data stored in the resource folder
    Enter a valid time stamp in  mm:ss format
    14:11
    TimeStamp, Team, Possession, Shot, Score
    14:11,    A,    94.242065%,    2,    1
    14:11,    B,    5.757932%,    0,    0
    Enter another timestamp in mm:ss format

User should enter a valid timestamp in mm:ss format. If the format does not comply it will prompt 
the user to enter the time again.

## Useful information and key points

## Design Patterns used 
### Singleton 
EventFactoryService and the ProcessEventsServices classes has been design as singleton as it would give only 
one instance for the application. Based on the context , one instance is enough to carryout the tasks
### Factory 
Events interface and its implementations of various events such as StartEvent, EndEvent, PossessEvent etc follows 
the factory design patterns as its implementation is hidden and referred through a common interface 
### Command 
This is the main design pattern used in solution as it suits as a data driven design pattern.
The Game object is passed on to each events and each events will execute its logis 
on game 
In this solution , Events behaves like commands and game as the wrapped object
Each event will have its own behaviour based on what it should be doing.

Further, have used the PrintSummaryEvent which is added to the last of the event records 
to print the summary of the teams

``
  for(EventRecord eventRecord: eventRecordList) 
  {
            Event event  = eventFactoryService.populateEvent(eventRecord);
            event.executeEvent(game,eventRecord);
   }``


### NullObject 
When the command is not found from the CSV it will create a DefaultEvent which does nothing


## Further enhancements 
Initially , this application design to be run as a web application with a RestApi 
exposed to receive a csv file and return an output.
 