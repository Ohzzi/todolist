import React, { useState } from 'react';
import styled, { css } from 'styled-components';
import { MdDone, MdDelete, MdUpdate } from 'react-icons/md';
import { useTodoState, useTodoDispatch, completeTodo, deleteTodo, updateTodo } from '../../context/TodoContext';

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
`;

const Text = styled.div`
  flex: 1;
  font-size: 21px;
  color: #495057;
  ${props =>
    props.isImportant &&
    css`
      color: red;
    `}
  ${props =>
    props.isDone &&
    css`
      color: #ced4da;
      text-decoration: line-through;
    `}
`;

const InsertForm = styled.form`
  background: #f8f9fa;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-left: 24px;
  padding-top: 16px;
  padding-right: 24px;
  padding-bottom: 16px;

  border-bottom-left-radius: 16px;
  border-bottom-right-radius: 16px;
  border-top: 1px solid #e9ecef;
`;

const Input = styled.input`
  padding: 12px;
  border-radius: 4px;
  border: 1px solid #dee2e6;
  width: 85%;
  outline: none;
  font-size: 18px;
  box-sizing: border-box;
`;

const ImportantBtn = styled.div`
  border-radius: 4px;
  font-size: 18px;
  background: #dee2e6;
  color: white;
  cursor: pointer;
  padding: 12px;
  &:hover {
    background: #ff8787;
  }
  ${props =>
    props.importance &&
    css`
        background: #ff6b6b;
      `}
`;

function TodoItem({ id, isDone, text, isImportant }) {
  const [updatable, setUpdatable] = useState(false);
  const [importance, setImportance] = useState(isImportant);
  const [value, setValue] = useState(text);

  const importanceHandler = () => setImportance(!importance);

  const UpdateHandler = () => {
    !isDone && setUpdatable(!updatable);
  }

  const todoState = useTodoState();
  const todoDispatch = useTodoDispatch();

  const onToggle = () => {
    completeTodo(todoState, todoDispatch, id);
  };
  const onRemove = () => {
    deleteTodo(todoDispatch, id);
  };

  const onChange = e => setValue(e.target.value);

  const onSubmit = e => {
    e.preventDefault(); // 새로고침 방지
    console.log(value); 
    updateTodo(todoDispatch, id, {
      content: value,
      isDone: isDone,
      isImportant: importance
    });
    setUpdatable(false);
  };

  return (
    <>
    <TodoItemBlock>
      <Text isDone={isDone} isImportant={isImportant}>{text}</Text>
      <Done onClick={onToggle}>
        <MdDone />
      </Done>
      <Update onClick={UpdateHandler}>
        <MdUpdate />
      </Update>
      <Remove onClick={onRemove}>
        <MdDelete />
      </Remove>
    </TodoItemBlock>
    {updatable && (
        <InsertForm onSubmit={onSubmit}>
          <Input autoFocus onChange={onChange} defaultValue={text} />
          <ImportantBtn onClick={importanceHandler} importance={importance}>중요</ImportantBtn>
        </InsertForm>
      )}
    </>
  );
}

export default React.memo(TodoItem);