package com.jea.cashpals.service;

import com.jea.cashpals.dto.PartyDTO;
import com.jea.cashpals.repository.PartyRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Data
@Service
public class PartyService {

    @Autowired
    PartyRepository partyRepository;

    public void saveGroup(PartyDTO partyDTO) {

    }
}
