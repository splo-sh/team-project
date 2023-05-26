import { useEffect, useState } from 'react';
// import axios from 'axios';
import { requestAuth } from '../utils/axiosConfig';
import { useSelector, useDispatch } from 'react-redux';
import {
  setContent,
  setSelectedBookmark,
  setSelectedTag,
  toggleModal,
} from '../features/collection/collectionSlice';
import { getConversation } from '../api/ChatInterfaceApi';
import { setConversation } from '../features/main/conversationSlice';
import { RootState } from '../app/store';
import styled from 'styled-components';
import { BookmarkType, Conversation, QnAType, TagType } from '../data/d';
import { ReactComponent as BookmarkSolid } from '../assets/icons/bookmark-solid.svg';
import { ReactComponent as ThumbtackSolid } from '../assets/icons/thumbtack-solid.svg';
import ModalContent from '../components/modals/ModalContent';
import ModalHistoryItem from '../components/modals/ModalHistoryItem';
const Main = styled.main`
  width: 1080px;
  padding: 0 40px 0 40px;
`;

const ContentWraper = styled.div`
  width: 100%;
  // overflow: scroll;
`;

const ContentContainer = styled.div`
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-content: flex-start;
  padding: 5px;
  overflow: scroll;
  height: 700px;
`;

const Title = styled.a`
  margin-bottom: 1rem;
  font-weight: bold;
`;

const Content = styled.a`
  flex-basis: 16rem;
  padding: 5px;
  border: solid;
  border-color: #c9ad6e;
  border-radius: 10px;
  margin: 0 1% 1% 0;
  height: 200px;
  overflow: hidden;
  p {
    max-height: 7rem;
    text-align: left;
    word-break: break-all;
    overflow: hidden;
    /* text-overflow: ellipsis; */
  }

  .header {
    display: flex;
    justify-content: space-between;
  }
  .title {
    /* word-break: break-all; */
  }
  .buttons {
    display: flex;
    justify-content: flex-end;
    align-items: flex-start;
    position: relative;
    top: -5px;
  }
  .bookmark {
    color: #c9ad6e;
  }
  .tag {
    color: #7bb06e;
  }
`;

const FixedContent = styled(Content)`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
`;

const FixedContentContainer = styled.div`
  display: flex;
  justify-content: flex-start;
  background-color: #faf7f1;
  overflow-x: scroll;
`;

const BookmarkContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 10.5rem;
`;

const Bookmark = styled.a`
  background-color: #f0f0f0;
  border-radius: 20px;
  margin: 0 5px 5px 0;
  padding: 5px;
`;

const BookmarkAdd = styled.button`
  flex-basis: 10rem;
  margin: 5px;
`;

const TagContainer = styled.div`
  display: flex;
  justify-content: flex-start;
  flex-wrap: wrap;
  width: 10.5rem;
  margin: 10px 0 0 0;
`;

const Tag = styled.a`
  background-color: #f0f0f0;
  border-radius: 20px;
  margin: 0 5px 5px 0;
  padding: 5px;
`;

const BookmarkTagContent = styled.div`
  display: flex;
  justify-content: space-between;
`;

const SvgButton = styled.button`
  width: 20px;
  border: none;
  margin: 20% 5% 0 5%;
  background-color: transparent;
  cursor: pointer;
