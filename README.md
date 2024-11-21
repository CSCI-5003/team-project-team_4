# project_template
Deliverable 1 Meeting Notes

Everyone
- push code to github

Erin
- Make difficulty level selection screen
- Make menu functional - opens correct screens
- Remove shuffle and deselct all buttons
- Make all return-to-menu buttons functional
- Go through Connections archives and create bank of four word groups

Will
- separate wordgrid initialization from UI
- work out logic for initializing words into grid

Andres
- FR3: when buttons are clicked, they are highlighted (change color). Only four buttons can be highlighted. Add the selected buttons to an ArrayList. When un-selected, the color goes back to lightGray and word is removed from ArrayList


## Deliverable 2

- all action listeners in controller

Erin
- change buttons to follow MVC pattern 
- add blank JLabel to game board for messages to user
- make end-of-game screen

Will
- submit button functionalities  (FR5)
    - check guess
    - disabling buttons if correct
    - counting incorrect
    - keeping record of guesses

Andres
- change wordGrid to follow MVC pattern
- FR3
- FR4


## Deliverable 3

Will
- randomize words
- WordGroup into Model & change logic so wordgroup stores an array of strings
- move checkGuess into model
- move checkLoss into model

Erin
- User input in control
- answer bars in gameboard
- endgame answer bars
- drop down words when answer correct
- work on duplicate game boards (initialize everything except game board)
- new game board instance overwrites old
    - game board stored as data member in model
    - one game board in existance at a time
    - stored in model?
- add getScore method

Andres
- high scores
- score tracking
- save data of score
- move submit button and word button handlers into control