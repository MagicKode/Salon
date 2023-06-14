package com.example.salon.service;

import com.example.salon.entity.Client;

import java.util.List;

public interface ClientService {
    Client createClient(Client client);
    List<Client> getAll();
}
