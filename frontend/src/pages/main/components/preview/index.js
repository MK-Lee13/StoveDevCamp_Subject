import { get } from '../../../../utils/api';
import React from 'react'
import styled from 'styled-components';
import Header from '../../../../components/header'
import Middle from '../../../../components/middle'
import Footer from '../../../../components/footer'

const PreviewContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    height: 100vh;
`

const PreviewTitle = styled.div`
    font-weight: bold;
    font-size: 28px;
    font-family: Noto Sans KR;
`

const BoardContainer = styled.div`
    display: flex;
    flex-direction: column;
    width: 800px;
    margin-top: 80px;
    margin-bottom: auto;
`

const NoContentElement = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    color: #807f89;
    font-size: 28px;
    font-family: Noto Sans KR;
`

const BoardElement = styled.div`
    display: flex;
    flex-direction: row;
    overflow: hidden;
    margin-bottom: 60px;
`

const BoardThumbail = styled.img`
    width: 192px;
    height: 192px;
    margin-right: 24px;
    background: #D7DBD1;
`

const BoardBody = styled.div`
    display: flex;
    flex-direction: column;
`

const BoardTitle = styled.div`
    display: flex;
    flex-direction: row;
    font-weight: bold;
    font-size: 20px;
    font-family: Noto Sans KR;
    line-height: 34px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    padding: 8px 0 0 0;
`

const BoardDesc = styled.div`
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    max-height: 55px;
    max-width: 550px;
    line-height: 24px;
    overflow: hidden;
    text-overflow: ellipsis;
    word-wrap: break-word;
    color: #807f89;
    font-size: 14px;
    font-family: Noto Sans KR;
    margin-bottom: 20px;
`

const BoardButton = styled.button`
    width: 102px;
    height: 35px;
    background: #ff5000;
    color: white;
    font-size: 14px;
    font-weight: bold;
    font-family: Noto Sans KR;
    cursor: pointer;
    margin-top: auto;
    margin-bottom: 20px;
    outline: none;
    border: 0;
`

const Preview = () => {
  const [boards, setBoards] = React.useState(null)

  const getBoards = () => {
    get("/api/v1/boards", {})
      .then(response => {
        if (response.data.length !== 0) {
          setBoards(response.data)
        }
      })
      .catch(error => {
        console.log(error)
      })
  }

  const handleImgError = (e) => {
    e.target.src = "default.png";
  }

  React.useEffect(() => {
    getBoards()
  }, [])

  return (
    <PreviewContainer>
      <Header name="Minky Stove Dev Blog"></Header>
      <PreviewTitle>
        전체 글
      </PreviewTitle>
      <BoardContainer>
        {boards && boards.map((element, index) => {
          var thumbnailUrl = element.thumbnailUrl

          if (thumbnailUrl === null || thumbnailUrl === "") {
            thumbnailUrl = "default.png"
          }

          return (
            <BoardElement key={index}>
              <BoardThumbail
                src={thumbnailUrl}
                alt="thumbnail_img"
                onError={handleImgError}
              />
              <BoardBody>
                <BoardTitle>{element.title}</BoardTitle>
                <BoardDesc>{element.body}</BoardDesc>
                <BoardButton>자세히 보기</BoardButton>
              </BoardBody>
            </BoardElement>
          )
        })}
        {
          !boards && (
            <NoContentElement>
              게시물이 없습니다.
            </NoContentElement>
          )
        }
      </BoardContainer>
      <Middle isEdit={true} />
      <Footer />
    </PreviewContainer>
  );
}

export default Preview