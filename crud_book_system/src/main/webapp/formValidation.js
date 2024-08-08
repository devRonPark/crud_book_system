window.addEventListener("DOMContentLoaded", () => {
	const addBookForm = document.getElementById('book-form');
	const pathname = window.location.pathname;
	let postReqURL = "http://localhost:8080/books";
	
	const currentURL = new URL(window.location.href);
	// URLSearchParams 객체
	const urlParams = currentURL.searchParams;
	const bookId = urlParams.get("id");
	if (pathname.includes("add")) {
		postReqURL += "/add";
	} else if (pathname.includes("edit")) {
		postReqURL += "/edit";
	}
	
	const handleSubmit = event => {
		event.preventDefault(); // 기본 제출 동작 방지
     	const form = event.target;
     	let valid = true;

        // 이전에 출력된 에러 메세지 제거
        document.querySelectorAll('.form-control').forEach(input => {
            input.classList.remove('is-invalid');
        });
        document.querySelectorAll('.invalid-feedback').forEach(feedback => {
            feedback.style.display = 'none';
        });	

        // 제목 입력하지 않은 경우,
        if (!form.title.value.trim()) {
            valid = false;
            form.title.classList.add('is-invalid');
            form.querySelector('#title ~ .invalid-feedback').style.display = 'block';
        }

        // 작가 입력하지 않은 경우,
        if (!form.writerName.value.trim()) {
            valid = false;
            form.writerName.classList.add('is-invalid');
            form.querySelector('#writerName ~ .invalid-feedback').style.display = 'block';
        }

        // 가격을 입력하는 경우 적절한 값을 입력하지 않은 경우,
        if (form.price.value.trim() && parseInt(form.price.value) <= 0) {
            valid = false;
            form.price.classList.add('is-invalid');
            form.querySelector('#price ~ .invalid-feedback').style.display = 'block';
        }

        // 총 페이지 수를 입력한 경우 적절한 값을 입력하지 않은 경우,
        if (form.totalPages.value.trim() && parseInt(form.totalPages.value) < 0) {
            valid = false;
            form.totalPages.classList.add('is-invalid');
            form.querySelector('#totalPages ~ .invalid-feedback').style.display = 'block';
        }

        if (valid) {
            document.getElementById('success-message').style.display = 'block';
			
			const formData = {
				id: parseInt(bookId), 
				title: form.title.value.trim(), 
				writerName: form.writerName.value.trim(),
				genre: form.genre.value.trim() != "" ? form.genre.value.trim() : null,
				publisher: form.publisher.value.trim() != "" ? form.publisher.value.trim() : null,
				summary: form.summary.value.trim() != "" ? form.summary.value.trim() : null,
				price: parseInt(form.price.value.trim()) > 0 ? parseInt(form.price.value.trim()) : 0,
				totalPages: parseInt(form.totalPages.value.trim()),
				publishedAt: form.publishedAt.value
			}
			
			axios.post(postReqURL, formData, { headers: {
			    'Content-Type': 'multipart/form-data' // 요청 본문이 JSON임을 명시
			}})
			.then(response => {
				if (response.status == 200) {
					if (pathname.includes("add")) {
						window.location.href = "/books/list";
					} else if (pathname.includes("edit")) {
						window.location.href = "/books/view?id=" + bookId;
					}					
				}
			})
			.catch(err => {
				// 요청 실패 시 처리
				console.log('Error:', err);
				// 예: 오류 메시지 표시
				document.getElementById('error-message').style.display = 'block';
			});
        } else {
            document.getElementById('error-message').style.display = 'block';
        }
	} 
	addBookForm.addEventListener('submit', handleSubmit);
});