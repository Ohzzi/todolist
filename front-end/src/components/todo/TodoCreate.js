import React, { useState } from 'react';
import styled, { css } from 'styled-components';
import { MdAdd } from 'react-icons/md';
import { createTodo, useTodoDispatch } from '../../context/TodoContext';
import { useUserState } from '../../context/UserContext';
import { useDateState } from '../../context/DateContext';

const CircleButton = styled.button`
  background: blue;
  &:hover {
    background: #63e6be;
  }
  &:active {
    background: blue;
  }

  z-index: 5;
  cursor: pointer;
  width: 80px;
  height: 80px;
  display: block;
  align-items: center;
  justify-content: center;
  font-size: 60px;
  position: absolute;
  left: 50%;
  bottom: 0px;
  transform: translate(-50%, 50%);
  color: white;
  border-radius: 50%;
  border: none;
  outline: none;
  display: flex;
  align-items: center;
  justify-content: center;

  transition: 0.125s all ease-in;
  ${props =>
    props.open &&
    css`
      background: #ff6b6b;
      &:hover {
        background: #ff8787;
      }
      &:active {
        background: #fa5252;
      }
      transform: translate(-50%, 50%) rotate(45deg);
    `}
`;

const InsertFormPositioner = styled.div`
  width: 100%;
  bottom: 0;
  left: 0;
`;

const InsertForm = styled.form`
  background: #f8f9fa;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-left: 32px;
  padding-top: 32px;
  padding-right: 32px;
  padding-bottom: 72px;

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


function TodoCreate() {
  const [open, setOpen] = useState(false);
  const [importance, setImportance] = useState(false);
  const [value, setValue] = useState('');

  const openHandler = () => setOpen(!open);

  const importanceHandler = () => setImportance(!importance);

  const dispatch = useTodoDispatch();
  const userState = useUserState();
  const dateState = useDateState();

  const onChange = e => setValue(e.target.value);

  const onSubmit = e => {
    e.preventDefault(); // 새로고침 방지
    createTodo(dispatch, {
      content: value,
      user: userState,
      date: dateState,
      isImportant: importance
    });
    setValue('');
    setOpen(false);
  };

  return (
    <>
      {open && (
        <InsertFormPositioner>
          <InsertForm onSubmit={onSubmit}>
            <Input autoFocus onChange={onChange} placeholder="할 일을 입력 후, Enter 를 누르세요" value={value} />
            <ImportantBtn onClick={importanceHandler} importance={importance}>중요</ImportantBtn>
          </InsertForm>
        </InsertFormPositioner>
      )}
      <CircleButton onClick={openHandler} open={open}>
        <MdAdd />
      </CircleButton>
    </>
  );
}

export default React.memo(TodoCreate);