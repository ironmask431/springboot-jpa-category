# springboot-jpa-category

목적 : 
1. JPA로 무한카테고리(하위카테고리 추가 가능) REST API 서버 만들어보기

기능 :
1. 카테고리 생성, 조회, 수정, 삭제 REST API 지원

개발환경 
* 언어  : java 11
* 프레임워크  : springboot 2.6.6
* 빌드도구  : gradle 7.4.1
* 개발도구  : Intellij IDEA (Community Edition)
* ORM : JPA
* 데이터베이스 : h2
* 테스트 : Junit 5
* api문서 : Swagger 2.9.2

------------

실행 가이드 (AWS EC2 Linux 서버 기준)

1. 리눅스 서버에 java 11 설치 확인    
```
java -version
```

2. java 11 미설치 설치    

```
-aws coreetto 다운로드   
sudo curl -L https://corretto.aws/downloads/latest/amazon-corretto-11-x64-linux-jdk.rpm -o jdk11.rpm   
```
```
-jdk11 설치   
sudo yum localinstall jdk11.rpm  
```
```
-jdk version 선택   
sudo /usr/sbin/alternatives --config java   
```
```
-java 버전 확인 (java 11)   
java --version
```

3. 리눅스 서버에 git 설치
```
sudo yum install git
```
4. git 설치 확인
```
git --version
```
5. git clone 으로 프로젝트를 저장할 디렉토리 생성, 이동
```
mkdir jpa_category/step1
cd jpa_category/step1
```

6. git clone 주소 복사
https://github.com/ironmask431/springboot-jpa-category.git

7. git clone 실행
```
git clone https://github.com/ironmask431/springboot-jpa-category.git
```
8. git을 통해 소스가 정상 다운로드 되었는지 확인 
```
cd springboot-jpa-category
ll
```

<스샷>

9. gradlew 권한 부여
```
chmod +x ./gradlew
```
10. gradle build, test실행, 정상확인
```
./gradlew build
```
<스샷>
```
./gradlew test
```
<스샷>

11. 빌드 후 생성된 jar파일 경로로 이동, jar파일 확인
```
cd build/libs/
```
<스샷>

12. java -jar 또는 nohup으로 jar 파일 실행
(java -jar로 실행 시에는 터미널 종료시 서버 중지됨.)
```
java -jar springboot-jpa-category-0.0.1-SNAPSHOT.jar
```
```
nohup java -jar springboot-jpa-category-0.0.1-SNAPSHOT.jar 2>&1 1 
```
13. 실행 확인
실행서버url:8080/api/category/hello  > 접속 
hello 메세지 확인 > 정상 구동 완료

http://ec2-3-37-146-110.ap-northeast-2.compute.amazonaws.com:8080/api/category/hello


14. h2-console 접속시
실행서버url:8080/h2-console 접속

nohup.out 파일에서 실행로그 확인
실행로그에서 h2-console url 정보 복사

<스샷>

에 입력 후 connect 시 h2-console 로그인 성공
