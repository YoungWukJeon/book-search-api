<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>책 검색</title>

        <link rel="stylesheet" href="/resources/css/booksearch.css">
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script type="text/javascript" src="/resources/js/jquery.uriget.min.js"></script>
        <script type="text/javascript" src="/resources/js/requester.js"></script>
        <script type="text/javascript" src="/resources/js/model.js"></script>
        <script type="text/javascript" src="/resources/js/booksearch.js"></script>
    </head>

    <body>
        <header class="center">
            책 검색!
            <button id="login-button">로그인</button>
            <button id="registration-button">회원가입</button><br>
            인기 검색어(상위 10개)
            <div id="keyword-top10"></div>
        </header>
        <hr>

        <div >
            <div id="search-bar" style="height: 100px">
                <input id='search-keyword' name="keyword" placeholder="검색어를 입력하세요." style="width: 80%; height: 90px; font-size: 20px"}/>
                <button id='search-button' style="width: 15%; height: 90px; font-size: 20px">검색</button>
            </div>
        </div>
        <hr>

        <div id="list-content"></div>
        <div id="pagination-area" class="center"></div>
    </body>
</html>