<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 검색 결과</title>
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
	padding-top: 70px; /* 네비게이션 바 높이만큼 패딩 추가 */
}

.navbar {
	position: fixed;
	top: 0;
	width: 100%;
	z-index: 1000;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
.card {
    transition: transform 0.3s, box-shadow 0.3s;
}
.card:hover {
    transform: scale(1.02);
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
}

.card-img-top {
	height: 200px;
	object-fit: cover;
}

.filter-container {
	margin-bottom: 20px;
}

.btn-add-book {
	margin-bottom: 20px;
}

.no-books-msg {
	text-align: center;
	margin-top: 50px;
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

.pagination {
	justify-content: center;
	margin-top: 20px;
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
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg mt-4">
		<div class="container">
			<a class="navbar-brand mx-auto" href="/books/list">Book Manager</a>
		</div>
	</nav>
	<div class="container mt-4">
		<!-- 검색 및 필터 -->
		<div
			class="filter-container d-flex flex-column flex-sm-row justify-content-between align-items-center mb-4">
			<form 
				action="search"
				class="d-flex w-100 w-sm-50"
				
			>
				<input id="search-input" class="form-control me-2" type="search" placeholder="검색어 입력"
					aria-label="Search" name="keyword">
				<button id="search-btn" class="btn btn-outline-success" type="submit">검색</button>
			</form>
		</div>

		<!-- 도서 목록 카드 -->
		<div class="row">
			<c:choose>
				<c:when test="${not empty searchResult}">
					<c:forEach var="book" items="${searchResult}">
						<div class="col-12 mb-4">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title">${book.getTitle()}</h5>
									<p class="card-text">
										<strong>작가:</strong> ${book.getWriterName()}
									</p>
									<p class="card-text">
										<strong>출판사:</strong> ${book.getPublisher()}
									</p>
									<p class="card-text">
										<strong>가격:</strong> ${book.getPrice()}원
									</p>
									<c:if test="${book.getSummary() != null || book.getSummary().trim().equals('')}">
										<p class="card-text">
											<strong>내용:</strong> ${book.getSummary()}
										</p>
									</c:if>
									<a href="/books/view?id=${book.getId()}" class="btn btn-primary">자세히
										보기</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="no-books-msg">
						<p>검색된 도서가 없습니다.</p>
					</div>
				</c:otherwise>
			</c:choose>
		</div>

		<!-- 페이징 컴포넌트 -->
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li class="page-item <c:if test="${page == 1}">disabled</c:if>">
					<a class="page-link" href="?page=${page - 1}" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
				</a>
				</li>
				<c:forEach var="i" begin="1" end="${totalPages}">
					<li class="page-item <c:if test="${i == page}">active</c:if>">
						<a class="page-link" href="?page=${i}">${i}</a>
					</li>
				</c:forEach>
				<li
					class="page-item <c:if test="${page == totalPages}">disabled</c:if>">
					<a class="page-link" href="?page=${page + 1}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
				</a>
				</li>
			</ul>
		</nav>
	</div>
</body>
</html>
