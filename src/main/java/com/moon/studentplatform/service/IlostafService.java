package com.moon.studentplatform.service;

import com.moon.studentplatform.dto.Found;

import java.util.List;

public interface IlostafService {
       List<Found> toshow(int offset, int limit);
       int delete(int id);
       int count();
       boolean add(Found found);
       List<Found> select(String code);
}
