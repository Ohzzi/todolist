import React, {useState} from 'react';
import styled, { css } from 'styled-components';
import { MdDone, MdDelete, MdUpdate } from 'react-icons/md';
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
  margin: 16px;
  display: flex;
  align-items: center;
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

const CheckCircle = styled.div`
  width: 32px;
  height: 32px;
  border-radius: 16px;
  border: 1px solid #ced4da;
  font-size: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 20px;
  cursor: pointer;
  ${props =>
    props.done &&
    css`
      border: 1px solid #38d9a9;
      color: #38d9a9;
    `}
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
    `}
`;

function TodoItem({ id, done, text, important }) {
  const [updatable, setUpdatable] = useState(false);

  const UpdateHandler = () => setUpdatable(!updatable);

  return (
    <>
    <TodoItemBlock>
      <CheckCircle done={done}>{done && <MdDone />}</CheckCircle>
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