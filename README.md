# 미션 - 숫자 야구

## 기능 명세 8/25 및 구현하면서 생각한것들

- 어떤 객체들이 서로 의사소통하는가?
    - Computer, Game, GameManager, Player
        - GameManager: 게임 클라이언트의 전반적인 동작을 담당하며, Game 객체와 Player 객체의 생성을 담당하고, 잘못된 입력에 대한 Exception 체크 및 어플리케이션 종료를 담당한다.
        - Game: 하나의 게임의 시작과 종료를 담당하고, Player 객체를 소속시키고 Computer 객체 생성을 담당한다.
        - Player: 사용자를 대변하는 객체로, 사용자로부터 적절한 입력을 받아서 validation 체크 후 입력값을 다른 객체(GameManager, Game 객체)로 전달한다.
        - Computer: 컴퓨터를 대변하는 객체로, Game 객체에 의해 생성되며 생성과 동시에 Baseball 게임을 위한 숫자값 초기화 과정을 갖고, 사용자로 부터 받은 추측값과 대조를 하여 결과를 만들어 낸다.

    - 피드백
        - validate 메소드의 반환타입을 void로 두는것을 고려해봐라
        - Computer 객체의 printResultMessage 의 복잡성을 해소시켜라
        - StreamAPI를 사용하여 조금 더 가독성을 높혀보자.

    - 개인적인 고민거리
        - Game 객체와 Computer 객체가 분리될 필요가 있을까?
            - 사실 Game의 생명주기와 Computer의 생명주기는 현재 같이 움직인다. 그리고 Computer가 Game과 달리 특별하게 행동하는것도 없다.
        - Enum들을 하나의 java파일로 둘 이유가 있을까?
            - 특정 클래스에 종속적이라면 내부로 구현하자.
            - 
## 리펙토링 8/27

- 수정사항
  - 종속적 enum들 nested enum으로 변경
    - 특정 클래스에 상태를 나타내는 목적으로 사용되는 enum들이라면 nested로 변경하는것이 좋다.
      - BaseballState -> Computer, GameManagerState -> GameManager 
    - 테스트코드를 함께 실행하면 두번째 테스트코드가 실패하는 경우가 있음
      - 원인은 밝히지 못했으나,,, 현재로서는 싱글톤이었던 GameManager에 문제가 있음을 판단하여 싱글톤 로직을 제거함
        - 제거하는 김에 다른 클래스들의 인스턴스 생성방식도 기존의 생성자에서 가독성을 위해 정적 팩토리 메소드로 변경
    - StreamAPI 적극활용
      - Computer 클래스의 pickComputerCards 메소드 내부 for-loop에서 StreamAPI로 가독성을 높혀보자.
  
- 고민했던 사안
  - 다른사람들 코드를 보니 MVC 패턴을 사용한것 같았다.
    - MVC는 Model View Controller로서 UI를 담당하는 View와 데이터와 비즈니스를 담당하는 Model, 그리고 Model과 View로의 지시사항을 전달하는 Controller로 구성된다.
      - 주로 사용자 인터페이스와 비즈니스 로직을 완전히 분리하여 비즈니스 로직에 집중하는 아키텍쳐이다.

    - 우선 MVC를 도입해야 할까를 고민했는데
      - 이는 사실 소프트웨어를 만든단 생각으로 접근을 해야하지, 과제를 한다는 입장으로 접근해서인듯 하다.
      - 왜냐하면 프로젝트를 진행하기전 디자인하는건 당연한 얘기고, 가장 높은 추상화 레벨부터 디자인하는것이 맞기 때문이다.
      - 그래서 아키텍쳐 레벨부터 생각을 하는것이 올바르다 판단하였다.
    - 왜 MVC인가? 
      - 프로그램을 생각해보면 실질적인 UI가 있는건 아니지만 분명 사용자와 상호작용하는 부분이 필요하다
        - 사용자에게 안내문구및 결과를 표시하는 UI 등 -> View에 해당됨
        - 사용자로 부터 입력을 받아서 처리하는 부분 -> Controller
      - 내부적인 비즈니스로직이 존재함
        - 이는 야구게임 로직에 해당함
      - 갖가지 데이터 모델이 필요함
        - 현재 야구게임이라는 도메인상에 존재하는 데이터 모델은 BaseballNumber가 된다.
      
    - 이를 바탕으로하여 아키텍쳐 단계에서 부터 새롭게 설계해보자.
      - 사용자로부터 입력을 받고, 입력을 가공 및 validation하는 등의 행동을 취하는 곳을 View
        - View에서는 Empty검사만을 저수준의 입력값 검사를 진행
      - 적절히 가공된 입력값에 따라 적절한 비즈니스 모델 객체를 호출하는 Controller
        - Controller에서는 View에서 넘겨받은 데이터에 대해서 비즈니스 모델에 적합한지에 관한 Validation을 진행한다.
      - Model은 비즈니스 모델로 게임 진행에 있어서 제공되는 갖가지 메소드들을 사용한다.

