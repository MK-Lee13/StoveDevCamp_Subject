import { get, _delete } from '../../../../utils/api';
import { redirect } from '../../../../utils/redirect';
import React from 'react'
import styled from 'styled-components';
import Header from '../../../../components/header'
import Middle from '../../../../components/middle'
import Footer from '../../../../components/footer'
import NotFound from '../../../../components/notfound'

const DetailViewContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    height: 100vh;
`

const DetailViewWrapper = styled.div`
    display: flex;
    flex-direction: column;
    width: 800px;
    margin-bottom: auto;
`

const DetailViewTitleBox = styled.div`
    padding: 5px;
    font-weight: bold;
    font-size: 20px;
    font-family: Noto Sans KR;
    line-height: 34px;
    word-wrap: break-word;
`

const DetailViewEditBox = styled.div`
    display: flex;
    flex-direction: row;
    color: #807f89;
    font-size: 14px;
    font-family: Noto Sans KR;
`

const DetailViewEditElement = styled.div`
    margin-right: 4px;
    margin-left: 4px;
    cursor: pointer;
`

const DetailViewDescBox = styled.div`
    padding: 5px;
    margin-top: 10px;
    line-height: 24px;
    word-wrap: break-word;
    color: #807f89;
    font-size: 16px;
    font-family: Noto Sans KR;
    margin-bottom: 60px;
`

const DetailView = ({ id }) => {
  const [title, setTitle] = React.useState("")
  const [desc, setDesc] = React.useState("")
  const [notFoundFlag, setNotFoundFlag] = React.useState(0)

  const getBoard = () => {
    get(`/api/v1/boards/${id}`, {})
      .then(response => {
        setTitle(response.data.title)
        setDesc(response.data.body)
      })
      .catch(error => {
        setNotFoundFlag(1)
      })
  }

  const deleteBoard = () => {
    if (window.confirm("이 글을 완전히 삭제합니다. 계속하시겠습니까?") === false) {
      return
    }

    _delete(`/api/v1/boards/${id}`, {})
      .then(response => {
        alert("요청하신 게시물을 삭제하였습니다")
        redirect("/main")
      })
      .catch(error => {
        alert("삭제에 실패하였습니다")
      })
  }

  React.useEffect(() => {
    getBoard()
  }, [])

  return (
    <DetailViewContainer>
      <Header name="Post"></Header>
      {notFoundFlag === 0 &&
        <DetailViewWrapper>
          <DetailViewTitleBox>
            {title}
          </DetailViewTitleBox>
          <DetailViewEditBox>
            <DetailViewEditElement onClick={() => { redirect(`/post/${id}`) }}>수정</DetailViewEditElement>
            |
            <DetailViewEditElement onClick={deleteBoard}>삭제</DetailViewEditElement>
          </DetailViewEditBox>
          <DetailViewDescBox>
            {
              desc.split('\n').map((text, index) => {
                return (<span key={index}>{text}<br /></span>)
              })
            }
          </DetailViewDescBox>
        </DetailViewWrapper>}
      {notFoundFlag === 1 && <NotFound name="요청하신 게시글을 찾을 수 없습니다" />}
      <Middle isEdit={false} />
      <Footer />
    </DetailViewContainer>
  );
}

export default DetailView