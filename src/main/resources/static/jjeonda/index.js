function getCookieValue(cookieName) {
  var name = cookieName + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var cookieArray = decodedCookie.split(';');
  
  for (var i = 0; i < cookieArray.length; i++) {
    var cookie = cookieArray[i];
    while (cookie.charAt(0) == ' ') {
      cookie = cookie.substring(1);
    }
    if (cookie.indexOf(name) == 0) {
      return cookie.substring(name.length, cookie.length);
    }
  }
  return "";
}

function extractPayloadFromJWT(jwt) {
  var base64Url = jwt.split('.')[1];
  var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
  var decodedPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  }).join(''));
  
  return JSON.parse(decodedPayload);
}

// JWT 추출
var jwtCookie = getCookieValue('JwToken');
var jwtCookie2 = getCookieValue('naverToken');

// userName 추출
var userName = '';
if (jwtCookie) {
  var payload = extractPayloadFromJWT(jwtCookie);
  userName = payload.UserName;
}

var userName2 = '';
if (jwtCookie2) {
  var payload2 = extractPayloadFromJWT(jwtCookie2);
  userName2 = payload2.UserName;
}
console.log(payload);
console.log(payload2);
console.log(userName);
console.log(userName2);

// HTML 요소에 userName 값 삽입




var userNameElement = document.getElementById('userName');
if (payload) {
  userNameElement.textContent = userName+"님";
}

var userNameElement2 = document.getElementById('userName2');
if (payload2) {
  userNameElement2.textContent = userName2+"님";
}


