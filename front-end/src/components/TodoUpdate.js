import React, { useState } from 'react';
import styled, { css } from 'styled-components';

const InsertForm = styled.form`
  background: #f8f9fa;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-left: 32px;
  padding-top: 32px;
  padding-right: 32px;
  padding-bottom: 32px;

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

function TodoUpdate({ id, open, text, important }) {
  const [importance, setImportance] = useState(important);

  const importanceHandler = () => setImportance(!importance);

  return (
    <>
      {open && (
        <InsertForm>
          <Input autoFocus defaultValue={text} />
          <ImportantBtn onClick={importanceHandler} importance={importance}>중요</ImportantBtn>
        </InsertForm>
      )}
    </>
  )
}

export default TodoUpdate;