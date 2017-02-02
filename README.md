# MarvelCharacterList
*Android app designed to retrieve all information about Marvel's Characters.*

This app uses Marvel's API to get information about its characters, comics, series and other. 
This project is implemented in a way you dont need to change de source code to use it. All you have to do is go to http://developer.marvel.com/ and login to receive a private key and a public key, used to get the information. Once there is a limit for the number of searches/day I cannot leave my keys public. 

**Attention:** This branch will be updated for new content. Only download this one, once Master will not be changed.

**Usage:**
Once the app opens, there are two fields to add each key. The keys are stored in app, so there is no need to add it every time the app opens. 

**Project description:**
There are two important activities: LoginActivity and MainActivity. I will explain each one.

When the app starts, it goes to the LoginActivity. It searches for 'PreferenceManager', trying to find some valid key to use. If no key was ever used (first time), the user is prompt to add their keys. Once the "Login" button is clicked, the app moves to MainActivity and begin hitting the Marvel´s site with query. (It first checks for internet connection and validated the keys, but I assume you got it right). If you dont want to write your keys (they are quite extensive), it is possible to add it directly to the app using the "util.Constant.private(public)key" field. 

The MainActivity uses *RetroFit* to create the query and parse the result. The Models are in the "Model" folder. The most important is the **"CharacterModel"**, since the other only exists to help populate it. *Important: the app retrieves a lot of information from the API, even though it doesnt show. So if you want to know the name of the comics Spiderman shows up, it is already there (in the future I will improve the app).* After retrieved some information (there is a limit of 100 characters/query), it is added to the ListView Adapter and a list of characters is built.

The display package holds the Observer that will automatically change the character being displayed when another one is selected. The *display.ShowDetails* has the structure displayed and I use *Picasso* to load the images. Currently, the images are fetched directly from URL, but the code to download it to device is already implemented on network.DownloadImage. 

TO DO LIST:
- [] Implement search
- [x] Adjust listview properties
- [x] Add information about comics, events and stories to each character
- [x] Only request a new query when listview reaches bottom (no need to fetch all character at once)
- [] Test routine
