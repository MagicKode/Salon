package com.example.salon.service.impl;

import com.example.salon.entity.Client;
import com.example.salon.repository.ClientRepository;
import com.example.salon.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        client = Client.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .phone(client.getPhone())
                .build();
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = clientRepository.findAll();
        if (clients.size() > 0) {
            return clients;
        } else {
            return null;
        }
    }
}
