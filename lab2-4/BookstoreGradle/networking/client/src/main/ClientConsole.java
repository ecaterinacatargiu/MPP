package main;

import java.util.concurrent.*;


public class ClientConsole
{
    private PublisherServiceInterface thisService;
    private ExecutorService executorService;

    public ClientConsole(PublisherServiceInterface thisService)
    {
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.thisService = thisService;
    }

    public void daemonExecute(Future<String> future)
    {
        CompletableFuture.runAsync(
                () -> {
                    try {
                        String s;
                        s = future.get();
                        System.out.println(s);
                    } catch (InterruptedException | ExecutionException ex) {
                        ex.printStackTrace();
                    }
                }, this.executorService);
    }

    public void runConsoleDummy()
    {

        String bodyBook = "1234567890, Breje, 2010";
        Future<String> addFuture = this.thisService.addBook(bodyBook); //non-blocking
        try
        {
            String result = addFuture.get(); //blocking :(
            //todo: client side operations should be non-blocking
            System.out.println(result);
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }

        String bodyBook1 = "1234567891, Breje, 2010";
        Future<String> addFuture1 = this.thisService.addBook(bodyBook1); //non-blocking
        try
        {
            String result = addFuture1.get(); //blocking :(
            //todo: client side operations should be non-blocking
            System.out.println(result);
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }

        String bodyBook2 = "1234567892, Breje, 2010";
        Future<String> addFuture2 = this.thisService.addBook(bodyBook2); //non-blocking
        try
        {
            String result = addFuture2.get(); //blocking :(
            //todo: client side operations should be non-blocking
            System.out.println(result);
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }

        String bod = null;
        Future<String> addFuture3 = this.thisService.showBooks(bod); //non-blocking
        try
        {
            String result = addFuture3.get(); //blocking :(
            //todo: client side operations should be non-blocking
            System.out.println(result);
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
    }
}