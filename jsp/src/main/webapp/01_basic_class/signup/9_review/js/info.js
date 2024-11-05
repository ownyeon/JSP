/**
 * 유효성 검사
 */

function validateForm() {
          const infoId = document.querySelector('input[name="info_id"]').value;
          const infoPw = document.querySelector('input[name="info_pw"]').value;
          const passwordConfirm = document.querySelector('input[name="password"]').value;
          const infoName = document.querySelector('input[name="info_name"]').value;
          const infoTel = document.querySelector('input[name="info_tel"]').value;
          const infoAddr = document.querySelector('input[name="info_addr"]').value;

          // 아이디 유효성 검사
          if (infoId.length < 5 || infoId.length > 10) {
              alert('아이디는 5자 이상 10자 이하여야 합니다.');
              return false;
          }

          // 비밀번호 유효성 검사
          if (infoPw.length < 4) {
              alert('비밀번호는 4자 이상이어야 합니다.');
              return false;
          }

          // 비밀번호 확인
          if (infoPw !== passwordConfirm) {
              alert('비밀번호가 일치하지 않습니다.');
              return false;
          }

          // 이름 유효성 검사
          if (infoName.length > 5) {
              alert('이름은 5자 이내여야 합니다.');
              return false;
          }

          // 전화번호 유효성 검사
          const telPattern = /^[0-9]{10,11}$/; // 숫자 10~11자리
          if (!telPattern.test(infoTel)) {
              alert('전화번호는 숫자만 입력해야 하며 10~11자리여야 합니다.');
              return false;
          }

          // 주소 유효성 검사
          if (infoAddr.length > 20) {
              alert('주소는 20자 이내여야 합니다.');
              return false;
          }

          // 모든 검사를 통과하면 true 반환
          return true;
      }