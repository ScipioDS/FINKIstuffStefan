import React, { useEffect, useState } from "react";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

const Chat = () => {
    const [messages, setMessages] = useState([]);
    const [message, setMessage] = useState("");
    const [stompClient, setStompClient] = useState(null);

    useEffect(() => {
        const socket = new SockJS("http://localhost:8080/chat-websocket");
        const client = new Client({
            webSocketFactory: () => socket,
            onConnect: () => {
                client.subscribe("/topic/chat", (msg) => {
                    setMessages((prevMessages) => [...prevMessages, JSON.parse(msg.body)]);
                });
            },
        });

        client.activate();
        setStompClient(client);

        return () => client.deactivate();
    }, []);

    const sendMessage = () => {
        if (stompClient && message.trim() !== "") {
            stompClient.publish({ destination: "/app/sendMessage", body: JSON.stringify({ content: message }) });
            setMessage("");
        }
    };

    return (
        <div>
            <h2>Chat</h2>
            <div>
                {messages.map((msg, index) => (
                    <p key={index}>
                        <strong>{msg.sender}:</strong> {msg.content}
                    </p>
                ))}
            </div>
            <input type="text" value={message} onChange={(e) => setMessage(e.target.value)} />
            <button onClick={sendMessage}>Send</button>
        </div>
    );
};

export default Chat;