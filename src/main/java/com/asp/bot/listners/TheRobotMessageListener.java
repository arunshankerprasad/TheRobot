package com.asp.bot.listners;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class TheRobotMessageListener implements MessageListener {
	public void processMessage(Chat chat, Message message) {
		System.out.println(chat.getParticipant() + " said -> "
				+ message.getBody());
		Message botMessage = null;

		try {
			botMessage = new Message();
			if (message.getBody().equals("nothing")) {
				botMessage.setBody("Fine...");
			} else if (message.getBody().equals("make me toast")) {
				botMessage.setBody("Sure thing");
			} else {
				botMessage.setBody("What do you need?");
			}
			chat.sendMessage(botMessage);
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
}
