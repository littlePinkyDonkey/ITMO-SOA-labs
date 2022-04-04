package andrei.teplykh.mappers;

import andrei.teplykh.entity.Person;
import andrei.teplykh.entity.enums.Color;
import andrei.teplykh.dto.PersonDto;

import javax.ejb.Stateless;

public class PersonMapper {

    public Person dtoToEntity(final PersonDto dto) {
        return Person.builder()
                .personId(dto.getPersonId())
                .name(dto.getName())
                .passportID(dto.getPassportId())
                .eyeColor(Color.of(dto.getEyeColor()))
                .hairColor(Color.of(dto.getHairColor()))
                .build();
    }

    public PersonDto entityToDto(final Person entity) {
        final PersonDto dto =  PersonDto.builder()
                .personId(entity.getPersonId())
                .name(entity.getName())
                .passportId(entity.getPassportID())
                .build();

        if (entity.getEyeColor() != null) {
            dto.setEyeColor(entity.getEyeColor().getDescription());
        }
        if (entity.getHairColor() != null) {
            dto.setHairColor(entity.getHairColor().getDescription());
        }

        return dto;
    }
}
