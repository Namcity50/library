package org.example;

import org.example.config.ConfigSpring;
import org.example.controller.AuthController;
import org.example.db.Database;
import org.example.db.InitDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        Database database = new Database();
        database.initTable();
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigSpring.class);
        InitDatabase initDataBase = (InitDatabase) context.getBean("initDatabase");
        initDataBase.intAdmin();

        AuthController authController = (AuthController) context.getBean("authController");
        authController.start();
    }
}