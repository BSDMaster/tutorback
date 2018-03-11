package com.bsd.tutor.webservices.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bsd.tutor.webservices.service.TutorService;
import com.bsd.tutor.webservices.service.TutorService;
import com.bsd.tutor.webservices.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bsd.tutor.webservices.model.Tutor;

@RestController
@RequestMapping("/tutor")
public class TutorController {
    private final Logger logger = LoggerFactory.getLogger(TutorController.class);

   //private static final Logger logger = LoggerFactory.getLogger(TutorController.class);
   @Autowired
   private TutorService tutorService;
/*
   @PostMapping("/tutor")
   public ResponseEntity<?> save(@RequestBody Tutor tutor) {
      long id = tutorService.save(tutor);
      return ResponseEntity.ok().body("New Tutor has been saved with ID:" + id);
   }

   @GetMapping("/tutor/{id}")
   public ResponseEntity<Tutor> get(@PathVariable("id") long id) {
      Tutor tutor = tutorService.get(id);
      return ResponseEntity.ok().body(tutor);
   }

   @GetMapping("/tutor")
   public ResponseEntity<List<Tutor>> list() {
      List<Tutor> tutors = tutorService.list();
      return ResponseEntity.ok().body(tutors);
   }

   @PutMapping("/tutor/{id}")
   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Tutor tutor) {
      tutorService.update(id, tutor);
      return ResponseEntity.ok().body("Tutor has been updated successfully.");
   }
   @DeleteMapping("/tutor/{id}")
   public ResponseEntity<?> delete(@PathVariable("id") long id) {
      tutorService.delete(id);
      return ResponseEntity.ok().body("Tutor has been deleted successfully.");
   }
*/
   @RequestMapping(value="/login", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody
   public ResponseEntity<Response<Map<String, Object>>>  login(@RequestBody Map<String, Object> mapRequest) {
      try {
         String email = (String) mapRequest.get("email");
         String password = (String) mapRequest.get("password");
         logger.info("Email : " + email + " Password : "+password);
         Long tutorId = tutorService.login(email, password);
         Map<String, Object> dataMap = new HashMap<String, Object>();
         dataMap.put("id", tutorId);
         if (tutorId != null) {
            // return ResponseEntity.ok().body("Return ID");
            return ResponseEntity.ok(new Response<Map<String, Object>>(HttpStatus.OK.value(),"Get successfully", dataMap));
         }
      }		catch (Exception ex) {
         ex.printStackTrace();
         logger.info(ex.getMessage());
      }
       return ResponseEntity.ok(new Response<Map<String, Object>>(HttpStatus.OK.value(),"Not found", null));


   }
}