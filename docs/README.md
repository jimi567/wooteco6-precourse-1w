# 요구사항 분석

1. 게임 시작하기
    + 3자리의 난수를 생성한다.
        + 단, ```camp.nextstep.edu.missionutils.Randoms``` 의 ```pickNumberInRange()```을 활용한다.
2. 플레이어에게 입력 받기(단, 정답을 맞출 때까지 이를 반복)
    + 플레이어에게 입력을 받는다.
        + 단, ```camp.nextstep.edu.missionutils.Console``` 의 ```readLine()```을 활용한다.
    + 플레이어가 입력한 숫자가 유효한지 검사
        + 세 자리인지를 확인
        + 숫자(int)인지를 확인
        + 서로 다른 숫자인지를 확인
        + 단, 사용자가 잘못된 값을 입력할 경우 ```IllegalArgumentException```을 발생 시 킨 후 애플리케이션은 "종료"되어야 한다.
3. 정답인지를 확인한다.
    + 정답이 아니라면 플레이어에게 힌트를 제공
        + 같은 수가 같은 자리에 있으면, 스트라이크 값을 증가한다.
        + 같은 수가 다른 자리에 있으면, 볼 값을 증가한다.
        + 같은 수가 없으면, 낫싱을 출력
        + 각각 구한 힌트를 플레이어에게 출력 한다.
    + 정답이라면, 게임을 종료한다.
        + 사용자가 입력한 숫자가 3 스트라이크라면(=일치한다면) 종료한다.
4. 게임이 종료되면, 게임을 다시 시작하거나, 완전히 종료하거나 선택
    + 게임을 재시작할 경우 1을 입력한다.
    + 게임을 종료할 경우 2를 입력한다.
    + 게임 재시작/종료에대한 각각에 입력에 대해 유효성 검사를 진행한다.

# MVC 패턴에 기반한 구현 목록 분류

### Model

1. BaseBallNumbers

+ [x] Numbers 데이터 저장(사용자 입력에 대한값과, 랜덤으로 생성한 값에 저장에 사용)
+ [x] 값에 대한 인덱스를 얻는 기능
+ [x] 인덱스에 대한 값을 얻는 기능
+ [x] 값을 가지고 있는지를 확인하는 기능
+ [x] 인덱스와 값을 인자로 받고, 해당 인덱스의 값이 인자로 들어온 값과 동일한지(=스트라이크) 판단하는 기능

### View

1. InputView
    + [x] 플레이어가 숫자를 입력
    + [x] 게임 재시작/종료 여부 입력
    + [x] playerInput에 공백을 허용

2. OutputView
    + [x] 게임 시작 문구 출력
    + [x] 입력한 수에 대한 결과를 출력
        + [x] 정답이라면, "3개의 숫자를 모두 맞히셨습니다! 게임 종료" 출력
        + [x] 정답이 아니라면, 힌트를 출력( o볼 o스트라이크 또는 낫싱)
    + [x] 게임 재시작/종료 여부에 관한 내용 출력
    + [x] 게임종료문구 출력

### Controller

1. InputValidator
    + [x] playerInput 유효성 검사
        + [x] 세 자리인지 확인
        + [x] 1-9 사이의 정수로 구성되어 있는지 확인
        + [x] 중복되는 값이 있는지(= 서로 다른 숫자인지) 확인
        + [x] 위 경우들에 따른 오류 메세지 출력 후 ```IllegalArgumentException``` 발생 후 종료
    + [x] 게임 재시작/종료 여부의 유효성 검사
        + [x] 1 또는 2 인지 확인한다.
        + [x] 1 또는 2가 아닐 경우 ```IllegalArgumentException``` 발생 후 종료
2. InputParser
    + [x] playerInput 을 playerNumbers로 파싱
3. BaseBallController
    + [x] 게임 시작
        + [x] 스트라이크, 볼 카운팅 변수를 0으로 초기화
        + [x] 난수 생성 후 Model에 저장
        + [x] 게임시작 사용자에게 출력하도록 View에 전달
    + [x] 게임 진행
        + [x] 플레이어에게 입력 받기
        + [x] 입력받은 데이터 유효성 검사
        + [x] 입력받은 데이터가 유효하다면, playerNumbers로 파싱
        + [x] 정답인지, 아닌지를 판단(= 스트라이크가 3개인지 아닌지)
        + [x] 정답이 아니라면 힌트를 만들어 출력하도록 View에 전달
        + [x] 위 과정을 정답을 맞힐 떄 까지 반복
        + [x] 정답이라면 "3개의 숫자를 모두 맞히셨습니다! 게임 종료"를 출력하도록 View에 전달
    + [x] 게임이 끝나면 재시작/종료 여부 묻기
        + [x] 1 입력받으면 : 게임 재시작
        + [x] 2 입력받으면 : 게임 종료
        + [x] 그 외 입력받으면 : ```IllegalArgumentException``` 발생 후 종료
4. RandomNumberGenerator
    + [x] 1~9 사이의 서로 다른 세 자리의 난수 생성

5. BaseBallConstants
    + [x] 상수클래스를 생성하여, 숫자야구게임의 상수들을 관리한다.

# TDD 방식을 적용하기 위한, 구현할 단위 테스트 목록

### 목표 : 각 경우에 대해 실패할 경우와 추가적으로 성공할 경우도 작성

+ [x] InputValidator 대한 단위테스트
    + [x] 세 자리인지
    + [x] 1-9 사이의 정수로 구성되어 있는지
    + [x] 중복되는 값이 존재하는지
    + [x] 알맞는 오류 메세지 출력 후 ```IllegalArgumentException```을 발생 시키는지
    + [x] 재시작/종료 여부의 입력에 대한 유효성 검사를 올바르게 하는지
+ [x] playerInput(문자열) 이 playerNumbers(리스트형태)로 정상적으로 파싱이 되는지
+ [x] RandomNumberGenerator 에 대한 테스트
    + [x] 길이가 3인 값을 생성하는지
    + [x] 생성한 값 중에 중복되는 값이 존재하는지 
