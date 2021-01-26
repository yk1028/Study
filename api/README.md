https://daddyprogrammer.org/post/19/spring-boot2-start-intellij/
-  블로그를 바탕으로 kotlin으로 변환

### kotlin으로 작성하면서 변경사항
- freemarker -> thymeleaf : freemarker가 잘 동작하지 않아 thymeleaf로 변경
- default constructor를 위한 설정 추가
  ```kolin
  plugins {
  	...
  	kotlin("plugin.noarg") version "1.3.71"
  }
  
  noArg {
  	annotation("javax.persistence.Entity")
  }
  ...
  ```