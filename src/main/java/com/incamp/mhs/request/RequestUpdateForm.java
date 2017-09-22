package com.incamp.mhs.request;

import lombok.Data;

@Data
public class RequestUpdateForm implements RequestForm {

    private Long id;

    private String captainName;

    private String phone;

    private Integer teamSize;

    private RequestStatus requestStatus;

    @Override
    public Request toRequest() {
        Request request = new Request();

        request.setId(id);
        request.setRequestStatus(requestStatus);
        request.setCaptainName(captainName);
        request.setPhone(phone);
        request.setTeamSize(teamSize);

        return request;
    }
}


