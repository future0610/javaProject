# javaProject

### TicTacToe
<pre>
Terminal: 터미널에서 실행 (완성)
</pre>

### AITest
<pre>
깊이가 1에서 3인 AI의 성능을 서로 비교하는 패키지
</pre>

# 1주차
<pre>
<ul>
  <li>Tic-Tac-Toe 게임 기본 구성 만들기</li>
</ul>
</pre>

# 2주차
<pre>
<ul>
  <li>Swing으로 GUI를 만드는 것을 시도했으나 실패, AI Method 메서드 구현 시작</li>
->게임의 모든 경우의 수를 구하는 작업 구현 시작
</ul>
</pre>

# 3주차
<pre>
<ul>
  <li>alphaNode, betaNode, inference 클래스 구현</li>
  <li>MonteCarlo 방식 구현</li>
  <li>오픈소스로 구한 코드를 수정하여 GUI 작업 시작</li>
</ul>
</pre>

# 4주차
<pre>
<ul>
  <li>alphaNode->maximizing, betaNode->minimizing으로 이름 변경</li>
  <li>AI 구현 완료 - depth에 따라 게임의 난도가 달라짐</li>
  <li>기존의 GUI 소스코드 삭제 후 새로운 GUI 소스코드 작성 시작</li>
</ul>
</pre>

# 5주차
<pre>
<ul>
  <li>AI 오류 수정</li>
  <li>아래의 GUI 구현까지 완료</li>
  <li>파일을 실행하면 플레이어와 상대 플레이어를 지정하는 화면이 첫화면에 나옴
AI의 depth를 1에서 3 중 하나의 값으로 지정하고 기본값은 3이다.
  </li>
  <li>승부가 결정되었을 경우 프로그램을 종료할지, 재시작할지, 메뉴로 돌아갈지를 정한다.
  </li>
  <li>
게임 도중에는 메뉴로 돌아갈 수 있다.
->게임 도중 재시작은 진행 중
  </li>
</ul>
</pre>

# Docker
### 도커 이미지 빌드
```docker
docker build -t [이미지 이름] .
```

### 도커 컨테이너 생성
```docker
docker run -itd -v [호스트 디렉토리]:[컨테이너 디렉토리] --name [컨테이너 이름] [이미지 이름]
```

### 도커 컨테이너 접속
```docker
docker exec -it [컨테이너 이름] /bin/bash
```
