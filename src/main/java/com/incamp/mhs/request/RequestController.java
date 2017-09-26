package com.incamp.mhs.request;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public void save(@RequestBody @Valid RequestCreateForm requestForm) {
        requestService.save(requestForm.toRequest());
    }

    @PutMapping("{id}")
    public void updateRequestStatus(@PathVariable long id, @RequestBody @Valid RequestUpdateForm requestForm) {
        requestService.update(id, requestForm.toRequest());
    }
}
