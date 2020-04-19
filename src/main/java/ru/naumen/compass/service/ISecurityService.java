package ru.naumen.compass.service;

public interface ISecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}