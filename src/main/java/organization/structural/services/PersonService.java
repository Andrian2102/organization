package organization.structural.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import organization.structural.entity.Person;
import organization.structural.pojo.MessageModel;
import organization.structural.pojo.PersonOutput;
import organization.structural.repository.PersonRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public ResponseEntity getDataPerson(UUID id){
        MessageModel msg = new MessageModel();
        try {
            Person person = personRepository.getById(id);
            PersonOutput output = new PersonOutput();
            output.setId(person.getId());
            output.setNama(person.getNama());
            output.setBawahan(personRepository.findBawahan(id));


            msg.setStatus(true);
            msg.setMessage("Berhasil get data");
            msg.setData(output);
            return ResponseEntity.ok().body(msg);
        } catch (Exception e){
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(msg);
        }
    }

    public ResponseEntity deleteDataPerson(UUID id){
        MessageModel msg = new MessageModel();
        try {
            //check apakah data person dari id tersebut ada atau tidak
            if (!personRepository.findById(id).isPresent()){
                msg.setStatus(false);
                msg.setMessage("Data Tidak Ada");
                return ResponseEntity.ok().body(msg);
            } else {
                // jika ada lakukan pengecekan bawahan
                Person person = personRepository.getById(id);
                List<Person> bawahan = personRepository.findBawahan(id);
                if(bawahan.isEmpty()){
                    // jika tidak ada bawahan delete secara langsung
                    personRepository.deleteById(id);
                } else {
                    // update kolom atasan semua person bawahan dengan atasan dari id yang akan di delete
                    for (Person personBawahan : bawahan){
                        Person newPerson = new Person();
                        newPerson.setId(personBawahan.getId());
                        newPerson.setNama(personBawahan.getNama());
                        newPerson.setAtasan(person.getAtasan());
                        personRepository.save(newPerson);
                    }
                    personRepository.deleteById(id);
                }
            }
            msg.setStatus(true);
            msg.setMessage("Berhasil delete id " + id);
            return ResponseEntity.ok().body(msg);
        } catch (Exception e){
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(msg);
        }
    }
}
