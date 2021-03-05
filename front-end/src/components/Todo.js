import React from 'react';
import TodoHeader from './todo/TodoHeader';
import TodoList from './todo/TodoList';
import TodoCreate from './todo/TodoCreate';
import { TodoProvider } from '../context/TodoContext';
import { UserProvider } from '../context/UserContext';
import { DateProvider } from '../context/DateContext';

function Todo() {
  return (
    <UserProvider>
      <TodoProvider>
        <DateProvider>
          <TodoHeader />
          <TodoList />
          <TodoCreate />
        </DateProvider>
      </TodoProvider>
    </UserProvider>
  );
};

export default Todo;