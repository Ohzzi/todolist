import React, { useState, useContext, useEffect } from 'react';
import DatePicker from 'react-datepicker';
import styled from 'styled-components';
import { ko } from "date-fns/esm/locale";
import { fetchTodos, useTodoDispatch, useTodoState } from '../../context/TodoContext';
import { useUserState, useUserDispatch, getUser } from '../../context/UserContext';
import { useDateState, useDateDispatch } from '../../context/DateContext';

import '../../react-datepicker.css';
import '../../../node_modules/react-datepicker/dist/react-datepicker.css';

const Header = styled.div`
  width: 80%;
  text-align: center;
  padding: 5%;
  .tasks-left {
    color: #20c997;
    font-size: 18px;
    margin-top: 20px;
    margin-right: 20px;
    font-weight: bold;
    text-align: center;
  }
  .logout {
    color: #20c997;
    font-size: 14px;
    margin-top: 20px;
    margin-right: 20px;
    font-weight: bold;
    text-align: right;
    cursor: pointer;
  }
  border-bottom: 2px solid #e9ecef;
`

const CustomDiv = styled.div`
  cursor: pointer;
`

function TodoHeader() {
  const [startDate, setStartDate] = useState(new Date());
  const ExampleCustomInput = ({ value, onClick }) => (
    <CustomDiv className="example-custom-input" onClick={onClick}>
      {value}
    </CustomDiv>
  );

  const getDayName = (date) => {
    return date.toLocaleDateString('ko-KR', {
      weekday: 'long',
    }).substr(0, 1);
  };

  const createDate = (date) => {
    return new Date(new Date(date.getFullYear()
      , date.getMonth()
      , date.getDate()
      , 0
      , 0
      , 0));
  };

  const handleLogout = () => {
    userDispatch({
      type: 'LOGOUT'
    });
    document.location.href = "/logout";
  };

  const todos = useTodoState();
  const todoDispatch = useTodoDispatch();
  const undoneTasks = todos.filter(todo => !todo.isDone);
  const userState = useUserState();
  const userDispatch = useUserDispatch();
  const dateState = useDateState();
  const dateDispatch = useDateDispatch();

  useEffect(() => {
    getUser(userDispatch, todoDispatch, dateState);
    return () => {};
  }, []);

  return (
    <Header>
      <DatePicker
        locale={ko}
        selected={startDate}
        dateFormat="yyyy.MM.dd(eee)"
        onChange={date => {
          dateDispatch({
            type: 'UPDATE_DATE',
            data: date.toJSON().substr(0,10)
          });
          fetchTodos(todoDispatch, userState.data, date.toJSON().substr(0,10));
          setStartDate(date);
        }}
        customInput={<ExampleCustomInput />}
        minDate={new Date()}
        popperModifiers={{
          preventOverflow: {
            enabled: true,
          },
        }}
        popperPlacement="auto"
        dayClassName={date =>
          getDayName(createDate(date)) === '토' ? "saturday"
            :
            getDayName(createDate(date)) === '일' ? "sunday" : undefined
        }
      />
      <div className="tasks-left">할 일 {undoneTasks.length}개 남음</div>
      <div className="logout" onClick={handleLogout}>Logout</div>
    </Header>
  );
};

export default TodoHeader;