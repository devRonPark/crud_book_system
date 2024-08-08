window.addEventListener("DOMContentLoaded", () => {
	const confirmDeleteBtn = document.getElementById("confirm-delete-btn");
	
	const handleDeleteBtnClick = () => {	
		const url = new URL(window.location.href);
		const bookId = parseInt(url.searchParams.get("id"));
		const deleteReqURL = "http://localhost:8080/books/delete?id=" + bookId;
		
		// axios 로 bookId 에 해당하는 데이터 삭제 요청
		axios.delete(deleteReqURL)
		.then(response => {
			// 삭제 요청에 대한 응답 성공 시, 도서 목록 페이지로 리다이렉션 처리
			if (response.status == 200) {
				// 단, 도서 목록 페이지 이동 시 deleted param 추가
				window.location.href = "/books/list?deleted=true";
			}
		})
		.catch(err => {
			// 요청 실패 시 처리
			console.log('Error:', err);
		});
	}
	
	confirmDeleteBtn.addEventListener("click", handleDeleteBtnClick);
	
})