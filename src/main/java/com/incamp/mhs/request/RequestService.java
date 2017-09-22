package com.incamp.mhs.request;

import com.incamp.mhs.team.Team;
import com.incamp.mhs.team.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final TeamService teamService;

    public RequestService(RequestRepository requestRepository, TeamService teamService) {
        this.requestRepository = requestRepository;
        this.teamService = teamService;
    }

    @Transactional
    public void save(RequestForm requestForm) {
        Request requestFromDto = requestForm.toRequest();
        Request request;
        if (Objects.nonNull(requestFromDto.getId())) {
            request = copyRequestFromDto(requestFromDto);
        } else {
            request = requestFromDto;
        }

        if (Objects.nonNull(request.getTeam().getId())) {
            List<Team> teamList = teamService.getByTeamName(request.getTeam().getName());
            for (Team team : teamList) {
                if (Objects.nonNull(team.getId())) {
                    request.setTeam(team);
                } else {
                    teamService.save(team);
                }
            }
        }

        requestRepository.persist(request);
    }

    private Request copyRequestFromDto(Request requestFromDto) {
        Request request;
        request = getById(requestFromDto.getId());

        if (Objects.nonNull(requestFromDto.getCaptainName())) {
            request.setCaptainName(requestFromDto.getCaptainName());
        }

        if (Objects.nonNull(requestFromDto.getPhone())) {
            request.setPhone(requestFromDto.getPhone());
        }

        if (Objects.nonNull(requestFromDto.getTeamSize())) {
            request.setTeamSize(requestFromDto.getTeamSize());
        }

        if (Objects.nonNull(requestFromDto.getTeam())) {
            request.setTeam(requestFromDto.getTeam());
        }

        if (Objects.nonNull(requestFromDto.getRequestStatus())) {
            request.setRequestStatus(requestFromDto.getRequestStatus());
        }
        return request;
    }

    @Transactional(readOnly = true)
    public Request getById(Long id) {
        return requestRepository.findOneByPk(id).orElseThrow(() -> new EntityNotFoundException("Request not found"));
    }
}