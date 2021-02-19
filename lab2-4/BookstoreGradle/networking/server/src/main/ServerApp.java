package main;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerApp {

    public static void main(String[] args)
    {
        try
        {
            System.out.println("server started");
            ExecutorService executorService = Executors.newFixedThreadPool(
                    Runtime.getRuntime().availableProcessors()
            );

            PublisherServiceInterface theService = new PublisherServiceServer(executorService);
            TCPServer tcpServer = new TCPServer(executorService);

            tcpServer.addHandler(PublisherServiceInterface.ADD_BOOK,
                    (request) -> {
                        String name = request.getBody();
                        Future<String> future = theService.addBook(name);
                        try
                        {
                            String result = future.get();
                            return new Message("ok", result);
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            e.printStackTrace();
                            return new Message("error", e.getMessage());
                        }
            });

            tcpServer.addHandler(PublisherServiceInterface.DELETE_BOOK,
                    (request) -> {
                        String name = request.getBody();
                        Future<String> future = theService.deleteBook(name);
                        try
                        {
                            String result = future.get();
                            return new Message("ok", result);
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            e.printStackTrace();
                            return new Message("error", e.getMessage());
                        }
            });

            tcpServer.addHandler(PublisherServiceInterface.SHOW_BOOKS,
                    (request) -> {
                        String name = request.getBody();
                        Future<String> future = theService.showBooks(name);
                        try
                        {
                            String result = future.get();
                            return new Message("ok", result);
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            e.printStackTrace();
                            return new Message("error", e.getMessage());
                        }
            });

            tcpServer.addHandler(PublisherServiceInterface.ADD_CLIENT,
                    (request) -> {
                        String name = request.getBody();
                        Future<String> future = theService.addClient(name);
                        try
                        {
                            String result = future.get();
                            return new Message("ok", result);
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            e.printStackTrace();
                            return new Message("error", e.getMessage());
                        }
                    });

            tcpServer.addHandler(PublisherServiceInterface.DELETE_CLIENT,
                    (request) -> {
                        String name = request.getBody();
                        Future<String> future = theService.deleteClient(name);
                        try
                        {
                            String result = future.get();
                            return new Message("ok", result);
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            e.printStackTrace();
                            return new Message("error", e.getMessage());
                        }
                    });

            tcpServer.addHandler(PublisherServiceInterface.SHOW_CLIENTS,
                    (request) -> {
                        String name = request.getBody();
                        Future<String> future = theService.showClients(name);
                        try
                        {
                            String result = future.get();
                            return new Message("ok", result);
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            e.printStackTrace();
                            return new Message("error", e.getMessage());
                        }
                    });

            tcpServer.addHandler(PublisherServiceInterface.UP_CLIENT,
                    (request) -> {
                        String name = request.getBody();
                        Future<String> future = theService.updateClient(name);
                        try
                        {
                            String result = future.get();
                            return new Message("ok", result);
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            e.printStackTrace();
                            return new Message("error", e.getMessage());
                        }
                    });

            tcpServer.addHandler(PublisherServiceInterface.UP_BOOK,
                    (request) -> {
                        String name = request.getBody();
                        Future<String> future = theService.updateBook(name);
                        try
                        {
                            String result = future.get();
                            return new Message("ok", result);
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            e.printStackTrace();
                            return new Message("error", e.getMessage());
                        }
                    });

            tcpServer.addHandler(PublisherServiceInterface.CHECK_BOOK,
                    (request) -> {
                        String name = request.getBody();
                        Future<String> future = theService.checkBook(name);
                        try
                        {
                            String result = future.get();
                            return new Message("ok", result);
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            e.printStackTrace();
                            return new Message("error", e.getMessage());
                        }
                    });

            tcpServer.addHandler(PublisherServiceInterface.CHECK_CLIENT,
                    (request) -> {
                        String name = request.getBody();
                        Future<String> future = theService.checkClient(name);
                        try
                        {
                            String result = future.get();
                            return new Message("ok", result);
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            e.printStackTrace();
                            return new Message("error", e.getMessage());
                        }
                    });

            tcpServer.addHandler(PublisherServiceInterface.DO_TRANSACT,
                    (request) -> {
                        String name = request.getBody();
                        Future<String> future = theService.makeTransaction(name);
                        try
                        {
                            String result = future.get();
                            return new Message("ok", result);
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            e.printStackTrace();
                            return new Message("error", e.getMessage());
                        }
                    });

            tcpServer.addHandler(PublisherServiceInterface.SHOW_TRANSACT,
                    (request) -> {
                        String name = request.getBody();
                        Future<String> future = theService.showTransactions(name);
                        try
                        {
                            String result = future.get();
                            return new Message("ok", result);
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            e.printStackTrace();
                            return new Message("error", e.getMessage());
                        }
                    });

            tcpServer.addHandler(PublisherServiceInterface.CLIENTS_SPENDING,
                    (request) -> {
                        String name = request.getBody();
                        Future<String> future = theService.clientSpending(name);
                        try
                        {
                            String result = future.get();
                            return new Message("ok", result);
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            e.printStackTrace();
                            return new Message("error", e.getMessage());
                        }
                    });

            tcpServer.addHandler(PublisherServiceInterface.TRANSACT_BEFORE_DATE,
                    (request) -> {
                        String name = request.getBody();
                        Future<String> future = theService.transactBefore(name);
                        try
                        {
                            String result = future.get();
                            return new Message("ok", result);
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            e.printStackTrace();
                            return new Message("error", e.getMessage());
                        }
                    });

            tcpServer.startServer();
            executorService.shutdown();
        }
        catch (RuntimeException ex)
        {
            ex.printStackTrace();
        }

    }
}
