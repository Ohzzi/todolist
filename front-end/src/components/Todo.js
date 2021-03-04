import React from 'react';
import TodoHeader from './todo/TodoHeader';
import TodoList from './todo/TodoList';
import TodoCreate from './todo/TodoCreate';
import { TodoProvider } from '../context/TodoContext';
import { UserProvider } from '../context/UserContext';

function Todo() {
  return (
    <UserProvider>
      <TodoProvider>
        <TodoHeader />
        <TodoList />
        <TodoCreate />
      </TodoProvider>
    </UserProvider>
  );
};

export default Todo;