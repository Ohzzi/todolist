import React from 'react';
import styled from 'styled-components';

const LoginBlock = styled.div`
  width: 80%;
  margin: 12px;
  margin-bottom: 24px;
  padding: 12px;
  text-align: center;
`

const GoogleLoginButton = styled.a`
  display: block;
  background-color: red;
  border-radius: 12px;
  margin: 12px;
  padding: 12px;
  text-decoration: none;
  color: white;
`
const NaverLoginButton = styled.a`
  display: block;
  background-color: #1EC800;
  border-radius: 12px;
  margin: 12px;
  padding: 12px;
  text-decoration: none;
  color: white;
`

function Login() {
  return (
    <LoginBlock>
      <GoogleLoginButton href="/oauth2/authorization/google" role="button">Google Login</GoogleLoginButton>
      <NaverLoginButton href="/oauth2/authorization/naver" role="button">네이버로 로그인</NaverLoginButton>
    </LoginBlock>
  );
};

export default Login;