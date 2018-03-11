package com.bsd.tutor.webservices.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.bsd.tutor.webservices.controller.TutorController;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bsd.tutor.webservices.model.Tutor;

@Repository
public class TutorDaoImp implements TutorDao {
   private final Logger logger = LoggerFactory.getLogger(TutorDaoImp.class);
   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public long save(Tutor tutor) {
      sessionFactory.getCurrentSession().save(tutor);
      return tutor.getTurId();
   }

   @Override
   public Tutor get(long id) {
      return sessionFactory.getCurrentSession().get(Tutor.class, id);
   }

   @Override
   public List<Tutor> list() {
      Session session = sessionFactory.getCurrentSession();
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Tutor> cq = cb.createQuery(Tutor.class);
      Root<Tutor> root = cq.from(Tutor.class);
      cq.select(root);
      Query<Tutor> query = session.createQuery(cq);
      return query.getResultList();
   }

   @Override
   public void update(long id, Tutor tutor) {

   }

   /*
      @Override
      public void update(long id, Tutor tutor) {
         Session session = sessionFactory.getCurrentSession();
         Tutor tutor2 = session.byId(Tutor.class).load(id);
         tutor2.setTitle(tutor.getTitle());
         tutor2.setAuthor(tutor.getAuthor());
         session.flush();
      }
   */
   @Override
   public void delete(long id) {
      Session session = sessionFactory.getCurrentSession();
      Tutor tutor = session.byId(Tutor.class).load(id);
      session.delete(tutor);
   }

   @Override
   public Tutor findByUserPassword(String turEmail,String turPass){
      Session session = sessionFactory.getCurrentSession();
      @SuppressWarnings("unchecked")
      String hql = "FROM Tutor WHERE turEmail=:turEmail AND turPass=:turPass";
      org.hibernate.Query query = session.createQuery(hql);
      query.setParameter("turEmail", turEmail);
      query.setParameter("turPass", turPass);
      // query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
      List<Tutor> tutors = query.list();
      if (!tutors.isEmpty()) {
         return tutors.get(0);
      }
      return null;
   }


}
