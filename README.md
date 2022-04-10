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
![003](https://user-images.githubusercontent.com/48856906/162619182-facba738-4ee5-446b-934a-510bfb85474d.PNG)

3. 리눅스 서버에 git 설치
```
sudo yum install git
```
4. git 설치 확인
```
git --version
```
5. 프로젝트를 저장할 디렉토리 생성, 이동
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
![001_다운로드확인](https://user-images.githubusercontent.com/48856906/162618735-28c5a143-cb70-472c-8155-d0cc277dec4a.PNG)

9. gradlew 에 실행 권한 부여
```
chmod +x ./gradlew
```
10. gradle build, test실행, 정상확인
```
./gradlew build
```
![002_빌드성공](https://user-images.githubusercontent.com/48856906/162618760-da627b3d-7104-418c-8f5e-0eeef840c197.PNG)

```
./gradlew test
```
![003_테스트성공](https://user-images.githubusercontent.com/48856906/162618767-1fad92b8-bc0d-4be3-9766-9ea024052532.PNG)

11. 빌드 후 생성된 jar파일 경로로 이동, jar파일 확인
```
cd build/libs/
```
![004_jar파일 확인](https://user-images.githubusercontent.com/48856906/162618771-b163b2e2-82fe-4c33-8d23-08b100f2f1e6.PNG)

12. java -jar 또는 nohup으로 jar 파일 실행
(java -jar로 실행 시에는 터미널 종료시 서버 중지됨.)
```
java -jar springboot-jpa-category-0.0.1-SNAPSHOT.jar
```
```
nohup java -jar springboot-jpa-category-0.0.1-SNAPSHOT.jar 2>&1 1 
```
13. 실행 확인 "실행서버url:8080/api/category/hello"  > 접속    
hello 메세지 확인 > 정상 구동 완료

![007_실행확인](https://user-images.githubusercontent.com/48856906/162618799-4d278499-d1f4-47e9-a27c-cd076c0e7b8e.PNG)

14. h2-console에 접속하기 위해서는 먼저 nohup.out 파일에서 실행로그 확인   
실행로그에서 h2-console url 정보 복사 (jdbc:h2:mem~~ )   

![006_h2_db정보확인](https://user-images.githubusercontent.com/48856906/162618806-bc794b40-42d2-4765-b8dc-3945f592b3e7.PNG)

 "실행서버url:8080/h2-console" 접속  
JDBC URL 에 복사한 h2-console url을 입력 후 connect 시 h2-console 로그인 성공

![008_h2접속](https://user-images.githubusercontent.com/48856906/162618823-6280848f-9862-429f-82d5-a42ffacf88d7.PNG)

![009_H2로그인확인](https://user-images.githubusercontent.com/48856906/162618830-60005f51-76da-424f-8bac-15d14c805424.PNG)



