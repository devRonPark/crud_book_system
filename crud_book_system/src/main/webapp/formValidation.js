window.addEventListener("DOMContentLoaded", () => {
	const addBookForm = document.getElementById('book-form');
	
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

        // 장르 입력하지 않은 경우,
        if (!form.genre.value.trim()) {
            valid = false;
            form.genre.classList.add('is-invalid');
            form.querySelector('#genre ~ .invalid-feedback').style.display = 'block';
        }

        // 출판사 입력하지 않은 경우,
        if (!form.publisher.value.trim()) {
            valid = false;
            form.publisher.classList.add('is-invalid');
            form.querySelector('#publisher ~ .invalid-feedback').style.display = 'block';
        }

        // 주요 내용 입력하지 않은 경우,
        if (!form.summary.value.trim()) {
            valid = false;
            form.summary.classList.add('is-invalid');
            form.querySelector('#summary ~ .invalid-feedback').style.display = 'block';
        }

        // 가격을 입력하지 않았거나 적절한 값을 입력하지 않은 경우,
        if (!form.price.value.trim() || parseInt(form.price.value) <= 0) {
            valid = false;
            form.price.classList.add('is-invalid');
            form.querySelector('#price ~ .invalid-feedback').style.display = 'block';
        }

        // 총 페이지 수를 입력하지 않았거나 적절한 값을 입력하지 않은 경우,
        if (!form.totalPages.value.trim() || parseInt(form.totalPages.value) < 0) {
            valid = false;
            form.totalPages.classList.add('is-invalid');
            form.querySelector('#totalPages ~ .invalid-feedback').style.display = 'block';
        }

        if (valid) {
            document.getElementById('success-message').style.display = 'block';
			
			const formData = { 
				title: form.title.value.trim(), 
				writerName: form.writerName.value.trim(),
				genre: form.genre.value.trim(),
				publisher: form.publisher.value.trim(),
				summary: form.summary.value.trim(),
				price: parseInt(form.price.value.trim()),
				totalPages: parseInt(form.totalPages.value.trim())
			}
			
			axios.post("http://localhost:8080/books/add", formData, { headers: {
			    'Content-Type': 'multipart/form-data' // 요청 본문이 JSON임을 명시
			}})
			.then(data => {
				// 요청 성공 시 처리
				console.log('Response:', response.data);
				// 예: 성공 메시지 표시
				document.getElementById('success-message').style.display = 'block';
			})
			.catch(err => {
				// 요청 실패 시 처리
				console.error('Error:', error);
				// 예: 오류 메시지 표시
				document.getElementById('error-message').style.display = 'block';
			});
        } else {
            document.getElementById('error-message').style.display = 'block';
        }
	} 
	addBookForm.addEventListener('submit', handleSubmit);
});