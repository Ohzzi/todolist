import React from 'react';
import styled from 'styled-components';

const Header = styled.div`
  align-content: center;
  padding: 5%;
`;

function TodoHeader() {
  return (
    <>
    <Header>
      환영합니다!
    </Header>
    <a href="/todolist">
      이동
    </a>
    </>
  );
}

export default TodoHeader;