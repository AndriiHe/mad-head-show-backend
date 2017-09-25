package com.incamp.mhs.request;

import lombok.Data;

@Data
public class RequestUpdateForm {

    private String captainName;

    private String phone;

    private Integer teamSize;

    private RequestStatus requestStatus;

    public Request toRequest() {
        Request request = new Request();

        request.setRequestStatus(requestStatus);
        request.setCaptainName(captainName);
        request.setPhone(phone);
        request.setTeamSize(teamSize);

        return request;
    }
}


