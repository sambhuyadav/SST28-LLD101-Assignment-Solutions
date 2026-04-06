import cache.CacheNode;
import cache.CacheRouter;
import client.Client;
import db.Database;
import db.Repository;
import distribution.ModuloStrategy;
import eviction.LRUEvictionPolicy;
import service.BackendService;
import service.LoadBalancer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Repository<String, String> database = new Database<>();

        ((Database<String, String>) database).put("user:1", "Alice");
        ((Database<String, String>) database).put("user:2", "Bob");
        ((Database<String, String>) database).put("user:3", "Charlie");

        System.out.println("\n======================================================");
        System.out.println("  Distributed Cache — Demo");
        System.out.println("======================================================");

        int numberOfNodes = 3;
        int nodeCapacity  = 2;

        List<CacheNode<String, String>> cacheNodes = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            cacheNodes.add(new CacheNode<>("Node-" + i, nodeCapacity, new LRUEvictionPolicy<>()));
        }

        CacheRouter<String, String> cacheRouter =
                new CacheRouter<>(cacheNodes, new ModuloStrategy<>());

        BackendService<String, String> service =
                new BackendService<>(cacheRouter, database);

        List<BackendService<String, String>> services = new ArrayList<>();
        services.add(service);

        LoadBalancer<String, String> loadBalancer = new LoadBalancer<>(services);

        Client<String, String> client = new Client<>("Client-1", loadBalancer);

        separator("Scenario 1: Cache miss — value fetched from DB then cached");
        client.sendGetRequest("user:1");

        separator("Scenario 2: Cache hit — same key read again");
        client.sendGetRequest("user:1");

        separator("Scenario 3: PUT a new entry (write-through)");
        client.sendPutRequest("user:4", "Diana");

        separator("Scenario 4: GET recently PUT entry — should be a cache hit");
        client.sendGetRequest("user:4");

        separator("Scenario 5: LRU eviction — fill a node past its capacity");
        System.out.println(">> Pre-loading nodes...");
        client.sendPutRequest("alpha", "Value-A");
        client.sendPutRequest("beta",  "Value-B");
        client.sendPutRequest("gamma", "Value-C");

        separator("Scenario 6: GET keys that may have been evicted");
        client.sendGetRequest("alpha");
        client.sendGetRequest("beta");
        client.sendGetRequest("gamma");

        separator("Scenario 7: GET a key absent from both cache and DB");
        client.sendGetRequest("user:999");

        System.out.println("\n======================================================");
        System.out.println("  Demo complete.");
        System.out.println("======================================================\n");
    }

    private static void separator(String title) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("  " + title);
        System.out.println("--------------------------------------------------");
    }
}