`;

const BookmarkButton = () => {
  return (
    <SvgButton>
      <BookmarkSolid />
    </SvgButton>
  );
};

const PinButton = () => {
  return (
    <SvgButton>
      <ThumbtackSolid />
    </SvgButton>
  );
};

type Content = {
  conversations: Conversation[];
  tags: TagType[];
  bookmarks: BookmarkType[];
};

const Collections = () => {
  const dispatch = useDispatch();
  const [isOpen, setIsOpen] = useState(false);

  const [selectedConversation, setSelectedConversation] =
    useState<Conversation | null>(null);

  const { content, selectedBookmark, selectedTag } = useSelector(
    (state: RootState) => state.collection,
  );
  useEffect(() => {
    requestAuth
      .get(
        'http://ec2-3-35-18-213.ap-northeast-2.compute.amazonaws.com:8080/collections/',
      )
      .then((response) => {
        console.log('loaded collections');
        dispatch(setContent(response.data));
      });
  }, []);

  const loadConv = async (cId: number) => {
    const conversation = await getConversation(cId);
    if (conversation) {
      dispatch(setConversation(conversation));
    }
    return;
  };

  const handleThumbnailClick = async (cId: number) => {
    await loadConv(cId);
    setIsOpen(!isOpen);
  };

  const handleBookmarkClick = (bookmark: string) => {
    dispatch(setSelectedBookmark(bookmark));
  };

  const handleTagClick = (tag: string) => {
    dispatch(setSelectedTag(tag));
  };

  const handleContentUpdate = (newContent: any) => {
    dispatch(setContent(newContent));
  };

  dispatch(toggleModal(false));

  const handleContentClick = (conversation: Conversation) => {
    requestAuth
      .get(
        `http://ec2-3-35-18-213.ap-northeast-2.compute.amazonaws.com:8080/conversations/${conversation.conversationId}`,
      )
      .then((response) => {
        setSelectedConversation(response.data);
      });
  };
  const handleCloseModal = () => {
    setSelectedConversation(null);
  };

  return (
    content.conversations && (
      <Main>
        {selectedConversation && (
          <ModalContent
            conversation={selectedConversation}
            onClose={handleCloseModal}
          />
        )}
        <FixedContentContainer>
          {content.conversations
            .filter((item: any) => item.pinned)
            .map((conversation: Conversation) => (
              <FixedContent>
                <div className="header">
                  <Title
                    className="title"
                    key={conversation.conversationId}
                    href="#"
                    onClick={() => {
                      handleThumbnailClick(conversation.conversationId);
                    }}
                    // onClick={() => handleContentClick(conversation)}
                  >
                    {conversation.title}
                  </Title>
                  <span className="buttons">
                    <PinButton /> <BookmarkButton />
                  </span>
                </div>
                <p>{conversation.answerSummary}</p>
                <span className="bookmark">
                  {conversation.bookmarks[0]?.bookmarkName}
                </span>

                <div className="tag">
                  {conversation.tags.map((tag: TagType) => (
                    <span key={tag.tagId}>#{tag.tagName} </span>
                  ))}
                </div>
              </FixedContent>
            ))}
        </FixedContentContainer>

        <BookmarkTagContent>
          <div>
            <BookmarkContainer>
              <Bookmark
                href="#"
                key={'All'}
                onClick={() => handleBookmarkClick('All')}
              >
                All
              </Bookmark>
              {content.bookmarks.map((bookmark: BookmarkType) => (
                <Bookmark
                  href="#"
                  key={bookmark.bookmarkName}
                  onClick={() => handleBookmarkClick(bookmark.bookmarkName)}
                >
                  {bookmark.bookmarkName}
                </Bookmark>
              ))}
            </BookmarkContainer>
            <BookmarkAdd>+New Collection</BookmarkAdd>
            <TagContainer>
              {content.tags.map((tag: TagType) => (
                <Tag
                  key={tag.tagId}
                  onClick={() => handleTagClick(tag.tagName)}
                >
                  {tag.tagName}
                </Tag>
              ))}
            </TagContainer>
          </div>
          <ContentWraper>
            <ContentContainer>
              {content.conversations
                .filter(
                  (conversation: Conversation) =>
                    selectedBookmark === 'All' ||
                    conversation.bookmarks
                      .map((b) => b.bookmarkName)
                      .includes(selectedBookmark),
                )
                .map((conversation: Conversation) => (
                  <Content>
                    <div className="header">
                      <Title
                        className="title"
                        key={conversation.conversationId}
                        href="#"
                        onClick={() => {
                          handleThumbnailClick(conversation.conversationId);
                        }}
                        // onClick={() => handleContentClick(conversation)}
                      >
                        {conversation.title}
                      </Title>
                      <span className="buttons">
                        <PinButton /> <BookmarkButton />
                      </span>
                    </div>
                    <p>{conversation.answerSummary}</p>
                    <div className="bookmark">
                      {conversation.bookmarks.map((bookmark) => (
                        <span key={bookmark.bookmarkId}>
                          {bookmark.bookmarkName}
                          {' || '}
                        </span>
                      ))}
                    </div>
                    <div className="tag">
                      {conversation.tags.map((tag: TagType) => (
                        <span key={tag.tagId}>#{tag.tagName} </span>
                      ))}
                    </div>
                  </Content>
                ))}
            </ContentContainer>
          </ContentWraper>

          <div></div>
        </BookmarkTagContent>
        {isOpen && <ModalHistoryItem visible={isOpen} setVisible={setIsOpen} />}
      </Main>
    )
  );
};

export default Collections;