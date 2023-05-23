import { ReactElement, useState, useEffect } from 'react';
import {
  SearchBox,
  HistoryBox,
  HistoryHeader,
  Filtering,
  DeleteButton,
  TimeLine,
  HistoryBody,
  DateContainer,
  TimeBox,
} from '../styles/HistoryStyle';

import HistoryData from '../components/history/HistoryData';
import ModalHistoryItem from '../components/modals/ModalHistoryItem';
import HistorySearch from '../components/history/HistorySearch';

function scrollToLeft() {
  console.log('scroll!');
  const bins = document.querySelectorAll('[id^="history-bin-"]');

  console.log(bins);
  bins.forEach((el) => {
    el.scrollLeft = 0;
  });
}
function History(): ReactElement {
  const [isOpen, setIsOpen] = useState(false);

  // 클릭하면 열리는 모달 혹은 페이지가 필요할 경우 사용
  const handleClick = () => {
    // e.stopPropagation();
    // e.preventDefault();
    console.log('modal!');
    console.log(isOpen);
    setIsOpen(!isOpen);
  };

  useEffect(() => {
    scrollToLeft();
    console.log('changed!');
  }, [isOpen]);

  return (
    <>
      <HistoryBox>
        <HistoryHeader>
          <HistorySearch />
          {/* <SearchBox placeholder=" Search your history! tags (#node.js), title, content, date (3-15-2023, 3-2023)"></SearchBox> */}
          <Filtering onClick={handleClick}>Newest</Filtering>
          <DeleteButton>Clear History</DeleteButton>
        </HistoryHeader>
        <HistoryBody>
          <HistoryData handleClick={handleClick} />
        </HistoryBody>
      </HistoryBox>
      {isOpen && <ModalHistoryItem visible={isOpen} setVisible={setIsOpen} />}
    </>
  );
}

export default History;
