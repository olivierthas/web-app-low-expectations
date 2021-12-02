package com.brielage.uitleendienst.controllers;

import com.brielage.uitleendienst.authorization.JWTChecker;
import com.brielage.uitleendienst.authorization.Permission;
import com.brielage.uitleendienst.models.Magazijn;
import com.brielage.uitleendienst.repositories.MagazijnRepository;
import com.brielage.uitleendienst.responses.Responder;
import com.brielage.uitleendienst.tools.APILogger;
import com.brielage.uitleendienst.tools.RemoveDuplicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings ("rawtypes")
@RestController
@RequestMapping (value = "/magazijn")
public class MagazijnController {
    @SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private MagazijnRepository magazijnRepository;

    @GetMapping (value = { "/", "" })
    public ResponseEntity findByProperties (
            @RequestParam (required = false) List<String> naam,
            @RequestParam (required = false) List<String> email,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("magazijn.get*");

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        if (!JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        if ((naam == null || naam.isEmpty())
                && (email == null || email.isEmpty())) {
            APILogger.logRequest("magazijn.findAll");
            List<Magazijn> magazijnen = magazijnRepository.findAll();

            if (magazijnen.isEmpty()) return Responder.respondNotFound();

            return Responder.respondOk(magazijnen);
        }

        List<Magazijn> magazijnen = new ArrayList<>();

        if (naam != null && !naam.isEmpty()) {
            APILogger.logRequest("magazijn.findAllByNaamIsIn");
            magazijnen.addAll(magazijnRepository.findAllByNaamIsIn(naam));
        }

        if (email != null && !email.isEmpty()) {
            APILogger.logRequest("magazijn.findAllByEmailIsIn");
            magazijnen.addAll(magazijnRepository.findAllByEmailIsIn(email));
        }

        magazijnen = RemoveDuplicates.removeDuplicates(magazijnen);

        if (magazijnen.isEmpty()) return Responder.respondNotFound();

        return Responder.respondOk(magazijnen);
    }

    @GetMapping ("/{id}")
    public ResponseEntity findById (
            @PathVariable String id,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("magazijn.findById", id);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        if (!JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        Optional<Magazijn> m = magazijnRepository.findById(id);

        if (m.isEmpty()) return Responder.respondNotFound();

        return Responder.respondOk(m.get());
    }

    @PostMapping (value = { "/", "" })
    public ResponseEntity create (
            @RequestBody Magazijn magazijn,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("magazijn.create", magazijn.toString());

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        if (!JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        try {
            if (!validateMagazijn(magazijn))
                return Responder.respondBadRequest("not valid");

            // ignore ID when creating, will get automagically generated by DB
            magazijn.setId(null);
            Magazijn m = magazijnRepository.save(magazijn);

            return Responder.respondCreated(m);
        } catch (Exception e) {return Responder.respondBadRequest(e.getMessage());}
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity put (
            @PathVariable String id,
            @RequestBody Magazijn magazijn,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("magazijn.put", id);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        if (!JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        try {
            if (!validateMagazijnId(magazijn))
                return Responder.respondBadRequest("not valid");

            Optional<Magazijn> m = magazijnRepository.findById(id);

            if (m.isEmpty())
                return Responder.respondNotFound();

            magazijn.setId(m.get()
                            .getId());
            Magazijn result = magazijnRepository.save(magazijn);

            return Responder.respondCreated(result);
        } catch (Exception e) {return Responder.respondBadRequest(e.getMessage());}
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity delete (
            @PathVariable String id,
            @RequestHeader ("Authorization") String token,
            @RequestHeader ("Origin") String origin) {
        APILogger.logRequest("magazijn.delete", id);

        if (!JWTChecker.checkToken(token)) return Responder.respondUnauthorized();

        if (!JWTChecker.checkPermission(token, Permission.ADMIN))
            return Responder.respondForbidden();

        try {
            Optional<Magazijn> m = magazijnRepository.findById(id);

            if (m.isEmpty())
                return Responder.respondNotFound();

            magazijnRepository.delete(m.get());

            return Responder.respondNoContent("deleted");
        } catch (Exception e) {return Responder.respondBadRequest(e.getMessage());}
    }

    private boolean validateMagazijnId (Magazijn m) {
        if (m.getId()
             .isEmpty())
            return false;
        return validateMagazijn(m);
    }

    private boolean validateMagazijn (Magazijn m) {
        return validateNaam(m.getNaam())
                && validateAdres(m.getAdres())
                && validateTelefoon(m.getTelefoon())
                && validateEmail(m.getEmail());
    }

    private boolean validateNaam (String naam) {
        return naam != null && !naam.isEmpty();
    }

    private boolean validateAdres (String adres) {
        return adres != null && !adres.isEmpty();
    }

    private boolean validateTelefoon (String telefoon) {
        return telefoon != null && !telefoon.isEmpty();
    }

    private boolean validateEmail (String email) {
        if (email == null || email.isEmpty()) return false;
        return email.contains("@");
    }
}
