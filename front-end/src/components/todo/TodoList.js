import React from 'react';
import styled from 'styled-components';
import TodoItem from './TodoItem';

const TodoListBlock = styled.div`
  flex: 1;
  width: 100%;
  margin-bottom: 16px;
  padding-bottom: 48px;
  overflow-y: auto;
`;

function TodoList() {
  return <TodoListBlock>
    <TodoItem text="할 일 1" done={true} />
    <TodoItem text="할 일 2" done={true} important={true} />
    <TodoItem text="할 일 3" done={false} important={true} />
    <TodoItem text="할 일 4" done={false} />
    <TodoItem text="할 일 5" done={false} />
  </TodoListBlock>;
}

export default TodoList;