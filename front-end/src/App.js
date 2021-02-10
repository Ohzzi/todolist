import React from 'react';
import ContentsBox from './components/ContentsBox';
import TodoHeader from './components/TodoHeader';
import TodoList from './components/TodoList';

import './App.css';

function App() {

  return (
    <>
      <ContentsBox>
        <TodoHeader />
        <TodoList />
      </ContentsBox>
    </>
  );
}

export default App;