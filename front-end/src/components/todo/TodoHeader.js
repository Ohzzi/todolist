import React, { useState } from 'react';
import DatePicker from 'react-datepicker';
import styled from 'styled-components';
import { ko } from "date-fns/esm/locale";

import '../../react-datepicker.css';
import '../../../node_modules/react-datepicker/dist/react-datepicker.css';

const Header = styled.div`
  width: 80%;
  text-align: center;
  padding: 5%;
  .logout {
    color: #20c997;
    font-size: 18px;
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

  const getFormattedDate = (date) => {
    const month = date.toLocaleDateString('ko-KR', {
      month: 'long',
    });

    const day = date.toLocaleDateString('ko-KR', {
      day: 'numeric',
    });

    return `${month.substr(0, month.length - 1)}/${day.substr(0, day.length - 1)}`;
  };

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
    document.location.href = "/logout";
  };

  return (
    <Header>
      <DatePicker
        locale={ko}
        selected={startDate}
        dateFormat="yyyy.MM.dd(eee)"
        onChange={date => setStartDate(date)}
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
      <div className="logout" onClick={handleLogout}>Logout</div>
    </Header>
  );
};

export default TodoHeader;