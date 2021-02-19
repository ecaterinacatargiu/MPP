package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class PublisherServiceClient implements PublisherServiceInterface
{
    private ExecutorService executorService;
    private TCPClient tcpClient;

    public PublisherServiceClient(ExecutorService execS, TCPClient cli)
    {
        this.executorService = execS;
        this.tcpClient = cli;
    }

    @Override
    public Future<String> addClient(String body)
    {
        return executorService.submit(
                () ->
                {
                    Message request = new Message(PublisherServiceInterface.ADD_CLIENT, body);
                    Message response = this.tcpClient.sendAndReceive(request);
                    return response.getBody();
                }
        );
    }

    @Override
    public Future<String> deleteClient(String body)
    {
        return executorService.submit(
                () ->
                {
                    Message request = new Message(PublisherServiceInterface.DELETE_CLIENT, body);
                    Message response = this.tcpClient.sendAndReceive(request);
                    return response.getBody();
                }
        );
    }

    @Override
    public Future<String> showClients(String body)
    {
        return executorService.submit(
                () ->
                {
                    Message request = new Message(PublisherServiceInterface.SHOW_CLIENTS, body);
                    Message response = this.tcpClient.sendAndReceive(request);
                    return response.getBody();
                }
        );
    }

    @Override
    public Future<String> showBooks(String body)
    {
        return executorService.submit(
                () ->
                {
                    Message request = new Message(PublisherServiceInterface.SHOW_BOOKS, body);
                    Message response = this.tcpClient.sendAndReceive(request);
                    return response.getBody();
                }
        );
    }

    @Override
    public Future<String> updateClient(String body)
    {
        return executorService.submit(
                () ->
                {
                    Message request = new Message(PublisherServiceInterface.UP_CLIENT, body);
                    Message response = this.tcpClient.sendAndReceive(request);
                    return response.getBody();
                }
        );
    }

    @Override
    public Future<String> updateBook(String body)
    {
        return executorService.submit(
                () ->
                {
                    Message request = new Message(PublisherServiceInterface.UP_BOOK, body);
                    Message response = this.tcpClient.sendAndReceive(request);
                    return response.getBody();
                }
        );
    }

    @Override
    public Future<String> checkBook(String body)
    {
        return executorService.submit(
                () ->
                {
                    Message request = new Message(PublisherServiceInterface.CHECK_BOOK, body);
                    Message response = this.tcpClient.sendAndReceive(request);
                    return response.getBody();
                }
        );
    }

    @Override
    public Future<String> checkClient(String body)
    {
        return executorService.submit(
                () ->
                {
                    Message request = new Message(PublisherServiceInterface.CHECK_CLIENT, body);
                    Message response = this.tcpClient.sendAndReceive(request);
                    return response.getBody();
                }
        );
    }

    @Override
    public Future<String> makeTransaction(String body)
    {
        return executorService.submit(
                () ->
                {
                    Message request = new Message(PublisherServiceInterface.DO_TRANSACT, body);
                    Message response = this.tcpClient.sendAndReceive(request);
                    return response.getBody();
                }
        );
    }

    @Override
    public Future<String> showTransactions(String body)
    {
        return executorService.submit(
                () ->
                {
                    Message request = new Message(PublisherServiceInterface.SHOW_TRANSACT, body);
                    Message response = this.tcpClient.sendAndReceive(request);
                    return response.getBody();
                }
        );
    }

    @Override
    public Future<String> clientSpending(String body)
    {
        return executorService.submit(
                () ->
                {
                    Message request = new Message(PublisherServiceInterface.CLIENTS_SPENDING, body);
                    Message response = this.tcpClient.sendAndReceive(request);
                    return response.getBody();
                }
        );
    }

    @Override
    public Future<String> transactBefore(String body)
    {
        return executorService.submit(
                () ->
                {
                    Message request = new Message(PublisherServiceInterface.TRANSACT_BEFORE_DATE, body);
                    Message response = this.tcpClient.sendAndReceive(request);
                    return response.getBody();
                }
        );
    }

    @Override
    public Future<String> deleteBook(String body)
    {
        return executorService.submit(
                () ->
                {
                    Message request = new Message(PublisherServiceInterface.DELETE_BOOK, body);
                    Message response = this.tcpClient.sendAndReceive(request);
                    return response.getBody();
                }
        );
    }

    @Override
    public Future<String> addBook(String body)
    {
        return executorService.submit(
                () ->
                {
                    Message request = new Message(PublisherServiceInterface.ADD_BOOK, body);
                    Message response = this.tcpClient.sendAndReceive(request);
                    return response.getBody();
                }
        );
    }


}
