import React from 'react';
import ContentsBox from './components/ContentsBox';
import ContentsLayout from './components/ContentsLayout';
import TodoHeader from './components/TodoHeader';
import './App.css';

function App() {

  return (
    <>
      <ContentsLayout>
        <ContentsBox>
          <TodoHeader />
        </ContentsBox>
      </ContentsLayout>
    </>
  );
}

export default App;