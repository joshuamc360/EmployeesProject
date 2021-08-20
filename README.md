#Employee CSV Reader
This project demonstrates how data can be read from a CSV file and uploaded into an SQL database. 
The data is first to read to create new objects for each record using Java I/O. 
Then is sorted so no duplicated records are accepted using lambda expressions. 
Then database connectivity is established with JDBC allowing the program to upload data to the MySql database.
 Finally, it implements threading to reduce the time it takes to upload records using concurrency. 


##Bugs and Future Improvements 

- Only supports two threads. Functionality to make it so that a varying number of threads can be chosen is necessary to optimise performance.
- Testing Classes introduced but needs implementing for all classes.
- Code structure to be reviewed for the CvsProgramBuilder.java class. 
