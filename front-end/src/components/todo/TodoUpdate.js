import React, { useState } from 'react';
import styled, { css } from 'styled-components';
import { useTodoDispatch, updateTodo } from '../../context/TodoContext';

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

function TodoUpdate({ id, open, text, isDone, isImportant }) {
  const [importance, setImportance] = useState(isImportant);
  const [value, setValue] = useState(text);
  const [isOpen, setIsOpen] = useState(open);

  const importanceHandler = () => setImportance(!importance);

  const dispatch = useTodoDispatch();

  const onChange = e => setValue(e.target.value);

  const onSubmit = e => {
    e.preventDefault(); // 새로고침 방지
    updateTodo(dispatch, id, {
      content: value,
      isDone: isDone,
      isImportant: importance
    });
    setValue('');
    setIsOpen()
  };

  return (
    <>
      {isOpen && (
        <InsertForm onSubmit={onSubmit}>
          <Input autoFocus onChange={onChange} defaultValue={text} />
          <ImportantBtn onClick={importanceHandler} importance={importance}>중요</ImportantBtn>
        </InsertForm>
      )}
    </>
  )
}

export default React.memo(TodoUpdate);