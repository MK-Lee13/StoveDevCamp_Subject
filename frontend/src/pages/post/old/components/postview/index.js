import { putNoHeader, get } from '../../../../../utils/api';
import { redirect } from '../../../../../utils/redirect';
import React from 'react'
import styled from 'styled-components';
import Header from '../../../../../components/header'
import Middle from '../../../../../components/middle'
import Footer from '../../../../../components/footer'
import Write from '../../../../../components/write';
import NotFound from '../../../../../components/notfound'

const PostViewContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    height: 100vh;
`

const PostView = ({ id }) => {
  const [title, setTitle] = React.useState(null)
  const [desc, setDesc] = React.useState(null)
  const [notFoundFlag, setNotFoundFlag] = React.useState(0)

  const getBoard = () => {
    get(`/api/v1/boards/${id}`, {})
      .then(response => {
        setTitle(response.data.title)
        setDesc(response.data.body)
        console.log(response.data)
      })
      .catch(error => {
        setNotFoundFlag(1)
      })
  }

  const uploadOldPost = (title, desc) => {
    if (title.replace(/\s/g, "") === "") {
      alert("제목을 입력해주세요")
      return
    }

    if (desc.replace(/\s/g, "") === "") {
      alert("본문을 입력해주세요")
      return
    }

    putNoHeader(`/api/v1/boards/${id}`, {
      body: {
        title: title,
        body: desc
      }
    })
      .then(response => {
        alert("포스트를 수정하셨습니다")
        redirect(`/board/${id}`)
      }).catch(error => {
        console.log(error.response)
      })

  }

  React.useEffect(() => {
    getBoard()
  }, [])

  return (
    <PostViewContainer>
      <Header name="Update Post"></Header>
      {notFoundFlag === 0 &&
        <Write
          clickToUploadAction={uploadOldPost}
          initTitle={title}
          initDesc={desc}
          buttonName="수정하기"
        ></Write>
      }
      {notFoundFlag === 1 && <NotFound name="요청하신 게시글을 수정할 수 없습니다" />}
      <Middle isEdit={false} />
      <Footer />
    </PostViewContainer>
  );
}

export default PostView