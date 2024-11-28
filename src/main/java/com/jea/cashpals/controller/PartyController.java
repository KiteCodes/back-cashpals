package com.jea.cashpals.controller;

import com.jea.cashpals.dto.EventDTO;
import com.jea.cashpals.dto.GroupDTO;
import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.entitiy.MierderGroup;
import com.jea.cashpals.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping
    public List<MierderGroup> getGroups(){
        return groupRepository.findAll();
    }
    @GetMapping(path = "/{id}")
    public MierderGroup getGroupById(Integer id){
        return null;// groupRepository.findGroupById(id);
    }
    @PostMapping
    private ResponseEntity<String> createGroup(@RequestBody GroupDTO group){
        MierderGroup newGroup= new MierderGroup();
        newGroup.setName(group.getName());
        newGroup.setDescription(group.getDescription());
       //TODO:terminar esto cuando este linkeado a user
        // newGroup.set(group.getOwnerId());
        return ResponseEntity.ok("Group created");
    }
    //TODO: CREATE GROUP
    //TODO: FIND GROUPS BY USER (ID)

}
