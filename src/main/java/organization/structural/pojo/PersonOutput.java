package organization.structural.pojo;

import lombok.Getter;
import lombok.Setter;
import organization.structural.entity.Person;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PersonOutput {
    private UUID id;
    private String nama;
    private List<Person> bawahan;
}
