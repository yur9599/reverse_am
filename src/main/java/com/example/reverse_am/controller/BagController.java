package com.example.reverse_am.controller;

import com.example.reverse_am.dto.BagDTO;
import com.example.reverse_am.service.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/bag")
public class BagController {

    @Autowired
    private BagService bagService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getBagById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.bagService.getBagById(id));
    }

    @PostMapping
    public ResponseEntity<?> createBag(@Valid @RequestBody BagDTO bagDTO){
        Assert.notNull(bagDTO,"Bag is null ");
        Assert.notNull(bagDTO.getUser(),"User is null ");
        return ResponseEntity.ok().body(this.bagService.createBag(bagDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBag(@PathVariable("id")Long id , @Valid @RequestBody BagDTO bagDTO){
        Assert.notNull(bagDTO,"Bag is null ");
        Assert.notNull(bagDTO.getUser(),"User is null ");
        return ResponseEntity.ok().body(this.bagService.updateBag(id,bagDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBag(@PathVariable("id")Long id){
        this.bagService.deleteBag(id);
        return ResponseEntity.ok().body("Bag is deleted ");
    }

}
