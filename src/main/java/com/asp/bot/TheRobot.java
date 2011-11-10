package com.asp.bot;

import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.DiscussionHistory;
import org.jivesoftware.smackx.muc.MultiUserChat;

import com.asp.bot.listners.ChatListener;
import com.asp.bot.listners.TheRobotChatListener;

public class TheRobot {
	public static void main(String[] args) {
		ConnectionConfiguration cc = new ConnectionConfiguration("10.2.0.88", 5222,
				"space");
		// Assumes you have the SRV records setup in DNS
		XMPPConnection conn = new XMPPConnection(cc);
		try {
			conn.connect();

			// You have to put this code before you login
			SASLAuthentication.supportSASLMechanism("PLAIN", 0);

			conn.login("therobot@space", "aspasp");
			ChatManager chatManager = conn.getChatManager();
			chatManager.addChatListener(new TheRobotChatListener());

			// Create a MultiUserChat using a Connection for a room
			MultiUserChat muc = new MultiUserChat(conn, "space-dev@conference.space");

			// User2 joins the new room using a password and specifying
			// the amount of history to receive. In this example we are requesting the last 5 messages.
			DiscussionHistory history = new DiscussionHistory();
			history.setMaxStanzas(0);
			muc.join("The Robot", null, history, SmackConfiguration.getPacketReplyTimeout());

			// Send an initial message when we join the room
			Message msg = muc.createMessage();
			msg.addBody(null, "Hello all");
			muc.sendMessage(msg);

			muc.addMessageListener(new ChatListener(muc));

			System.out.println("Connected");
			while (conn.isConnected()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			conn.disconnect();
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
}
