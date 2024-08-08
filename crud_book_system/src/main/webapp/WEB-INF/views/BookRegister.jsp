<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>도서 정보 등록</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script> <!-- Axios CDN 추가 -->
<style>
body {
	padding-top: 130px;
}

.navbar {
	position: fixed;
	top: 0;
	width: 100%;
	z-index: 1000;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

@media ( min-width : 576px) {
	.container {
		max-width: 960px;
	}
}

@media ( min-width : 992px) {
	.container {
		max-width: 1140px;
	}
}

.form-group label {
	font-weight: bold;
}

.form-label {
	margin-bottom: 0.5rem;
}

.form-control {
	background-color: #f8f9fa;
}

.btn-primary {
	transition: background-color 0.3s, border-color 0.3s;
}

.btn-primary:hover {
	background-color: #0056b3;
	border-color: #004085;
}

.navbar-brand {
	font-weight: bold;
	font-size: 1.5rem;
	cursor: pointer;
}

.navbar-nav {
	margin-left: auto;
	margin-right: auto;
}

.invalid-feedback {
	display: none;
}

.is-invalid {
	border-color: #dc3545;
	padding-right: calc(1.5em + .75rem);
	background-position: right calc(.375em + .1875rem) center;
	background-repeat: no-repeat;
	background-size: calc(.75em + .375rem) calc(.75em + .375rem);
	background-image:
		url("data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMiIgaGVpZ2h0PSIxMiIgdmlld0JveD0iMCAwIDEyIDEyIj4KICA8Y2lyY2xlIGN4PSI2IiBjeT0iNiIgcj0iNiIgc3Ryb2tlLXdpZHRoPSI0IiBzdHJva2Utbm9uLWZpbGw9InJlZCIgc3Ryb2tlLWxpbmVjY2xhcnM9InJvbGUiLz4KPC9zdmc+");
}

.alert {
	display: none;
}
s</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg mt-4">
		<div class="container">
			<a class="navbar-brand mx-auto" href="/books/list">Book Manager</a>
		</div>
	</nav>

	<div class="container">
		<h2 class="text-center mb-4 mt-4" hidden>도서 정보 등록</h2>

		<!-- 성공 메시지 -->
		<div class="alert alert-success" role="alert" id="success-message"
			style="display: none;">도서 정보가 성공적으로 등록되었습니다!</div>
		<!-- 오류 메시지 -->
		<div class="alert alert-danger" role="alert" id="error-message">
			등록 중 오류가 발생했습니다. 다시 시도해 주세요.</div>

		<form id="book-form" action="/books/add" method="post">
			<div class="mb-3">
                <label for="title" class="form-label">제목</label>
                <input type="text" class="form-control" id="title" name="title">
                <div class="invalid-feedback">제목을 입력해 주세요.</div>
            </div>
            <div class="mb-3">
                <label for="writerName" class="form-label">작가</label>
                <input type="text" class="form-control" id="writerName" name="writerName">
                <div class="invalid-feedback">작가를 입력해 주세요.</div>
            </div>
            <div class="mb-3">
                <label for="genre" class="form-label">장르</label>
                <input type="text" class="form-control" id="genre" name="genre">
                <div class="invalid-feedback">장르를 입력해 주세요.</div>
            </div>
            <div class="mb-3">
                <label for="publisher" class="form-label">출판사</label>
                <input type="text" class="form-control" id="publisher" name="publisher">
                <div class="invalid-feedback">출판사를 입력해 주세요.</div>
            </div>
            <div class="mb-3">
            	<label for="publishedAt" class="form-label">출판일</label>
            	<input type="date" class="form-control" id="publishedAt" name="publishedAt" min="1900-01-01" max="2024-08-08">
        	</div>
        	<div class="mb-3">
                <label for="price" class="form-label">가격</label>
                <input type="number" class="form-control" id="price" name="price" min="0" step="0.01">
                <div class="invalid-feedback">올바른 가격을 입력해 주세요.</div>
            </div>
            <div class="mb-3">
                <label for="totalPages" class="form-label">총 페이지 수</label>
                <input type="number" class="form-control" id="totalPages" name="totalPages" min="1">
                <div class="invalid-feedback">총 페이지 수를 입력해 주세요.</div>
            </div>
            <div class="mb-3">
                <label for="summary" class="form-label">주요 내용</label>
                <textarea class="form-control" id="summary" name="summary" rows="4"></textarea>
                <div class="invalid-feedback">주요 내용을 입력해 주세요.</div>
            </div>
            
			<button type="submit" class="btn btn-primary w-100">등록하기</button>
		</form>

	</div>
	<script src="/formValidation.js"></script>
</body>
</html>
