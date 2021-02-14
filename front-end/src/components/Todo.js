import React from 'react';
import ContentsBox from './ContentsBox';
import TodoHeader from './todo/TodoHeader';
import TodoList from './todo/TodoList';
import TodoCreate from './todo/TodoCreate';

function Todo() {
  return (
    <ContentsBox>
      <TodoHeader />
      <TodoList />
      <TodoCreate />
    </ContentsBox>
  );
};

export default Todo;