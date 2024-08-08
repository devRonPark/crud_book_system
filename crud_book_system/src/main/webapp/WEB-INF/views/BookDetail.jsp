<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>도서 상세정보</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<style>
body {
	padding-top: 70px;
}

.navbar {
	position: fixed;
	top: 0;
	width: 100%;
	z-index: 1000;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.book-card {
	border-radius: 15px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	margin-bottom: 20px;
}

.book-card-body {
	padding: 20px;
}

.book-card-footer {
	display: flex;
	justify-content: flex-end;
	gap: 10px;
}

.card {
    transition: transform 0.3s, box-shadow 0.3s;
}
.card:hover {
    transform: scale(1.02);
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
}

.card-body {
    padding: 25px;
}
.card-text {
    margin-bottom: 15px;
}

.btn {
    transition: background-color 0.3s, box-shadow 0.3s;
}
.btn:hover {
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.btn-warning {
    background-color: #f0ad4e;
    border-color: #eea236;
}
.btn-warning:hover {
    background-color: #ec971f;
    border-color: #d58512;
}

.btn-danger {
    background-color: #d9534f;
    border-color: #d43f3a;
}
.btn-danger:hover {
    background-color: #c9302c;
    border-color: #ac2925;
}

@media ( max-width : 576px) {
	.book-card-footer {
		flex-direction: column;
		align-items: flex-end;
	}
	.book-card-footer .btn {
		width: 100%;
		margin-bottom: 10px;
	}
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg mt-4">
		<div class="container">
			<a class="navbar-brand mx-auto" href="/books/list">Book Manager</a>
		</div>
	</nav>
	
	<div class="container my-5">
		<!-- 도서 상세정보 카드 -->
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card book-card">
					<div class="card-body book-card-body">
						<h3 class="card-title">도서 제목: ${book.getTitle()}</h3>
						<p class="card-text">
							<strong>작가:</strong> ${book.getWriterName()}
						</p>
						<p class="card-text">
							<strong>장르:</strong> ${book.getGenre()}
						</p>
						<p class="card-text">
							<strong>출판사:</strong> ${book.getPublisher()}
						</p>
						<p class="card-text">
							<strong>출판일:</strong> ${book.getPublishedAt()}
						</p>
						<c:if test="${book.getSummary() != null}">
							<p class="card-text">
								<strong>주요 내용:</strong> ${book.getSummary()}
							</p>
						</c:if>
						<p class="card-text">
							<strong>가격:</strong> ${book.getPrice()} 원
						</p>
						<p class="card-text">
							<strong>총 페이지 수:</strong> ${book.getTotalPages()} 페이지
						</p>
					</div>
					<div class="card-footer book-card-footer">
						<a href="/books/edit?id=${book.id}" class="btn btn-warning">수정</a>
						<a href="/books/delete?id=${book.id}" class="btn btn-danger">삭제</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
