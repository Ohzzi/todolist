import React from 'react';
import styled from 'styled-components';

const Contents = styled.div`
  width: 512px;
  height: 85vh;
  background: white;
  position: relative; /* 추후 박스 하단에 추가 버튼을 위치시키기 위한 설정 */
  box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.04);

  margin: 0 auto; /* 페이지 중앙에 나타나도록 설정 */

  margin-top: 60px;
  margin-bottom: 32px;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

function ContentsBox({ children }) {
  return <Contents>{children}</Contents>;
}

export default ContentsBox;