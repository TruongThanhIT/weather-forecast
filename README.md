# Get Weather Forecast Android App
This app will let you retrieve weather information based on your searching criteria and render the searched results on the dashboard screen.

![processed](https://user-images.githubusercontent.com/22128728/113499008-6c26f180-953c-11eb-996a-fc6fbcbfb480.jpeg)

# Getting Started

- Clone or download this repository
- Run and feel free to use this app without any account

# Functionalities
- Input the city name with a minimum of 3 keywords, it's will automatically searching. Or you can manually search by click on the "Get weather" button.
- Render the searched results as a list of weather items.
- Support caching mechanism to prevent the app from generating a bunch of API requests.
- Manage caching mechanism & lifecycle.
- Handle failures.
- Supports disability using Talkback (screen reader)
- Encrypt and decrypt any internal data caching.
- Unit testing for ViewModel logic.

# Libraries and Frameworks

- Presentation module
   - Data-binding
   - MVVM
   - Navigation Graph
- Data module
    - SharePreference
-  Network
    - Retrofit: ver 2.7.1 support kotlin coroutines
    - Okhttp3
    - Gson
- Dependence Injection: Hilt
- Kotlin coroutines
- Layout
    - ConstraintLayout
- Testing
    - JUnit4
    - Mockito
    - Nhaarman mockito kotlin

# Base Architecture Diagram (Model-View-ViewModel)

![image](https://user-images.githubusercontent.com/22128728/113498788-5284aa80-953a-11eb-97ee-8edb7f201c15.png)

### View

- Activity, Fragment, Views
- Binding data from ViewModel
- Handle UI logic

### View-Model

- Live Data
- Code logic

### Domain use-case

Define all functions to use-case

- Get data from the repository

### Model - Data layer | Repository

All data needed for the application comes from this

Receive a request to get data. Switch data between remote and local to return a value 

- Local data source: Room for complexly functions in the feature
- Remote data source: web service API
- Share preference

# Outputs completed
1. Programming language: Kotlin
2. Design app's architecture (MVVM)
3. Apply LiveData mechanism
4. UI looks like in the attachment.
5. Write UnitTests
6. Acceptance Tests
7. Exception handling
8. Caching handling (using hashMap instead of DataBase cause weather forecast information can be changed by time)
9. Secure Android app from:
   - a. Decompile APK (ProGuard)
   - d. Encryption for sensitive information (Encrypt in share preference)
10. Accessibility for Disability Supports:
   a. Talkback: Use a screen reader.
11. Solution diagrams for the components, infrastructure design.
12. Readme file includes:
      - a. A brief explanation for the software development principles, patterns & practices being applied
      - b. A brief explanation for the code folder structure and the key Kotlin libraries and frameworks being used
      - c. All the required steps to get the application run on local computer
      - d. A checklist of items has been done.
      - d. A checklist of items has been done.
