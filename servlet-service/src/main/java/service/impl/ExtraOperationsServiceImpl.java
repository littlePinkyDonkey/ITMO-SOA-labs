package service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.Coordinates;
import dao.Dragon;
import dao.Person;
import mapeprs.CoordinatesMapper;
import mapeprs.DragonMapper;
import mapeprs.PersonMapper;
import service.CoordinatesService;
import service.DragonService;
import service.ExtraOperationsService;
import service.PersonService;

import java.util.List;

public class ExtraOperationsServiceImpl implements ExtraOperationsService {
    private final GsonBuilder gsonBuilder;

    private final DragonMapper dragonMapper;
    private final PersonMapper personMapper;
    private final CoordinatesMapper coordinatesMapper;

    private final DragonService dragonService;
    private final PersonService personService;
    private final CoordinatesService coordinatesService;

    private static volatile ExtraOperationsServiceImpl instance;
    private ExtraOperationsServiceImpl() {
        this.gsonBuilder = new GsonBuilder();
        this.dragonMapper = new DragonMapper();
        this.personMapper = new PersonMapper();
        this.coordinatesMapper = new CoordinatesMapper();
        this.dragonService = DragonServiceImpl.getInstance();
        this.coordinatesService = CoordinatesServiceImpl.getInstance();
        this.personService = PersonServiceImpl.getInstance();
    }

    public static ExtraOperationsService getInstance() {
        if (instance == null) {
            synchronized (ExtraOperationsServiceImpl.class) {
                if (instance == null) {
                    instance = new ExtraOperationsServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public String findMinId() {
        Gson gson = gsonBuilder.create();

        List<Coordinates> coordinates = coordinatesService.getAll();
        List<Dragon> dragons = dragonService.getAll();
        List<Person> persons = personService.getAll();

        Coordinates coordinate = coordinates.get(0);
        Dragon dragon = dragons.get(0);
        Person person = persons.get(0);

        int coordinatesId = Integer.MAX_VALUE;
        int dragonId = Integer.MAX_VALUE;
        int personId = Integer.MAX_VALUE;

        if (coordinate != null) {
            coordinatesId = coordinate.getCoordinatesId();
        }
        if (dragon != null) {
            dragonId = Math.toIntExact(dragon.getId());
        }
        if (person != null) {
            personId = person.getPersonId();
        }

        if (dragonId < coordinatesId) {
            return gson.toJson(dragonMapper.entityToDto(dragons.get(0)));
        } else if (personId < coordinatesId) {
            return gson.toJson(personMapper.entityToDto(persons.get(0)));
        } else {
            return gson.toJson(coordinatesMapper.entityToDto(coordinates.get(0)));
        }
    }
}
