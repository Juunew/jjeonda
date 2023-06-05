'use strict';

const form = document.querySelector('#form__wrap');
const checkAll = document.querySelector('.terms__check__all input');
const checkBoxes = document.querySelectorAll('.input__check input');
const submitButton = document.querySelector('button');
const agreementYn = document.querySelector('#agreementYn')
const agreements = {
  termsOfService: false,
  privacyPolicy: false,
  allowPromotions: false,
};

//form.addEventListener('submit', (e) => e.onsubmit); // 새로고침(submit) 되는 것 막음

checkBoxes.forEach((item) => item.addEventListener('input', toggleCheckbox));

function toggleCheckbox(e) {
  const { checked, id } = e.target;  
  agreements[id] = checked;
  this.parentNode.classList.toggle('active');
  checkAllStatus();
  toggleSubmitButton();
}

function checkAllStatus() {
  const { termsOfService, privacyPolicy, allowPromotions } = agreements;
  if (termsOfService && privacyPolicy && allowPromotions) {
    checkAll.checked = true;
    agreementYn.value="Y";
  } else {
    checkAll.checked = false;
    agreementYn.value="N";
  }
}

function toggleSubmitButton() {
  const { termsOfService, privacyPolicy } = agreements;
  if (termsOfService && privacyPolicy) {
    submitButton.disabled = false;
    agreementYn.value
  } else {
    submitButton.disabled = true;
  }
}

checkAll.addEventListener('click', (e) => {
  const { checked } = e.target;
  if (checked) {
    checkBoxes.forEach((item) => {
      item.checked = true;
      agreements[item.id] = true;
      agreementYn.value="Y"
      item.parentNode.classList.add('active');
    });
  } else {
    checkBoxes.forEach((item) => {
      item.checked = false;
      agreements[item.id] = false;
      item.parentNode.classList.remove('active');
    });
  }
  toggleSubmitButton();
});

form.addEventListener('submit', (e) => {
 // e.preventDefault(); // 새로고침 방지
  const agreementYnValue = agreementYn.value;
  console.log('agreementYn:', agreementYnValue);
  // 여기에서 서버로 전달할 로직을 추가하세요.
});
