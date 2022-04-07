package Talan.DTO;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MessageDTO {
    private String messageNumber;
    private String messageSender;
    private String messageReceiver;
    private String messageContent;
    private Date messageTime;

    public String getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public String getMessageReceiver() {
        return messageReceiver;
    }

    public void setMessageReceiver(String messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
        String strFeedRegisterDate = dateFormat.format(messageTime);
        return strFeedRegisterDate;

    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "messageNumber='" + messageNumber + '\'' +
                ", messageSender='" + messageSender + '\'' +
                ", messageReceiver='" + messageReceiver + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", messageTime=" + messageTime +
                '}';
    }

}
