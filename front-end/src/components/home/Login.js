import React from 'react';
import styled from 'styled-components';
import LoginContent from './LoginContent';

const LoginBlock = styled.div`
  width: 80%;
  margin: 12px;
  margin-bottom: 48px;
  padding: 12px;
  text-align: center;
`

const GoogleLoginButton = styled.a`
  display: block;
  background-color: red;
  border-radius: 12px;
  margin: 10px 20px;
  padding: 12px;
  text-decoration: none;
  color: white;
`
const NaverLoginButton = styled.a`
  display: block;
  background-color: #1EC800;
  border-radius: 12px;
  margin: 10px 20px;
  padding: 12px;
  text-decoration: none;
  color: white;
`

const SignUp = styled.div`
  margin-top: 1rem;
  text-align: right;
  margin-right: 20px;
  color: white;
`;

function Login() {
  return (
    <LoginBlock>
      <LoginContent />
      <GoogleLoginButton href="/oauth2/authorization/google" role="button">Google Login</GoogleLoginButton>
      <NaverLoginButton href="/oauth2/authorization/naver" role="button">네이버로 로그인</NaverLoginButton>
      <SignUp>회원가입</SignUp>
    </LoginBlock>
  );
};

export default Login;