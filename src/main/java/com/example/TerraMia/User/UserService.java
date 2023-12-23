package com.example.TerraMia.User;

import com.example.TerraMia.exceptions.NotFoundException;
import com.example.TerraMia.payloads.entities.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository utenteRepository;


    // @Autowired
    //private EmailSender emailSender;

    /*public Dipendente save(DipendenteDTO body) throws IOException {
        System.out.println(body.nome());
        dipendenteRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestExceptions("L'email " + body.email() + " è già in uso!");
        });
        Dipendente dipendente = new Dipendente(body.username(), body.nome(), body.cognome(), body.email(), body.password());
        return dipendenteRepository.save(dipendente);
    }*/

    public Page<User> getUtenti(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return utenteRepository.findAll(pageable);
    }

    public User findById(long id) throws NotFoundException {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByIdAndUpdate(long id, UserRegistrationDTO body) throws NotFoundException {
        User found = utenteRepository.findById(id).get();
        found.setCognome(body.cognome());
        found.setNome(body.nome());
        found.setEmail(body.email());
        //found.setPassword(bcrypt.encode(body.getPassword()));
        return utenteRepository.save(found);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        User found = this.findById(id);
        utenteRepository.delete(found);
    }


    public User getRandomUtente() throws NotFoundException {
        return utenteRepository.findRandomUtente();
    }

    public User findByEmail(String email) throws NotFoundException {
        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email "+ email + " non trovato"));
    }

}
