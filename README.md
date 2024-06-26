# Bangkit 2024 Batch 1 Capstone Team `C241-PS069`
<h1>COMPASSION.LY</h1>
<h4>Compass For Your Passion! Compassion.ly is an Android application designed to provide recommendations for fields of study and college majors based on the user's learning activities in the app.</h4>
<h5>
Our team aims to address the issue of students choosing the wrong major, a common problem in Indonesia. We offer an approach that provides recommendations using Machine Learning to analyze user interests through the learning process. Through the features we provide, we hope this application can help users explore their interests and the majors we recommend.</h5>

## MD Members:
| Name | Bangkit-ID     | Github-Profile  | LinkedIn |
| :-------- | :------- | :-------------------------------- | :-------------------------------- |
| Ilham Surya Putra Pamungkas      | `A622D4KY4540` | [@Ilham](https://github.com/ispamungkas) | <a href="https://www.linkedin.com/in/ilham-surya-putra-pamungkas-71b63825a/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"/></a>   |
| Jesica Sanditia Putri      | `A132D4KX4100` | [@Jesica](https://github.com/jesicasp) |<a href="https://www.linkedin.com/in/jesica-sanditia-putri/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"/></a>|

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Dependencies](#dependencies)
- [Prerequisites](#prerequisites)
- [Deployment Link APK Compassion.ly](#deplink)
- [How to Install](#howto)
- [Languages and Tools](#lang_tool)
- [Project Structure](#structure)


## <a name="features"></a> Features

1. Google Login
2. Learn
   
   In this feature, users will engage in learning about the presented topics briefly. The topics provided are fundamental lessons from various courses and majors, enabling   users to gain brief insights into several majors displayed randomly. Users can choose to read a presented topic or skip it. After reading the presented topic, users are prompted to rate the topic.
   
3. Fields and Major Recommendation
   
   Users will be provided with recommendations for fields of study and majors based on their learning activities in the application. These recommendations will continuously update as users engage in more learning. Therefore, the more learning and ratings provided by the user, the more accurate the recommendations will become.

4. Quick Recommendation
   
   The Quick Recommendation feature is used to obtain field recommendations without going through the learning process. Users simply provide a description of at least 50 characters for 3 questions, and we will provide field recommendations by analyzing the given descriptions.

5. Major Introduction
   
   The Major Introduction feature is designed to display information related to majors, including an introduction to the majors, the courses available in those majors, job prospects, and a list of universities offering those majors. This feature was created to provide users with detailed information about various majors and enrich their knowledge about a major after receiving recommendations from our application.

6. Course Introduction
    
   For each major, there will be a list of courses, and we will also provide brief information about each course. This way, users can understand what courses they will study if they choose that major and get brief insights into each course.
   
7. Colleges Information
   
      We provide a list of universities along with the majors they offer.
   
8. Features Introduction

      Introducing features to guide users on how to use the app.

9. Topic History
      
      Feature to display the rating history of topics already learned by user.
10. Fields and major Recommendation histories
    
       Show the fields and the last major recommendation obtained by user.

## <a name="technologies"></a> Technologies 
  ![Technologies](https://dicoding-web-img.sgp1.cdn.digitaloceanspaces.com/original/academy/dos:61e970043ca90360db4661e6e35adb0220221228091054.png)
- [MVVM](https://developer.android.com/topic/architecture)
- [Dependency Injection](https://developer.android.com/training/dependency-injection)
- [Repository](https://developer.android.com/topic/architecture/data-layer)

## <a name="dependencies"> Dependencies 
- [Material Design](https://m2.material.io/develop/android)
- [Firebase Authentication](https://firebase.google.com/docs/auth?hl=id)
- [JUnit](https://junit.org/junit4/)
- [Dots Indicator](https://github.com/tommybuonomo/dotsindicator)
- [Custom Google Signin Button](https://github.com/shobhitpuri/custom-google-signin-button)
- [ROOM](https://developer.android.com/training/data-storage/room)
- [Shared Preference](https://developer.android.com/training/data-storage/shared-preferences)
- [Retrofit](https://square.github.io/retrofit/)
- [Circle Imageview](https://github.com/hdodenhof/CircleImageView)
- [Image Slide Show](https://github.com/denzcoskun/ImageSlideshow)
- [Glide](https://github.com/bumptech/glide)
- [Lifecycle & Livedata](https://developer.android.com/jetpack/androidx/releases/lifecycle)

## <a name="prerequisites"> Prerequisites
         1. Android Studio at least version Hedgehog
         2. JRE (Java Runtime Environment) or JDK (Java Development Kit).
         3. Firebase Authentication 

## <a name="deplink"> Deployment Link APK Compassion.ly

Download Link apk Compassion.ly:<br>
[Compassion.ly](https://drive.google.com/file/d/19UzTTVlRCTD8c-FWAlIQ8Hp9k7w9Oo29/view?usp=sharing)

## <a name="howto"> How to Install and Run the Project
1. Click on the Compassion.ly APK link.
2. Download the file.
3. Once downloaded, locate the file in your File Manager.
4. Click on the file and install it.
5. After successfully installing, you can open the application and begin logging in.

## <a name="lang_tool"> Languages and Tools:
<p align="left">
  <a href="https://developer.android.com" target="_blank" rel="noreferrer">
    <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/android/android-original-wordmark.svg" alt="android" width="40" height="40"/>
  </a>
    <a href="https://www.figma.com/" target="_blank" rel="noreferrer">
    <img src="https://www.vectorlogo.zone/logos/figma/figma-icon.svg" alt="figma" width="40" height="40"/>
  </a>
    </a>
  <a href="https://kotlinlang.org" target="_blank" rel="noreferrer">
    <img src="https://www.vectorlogo.zone/logos/kotlinlang/kotlinlang-icon.svg" alt="kotlin" width="40" height="40"/>
  </a>
</p>

###

## <a name="structure">Project Structure :
* `datasource`
  - `local`
  - `network`
  - `preference`
* `models`
  - `forSending`
  - `local`
* `presentation`
  - `adapter`
  - `feature`
* `repository`
  - `core`
    - `local`
    - `network`
  - `di`
* `utility`

🔥 Supported By

###

<div align="center">
   <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeGw8kO0DxGpoYXf4pL6NgnEs7NxaFGA8H4Q&s" height="80" alt="kampus merdeka" style="margin-right:20px;"/>
  <img src="https://storage.googleapis.com/kampusmerdeka_kemdikbud_go_id/mitra/mitra_af66db2e-0997-4f52-9cc0-a14412eeeab9.png" height="80" alt="bangkit academy" style="margin-right:left0px;"/>
  
</div>

###


