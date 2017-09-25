package com.incamp.mhs.request;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Transactional
    public void save(Request request) {
        requestRepository.persist(request);
    }

    @Transactional
    public void update(Long id, Request request) {
//        Request byId = getById(id);
//        request.setId(id);
//        BeanUtils.copyProperties(request, byId);
//        save(byId);
        requestRepository.update(id, request);
    }

    @Transactional(readOnly = true)
    public Request getById(Long id) {
        return requestRepository.findOneByPk(id).orElseThrow(() -> new EntityNotFoundException("Request not found"));
    }
}