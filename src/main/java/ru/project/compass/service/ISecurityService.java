package ru.project.compass.service;

import ru.project.compass.entity.User;

public interface ISecurityService {
    User getLogged();
    void autoLogin(String username, String password);
}