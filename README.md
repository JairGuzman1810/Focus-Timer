# Focus Timer - Android App

This repository contains the source code for Focus Timer, an Android app that helps users manage their time with customizable focus, short break, and long break timers. It tracks rounds and total time, and uses Room for data persistence.

## Key Features

-   **Pomodoro Timer:** Includes three timer options:
    -   **Focus Time:** 25:00 minutes
    -   **Short Break:** 5:00 minutes
    -   **Long Break:** 15:00 minutes
-   **Customizable Timer:** Allows you to increase or decrease the duration of each timer.
-   **Start/Restart:** Easily start and restart the timer as needed.
-   **Round Tracking:** Keeps track of the number of completed rounds.
-   **Total Time Tracking:** Displays the total time spent on focus sessions.
-   **Daily Data Persistence:** Rounds and total time are saved per day using Room.
-   **Data Persistence:** Uses Room for local data storage, ensuring that your progress is saved.
-   **Tablet Support:** Adaptable layout for larger screens, such as tablets.

## Technologies and Patterns

-   **Kotlin:** The primary programming language used for the app.
-   **MVVM (Model-View-ViewModel):** Follows the MVVM architectural pattern for a clean separation of concerns.
-   **Clean Architecture:** Implements Clean Architecture principles for a scalable and maintainable codebase.
-   **Room:** Used for local data persistence, allowing the app to store and retrieve data efficiently.
-   **Hilt:** Used for dependency injection, simplifying the management of dependencies.
-   **Material 3 Window Size Class:** Implements Material 3's Window Size Class to adapt the UI to different screen sizes.
-   **Coroutines:** Utilizes Kotlin Coroutines for asynchronous programming, ensuring a smooth user experience.

## Dependencies

Here's a detailed list of the dependencies, libraries, and plugins used in the Focus Timer project, along with their respective versions:
### Libraries
```
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
hilt-android = { module = "com.google.dagger:hilt-android", version.ref = "hiltVersion" }
hilt-android-compiler = { module = "com.google.dagger:hilt-android-compiler", version.ref = "hiltVersion" }
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
androidx-ui = { group = "androidx.compose.ui", name = "ui" }
androidx-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
androidx-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
androidx-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
androidx-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
androidx-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
androidx-material3 = { group = "androidx.compose.material3", name = "material3" }
androidx-material3-windowsizeclass = { module = "androidx.compose.material3:material3-window-size-class", version.ref = "material3WindowSizeClass" }
androidx-lifecycle-viewmodel-compose = { group = "androidx.lifecycle", name = "lifecycle-viewmodel-compose", version.ref = "lifecycleViewmodelCompose" }
androidx-hilt-navigation-compose = { group = "androidx.hilt", name = "hilt-navigation-compose", version.ref = "hiltNavigationCompose" }
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "roomVersion" }
androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "roomVersion" }
androidx-room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "roomVersion" }
```
### Plugins
```
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
hilt-dagger = {id = "com.google.dagger.hilt.android", version.ref = "hiltVersion"}
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
```

## Installation and Setup
This section provides instructions on how to clone, build, and run the Focus Timer Android application on your local machine using Android Studio.

1.  **Clone the Repository:**

    -   Open your terminal or command prompt.
    -   Navigate to the directory where you want to store the project.
    -   Use the following command to clone the repository:
```
 git clone https://github.com/JairGuzman1810/ Focus- Timer. git
```
2.  **Open in Android Studio:**

    -   Launch Android Studio.
    -   If you see the "Welcome to Android Studio" screen, select "Open an Existing Project."
    -   If Android Studio is already open, go to "File" > "Open..."
    -   Navigate to the directory where you cloned the repository (e.g., `Focus-Timer`) and select it.
    -   Click "Open."

3.  **Sync Project with Gradle Files:**

    -   Once the project is open, Android Studio will prompt you to sync the project with the Gradle files.
    -   Click "Sync Now" in the notification bar that appears at the top of the editor.
    -   Alternatively, you can go to "File" > "Sync Project with Gradle Files."

4.  **Build the Project:**

    -   After the Gradle sync is complete, go to "Build" > "Make Project" to build the project.
    -   This step compiles the code and checks for any errors.

5.  **Run the App:**

    -   Connect an Android device to your computer via USB, or start an Android emulator.
    -   In Android Studio, select your connected device or emulator from the device dropdown menu in the toolbar.
    -   Click the "Run" button (green play icon) in the toolbar to run the app on your selected device or emulator.
  
## Screenshots
<div style="display:flex; flex-wrap:wrap; justify-content:space-between;">
    <img src="https://github.com/JairGuzman1810/Focus-Timer/blob/master/resources/screenshots/1.jpg" alt="Screenshot 1" width="180"/>
    <img src="https://github.com/JairGuzman1810/Focus-Timer/blob/master/resources/screenshots/2.jpg" alt="Screenshot 2" width="180"/>
    <img src="https://github.com/JairGuzman1810/Focus-Timer/blob/master/resources/screenshots/3.jpg" alt="Screenshot 3" width="180"/>
    <img src="https://github.com/JairGuzman1810/Focus-Timer/blob/master/resources/screenshots/4.jpg" alt="Screenshot 4" width="180"/>
</div>
