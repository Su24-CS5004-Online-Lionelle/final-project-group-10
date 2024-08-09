# Final Project for CS 5004 - (Rick and Morty Character Search)

Group Members:
* Grace Chong: [Personal Github](https://github.com/hey-sj)
* Xiaotong Li: [Personal Github](https://github.com/Lxsong77)
* Tianyuan Yan: [Personal Github](https://github.com/JerryYanTY)
* Xiwei Li: [Personal Github](https://github.com/xiwei22)

### Rick and Morty Character Search

<img width="597" alt="Screenshot 2024-08-08 at 21 03 47" src="https://github.com/user-attachments/assets/00a7e71b-70cd-4d59-baa8-1dc72ae77995">

For our group project, we created a program utilizing a GUI that allows the user to search for information related to Rick and Morty characters. Our program utilizes a free [Rick and Morty Character API](https://rickandmortyapi.com/). We build the link for the search requests by [adding filters to their character schema](https://rickandmortyapi.com/documentation/#filter-characters) to get the character records that match the search criteria. 

<img width="600" alt="Screenshot 2024-08-08 at 21 05 01" src="https://github.com/user-attachments/assets/7d90f63f-6ca5-45af-b990-fe412e72f288">

The user can add search criteria by name, filter by gender, filter by status, and filter by species. The search results are sorted by name, and the user can display these results in ascending or descending order by name. If there are many results, then the results are split up into pages, and the user can navigate the results page by page. The user can also export the results to a file in XML, JSON, and CSV formats. Please see the manual linked below for more detailed information on how to use the program. 

* [Manual](https://github.com/Su24-CS5004-Online-Lionelle/final-project-group-10/blob/main/Manual/README.md)

Here are also our design documents and the GUI testing history. 

* [Initial UML Diagram](https://github.com/Su24-CS5004-Online-Lionelle/final-project-group-10/blob/main/DesignDocuments/InitialUML.md)
* [Final UML Diagram](https://github.com/Su24-CS5004-Online-Lionelle/final-project-group-10/blob/main/DesignDocuments/FinalUML.md)
* [GUI Testing History](https://github.com/Su24-CS5004-Online-Lionelle/final-project-group-10/blob/main/Manual/GUITestingHistory.md)

### Running the Program

```java
// build the project using gradle.
./gradlew build

// run the application
./gradlew run
```

### Features

Required Features

* **GUI:** Java swing/awt JFrame
* **view all items in a logical order:** view all character records, page by page, with each page's results in alphabetical order. default is ascending alphabetical, but user can toggle to descending.
* **build out a list from the collection:** user can search for characters and save out the search results.
* **save out the list:** user is able to save out list to .xml, .json, and .csv file formats.

Additional Features

* **be able to search for items in the collection:** user can search for characters by name. all characters whose name fields contain the name that the user searches for are included in the records.
* **sort items in the collection:** sort search results by name alphabetically, ascending and descending.
* **filter items:** filter search results by gender, status, and species.
* **have original item list come from an online API/online access:** use the aforementioned Rick and Morty API's character schema.
* ** include images for your items:** search results generate an image for each character. 
