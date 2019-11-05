# Spring AOP
AOP : 인프라 로직의 중복을 제거하기 위한 방법

### AOP 관련 용어들
- target : 부가 기능을 부여할 대상
- advice : target에 제공할 부가 기능을 담은 클래스
- pointcut : advice가 적용될 target을 지정하는 것을 의미
- joinpoint : advice가 적용될 수 있는 위치. 예를 들어 method의 실행 단계

### 구현 목록
1. logging
- advice에서 pointcut 설정하도록 구현

2. timer
- 메소드에 어노테이션을 붙여서 advice를 활용하도록 구현


*포인트컷 표현식을 잘 알고 있으면 AOP를 다양한 방식으로 활용할 수 있다.*