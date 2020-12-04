
ABOUT InsuranceApp 
-------------------

This app was created as a final project for CIS 452: Dababase Systems @ UMass Dartmouth. 

It was built using a SQLite database and the SQLite JDBC, the JavaFX library to build the GUI, and the Eclipse IDE.

To start the application from an IDE, run src > gui > MainMenu.java as the main function. 
From the main menu, the user can choose from 3 buttons to access different functionalities of the app, or the exit button. 

Add An Accident 
----------------

This screen allows the user to add a new accident and involvements to their respective tables in the SQLite database. 
The user can add multiple vehicles/drivers invoved in the accident to create multiple involvements associated with that accident. 
The user can submit the accident information or return to the main menu without saving the information added.

Search By Accident ID 
----------------------

This screen allows the user to search for accident information matching an entered Accident ID. 
If a matching accident is found, the screen will display the accident's location information, along with information on each vehicle/driver involved. 
If no match is found, the screen displays "No Results"

Search By Criteria
----------------------

This screen allows the user to search for an accident based on several criteria. 
Users can create filters based on min/max of average damages sustained in the accident, min/max of total damages sustained in the accident, or a range of dates. 
Adding multiple filters to the search narrows the parameters for the displayed accidents. 
Selecting "Search" with no parameters displays all accidents in the database.
