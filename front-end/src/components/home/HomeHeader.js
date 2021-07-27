import React from 'react';
import styled from 'styled-components';

const Header = styled.div`
  margin: 24px;
  align-content: center;
  padding: 5%;
`;

const TextBlock = styled.div`
  font-size: 72px;
  font-weight: 700;
  color: cornflowerblue;
`

function HomeHeader() {
  return (
    <>
    <Header>
      <TextBlock>
        Todolist
      </TextBlock>
    </Header>
    </>
  );
}

export default HomeHeader;