## 🔍 진행 방식

- 미션은 **기능 요구 사항, 프로그래밍 요구 사항, 과제 진행 요구 사항** 세 가지로 구성되어 있다.
- 세 개의 요구 사항을 만족하기 위해 노력한다. 특히 기능을 구현하기 전에 기능 목록을 만든다.
- 기능 요구 사항에 기재되지 않은 내용은 스스로 판단하여 구현한다.

## 📮 미션 제출 방법

- 미션 구현을 완료한 후 GitHub을 통해 제출해야 한다.
    - GitHub을 활용한 제출 방법은 [프리코스 과제 제출](https://github.com/woowacourse/woowacourse-docs/tree/master/precourse) 문서를 참고해
      제출한다.
- GitHub에 미션을 제출한 후 [우아한테크코스 지원](https://apply.techcourse.co.kr) 사이트에 접속하여 프리코스 과제를 제출한다.
    - 자세한 방법은 [제출 가이드](https://github.com/woowacourse/woowacourse-docs/tree/master/precourse#제출-가이드) 참고
    - **Pull Request만 보내고 지원 플랫폼에서 과제를 제출하지 않으면 최종 제출하지 않은 것으로 처리되니 주의한다.**

## 🚨 과제 제출 전 체크 리스트 - 0점 방지

- 기능 구현을 모두 정상적으로 했더라도 **요구 사항에 명시된 출력값 형식을 지키지 않을 경우 0점으로 처리**한다.
- 기능 구현을 완료한 뒤 아래 가이드에 따라 테스트를 실행했을 때 모든 테스트가 성공하는지 확인한다.
- **테스트가 실패할 경우 0점으로 처리**되므로, 반드시 확인 후 제출한다.

### 테스트 실행 가이드

- 터미널에서 `java -version`을 실행하여 Java 버전이 17인지 확인한다.
  Eclipse 또는 IntelliJ IDEA와 같은 IDE에서 Java 17로 실행되는지 확인한다.
- 터미널에서 Mac 또는 Linux 사용자의 경우 `./gradlew clean test` 명령을 실행하고,
  Windows 사용자의 경우 `gradlew.bat clean test` 또는 `./gradlew.bat clean test` 명령을 실행할 때 모든 테스트가 아래와 같이 통과하는지 확인한다.

```
BUILD SUCCESSFUL in 0s
```

---

## 🚀 기능 요구 사항

기본적으로 1부터 9까지 서로 다른 수로 이루어진 3자리의 수를 맞추는 게임이다.

- 같은 수가 같은 자리에 있으면 스트라이크, 다른 자리에 있으면 볼, 같은 수가 전혀 없으면 낫싱이란 힌트를 얻고, 그 힌트를 이용해서 먼저 상대방(컴퓨터)의 수를 맞추면 승리한다.
    - 예) 상대방(컴퓨터)의 수가 425일 때
        - 123을 제시한 경우 : 1스트라이크
        - 456을 제시한 경우 : 1볼 1스트라이크
        - 789를 제시한 경우 : 낫싱
- 위 숫자 야구 게임에서 상대방의 역할을 컴퓨터가 한다. 컴퓨터는 1에서 9까지 서로 다른 임의의 수 3개를 선택한다. 게임 플레이어는 컴퓨터가 생각하고 있는 서로 다른 3개의 숫자를 입력하고, 컴퓨터는 입력한 숫자에 대한
  결과를 출력한다.
- 이 같은 과정을 반복해 컴퓨터가 선택한 3개의 숫자를 모두 맞히면 게임이 종료된다.
- 게임을 종료한 후 게임을 다시 시작하거나 완전히 종료할 수 있다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다.

### 입출력 요구 사항

#### 입력

- 서로 다른 3자리의 수
- 게임이 끝난 경우 재시작/종료를 구분하는 1과 2 중 하나의 수

#### 출력

- 입력한 수에 대한 결과를 볼, 스트라이크 개수로 표시

```
1볼 1스트라이크
```

- 하나도 없는 경우

```
낫싱
```

- 3개의 숫자를 모두 맞힐 경우

```
3스트라이크
3개의 숫자를 모두 맞히셨습니다! 게임 종료
```

- 게임 시작 문구 출력

```
숫자 야구 게임을 시작합니다.
``` 

#### 실행 결과 예시

```
숫자 야구 게임을 시작합니다.
숫자를 입력해주세요 : 123
1볼 1스트라이크
숫자를 입력해주세요 : 145
1볼
숫자를 입력해주세요 : 671
2볼
숫자를 입력해주세요 : 216
1스트라이크
숫자를 입력해주세요 : 713
3스트라이크
3개의 숫자를 모두 맞히셨습니다! 게임 종료
게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.
1
숫자를 입력해주세요 : 123
1볼
...
```

---

## 🎯 프로그래밍 요구 사항

- JDK 17 버전에서 실행 가능해야 한다. **JDK 17에서 정상적으로 동작하지 않을 경우 0점 처리한다.**
- 프로그램 실행의 시작점은 `Application`의 `main()`이다.
- `build.gradle` 파일을 변경할 수 없고, 외부 라이브러리를 사용하지 않는다.
- [Java 코드 컨벤션](https://github.com/woowacourse/woowacourse-docs/tree/master/styleguide/java) 가이드를 준수하며 프로그래밍한다.
- 프로그램 종료 시 `System.exit()`를 호출하지 않는다.
- 프로그램 구현이 완료되면 `ApplicationTest`의 모든 테스트가 성공해야 한다. **테스트가 실패할 경우 0점 처리한다.**
- 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 이름을 수정하거나 이동하지 않는다.

### 라이브러리

- `camp.nextstep.edu.missionutils`에서 제공하는 `Randoms` 및 `Console` API를 사용하여 구현해야 한다.
    - Random 값 추출은 `camp.nextstep.edu.missionutils.Randoms`의 `pickNumberInRange()`를 활용한다.
    - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.

#### 사용 예시

```java
List<Integer> computer = new ArrayList<>();
while (computer.size() < 3) {
    int randomNumber = Randoms.pickNumberInRange(1, 9);
    if (!computer.contains(randomNumber)) {
        computer.add(randomNumber);
    }
}
```

---

## ✏️ 과제 진행 요구 사항

- 미션은 [java-baseball-6](https://github.com/woowacourse-precourse/java-baseball-6) 저장소를 Fork & Clone해 시작한다.
- **기능을 구현하기 전 `docs/README.md`에 구현할 기능 목록을 정리**해 추가한다.
- 과제 진행 및 제출 방법은 [프리코스 과제 제출](https://github.com/woowacourse/woowacourse-docs/tree/master/precourse) 문서를 참고한다.
