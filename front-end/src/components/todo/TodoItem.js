import React, {useState} from 'react';
import styled, { css } from 'styled-components';
import { MdDelete, MdUpdate } from 'react-icons/md';
import TodoUpdate from './TodoUpdate';

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

function TodoItem({ id, done, text, important }) {
  const [updatable, setUpdatable] = useState(false);

  const UpdateHandler = () => setUpdatable(!updatable);

  return (
    <>
    <TodoItemBlock>
      <Text done={done} important={important}>{text}</Text>
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

export default TodoItem;