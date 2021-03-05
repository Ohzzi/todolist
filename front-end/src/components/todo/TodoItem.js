import React, { useState } from 'react';
import styled, { css } from 'styled-components';
import { MdDone, MdDelete, MdUpdate } from 'react-icons/md';
import TodoUpdate from './TodoUpdate';
import { useTodoDispatch } from '../../context/TodoContext';

const Done = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  color: #dee2e6;
  font-size: 24px;
  cursor: pointer;
  &:hover {
    color: #ff6b6b;
  }
  display: none;
`;

const Update = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  color: #dee2e6;
  font-size: 24px;
  padding: 8px;
  cursor: pointer;
  &:hover {
    color: #ff6b6b;
  }
  display: none;
`;

const Remove = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  color: #dee2e6;
  font-size: 24px;
  cursor: pointer;
  &:hover {
    color: #ff6b6b;
  }
  display: none;
`;

const TodoItemBlock = styled.div`
  margin: 8px 16px;
  display: flex;
  align-items: center;
  padding-left: 24px;
  padding-right: 24px;
  padding-top: 12px;
  padding-bottom: 12px;
  &:hover {
    ${Done} {
      display: initial;
    }
    ${Update} {
      display: initial;
    }
    ${Remove} {
      display: initial;
    }
  }
  .important {
    color: red !important;
  }
`;

const Text = styled.div`
  flex: 1;
  font-size: 21px;
  color: #495057;
  ${props =>
    props.important &&
    css`
      color: red;
    `}
  ${props =>
    props.done &&
    css`
      color: #ced4da;
      text-decoration: line-through;
    `}
`;

function TodoItem({ id, done, text, isImportant }) {
  const [updatable, setUpdatable] = useState(false);

  const UpdateHandler = () => setUpdatable(!updatable);

  const dispatch = useTodoDispatch();

  const onToggle = () => dispatch({ type: 'TOGGLE', id });
  const onRemove = () => dispatch({ type: 'REMOVE', id });

  return (
    <>
    <TodoItemBlock>
      <Text done={done} isImportant={isImportant}>{text}</Text>
      <Done onClick={onToggle}>
        <MdDone />
      </Done>
      <Update onClick={UpdateHandler}>
        <MdUpdate />
      </Update>
      <Remove>
        <MdDelete />
      </Remove>
    </TodoItemBlock>
    <TodoUpdate text={text} open={updatable}/>
    </>
  );
}

export default React.memo(TodoItem);