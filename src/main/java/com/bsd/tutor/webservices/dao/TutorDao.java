package com.bsd.tutor.webservices.dao;

import java.util.List;

import com.bsd.tutor.webservices.model.Tutor;

public interface TutorDao {

   long save(Tutor tutor);

   Tutor get(long id);

   List<Tutor> list();

   void update(long id, Tutor tutor);

   void delete(long id);

   Tutor findByUserPassword(String turEmail,String turPass);


}
