import React from 'react';
import TodoHeader from './todo/TodoHeader';
import TodoList from './todo/TodoList';
import TodoCreate from './todo/TodoCreate';
import { TodoProvider } from '../context/TodoContext';
import axios from 'axios';

function Todo() {
  let user;
  axios.get('/api/user')
  .then(({data}) => {
    user = {
      name: data.name,
      email: data.email,
    };
    console.log(user);
  });
  return (
    <TodoProvider>
      <TodoHeader />
      <TodoList />
      <TodoCreate />
    </TodoProvider>
  );
};

export default Todo;