package com.incamp.mhs.request;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("{id}")
    @JsonView(Request.MinimalView.class)
    public Request getById(@PathVariable long id) {
        return requestService.getById(id);
    }

    @PostMapping
    public void save(@RequestBody RequestForm requestForm) {
        requestService.save(requestForm.toRequest());
    }
}
