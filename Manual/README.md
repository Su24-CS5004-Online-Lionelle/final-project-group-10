# Manual 

We created a GUI interacting with the **character schema** from this [Rick and Morty Character API](https://rickandmortyapi.com/) to return information about characters from the show Rick and Morty. This API allows us to look up information about each character, such as their name, status, species, last known location, and so forth. We created filters for character searches including their name, status, species, and gender. This API seems to be up-to-date with the 51st episode of the show. You can learn more about the character schema by looking at [the corresponding API documentation](https://rickandmortyapi.com/documentation/#character-schema). 

### Overview

The user is able to:
* Search for a character by name.
* Filter for characters by gender (Female, Male, Genderless, Unknown).
* Filter for characters by status (Dead, Alive, Unknown).
* Filter for characters by species (Human, Alien, Humanoid, Unknown, Poopybutthole, Mythological Creature, Animal, Robot, Cronenburg, Disease).
* Sort by character name alphabetically, ascending and descending.
* Export search results to XML, JSON, or CSV formats.

Each search interacts with the API by building a link with the filters to apply that will load the matching character records. For search requests that display a large number of results, the API splits up the results into pages. Our program directly accesses these pages to display the results back to the user. 

## How to Use

### Searching for a Character

<img width="602" alt="Screenshot 2024-08-08 at 19 43 41" src="https://github.com/user-attachments/assets/036fdb37-6f54-4806-933b-b6295bf7981c">

The search bar acts as its own filter for character names, returning all characters that have the name. Leaving this blank, and having the filter dropdown menus at their default settings ("All"), will return every single character in the record. 

> [!NOTE]
> The **Sort By Name** option sorts the characters by name alphabetically (ascending or descending) by page, instead of the entire list of characters that match the search criteria. We made this decision to increase time performance.

<img width="598" alt="Screenshot 2024-08-08 at 19 44 55" src="https://github.com/user-attachments/assets/d5df057e-d753-41f0-b41e-feb84b602ffe">

> There are over 800 character records in the API. In order to make the program more efficient, instead of loading all matching results from the API at once in the display, we created pages of the results for searches with many results. These pages are corresponding with the pages that the API creates. The previous page button will only appear if there is a previous page to go to, and the next page button will only appear if there is a next page to go to. The user can see the population of the previous page and next page buttons on the above screen shot.

<img width="255" alt="Screenshot 2024-08-08 at 19 55 47" src="https://github.com/user-attachments/assets/6c8ab471-4568-4b8a-bfb1-0167f1d186ba">
<img width="255" alt="Screenshot 2024-08-08 at 19 56 34" src="https://github.com/user-attachments/assets/099d588a-be1d-4e0d-91f6-e8979ac1579e">
<img width="255" alt="Screenshot 2024-08-08 at 19 57 05" src="https://github.com/user-attachments/assets/7ae0a12b-78d0-4466-ada7-57711059ae02">

The above screenshots show a progression of filters being applied from left to right, applying a gender filter (Male), a status filter (Unknown), and then a species filter (Humanoid). 

<img width="598" alt="Screenshot 2024-08-08 at 20 00 46" src="https://github.com/user-attachments/assets/db4e2f6e-469c-44f5-a9f9-b727d15b0842">

Changing the sort by name option to Descending will show the results in the opposite order. Again, this is based on the results from the page generated by the API. 

<img width="595" alt="Screenshot 2024-08-09 at 00 07 50" src="https://github.com/user-attachments/assets/876993f0-4cce-4bb3-8515-57b1ff52640c">

If there are no characters that match the search criteria, the user will get this message displayed to the screen. 

> [!WARNING]
> The results may take a little while to load onto the display area. Each button click will queue another task - this program will work best if the user allows each click to return a result first.

### Exporting Search Results

The user can export the search results out to a file in XML, JSON, or CSV format. Please note that the API link must be generated first by clicking the search button, and these results will be the ones saved to the new file. 

<img width="602" alt="Screenshot 2024-08-08 at 19 43 41" src="https://github.com/user-attachments/assets/c55c254a-59b5-44e4-b684-bffa72c06ea8">

Earlier, we saw that a search for characters matching "Rick" in the name with all other filters set to their default setting shows multiple results. In the display area of the GUI, we have pages. [Here is the corresponding link that generates](https://rickandmortyapi.com/api/character/?name=rick). We see that there are 107 results and 6 pages (if you click through the buttons with the same search, you should get the same number of pages). While not all 107 results show up in the display area at once, they will all load into the file that the user will export. 

<img width="631" alt="Screenshot 2024-08-08 at 20 26 02" src="https://github.com/user-attachments/assets/0c848633-168e-492d-99df-04621af74250">

Here, I saved it out to a CSV format to easily show the number of rows of results.

<img width="711" alt="Screenshot 2024-08-08 at 20 28 24" src="https://github.com/user-attachments/assets/cf8a43fd-a18a-4b75-8e27-d44d52921fb5">

We see that there are 108 rows that populated from the saved file, with one row for the header, matching the 107 results as described above. 
