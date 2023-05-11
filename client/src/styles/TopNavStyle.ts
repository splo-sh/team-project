import styled from "styled-components";

export const StyledSpan = styled.span`
  padding-right: var(--padding-right-topnavitems);
  color: var(--color-green);
  text-decoration: none;
  font-size: var(--text-fontsize-link);
`;

export const TopNavBox = styled.header`
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: var(--size-minheight-topnav);
  z-index: 999;
  min-height: var(--size-minheight-topnav);
  padding: var(--padding-top-topnavbox) var(--padding-left-topnavbox);
  font-size: var(--text-fontsize-link);
`;
export const LogoBox = styled.div`
  min-width: var(--size-minwidth-logo);
  min-height: 30px;
  color: var(--color-default-yellow);
  font-size: var(--text-fontsize-logo);
  font-weight: var(--text-fontweight-logo);
  letter-spacing: var(--text-letterspacing-logo);
  text-transform: uppercase;
`;
export const NavIconsBox = styled.div`
  min-width: var(--size-minwiddth-topnavicons);
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;
export const AvatarBox = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  min-width: var(--size-avatar);
  min-height: var(--size-avatar);
  padding: 0 20px;
  overflow: false;
  & .navitem {
    padding: 0 var(--padding-left-topnavitems);
  }
  .svg {
    width: var(--size-avatar-default);
    transition: width 0.1s ease-in-out;
  }

  .svg:hover {
    width: var(--size-avatar-hover);
    filter: invert(25%) sepia(80%) saturate(1.3);
    // fill: blue;
    transition: width 0.2s ease-in-out;
    cursor: pointer;
  }

  .center {
    width: var(--size-avatar-center);
  }
  .center:hover {
    width: var(--size-avatar-center-hover);
  }
`;

export const MemberBox = styled(AvatarBox)`
  min-width: var(--size-minwidth-logo);
`;

export const DialogBox = styled.div`
  position: absolute;
  top: 0;
  left: 0;
  width: auto;
  height: auto;
  border: 1px solid var(--color-border-dialogbox);
  box-shadow: 1px 2px 2px 1px var(--color-dropshadow-dialogbox);
  border-radius: 10px;
  text-align: center;
`;

export const ModalBackdrop = styled.div`
  background-color: rgba(0, 0, 0, 0.2);
  width: 100vw;
  height: 100vh;
  z-index: 0;
  top: 0;
  position: absolute;
`;

export const UserInfo = styled.div`
  display: flex;
  flex-direction: column;
  padding: 16px 10px;
`;

export const UserCreatedDate = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  background: var(--color-default-lightestgray);
  color: var(--color-default-gray);
  font-weight: 400;
  letter-spacing: -0.03em;
  margin-bottom: 25px;
  padding: 10px 25px;
`;

export const DialogItems = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  color: var(--color-default-gray);
  padding-bottom: 20px;
`;

export const DialogSelectItem = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  width: 100%;
  height: 35px;
  padding: 20px 10px;
  font-size: 18px;
  cursor: pointer;
`;

export const SignoutItem = styled(DialogSelectItem)`
  padding: 0 10px;
`;

export const EmailItem = styled(DialogSelectItem)`
  padding: 0 10px;
  font-size: 13px;
  letter-spacing: -0.03em;
`;

export const SignOutFooter = styled(DialogItems)`
  display: flex;
  border-top: 1px solid #f3efef;
  padding: 15px 0;
  font-size: 18px;
`;

// export const;

// tags = ['react', 'AI', 'ML']

// collectionItems = [{
//   title: 'title1'
//   content: 'content1'
//   date: 'date'

// },
// {

// }]
