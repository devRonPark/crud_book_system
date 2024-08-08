document.addEventListener('DOMContentLoaded', function () {
    // Toast 메시지 표시
    const successToast = document.getElementById('successToast');
    const toast = new bootstrap.Toast(successToast);

    // URL에서 쿼리 파라미터 확인
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('deleted')) {
        toast.show();  // 삭제 성공 메시지 표시
    }
});