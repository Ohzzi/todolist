import React from 'react';
import TodoHeader from './todo/TodoHeader';
import TodoList from './todo/TodoList';
import TodoCreate from './todo/TodoCreate';

function Todo() {
  return (
    <>
      <TodoHeader />
      <TodoList />
      <TodoCreate />
    </>
  );
};

export default Todo;