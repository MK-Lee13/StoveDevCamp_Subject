import { postNoHeader } from '../../../../utils/api';
import { redirect } from '../../../../utils/redirect';
import React from 'react'
import styled from 'styled-components';
import Header from '../../../../components/header'
import Middle from '../../../../components/middle'
import Footer from '../../../../components/footer'

const WriteContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    height: 100vh;
`

const WriteWrapper = styled.div`
    display: flex;
    flex-direction: column;
    margin-bottom: auto;
`

const WriteTitleBox = styled.div`
    padding: 5px;
    border-bottom: 1px solid #eee;
`

const WriteTitleTextArea = styled.textarea`
    width: 800px;
    height: 56px;
    border: none;
    font-size: 30px;
    color: #202020;
    font-family: Noto Sans KR;
    resize: none;
    outline: 0 none;
    line-height: 40px;
    overflow: hidden;
`

const WriteDescBox = styled.div`
    padding: 5px;
    margin-top: 10px;
`

const WriteDescTextArea = styled.textarea`
    width: 800px;
    height: 400px;
    border: none;
    font-size: 30px;
    color: #202020;
    font-family: Noto Sans KR;
    resize: none;
    outline: 0 none;
    line-height: 40px;
`

const ButtonBox = styled.div`
    display: flex;
    flex-direction: row;
`

const WriteButton = styled.button`
    width: 102px;
    height: 35px;
    background: #ff5000;
    color: white;
    font-size: 14px;
    font-weight: bold;
    font-family: Noto Sans KR;
    cursor: pointer;
    margin-top: auto;
    margin-right: 10px;
    margin-bottom: 20px;
    outline: none;
    border: 0;
`

const Write = () => {
  // const [url, setUrl] = React.useState("")
  const [title, setTitle] = React.useState("")
  const [desc, setDesc] = React.useState("")

  // const handleUrl = (event) => {
  //   setUrl(event.target.value)
  // }

  const handleTitle = (event) => {
    setTitle(event.target.value)
  }

  const handleDesc = (event) => {
    setDesc(event.target.value)
  }

  const uploadNewPost = () => {
    if (title.replace(/\s/g, "") === "") {
      alert("제목을 입력해주세요")
      return
    }

    if (desc.replace(/\s/g, "") === "") {
      alert("본문을 입력해주세요")
      return
    }

    postNoHeader(`/api/v1/boards`, {
      body: {
        title: title,
        body: desc
      }
    })
      .then(response => {
        alert("새로운 포스트를 등록하셨습니다")
        redirect("/main")
      }).catch(error => {
        console.log(error.response)
      })

  }

  return (
    <WriteContainer>
      <Header name="New Post"></Header>
      <WriteWrapper>
        <WriteTitleBox>
          <WriteTitleTextArea onChange={handleTitle} placeholder="제목을 입력하세요"></WriteTitleTextArea>
        </WriteTitleBox>
        <WriteDescBox>
          <WriteDescTextArea onChange={handleDesc} placeholder="본문을 입력하세요"></WriteDescTextArea>
        </WriteDescBox>
        <ButtonBox>
          <WriteButton onClick={uploadNewPost}>작성하기</WriteButton>
          <WriteButton>썸네일 업로드</WriteButton>
        </ButtonBox>
      </WriteWrapper>
      <Middle isEdit={false} />
      <Footer />
    </WriteContainer>
  );
}

export default Write