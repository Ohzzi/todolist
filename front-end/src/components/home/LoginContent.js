import React from 'react';
import styled from 'styled-components';

const Container = styled.div`
  padding: 20px;
  padding-bottom: 10px;
`;

const Input = styled.input`
  position: relative;
  overflow: hidden;
  width: 100%;
  height: 40px;
  margin: 0 0 8px;
  padding: 20px;
  border: solid 1px #dadada;
  background: white;
  box-sizing: border-box;
  border-radius: 12px;
`;

const Button = styled.div`
  font-size: 18px;
  line-height: 49px;
  margin: 10px 0 0 0;
  display: block;
  width: 100%;
  cursor: pointer;
  text-align: center;
  color: white;
  border: none;
  border-radius: 12px;
  background-color: cornflowerblue;
`;

function LoginContent() {
  return (
    <Container>
      <Input id="id" name="id" placeholder="아이디를 입력해주세요" />
      <Input
        id="password"
        name="password"
        type="password"
        placeholder="비밀번호를 입력해주세요"
      />
      <Button>로그인</Button>
    </Container>
  );
}

export default LoginContent;