package com.bsd.tutor.webservices.service;

import java.util.List;

import com.bsd.tutor.webservices.model.Tutor;

public interface TutorService {

   long save(Tutor tutor);
   Tutor get(long id);
   List<Tutor> list();
   void update(long id, Tutor tutor);
   void delete(long id);
   Long login(String email, String password);
}
