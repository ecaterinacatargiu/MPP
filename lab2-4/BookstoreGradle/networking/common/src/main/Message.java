package main;

import java.io.*;

public class Message
{
    public static final int PORT = 65000;
    public static final String HOST = "localhost";

    private String header;
    private String body;

    public Message()
    {
        this.header = "NOP";
        this.body = "NOP";
    }

    public Message(String head, String body)
    {
        this.header = head;
        this.body = body;
    }


    public String getHeader()
    {
        return header;
    }

    public String getBody()
    {
        return body;
    }

    public void setHeader(String header)
    {
        this.header = header;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public void writeTo(OutputStream os) throws IOException
    {
        os.write((header + System.lineSeparator() + body + System.lineSeparator()).getBytes());
    }

    public void readFrom(InputStream is) throws IOException
    {
        var br = new BufferedReader(new InputStreamReader(is));
        header = br.readLine();
        body = br.readLine();
    }

    @Override
    public String toString()
    {
        return "Message{" +
                "header='" + header + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
