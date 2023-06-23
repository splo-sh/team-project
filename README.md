<br><br>

서비스 소개 및 프로젝트 팀 관련 정보는 [팀 github](https://github.com/codestates-seb/seb43_main_012)에서 확인하실 수 있습니다.

---
<br>

## 확장, 개선 (진행중)
[JPA n+1 문제](https://backwards.tistory.com/11) 
<br>
[지연로딩 문제 - 영속성 컨텍스트](https://backwards.tistory.com/10)

<br>


## 담당
전반적인 비지니스 로직 담당

<br>

github 폴더 바로가기 : 
[대화방 관련](https://github.com/splo-sh/team-project/tree/main/server/src/main/java/com/codestates/seb43_main_012/conversation) /
[질문-답변 관련](https://github.com/splo-sh/team-project/tree/main/server/src/main/java/com/codestates/seb43_main_012/qna) /
[카테고리 관련](https://github.com/splo-sh/team-project/tree/main/server/src/main/java/com/codestates/seb43_main_012/category) /
[컬렉션페이지 관련](https://github.com/splo-sh/team-project/tree/main/server/src/main/java/com/codestates/seb43_main_012/collection)

---

* 대화방 CRUD
* 대화방 북마크
* 대화방 카테고리 추가, 삭제
  * 대화방 북마크 시 카테고리를 추가해야 합니다.
 
* 대화방 태그 추가, 삭제
  * 여러 태그를 추가할 수 있습니다.
    
* 대화방 검색
  * 대화에 속하는 질문-답변의 키워드로 검색할 수 있습니다.
    
* 질문-답변 생성
  * 질문 작성 시 OpenAI api를 사용하여 답변을 받아옵니다.
    
* 카테고리 생성,수정,삭제
  * 카테고리는 사용자가 원하는 대로 커스텀할 수 있습니다.
    
* 컬렉션페이지 조회
  * 북마크, 태그된 모든 대화방을 조회할 수 있습니다.
