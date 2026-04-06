package client;

import service.LoadBalancer;

public class Client<K, V> {

    private final LoadBalancer<K, V> loadBalancer;
    private final String clientId;

    public Client(String clientId, LoadBalancer<K, V> loadBalancer) {
        this.clientId = clientId;
        this.loadBalancer = loadBalancer;
    }

    public V sendGetRequest(K key) {
        System.out.println("\n[" + clientId + "] --> GET key='" + key + "'");
        V result = loadBalancer.forwardGet(key);
        System.out.println("[" + clientId + "] <-- result='" + result + "'");
        return result;
    }

    public void sendPutRequest(K key, V value) {
        System.out.println("\n[" + clientId + "] --> PUT key='" + key + "' value='" + value + "'");
        loadBalancer.forwardPut(key, value);
        System.out.println("[" + clientId + "] <-- PUT acknowledged");
    }
}
