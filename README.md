<img width=500 src="https://github.com/user-attachments/assets/0e060721-897e-4b0e-9a81-6b7d7df7af34"/>

# 왓캠퍼스 - 대학 소식을 한 눈에

왓캠퍼스는 원하는 학과/학교 공지를 구독하고 알림 받을 수 있는 서비스에요

**`KMM(Kotlin Multiplatform Mobile)`** 환경에서 개발된 서비스인만큼 **Android, iOS 플랫폼을 모두 지원**합니다

# 무엇을 위한 서비스인가요?

왓캠퍼스는 대학생들이 **학교 공지를 접하기 불편해하는 문제를 해결**하기 위해 출시되었어요.

## 대학 공지 확인이 불편해요 🥺

장학금, 수강신청 등 공지는 대학 생활에서 굉장히 중요한 요소라고 생각해요.

그렇기 때문에, 대부분의 대학에는 이미 자체적인 공지 푸시 알림 시스템이 있지만 **모든 공지를 수신받거나 선택적으로 수신받을 수는 없는 경우가 대부분**이에요.

가령, 학과별 공지 푸시 시스템은 없는 경우가 많기 때문에, 학과별로 학생회 인원들이 **중요한 공지를 직접 단톡방에 공유하는 불편함**이 있어요.

뿐만 아니라 **매번 공지를 직접 확인하기 불편**하고, **중요한 공지를 깜빡 놓치는 경우도 빈번**해요.

## 왓캠퍼스가 문제들을 직접 해결해봤어요 🧐

원하는 대학교와 학과를 설정 해두면 **학과 / 카테고리별 공지를 원-클릭으로 필터링해서 모두 조회**할 수 있어요.

중요한 공지를 놓치지 마세요! **공지 카테고리를 구독하면 공지가 등록되었을 때 푸시 알림**으로 알려드려요.

**공지 북마크 및 공유하기 기능을 제공**해요. 중요한 공지를 보기 쉽게 보관하고 손쉽게 친구들에게 공유할 수 있어요.

캠퍼스가 익숙하지 않아서 매번 웹사이트나 갤러리에서 캠퍼스 지도를 찾느라 불편하셨나요? 왓캠퍼스에서 **캠퍼스 지도를 볼 수 있어요.**

## 이런 계획을 하고 있어요 🚀

신규 서비스인만큼 불안정한 기능이 있을 수 있어요. 버그와 성능을 꾸준히 개선하고 사용자분들께 더 나은 서비스를 제공해드리려고 해요.

아직 왓캠퍼스에 등록되지 않은 학교가 많아요. 꾸준히 학교를 추가할 계획이며 `whatcam24@gmail.com` 으로 학교 등록을 신청해주시면 더 높은 우선순위 대상이 될 수 있어요!

이 외에도 대학교별 채팅같은 학교 소식과 관련된 기능을 점차 늘려 나갈 예정이에요.

# 개발

## 환경

- IDE : Android Studio ([Koala](https://developer.android.com/studio/releases?hl=ko)), XCode
- Target : CMM(Compose Multiplatform Mobile)
- Language : Kotlin 2.0.0, Swift

## 라이브러리

### Feature/Core

- AndroidX
  + Activity
  + AppCompat
  + Compose
    * ViewModel
    * Navigation
- Coroutine & Flow
- Ktor
- Room
- Datastore
- Coil3
- Kotlinx Datetime
- Firebase Messaging, KMP Notifier
- Kottie (Lottie)

### DI

- Koin

### Logging

- Napier

### Permisson

- Moko

### Build

- Build Konfig
- [Gradle Version Catalog](https://docs.gradle.org/current/userguide/platforms.html) ([libs.versions.toml](https://github.com/CampusPing/Mobile-WhatCampus/blob/main/WhatCampus/gradle/libs.versions.toml))

# 아키텍쳐

- Clean Architecture
- Multi-Module (현재 패키지만 분리되어 있어 리팩터링 예정)

_아키텍처 이미지 추가 예정_
  
