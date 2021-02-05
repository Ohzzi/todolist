import React from 'react';
import styled from 'styled-components';

const Layout = styled.div`
  width: 1024px;
  height: 768px;

  margin: 0 auto;

  display: flex;
`

function ContentsLayout({ children }) {
    return <Layout>{ children }</Layout>
}

export default ContentsLayout;