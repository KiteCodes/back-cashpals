package com.jea.cashpals.service;

import com.jea.cashpals.dto.PartyDTO;
import com.jea.cashpals.dto.SimpleUserDTO;
import com.jea.cashpals.dto.UserDTO;
import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.entitiy.Party;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.mapper.PartyMapper;
import com.jea.cashpals.mapper.UserMapper;
import com.jea.cashpals.repository.PartyRepository;
import com.jea.cashpals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PartyService {
    @Autowired
    PartyRepository partyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PartyMapper partyMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    EventService eventService;

    public PartyDTO createParty( PartyDTO partyDTO){

        List<User> users = userService.getUsersById(partyDTO.getUsersIds());
        User owner = userService.getUserById(partyDTO.getOwnerId());
        List<Event> events = eventService.getEventsById((partyDTO.getEventsIds()));
        Party party = partyMapper.fromPartyDTO(partyDTO,owner,users,events);
        party.getUserList().add(owner);
        partyRepository.save(party);
        return partyMapper.fromParty(party,partyDTO.getOwnerId(),partyDTO.getUsersIds(),partyDTO.getEventsIds());
    }

    public PartyDTO updateParty(Integer id, PartyDTO partyRequest) {
        Party party = partyRepository.findPartyById(id);

        party.setOwner(userRepository.findUserById(partyRequest.getOwnerId()));
        party.setName(partyRequest.getName());
        party.setDescription(partyRequest.getDescription());
        party.setEventList(eventService.getEventsById(partyRequest.getEventsIds()));
        party.setUserList(userService.getUsersById(partyRequest.getUsersIds()));

        partyRepository.save(party);

        return partyMapper.fromParty(party,partyRequest.getOwnerId(),partyRequest.getUsersIds(),partyRequest.getEventsIds());
    }
    public void deleteParty(Integer id) {
        partyRepository.delete(partyRepository.findPartyById(id));
    }

    public List<PartyDTO> getAllParties() {
        List<Party> parties = partyRepository.findAll();
        List<PartyDTO> partyDTOList = new ArrayList<>();
        for (Party party : parties) {
            partyDTOList.add(partyMapper.fromParty(party, party.getOwner().getId(), party.getUserList().stream().map(User::getId).toList(), party.getEventList().stream().map(Event::getId).toList()));

        }
        return partyDTOList;
    }
    public PartyDTO getPartyById(Integer id) {
        Party party = partyRepository.findPartyById(id);
        return partyMapper.fromParty(party, party.getOwner().getId(), party.getUserList().stream().map(User::getId).toList(), party.getEventList().stream().map(Event::getId).toList());
    }

    public List<SimpleUserDTO> addPartyMembers(List<Integer> usersIds, Integer partyId) {
        Party party = partyRepository.findPartyById(partyId);
        if (party == null) {
            throw new IllegalArgumentException("Party not found for ID: " + partyId);
        }
        List<User> users = usersIds.stream()
                .map(userRepository::findUserById)
                .filter(Objects::nonNull)
                .toList();

        if (users.isEmpty()) {
            throw new IllegalArgumentException("No valid users found for the given IDs");
        }

        party.getUserList().addAll(users);

        partyRepository.save(party);

        List<SimpleUserDTO> simpleUserDTOS = new ArrayList<>();
        for (User user : users) {
            SimpleUserDTO simpleUserDTO = new SimpleUserDTO();
            simpleUserDTO.setId(user.getId());
            simpleUserDTO.setUsername(user.getUsername());
            simpleUserDTO.setFirstName(user.getFirstName());
            simpleUserDTO.setLastName(user.getLastName());
            simpleUserDTO.setEmail(user.getEmail());
            simpleUserDTOS.add(simpleUserDTO);
        }
        return simpleUserDTOS;

    }
    public List<PartyDTO> getPartiesByUserId(Integer userId){
        User user = userRepository.findUserById(userId);
        List <Party> parties = new ArrayList<>();
        parties.addAll(user.getPartyList());
        List<PartyDTO> partyDTOList = new ArrayList<>();
        for (Party party : parties) {
            partyDTOList.add(partyMapper.fromParty(party, party.getOwner().getId(), party.getUserList().stream().map(User::getId).toList(), party.getEventList().stream().map(Event::getId).toList()));
        }
        return partyDTOList;
    }
}
