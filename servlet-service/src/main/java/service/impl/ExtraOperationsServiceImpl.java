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
import util.GsonProvider;

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
        Gson gson = GsonProvider.gson;

        return gson.toJson(dragonService.getWithMinId());
    }
}
