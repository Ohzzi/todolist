import React from 'react';
import ContentsBox from './components/ContentsBox';
import TodoHeader from './components/TodoHeader';
import TodoList from './components/TodoList';
import TodoCreate from './components/TodoCreate';

import './App.css';

function App() {

  return (
    <>
      <ContentsBox>
        <TodoHeader />
        <TodoList />
        <TodoCreate />
      </ContentsBox>
    </>
  );
}

export default App;