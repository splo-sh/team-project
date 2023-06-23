<br><br>

프로젝트 팀 관련 정보는 [팀 github](https://github.com/codestates-seb/seb43_main_012)에서 확인하실 수 있습니다.

---
<br>

## 확장, 개선 (진행중)
[JPA n+1 문제](https://backwards.tistory.com/11) 
<br>
[지연로딩 문제 - 영속성 컨텍스트](https://backwards.tistory.com/10)

<br>

## 소개

저희 서비스 chatcrawl은 기존 chatGPT의 불편함을 개선하고자 만들어졌습니다.

chatGPT와 같은 OpenAI api를 사용하여 제작하였으며 기본적인 기능은 chatGPT와 동일합니다.

GPT의 대화방이 쌓여감에 따라 자주 찾는 대화방일지라도 신규 대화방에 밀려 휠 스크롤 아래쪽으로 밀려나는 문제점이 존재합니다. 

저희 서비스에서는 북마크, 태그, 핀 기능을 추가하여 원하는 정보에 쉽게 접근할 수 있도록 하였습니다.
<br><br>

## 담당
전반적인 비지니스 로직 담당

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
