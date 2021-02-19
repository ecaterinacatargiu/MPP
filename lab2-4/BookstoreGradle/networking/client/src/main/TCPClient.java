package main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by radu.
 */
public class TCPClient
{
    public Message sendAndReceive(Message request)
    {
        try (var socket = new Socket(Message.HOST, Message.PORT);
             var is = socket.getInputStream();
             var os = socket.getOutputStream()
        )
        {
            request.writeTo(os);
            Message response = new Message();
            response.readFrom(is);
            //System.out.println(response);

            return response;
        }
        catch (IOException e)
        {
            throw new CustomNetworkException("[ERROR] Connection error: " + e.getMessage(), e);
        }
    }
}