package mapeprs;

import dao.Person;
import dao.enums.Color;
import dto.PersonDto;

public class PersonMapper {

    public Person dtoToEntity(PersonDto dto) {
        Person person = new Person();

        person.setPersonId(dto.getPersonId());
        person.setName(dto.getName());
        person.setPassportID(dto.getPassportId());
        person.setEyeColor(Color.of(dto.getEyeColor()));
        person.setHairColor(Color.of(dto.getHairColor()));

        if (dto.getHairColor() != null && !dto.getHairColor().isEmpty() && dto.getEyeColor() != null && !dto.getEyeColor().isEmpty()) {
            person.setStringEyeColor(Color.of(dto.getEyeColor()).getDescription());
            person.setStringHairColor(Color.of(dto.getHairColor()).getDescription());
        }

        return person;
    }

    public PersonDto entityToDto(Person entity) {
        PersonDto personDto = new PersonDto();

        personDto.setPersonId(entity.getPersonId());
        personDto.setName(entity.getName());
        personDto.setPassportId(entity.getPassportID());
        personDto.setEyeColor(entity.getStringEyeColor());
        personDto.setHairColor(entity.getStringHairColor());

        return personDto;
    }
}
