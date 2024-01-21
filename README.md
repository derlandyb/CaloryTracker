# Calorie Tracker App
## Calorie Tracker is a multi module Android app that calculate the ideal nutrients for each meal in a daily routine. Developed to studies about Android Modular Architecture, Unit testing, Integration testing and E2E testing.

|                                                                                                                             |             |
|:---------------------------------------------------------------------------------------------------------------------------:|    :----:   | 
| <img src="https://github.com/derlandyb/CaloryTracker/assets/1301100/96ee719e-c688-4b0c-ad6f-9f2c5fbbf435" height="400" />   |  <img src="https://github.com/derlandyb/CaloryTracker/assets/1301100/fe305e04-8cd6-4a19-ab4f-d0cd50aa054d" height="400" />  |
| <img src="https://github.com/derlandyb/CaloryTracker/assets/1301100/99beedda-cebb-4679-9062-55ce0c61fd71" height="400" />  |  <img src="https://github.com/derlandyb/CaloryTracker/assets/1301100/8bc1ce1c-80f2-42bb-a160-3609787c4b00" height="400" />  |


### High-level view:

- [Modular architecture](https://android-developers.googleblog.com/2022/09/announcing-new-guide-to-android-app-modularization.html)
- [FRP (Functional Reactive Programming)](https://www.toptal.com/android/functional-reactive-programming-part-1)
- [MVVM (Model-View-ViewModel)](https://www.techtarget.com/whatis/definition/Model-View-ViewModel#:~:text=Model%2DView%2DViewModel%20(MVVM)%20is%20a%20software%20design,Ken%20Cooper%20and%20John%20Gossman.)
- [Clean Architecture](https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164/ref=asc_df_0134494164/?tag=googleshopp00-20&linkCode=df0&hvadid=379726160779&hvpos=&hvnetw=g&hvrand=13366789383996027308&hvpone=&hvptwo=&hvqmt=&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9101050&hvtargid=pla-423658477418&psc=1&mcid=5207d568cee83006a8daea3090fc38ad)


## Tech Stack

### Core

- 100% [Kotlin](https://kotlinlang.org/)
- 100% [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Kotlin Flow](https://kotlinlang.org/docs/flow.html)
- [Hilt](https://dagger.dev/hilt/) (DI)
- [Jetpack Compose Navigation](https://developer.android.com/jetpack/compose/navigation)

### Local Persistence
- [SharedPreferences](https://developer.android.com/reference/kotlin/android/content/SharedPreferences)
- [Room DB](https://developer.android.com/training/data-storage/room) (SQLite ORM)

### Networking
- [Retrofit](https://square.github.io/retrofit/) (REST client)
- [Moshi](https://github.com/square/moshi) (JSON serialization)
