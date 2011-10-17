package com.asp.bot.listners;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;

public class TheRobotChatListener implements ChatManagerListener {

	public void chatCreated(Chat chat, boolean local) {
		chat.addMessageListener(new TheRobotMessageListener());
	}

}
