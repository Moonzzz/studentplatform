package com.moon.studentplatform.service;

import com.moon.studentplatform.dto.Found;
import com.moon.studentplatform.mapper.LostafMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class lostafServiceImp  implements  IlostafService{
 @Autowired
   private LostafMapper lostafMapper;
    @Override
    public List<Found> toshow(int offect,int limit) {
        return  lostafMapper.querry(offect,limit);
    }

    @Override
    public List<Found> show() {
        return lostafMapper.toquery();
    }

    @Override
    public int delete(int id) {
        return lostafMapper.showdelete(id);
    }

    @Override
    public int count() {
        return lostafMapper.showcount();
    }

    @Override
    public boolean add(Found found) {
        return lostafMapper.toadd(found);
    }

    @Override
    public List<Found> select(String code) {
        return lostafMapper.toselect(code);
    }
}
