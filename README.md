# NewsApp
NewsApp is a modern Android application built with Kotlin that allows users to stay updated with the latest news. It fetches articles from the NewsAPI, enabling users to browse breaking news, search for specific topics, and save articles for offline reading.

## Features

*   **Breaking News**: Displays the latest top-headlines from the US.
*   **Search**: Provides a real-time search functionality for news articles across various topics.
*   **Saved Articles**: Allows users to save their favorite articles locally to read them later, even without an internet connection.
*   **Article Details**: Opens a full article view within a WebView for a seamless reading experience.
*   **Responsive UI**: Handles loading, success, empty, and error states gracefully.

## Architecture

This project follows the **MVVM (Model-View-ViewModel)** architecture pattern, promoting a separation of concerns and making the codebase more modular, testable, and maintainable.

*   **Model**: Represents the data layer, consisting of the remote data source (NewsAPI), the local database (Room), and the repository that abstracts the data operations.
*   **View**: The UI layer (Activities and Fragments) responsible for observing data from the ViewModel and displaying it to the user. Data Binding is used to connect UI components to data sources.
*   **ViewModel**: Acts as a bridge between the Model and the View. It holds and processes UI-related data, survives configuration changes, and exposes data via `LiveData`.

## Tech Stack & Libraries

*   **Kotlin**: Official programming language for Android development.
*   **Coroutines & Flow**: For managing background threads and handling asynchronous operations.
*   **Android Jetpack**:
    *   **ViewModel**: Manages UI-related data in a lifecycle-conscious way.
    *   **LiveData**: Observable data holder class used to notify views of data changes.
    *   **Navigation Component**: Handles all aspects of in-app navigation.
    *   **Room**: A persistence library for local data storage (SQLite).
    *   **Data Binding**: Binds UI components in layouts to data sources.
*   **Retrofit**: A type-safe HTTP client for making network requests to the NewsAPI.
*   **OkHttp**: Used for implementing an interceptor to log HTTP request and response data.
*   **Glide**: An image loading and caching library.
*   **Lottie**: Renders After Effects animations natively on Android for a richer UI experience.
*   **Material Components**: For building modern and beautiful UI components.

## Project Structure

The project is organized into packages to maintain a clean and scalable architecture:

```
├── model
│   ├── entity          # Kotlin data classes for API responses (Article, Source)
│   ├── localdata       # Room database components (DAO, Database, TypeConverters)
│   ├── remotedata      # Retrofit API service and setup
│   └── reposotry       # NewsRepository to manage data from remote and local sources
├── utils               # Utility classes, constants, and custom BindingAdapters
├── view
│   ├── adapters        # RecyclerView adapter (NewsAdapter)
│   ├── fragments       # UI fragments for each screen (BreakingNews, Search, Saved, Article)
│   └── MainActivity.kt # The single activity hosting all fragments
└── viewmodel           # ViewModels and the ViewModelProviderFactory
```
## Snapshots
<div align="center" style="display:flex; justify-content:center; gap:10px; flex-wrap:wrap">
  <img src="https://github.com/user-attachments/assets/21e3c6b3-8508-44d2-9685-11769686ef76" width="180">
  <img src="https://github.com/user-attachments/assets/7c458047-fc05-4ca2-a054-6f3bc41de89d" width="180">
  <img src="https://github.com/user-attachments/assets/100a59c7-c3f4-441e-81f8-4d6f2171d679" width="180">
  <img src="https://github.com/user-attachments/assets/86a93334-28e1-486b-b859-eec314855a2b" width="180">
  <img src="https://github.com/user-attachments/assets/e1a1b208-1a5f-453d-90f6-5f1311467725" width="180">
  <img src="https://github.com/user-attachments/assets/858ea250-c92d-4e67-9cb6-41b79a1563a6" width="180">
  <img src="https://github.com/user-attachments/assets/f20059df-e5ea-4751-a8c6-a3448b2ec9cd" width="180">
</div>



## Setup

1.  **Clone the repository**:
    ```sh
    git clone https://github.com/nourhanbakry/NewsApp.git
    ```
2.  **Open in Android Studio**:
    Open the cloned project in Android Studio.
3.  **Build the project**:
    Let Android Studio sync the Gradle files. The project includes the necessary API key in the `Constants.kt` file, so it should build and run without any additional configuration.
