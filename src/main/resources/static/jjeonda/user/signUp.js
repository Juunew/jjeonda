	var userIdCheckFlag = false;
	var submitFlag = false;
	$(function (){
		$('#id').blur(function(){
			checkId();
		});
		
		$('#checkIdBtn').click(function(){
			var id = $('#id').val();
			var idMsg = $('#idMsg');
			if(id === ''){
				showErrorMsg(idMsg, '아이디를 입력해 주세요.')
				return false;
			}
			
			if(id !== ''){
				var regexp = /^[a-z0-9][a-z0-9_\-]{4,19}$/ //구성된 길이 4 ~ 19자리 사이 문자열
				if(!regexp.test(id)){
					showErrorMsg(idMsg,'5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.');
					return false;
				}
				hideMsg(idMsg);
			}
			
 			$.ajax({
				type:'post',
				url:'/checkId',
				data:{"id":id},
				success: function(result){
					console.log("result:"+result)
					if(result === true){
						showErrorMsg(idMsg, '이미 사용 중인 아이디입니다.');
						return false;
					}else{
						showSuccessMsg(idMsg, '사용 가능한 아이디입니다.');
						userIdCheckFlag = true;
					}
				}
			});
			
		});
		
		
		
		//비밀번호
		$('#password').blur(function(){
			checkPassword1();
		});
		
		$('#password').keyup(function(){
			$('#pwdMsg').html('');
			checkPassword1();
		});
		
		
		$('#passwordChk').blur(function(){
			checkPassword2();
		})
		
		$('#passwordChk').keyup(function(){
			if($('#password').val() != $('#passwordChk').val()){
				$('#pwdMsg').html('비밀번호가 일치하지 않습니다.<br><br>');
		        $('#pwdMsg').attr('class', 'text-danger');
			} else{
				$('#pwdMsg').html('비밀번호가 일치합니다.<br><br>');
		        $('#pwdMsg').attr('class', 'text-primary');
			}
		});
		
		// 이름
		$('#name').blur(function(){
			checkName();
		})
		
		// 전화번호
		$('#phone').blur(function(){
			checkPhone();
		})
		$('#phone').on('input', function() {
		    var phone = $(this).val();
		    var formattedPhone = autoHyphen(phone);
		    $(this).val(formattedPhone);
		});
		
		// 성별
		
		// 생년월일 birth
		$('#birth').blur(function(){
			var birth = $('#birth').val();
			var oMsg = $('#birthMsg');
			if (birth === ''){
				showErrorMsg(oMsg, '생년월일을 입력해 주세요.');
				return false;
			}
			return true;
		});
		$('#birth').change(function(){
			checkBirth()
		});
		
		// 이메일
		$('#email').blur(function(){
			checkEmail();
		});
		
		$('#checkEmail').click(function() {
			checkEmail();
		   $.ajax({
		      type : "POST",
		      url : "/mailConfirm",
		      data : {
		         "email" : $('#email').val()
		      },
		      success : function(data){
		         alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인부탁드립니다.")
		         console.log("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인부탁드립니다.")
		         console.log("data : "+data);
		         chkEmailConfirm(data, $('#memailconfirm'));
		      }
		   })
		   
		})
		$('#checkEmailNum').blur(function(){
			checkEmailNum();
		})
		
		
		$('#signup-btn').click(function () {
			let searchParams = new URLSearchParams(window.location.search)
			if(searchParams.has('agreementYn')){
				let param = searchParams.get('agreementYn')
				agreementYn.value = param
				$('#agreementYn').value = param
			}
			
	        if (checkInputValue()) {
				alert("성공")
	            $('#signup-form').submit(); //
	        }
	    });
		
		
	});
	
	//////////////////////////////////////////////////////////////////
	
	function checkId(){
		var id = $('#id').val();
		var idMsg = $('#idMsg');
		var regexp = /^[a-z0-9][a-z0-9_\-]{4,19}$/ //구성된 길이 4 ~ 19자리 사이 문자열
		if(id === ''){
			showErrorMsg(idMsg,'필수 정보입니다.');
			return false;
		}
		if(!regexp.test(id)){
			showErrorMsg(idMsg,'5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.');
			return false;
		}
		hideMsg(idMsg);
		return true;
	}
	
	function showErrorMsg(idMsg, message){
		idMsg.attr('class', 'text-danger');
		idMsg.html(message);
		idMsg.show();
	}
	
	function showSuccessMsg(idMsg, message){
		idMsg.attr('class', 'text-primary');
		idMsg.html(message);
		idMsg.show();
	}
	
	function hideMsg(idMsg){
		idMsg.hide();
	}
	
	/* 비밀번호 */
	function checkPassword1(){
		var password1 = $('#password').val();
		var oMsg = $('#pwd1Msg');
		var regexp = /^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{8,16}$/;
	    if (password1 === '') {
	        showErrorMsg(oMsg, '필수 정보입니다.');
	        return false;
	    }
		 if (!regexp.test(password1)) {
		        showErrorMsg(oMsg, '8~16자 영문 대소문자, 숫자, 특수문자를 사용하세요.');
		        return false;
		    }
		    showSuccessMsg(oMsg, '사용 가능한 비밀번호입니다.');
		    return true;
	}
	function checkPassword2(){
		var password2 = $('#passwordChk').val();
		var oMsg = $('#pwdMsg');
		if(password2 === ''){
			showErrorMsg(oMsg, '필수 정보입니다.');
	        return false;
		}
		return true;
		
	}
	/* 사용자 이름 */
	function checkName(){
		var name = $('#name').val();
		var oMsg = $('#nameMsg');
		if (name === '') {
	        showErrorMsg(oMsg, '필수 정보입니다.');
	        return false;
	    }else{
			hideMsg(oMsg);
		}
	    return true;
	}
	/* 사용자 전화번호 phone*/
	function checkPhone(){
		var phone = $('#phone').val();
		var oMsg = $('#phoneMsg');
		var regexp = /01[016789]-[0-9]{3,4}-[0-9]{3,4}/; // 01X(1,6,7,8,9)-XXXX-XXXX 검사
		if (phone === ''){
			showErrorMsg(oMsg, '필수 정보입니다.');
			return false;
		}
		if (!regexp.test(phone)) {
	        showErrorMsg(oMsg, '핸드폰 번호를 확인해 주세요.')
	        return false;
	    }
	    hideMsg(oMsg);
	    return true;
	}
	
	function autoHyphen1(target) {
	    return target.value = target.value
	    .replace(/[^0-9]/g, '')
	    .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
	}
	
	function autoHyphen(target) {
	    return target.replace(/[^0-9]/g, '')
	        .replace(/(\d{2,3})(\d{3,4})(\d{4})/, '$1-$2-$3');
	}
	
	
	/* 성별 gender*/
	function checkGender(){
		if($("input[name=gender]:radio:checked").length<1){
			return false;
		}
		return true;
	}
	/* 생일 birth */
	function checkBirth(){
		return true;
	}
	
	
	/* EmailAddress email*/
	function checkEmail(){
		var email = $('#email').val();
		var oMsg = $('#emailMsg');
		if (email === ''){
			showErrorMsg(oMsg, '이메일을 입력해주세요.');
			return false;
		}
		hideMsg(oMsg);
		return true;
	}
	
	function checkEmailNum(){
		var memailconfirm = $('#memailconfirm').val();
		var oMsg = $('#emailMsg1');
		if(memailconfirm === ''){
			showErrorMsg(oMsg, '인증번호를 입력해주세요.');
			return false;
		}
		/*if(!memailconfirm){
			showErrorMsg(oMsg, '인증번호가 일치하지 않습니다.');
			return false;
		}*/
	}
	
	// 생년월일
	function checkBirth(){
		var enteredDate = $(this).val();
    
	    // 유효성 검사 로직을 추가합니다.
	    var currentDate = new Date(); // 현재 날짜 가져오기
	    var selectedDate = new Date(enteredDate); // 입력된 날짜 가져오기
	
	    if (selectedDate > currentDate) {
	      // 선택된 날짜가 현재 날짜보다 미래인 경우 유효하지 않음
	      alert('유효하지 않은 날짜입니다. 다시 입력해주세요.');
	      $(this).val(''); // 입력 값을 지웁니다.
	      return false;
	    } 
		return true;
	}
	// 이메일 인증번호 체크 함수
	function chkEmailConfirm(data, $memailconfirm) {
	  $memailconfirm.on("keyup", function() {
		var oMsg = $('#emailMsg');
	    if (data !== $memailconfirm.val()) {
	      emconfirmchk = false;
	      showErrorMsg(oMsg, '인증번호가 잘못되었습니다');
	      $('#emconfirmchk').css({
	        "color": "#FA3E3E",
	        "font-weight": "bold",
	        "font-size": "10px"
	      });
	    } else {
	      emconfirmchk = true;
	      showSuccessMsg(oMsg,'인증번호 확인 완료');
	      $('#emconfirmchk').css({
	        "color": "#0D6EFD",
	        "font-weight": "bold",
	        "font-size": "10px"
	      });
	    }
	  });
	}
	/* 약관동의 여부 agreementYn*/
	
	/*회원가입 버튼*/
	function checkInputValue() {
    if (checkId() &&
        checkPassword1() &&
        checkName() &&
        checkGender() &&
        checkPhone() &&
        checkEmail()) {
	        if (!userIdCheckFlag) {
	            alert('아이디 중복 확인을 해주세요.');
	            return false;
	        } else {
	            return true;
	        }
    }else{
		alert("답변을 모두 채우세요.");
		return false;
	}
}






