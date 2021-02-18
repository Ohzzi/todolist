import React from 'react';
import styled from 'styled-components';

import Login from './home/Login';
import HomeHeader from './home/HomeHeader';

const Layout = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  flex-direction: column;
  justify-content: space-between;
`

function Home() {
  return (
    <Layout>
      <HomeHeader />
      <Login />
    </Layout>
  );
};

export default Home;