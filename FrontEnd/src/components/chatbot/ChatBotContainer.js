import React from "react";
import "./ChatBotContainer.scss";
import ChatBot from "react-simple-chatbot";

function ChatBotContainer() {
  const theme = {
    background: "#f5f8fb",
    fontFamily: "Helvetica Neue",
    headerBgColor: "#EF6C00",
    headerFontColor: "#fff",
    headerFontSize: "15px",
    botBubbleColor: "#EF6C00",
    botFontColor: "#fff",
    userBubbleColor: "#fff",
    userFontColor: "#4a4a4a",
  };
  return (
    <ChatBot
      id="chatBot"
      headerTitle="KB 국민은행 챗봇 도깨비"
      recognitionEnable={true}
      speechSynthesis={{ enable: true, lang: "en" }}
      steps={[
        {
          id: "1",
          message: "STT, TTS 기능을 사용하시겠습니까?",
          trigger: "2",
        },
        {
          id: "2",
          user: true,
          trigger: "3",
        },
        {
          id: "3",
          message: "STT, TTS 기능을 사용합니다",
          end: true,
        },
      ]}
    />
  );
}

export default ChatBotContainer;
