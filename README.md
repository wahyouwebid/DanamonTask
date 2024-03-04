# DanamonTask
Native Kotlin

## Screenshot App
#### Auth (Login & Register)
<table>
  <tr>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709515286/github/Screenshot_20240304_080330_DanamonTask.jpg" alt="Screenshot 1" width="200">
    </td>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709515286/github/Screenshot_20240304_080350_DanamonTask.jpg" alt="Screenshot 2" width="200">
    </td>
   <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709515286/github/Screenshot_20240304_080400_DanamonTask.jpg" alt="Screenshot 3" width="200">
    </td>
   <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709515287/github/Screenshot_20240304_080406_DanamonTask.jpg" alt="Screenshot 4" width="200">
    </td>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709515287/github/Screenshot_20240304_080455_DanamonTask.jpg" alt="Screenshot 5" width="200">
    </td>
  </tr>
</table>

#### Main Feature (Users & Admin)
<table>
  <tr>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709515286/github/Screenshot_20240304_080952_DanamonTask.jpg" alt="Screenshot 6" width="200">
    </td>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709515288/github/Screenshot_20240304_080813_DanamonTask.jpg" alt="Screenshot 7" width="200">
    </td>
   <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709515287/github/Screenshot_20240304_080823_DanamonTask.jpg" alt="Screenshot 8" width="200">
    </td>
   <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709515286/github/Screenshot_20240304_080828_DanamonTask.jpg" alt="Screenshot 9" width="200">
    </td>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709515288/github/Screenshot_20240304_080813_DanamonTask.jpg" alt="Screenshot 10" width="200">
    </td>
  </tr>
</table> 

<br>

## Setup Project
For Gradle, i am using the Kotlin DSL, so the extension of the build.gradle file, which was previously in Groovy, will be changed to *build.gradle.kts*, as shown in the following image.

<table>
  <tr>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709515671/github/Screenshot_2024-03-04_at_08.27.31.png" alt="Screenshot 1" width="100%">
    </td>
  </tr>
</table>

Please used JAVA Version using VERSION_17:

```kotlin
android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
```

Android Gradle Plugin Version **7.4.2** and Gradle Version **8.1**

```kotlin
plugins {
    id("com.android.application") version "7.4.2" apply false
    id ("com.android.library") version "7.4.2" apply false
    id ("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id ("com.google.dagger.hilt.android") version "2.44" apply false
}
```

<br>

## Architectures
The architecture of this project, I am using **Clean Architecture** combined with **Model View ViewModel (MVVM)**, which acts as the **presentation layer**.

Here is the implemented flow diagram for this project:
<table>
  <tr>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709516073/github/Screenshot_2024-03-04_at_08.34.12.png" alt="Screenshot 1" width="100%">
    </td>
  </tr>
 <tr>
  <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709516537/github/Screenshot_2024-03-04_at_08.41.57.png" alt="Screenshot 1" width="100%">
    </td>
 </tr>
</table>

Here is the project structure **Common, Core, Navigation**.

<table>
  <tr>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709516854/github/Screenshot_2024-03-04_at_08.47.19.png" alt="Screenshot 1" width="100%">
    </td>
   <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709516854/github/Screenshot_2024-03-04_at_08.47.19.png" alt="Screenshot 2" width="100%">
    </td>
   <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709516854/github/Screenshot_2024-03-04_at_08.47.19.png" alt="Screenshot 3" width="100%">
    </td>
  </tr>
</table>

Here is the project structure that I have implemented using **Clean Architecture**.

<table>
  <tr>
    <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709517237/github/Screenshot_2024-03-04_at_08.53.31.png" alt="Screenshot 1" width="100%">
    </td>
   <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709517238/github/Screenshot_2024-03-04_at_08.52.14.png" alt="Screenshot 2" width="100%">
    </td>
   <td>
      <img src="https://res.cloudinary.com/ujangwahyu/image/upload/v1709517238/github/Screenshot_2024-03-04_at_08.52.44.png" alt="Screenshot 3" width="100%">
    </td>
  </tr>
</table>

<br>

## Api List Photo

The API used in this project is https://jsonplaceholder.typicode.com.

The base URL used is as follows:
```
https://jsonplaceholder.typicode.com/
```

|Method | Endpoint | Usage |
| ---- | ---- | --------------- |
|GET| `photos?_page={PAGE}&_limit={LIMIT}` or `photos?_page=1&_page=10` | Get All Photos|

<br>

## 3rd Party Library

I am using several third-party libraries for this project, such as libraries for Dependency Injection, RestAPI, Reactive Programming, Unit Testing, etc.

### Dependency Injection
|Name | Library |
| ---- | ---- |
|Dagger Hilt| com.google.dagger:hilt-android:2.44 |
|Dagger Hilt Compiler| com.google.dagger:hilt-android-compiler:2.44 | 
|AndroidX Hilt Compiler| androidx.hilt:hilt-compiler:1.0.0|

### Reactive Programming & Data Manipulation
|Name | Library |
| ---- | ---- |
|RxAndroid| io.reactivex.rxjava3:rxandroid:3.0.0 |
|RxRava| io.reactivex.rxjava3:rxjava:3.0.7 |
|RxBinding| com.jakewharton.rxbinding3:rxbinding:3.0.0|

### Networking & Rest Api
|Name | Library |
| ---- | ---- |
|Retrofit2| com.squareup.retrofit2:retrofit:2.9.0 |
|Retrofit2 RxJava| com.squareup.retrofit2:adapter-rxjava3:2.9.0 |
|Retrofit2 Gson| com.squareup.retrofit2:converter-gson:2.9.0|

### Interceptor & Logging
|Name | Library |
| ---- | ---- |
|OkHttp| com.squareup.okhttp3:okhttp:4.9.0 |
|OkHttp Interceptor| com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3 |
|Chucker Debug| com.github.chuckerteam.chucker:library:3.5.2 |
|Chucker Release| com.github.chuckerteam.chucker:library-no-op:3.5.2 |

### Unit Test
|Name | Library |
| ---- | ---- |
|Junit4| junit:junit:4.13.2 |
|Mockito| org.mockito:mockito-core:4.0.0 |
<br>

<br>
 <h1>Creator</h1>
 <p>created by [wahyouwebid](http://github.com/wahyouwebid)</p>
 <p>You can contact me at : wahyouwebid@gmail.com</p>

