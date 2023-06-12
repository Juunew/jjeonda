
$(function(){
	$('#naverBtn').click(function(){
		$.ajax({
			type: 'post',
			url: '/login/naver',
			success:function(data){
				window.location.href=data;
			}
		})
	})
})

/*$(function() {
	$('#loginBtn').click(function() {
		var id = $('#id').val();
		var pwd = $('#password').val();
		$.ajax({
			type: 'post',
			url: '/sign-in',
			data: {
				'id': id,
				'password': pwd
			},
			success: function(result, status, xhr) {
				console.log("resutl:", result.data)
				var token = xhr.getResponseHeader("token");
				if (token) {
					localStorage.setItem('token', token);
					console.log("받은 토큰:", token);

					var xhr = new XMLHttpRequest();
					xhr.open('GET', '/', true);
					xhr.setRequestHeader('Content-Type', 'application/json');
					xhr.setRequestHeader('token', token);

					xhr.onreadystatechange = function() {
						if (xhr.readyState === 4 && xhr.status === 200) {
							// 요청 완료 후의 처리
						}
					};
					xhr.send();
				}
			}
		})
	})




})*/
/*$.ajax({
  url: '/sign-in',
  type: 'POST', // 요청 방식(GET, POST 등)에 맞게 설정
  headers: {
	'Accept': 'application/json',
	// 헤더에 추가된 토큰 값을 가져옴
	'Token': response.getResponseHeader('Token')
  },
  success: function(data) {
	localStorage.setItem('Token',token);
	console.log(response.getResponseHeader('token'));
  },
  error: function(xhr, status, error) {
	// 에러 처리 로직
  }
});*/
///////////////////////////