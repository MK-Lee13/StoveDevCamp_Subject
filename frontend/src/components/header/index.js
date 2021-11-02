import React from 'react'
import styled from 'styled-components';

const HeaderContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-height: 140px;
    width: 100%;
    color: #eca4a6;
    font-size: 2em;
    font-family: Noto Sans KR;
    margin-bottom: 60px;
    border-bottom: 1px solid #eee;
`

const Header = ({ name }) => {
  return (
    <HeaderContainer>
      {name}
    </HeaderContainer>
  );
}

export default Header