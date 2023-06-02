/**
 * 
 */
	
	var userIdCheckFlag = false;
	$(function (){
		$('#checkIdBtn').click(function(){
			if(!checkId()){
				return false;
			}
			var id = $('#id').val();
			var idMsg = $('#idMsg');
			if(id === ''){
				showErrorMsg(idMsg, '아이디를 입력해 주세요.')
				return false;
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
						idCheckFlag = true;
					}
				}
			});
		});
		
		//비밀번호
		$('#password').keyup(function(){
			$('#pwdMsg').html('');
			checkPassword1();
		});
		
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
		
		// 생년월일 birth
		$('#birth').blur(function(){
			
		})
		
		
	});
	
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
	/* 사용자 이름 */
	function checkName(){
		var name = $('#name').val();
		var oMsg = $('#nameMsg');
		if (name === '') {
	        showErrorMsg(oMsg, '필수 정보입니다.');
	        return false;
	    }
	}
	/* 사용자 전화번호 phone*/
	function checkPhone(){
		var phone = $('#phone').val();
		var oMsg = $('#phoneMsg');
		var regexp = /01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/; // 01X(1,6,7,8,9)-XXXX-XXXX 검사
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
	        /* .replace(/[^0-9]/g, '')
	        .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3")
	        .replace(/(\-{1,2})$/g, ""); */
	    .replace(/[^0-9]/g, '')
	    .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
	}
	
	function autoHyphen(target) {
	    return target.replace(/[^0-9]/g, '')
	        .replace(/(\d{2,3})(\d{3,4})(\d{4})/, '$1-$2-$3');
	}
	
	
	/* 성별 gender*/
	function checkGender(){
		
	}
	/* 생일 birth */
	function checkBirth(){
		
	}
	
	
	
	/* EmailAddress email*/
	/* 약관동의 여부 agreementYn*/