import { postNoHeader } from '../../../../../utils/api';
import { redirect } from '../../../../../utils/redirect';
import React from 'react'
import styled from 'styled-components';
import Header from '../../../../../components/header'
import Middle from '../../../../../components/middle'
import Footer from '../../../../../components/footer'
import Write from '../../../../../components/write';

const PostViewContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    height: 100vh;
`

const PostView = () => {
  const uploadNewPost = (title, desc) => {
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
    <PostViewContainer>
      <Header name="New Post"></Header>
      <Write
        clickToUploadAction={uploadNewPost}
        initTitle=""
        initDesc=""
        buttonName="작성하기"
      ></Write>
      <Middle isEdit={false} />
      <Footer />
    </PostViewContainer>
  );
}

export default PostView