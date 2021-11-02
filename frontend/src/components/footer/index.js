import React from 'react'
import styled from 'styled-components';

const FooterContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-height: 70px;
    width: 100%;
    color: #eca4a6;
    font-size: 1.2em;
    font-family: Noto Sans KR;
    border-top: 1px solid #eee;
    margin-top: auto;
`

const Footer = () => {
  return (
    <FooterContainer>
      Produced By Minky
    </FooterContainer>
  );
}

export default Footer