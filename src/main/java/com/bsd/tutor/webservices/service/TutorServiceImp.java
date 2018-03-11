package com.bsd.tutor.webservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsd.tutor.webservices.dao.TutorDao;
import com.bsd.tutor.webservices.model.Tutor;

@Service
@Transactional(readOnly = true)
public class TutorServiceImp implements TutorService {

   @Autowired
   private TutorDao tutorDao;

   @Transactional
   @Override
   public long save(Tutor tutor) {
      return tutorDao.save(tutor);
   }

   @Override
   public Tutor get(long id) {
      return tutorDao.get(id);
   }

   @Override
   public List<Tutor> list() {
      return tutorDao.list();
   }

   @Transactional
   @Override
   public void update(long id, Tutor tutor) {
      tutorDao.update(id, tutor);
   }

   @Transactional
   @Override
   public void delete(long id) {
      tutorDao.delete(id);
   }

   @Override
   public Long login(String email, String password) {
      Tutor tutor = tutorDao.findByUserPassword(email, password);
      return tutor.getTurId();
   }
}
