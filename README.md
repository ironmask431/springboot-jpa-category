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
* API문서 : Swagger 2.9.2

-----------------
### API 문서 

확인 : http://ec2-3-37-146-110.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui.html#/

![스웨거](https://user-images.githubusercontent.com/48856906/162619929-3d72f3a6-49c0-4869-9b21-16e5dc55fa28.PNG)


--------------
### 실행 가이드 (AWS EC2 Linux 서버 기준)

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
nohup java -jar springboot-jpa-category-0.0.1-SNAPSHOT.jar 2>&1 &
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

-------------
## 부록 - 단위테스트 실행 결과
![test완료](https://user-images.githubusercontent.com/48856906/162620673-29139990-df8e-4b7a-a96b-5244bdfee9d0.PNG)

-------------
## 부록 - API 실행결과 스샷모음

1. 카테고리 등록 - 최상위카테고리(성공)
![save_상위카테고리입력](https://user-images.githubusercontent.com/48856906/162620584-20f022c0-fac7-44a3-be41-f12fca23e655.PNG)

2. 카테고리 등록 - 하위카테고리(성공)
![save_하위카테고리입력](https://user-images.githubusercontent.com/48856906/162620585-dca11bb0-075d-431c-8485-992f3c46b3c0.PNG)

3. 카테고리 등록 - 카테고리name "root" 입력 시
![save_root금지](https://user-images.githubusercontent.com/48856906/162620588-bb313980-b624-40ac-93b4-25b908d3d21b.PNG)

4. 카테고리 등록 - 카테고리name 누락 시 
![save_name누락](https://user-images.githubusercontent.com/48856906/162620590-8f08eba6-9ffc-49b6-848a-25e183441f63.PNG)

5. 카테고리 등록 - 해당 부모카테고리 없을 시 
![save_부모카테고리없음](https://user-images.githubusercontent.com/48856906/162620593-3589de84-85ac-41e6-91c4-31cd00e5404e.PNG)

6. 카테고리 등록 - 상위카테고리 하위에 동일한 카테고리명 있을 시 
![save_카테고리명중복](https://user-images.githubusercontent.com/48856906/162620599-acb05168-22da-4650-86b3-4594756528cd.PNG)

7. 카테고리 조회 - 전체 카테고리 조회
![get_전체카테고리조회](https://user-images.githubusercontent.com/48856906/162620602-c2724ff7-9a30-4615-b30f-913a91fed19d.PNG)

8. 카테고리 조회 - 특정 카테고리 조회
![get_특정카테고리조회](https://user-images.githubusercontent.com/48856906/162620605-172d0be7-89cd-4522-8153-903e1a5c1cad.PNG)

9. 카테고리 조회 - 해당 카테고리 없을 시
![get_카테고리없음](https://user-images.githubusercontent.com/48856906/162620608-0a9107d8-9744-4e2a-8e81-18590bd4380d.PNG)

10. 카테고리 수정 - 성공
![update_성공](https://user-images.githubusercontent.com/48856906/162620620-261056fb-c28f-46d9-b4d9-61009c771c0a.PNG)

11. 카테고리 수정 - 카테고리id 누락 시 
![update_id누락](https://user-images.githubusercontent.com/48856906/162620628-2e70a601-7bb4-470e-b36d-aae1798f379e.PNG)

12. 카테고리 수정 - 카테고리name 누락 시
![update_name 누락](https://user-images.githubusercontent.com/48856906/162620630-ce557db8-e471-4349-8ace-2b17cf1ed074.PNG)

13. 카테고리 수정 - 상위카테고리 하위에 동일한 카테고리명 있을 시 
![update_카테고리명중복](https://user-images.githubusercontent.com/48856906/162620633-ba4e18c0-3c48-4eda-9b53-3f996516e481.PNG)

14. 카테고리 수정 - 해당 카테고리 없을 시 
![update_해당카테고리없음](https://user-images.githubusercontent.com/48856906/162620635-2b860cc1-5587-4276-af3d-ba3a4b03283f.PNG)

15. 카테고리 삭제 - 성공
![delete_카테고리삭제성공](https://user-images.githubusercontent.com/48856906/162620642-1dadc83f-7a33-4bc7-9402-ff084cfb188d.PNG)

16. 카테고리 삭제 - 카테고리id 누락 시
![delete_id누락](https://user-images.githubusercontent.com/48856906/162620645-e5c1e557-bf47-4b92-87da-443ac54399a5.PNG)

17. 카테고리 삭제 - 하위카테고리가 있을 시 
![delete_하위카테고리있어 삭제불가](https://user-images.githubusercontent.com/48856906/162620650-70a74135-b513-43c9-87ba-afd8982735c4.PNG)

18. 카테고리 삭제 - 해당 카테고리 없을 시 
![delete_해당카테고리없음](https://user-images.githubusercontent.com/48856906/162620654-81c54c9e-c88f-472f-8d66-4674f15656c8.PNG)


