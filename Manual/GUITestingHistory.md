### Test search results work as intended

<img width="599" alt="Screenshot 2024-08-08 at 21 15 31" src="https://github.com/user-attachments/assets/1cbe693d-6d13-492b-8fd6-93847d877ef8">

Testing for search results with no filters applied. 

<img width="598" alt="Screenshot 2024-08-08 at 21 16 18" src="https://github.com/user-attachments/assets/4adffaa9-01a2-4f00-8412-7eca23352c75">

Testing for the search results with a name filter (searching for character by name). Next Page button loads correctly, indicating a next page of results ([generated API link indicates 4 pages of results](https://rickandmortyapi.com/api/character/?name=morty)).

### Test clicking of next and previous buttons

<img width="599" alt="Screenshot 2024-08-08 at 21 18 58" src="https://github.com/user-attachments/assets/c7f178cb-3592-410e-a7ac-b9c75bf75f46">

Page 2

<img width="597" alt="Screenshot 2024-08-08 at 21 19 23" src="https://github.com/user-attachments/assets/64e78401-989b-4e8d-bdb2-35a2da4c7467">

Page 3

<img width="595" alt="Screenshot 2024-08-08 at 21 19 58" src="https://github.com/user-attachments/assets/ce07a910-7ffe-41fc-8c55-04134fea2f29">

Page 4. Next Page button does not generate, which is the expected and correct behavior.

<img width="599" alt="Screenshot 2024-08-08 at 21 20 22" src="https://github.com/user-attachments/assets/96a5a113-5134-4034-bdaf-3b8ddcf7779e">

Previous button loads the prior page, as expected. 

### Test clicking search button again with the same search criteria

<img width="598" alt="Screenshot 2024-08-08 at 21 16 18" src="https://github.com/user-attachments/assets/8ed057f4-b881-45a9-9894-bab4ec0f532b">

Testing that clicking the search button again with the same search criteria reloads the first page of the results matching the search criteria. 

### Test clicking search button with different search criteria

<img width="602" alt="Screenshot 2024-08-08 at 19 43 41" src="https://github.com/user-attachments/assets/df26065e-3d5c-4f6b-aef4-51c04a9ef4ad">

### Test filters and sorting

<img width="596" alt="Screenshot 2024-08-08 at 19 55 47" src="https://github.com/user-attachments/assets/6a6d3561-2e54-420d-a380-bd099807b18b">

Select "Male" in the filter by gender dropdown menu.

<img width="596" alt="Screenshot 2024-08-08 at 19 56 34" src="https://github.com/user-attachments/assets/99d08362-47da-47ce-9ee4-e1b985419e78">

Select "Unknown" in the filter by status dropdown menu.

<img width="599" alt="Screenshot 2024-08-08 at 19 57 05" src="https://github.com/user-attachments/assets/249eff5a-6860-4195-a8eb-183c06eb3247">

Select "Humanoid" in the filter by species dropdown menu.

<img width="598" alt="Screenshot 2024-08-08 at 20 00 46" src="https://github.com/user-attachments/assets/a2fd4f92-9888-42ce-8416-b04838194ce5">

Select "Descending" in Sort By Name. 

### Test search criteria with no matching characters properly returns no search found message.

### Test export

<img width="599" alt="Screenshot 2024-08-08 at 21 37 04" src="https://github.com/user-attachments/assets/aa768a29-8dea-4a02-803d-1a0fe5d170bd">

Save the search results to an XML file.

<img width="645" alt="Screenshot 2024-08-08 at 21 37 36" src="https://github.com/user-attachments/assets/46526386-31fd-4a76-bbb2-25db4504d412">

XML file loaded as expected.

<img width="599" alt="Screenshot 2024-08-08 at 21 43 36" src="https://github.com/user-attachments/assets/1971d18f-67fd-49d8-bdcf-c7630f944bbe">

Save the search results to a JSON file.

<img width="643" alt="Screenshot 2024-08-08 at 21 46 46" src="https://github.com/user-attachments/assets/e221e65f-0810-4ee1-9c0d-edfdd45cf59d">

JSON file loaded as expected.

<img width="598" alt="Screenshot 2024-08-08 at 21 45 06" src="https://github.com/user-attachments/assets/e3b4eb80-c744-47c7-b941-63268665f7fd">

Save the search results to a CSV file.

<img width="644" alt="Screenshot 2024-08-08 at 21 48 21" src="https://github.com/user-attachments/assets/fcfb93ce-67b4-4d82-b2fa-26800d1e58b7">

CSV file loaded as expected. 

<img width="597" alt="Screenshot 2024-08-08 at 21 59 42" src="https://github.com/user-attachments/assets/d691e33a-449d-4b69-9a59-2562fea00c5e">

Save all results to a CSV. 826 results are expected, so 827 lines should be in the CSV, including the header. 

<img width="904" alt="Screenshot 2024-08-08 at 22 03 48" src="https://github.com/user-attachments/assets/165f8428-93ae-440c-a76d-7409a469b637">

All results saved out as expected. 
