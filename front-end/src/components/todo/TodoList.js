import React, { useContext } from 'react';
import styled from 'styled-components';
import TodoItem from './TodoItem';
import { useTodoState } from '../../context/TodoContext';

const TodoListBlock = styled.div`
  flex: 1;
  width: 100%;
  margin-bottom: 16px;
  padding-bottom: 48px;
  overflow-y: auto;
`;

function TodoList() {
  const todos = useTodoState();

  return (
    <TodoListBlock>
      {todos.map(todo => (
        <TodoItem key={todo.id} id={todo.id} text={todo.content} done={todo.done} important={todo.important} />
      ))}
    </TodoListBlock>
  );
}

export default TodoList;