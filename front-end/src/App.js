import React from 'react';
import { createGlobalStyle } from 'styled-components';
import ContentsBox from './components/ContentsBox';
import ContentsLayout from './components/ContentsLayout';

const GlobalStyle = createGlobalStyle`
  body {
    background: #e9ecef;
    padding: 0;
    margin: 0;
  }
`;

function App() {
  return (
    <>
      <GlobalStyle />
      <ContentsLayout>
        <ContentsBox> 컨텐츠 박스 </ContentsBox>
        <ContentsBox> 컨텐츠 박스 </ContentsBox>
      </ContentsLayout>
    </>
  );
}

export default App;