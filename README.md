Shopping Mall Android App
=================

**Shopping Mall 앱은 Kotlin 그리고 Jetpack Compose 로 제작 되었습니다.


# 개발 환경
1. 안드로이드 스튜디오 버전 : Android Studio Flamingo | 2022.2.1
2. JDK Gradle 환경 Jdk17 로 변경 하시면 앱의 빌드가 가능합니다.
   # Jdk17 변경방법
   1. 안드로이드 스튜디오 상단의 Andoid Stuiod 클릭
   2. Settings 클릭
   3. Build, Execution, Deployment 왼쪽 화살표 클릭
   4. Build Tools 왼쪽 화살표 클릭
   5. Gradle 클릭
   6. 하단의 Gradle Jdk 버전 17로 변경


# 아키텍처
클린 아키텍쳐로 구성되었습니다. 

PresentationLayer

DomainLayer

DataLayer - DataPaging
          - DataNetwork
          - DataLocalData

CommonUtils 

# Gradle 버전관리
buildSrc 디렉토리 내의 Dependencies 를 통해 여러개의 모듈의 버전관리를 한곳에서 관리하도록 설계 했습니다.

# Modularization
Shopping Mall 앱은 멀티모듈로 구성 하였습니다.

Domain Module 
- 클린 아키텍쳐의 도메인 레이어 담당

Data Module 
- DataPaging Module  -> 페이징 처리 모듈화 
- DataNetwork Module -> retrofit을 통한 API 통신 모듈화
- DataLocalData Module -> 내부 데이터베이스 모듈화
- CommonUtils Module - > 앱 전반적인 기능 담당 모듈화


# Test
- DataPaging Module
  - androidTest 
    - factory -> Category 상품 리스트 API 에서 반환되는 response 값을 직접 만들어주기 위해 생성 
    - fake -> api 호출 interface 를 구현한 모의 retrofit API 반환 클래스  
    - mediatorTest
      - mockDb , mockApi 를 통한 페이징 처리의 테스트를 위해 생성 하였습니다.
        1. 페이징의 끝에 도달하지 않았을 때 테스트 
        2. 페이징의 끝에 도달했을 때 테스트
        3. API의 오류로인한 에러 핸들링 테스트

# 사용자의 경험을 확대하기 위해 한 노력
- 네트워크 연결이 불안정하거나 사용자가 오프라인 상태일 때 에도 앱을 사용할 수 있도록 로컬 데이터베이스를 활용했습니다.
- 화면의 회전에 대응하도록 제작 하였습니다.
















